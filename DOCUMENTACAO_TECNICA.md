# Documentação Técnica: Entity, Repository e Service

Este documento explica, com exemplos do código **real** deste projeto, **o que faz cada camada**, **por que as escolhas foram feitas** e **como elas se comunicam**.

---

## Camada 1: `Entity` (O Modelo de Dados)

### O que é?
As Entities são as classes que representam os "objetos do mundo real" da sua oficina mecânica. Por exemplo, um `Cliente` real tem nome, CPF e telefone. A classe `Cliente.java` é exatamente isso: uma representação desse objeto no código.

### Por que usamos `EntidadeBase`?

Sem a classe `EntidadeBase`, cada uma das 9 entidades (`Cliente`, `Veiculo`, `Mecanico`...) teria que ter o seguinte trecho de código **copiado e colado** dentro de si:

```java
// Isso seria repetido 9 vezes, em 9 arquivos diferentes! (Ruim!)
private Long id;

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
```

Com a `EntidadeBase`, escrevemos isso **uma única vez** e todas as outras classes simplesmente **herdam** (com o `extends`):

```java
// EntidadeBase.java — Escrito apenas UMA VEZ
public abstract class EntidadeBase {
    protected Long id;   // O 'protected' permite que classes filhas acessem diretamente
    ...
}

// Autenticacao.java — Herda tudo acima automaticamente
public class Autenticacao extends EntidadeBase {
    private String cargo;
    ...
}
```

**Princípio aplicado:** DRY (*Don't Repeat Yourself* — Não Se Repita). Menos código duplicado = menos chance de bug.

### Por que o `id` é `Long` e não `int`?

| Tipo     | Valor Máximo          | Motivo da Escolha         |
|----------|-----------------------|---------------------------|
| `int`    | 2.147.483.647         | Pode acabar em sistemas grandes |
| `Long`   | 9.223.372.036.854.775.807 | ✅ Nunca vai acabar       |

O `Long` também aceita `null`, o que é vital para o sistema saber que um objeto **ainda não foi salvo** no banco (ID `null` = novo registro).

### Por que o `toString()` na `Autenticacao`?

```java
@Override
public String toString() {
    return "Autenticacao [ID=" + id + ", Cargo=" + cargo + ", Permissoes=" + permissoes + "]";
}
```

O `toString()` é chamado automaticamente pelo Java quando você tenta imprimir um objeto. Sem ele, o `System.out.println(autenticacao)` imprimiria algo inútil como `Autenticacao@7852e922`. Com ele, você vê: `Autenticacao [ID=1, Cargo=Gerente, Permissoes=[clientes, veiculos]]`.

### Por que `private` nos atributos e `get`/`set` públicos? (Encapsulamento)

```java
public class Autenticacao extends EntidadeBase {
    private String cargo; // Ninguém de fora acessa diretamente
    
    public String getCargo() { return cargo; } // Leitura controlada
    public void setCargo(String cargo) { this.cargo = cargo; } // Escrita controlada
}
```

Se o atributo fosse `public`, qualquer parte do código poderia fazer `autenticacao.cargo = ""` sem nenhum controle. Com `private` + getter/setter, você pode colocar validações dentro do setter no futuro se precisar.

---

## Camada 2: `Repository` (O Banco de Dados em Memória)

### O que é?
O Repository é responsável **única e exclusivamente** por guardar e recuperar dados. Ele é o "armário" do sistema. Ele não sabe se um cliente é válido ou não, ele apenas guarda e entrega o que pedem.

### Por que `RepositoryGenerico<T>`?

Sem ele, teríamos 9 arquivos de Repository, cada um com a **mesma lógica de CRUD** copiada:

```java
// Sem o genérico — Isso seria repetido 9 vezes! (Muito ruim!)
public class ClienteRepository {
    private List<Cliente> dados = new ArrayList<>();
    public void salvar(Cliente c) { dados.add(c); }
    public List<Cliente> listar() { return dados; }
    // ...
}

public class VeiculoRepository {
    private List<Veiculo> dados = new ArrayList<>();
    public void salvar(Veiculo v) { dados.add(v); }
    public List<Veiculo> listar() { return dados; }
    // ...
}
```

Com o `RepositoryGenerico<T>`, a lógica é escrita **uma única vez** usando o "coringa" `T`. Quando fazemos `ClienteRepository extends RepositoryGenerico<Cliente>`, o Java substitui todos os `T` por `Cliente` automaticamente.

### Por que `LinkedHashMap` em vez de `List`?

Esta foi a escolha mais importante de **Alta Performance** do projeto.

| Estrutura         | Buscar por ID  | Inserir | Remover |
|-------------------|----------------|---------|---------|
| `List<T>`         | O(N) — Lento   | O(1)   | O(N) — Lento |
| `LinkedHashMap`   | **O(1) — Rápido** | **O(1)** | **O(1) — Rápido** |

**O(N)** significa que, se você tiver 1 milhão de clientes e procurar o de ID 999.999, a `List` teria que percorrer (verificar) todos os 999.999 registros anteriores um por um.

**O(1)** do `Map` significa que, independente de ter 10 ou 10 milhões de registros, a busca sempre leva o mesmo tempo instantâneo. Isso acontece porque o `Map` funciona como um índice de livro: você não lê o livro inteiro para achar uma palavra, você vai direto no índice.

O `LinkedHashMap` especificamente foi escolhido (em vez do `HashMap` simples) porque ele **preserva a ordem de inserção** — o primeiro cliente cadastrado sempre aparece primeiro na lista.

### Por que `proximoId` e a lógica `if (id == null)`?

```java
protected long proximoId = 1;

public T salvar(T entidade) {
    if (entidade.getId() == null) { // É um novo registro (nunca foi salvo)
        entidade.setId(proximoId);  // Gera o ID automaticamente
        proximoId++;                // Incrementa para o próximo
    }
    dados.put(entidade.getId(), entidade); // Salva ou atualiza no Map
    return entidade;
}
```

Essa lógica resolve **dois problemas** com um único método:
- Se o `id == null`: é um **novo cadastro** → gera ID e salva.
- Se o `id != null`: é uma **atualização** → o `dados.put()` sobrescreve o registro antigo com o mesmo ID.

---

## Camada 3: `Service` (As Regras de Negócio)

### O que é?
O Service é o "Cérebro" do sistema. Enquanto o Repository só guarda dados, o Service **pensa e decide** se aquela operação pode ou não acontecer. Ele contém as "Regras da Oficina".

### Por que usar `if` + `throw` em vez de outras abordagens?

```java
public Cliente salvar(Cliente cliente) {
    if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
        throw new RegraNegocioException("O nome do cliente é obrigatório!");
    }
    // Só chega aqui se passou na validação
    return repositorio.salvar(cliente);
}
```

O `throw` funciona como um "**freio de emergência**". No instante em que o Java executa essa linha, ele abandona completamente o método atual e volta para quem chamou (o `Main`), carregando a mensagem de erro. Isso garante que a linha `repositorio.salvar(cliente)` **jamais será executada** com dados inválidos.

Poderíamos usar apenas um `System.out.println("Erro!")` e um `return null`, mas isso é perigoso: o programa continuaria rodando e poderia tentar usar esse `null` em outro lugar, causando um crash inesperado.

### Por que `RegraNegocioException extends RuntimeException`?

Existem dois tipos de Exception no Java:
- **Checked Exception** (herda de `Exception`): O Java **obriga** que todo método que possa lançar esse erro declare `throws NomeDaException` na assinatura. Muito verboso.
- **Unchecked Exception** (herda de `RuntimeException`): ✅ O Java **não obriga** a declaração. O erro "sobe" automaticamente até o `Main` quando é lançado.

Escolhemos `RuntimeException` para manter o código mais limpo, sem poluir todos os métodos com `throws RegraNegocioException`.

### Por que o Service recebe o Repository pelo construtor?

```java
public class ClienteService {
    private final ClienteRepository repositorio; // final: não pode ser trocado depois!
    
    public ClienteService(ClienteRepository repositorio) { // Injeção de Dependência
        this.repositorio = repositorio;
    }
}
```

Esse padrão se chama **Injeção de Dependência**. Em vez do Service criar seu próprio repositório (`new ClienteRepository()` internamente), ele **exige** que alguém de fora entregue um repositório pronto.

O benefício prático: o `Main` cria **um único** `ClienteRepository` e entrega para **todos os Services** que precisam dele. Assim, todos os services compartilham o mesmo "banco de dados" em memória — se você salvar um cliente num Service, ele aparece quando outro Service listar.

---

## Como as 3 Camadas se Comunicam

A comunicação é sempre em **uma única direção**: de fora para dentro.

```
Main (Orquestrador / Ponto de Partida)
  │  Cria tudo e chama o Controller
  ▼
ClienteController (Interação com Usuário via ConsoleUtils)
  │  Chama clienteService.salvar(cliente)
  ▼
ClienteService (Regras de Negócio)
  │  Valida as regras, depois chama repositorio.salvar(cliente)
  ▼
ClienteRepository (Banco de Dados em Memória)
  │  Executa o put() no LinkedHashMap
  ▼
Cliente (Entity)
  └── Guarda os dados estáticos
```

**Regra de Ouro:**
- A `Entity` não conhece ninguém.
- O `Repository` conhece apenas a `Entity`.
- O `Service` conhece o `Repository` e a `Entity`.
- O `Controller` conhece apenas o `Service`.

Nenhuma camada "pula" a outra. O `Controller` nunca chama o `Repository` diretamente. A `Entity` nunca chama o `Service`. Essa disciplina é o que torna o sistema **fácil de manter**, **fácil de testar** e **fácil de evoluir**.

---

## Camada 4: `Controller` (A Interface de Comunicação)

### O que é?
A camada Controller tem como única função "conversar" com o mundo exterior. No nosso caso atual, o mundo exterior é o terminal do Windows/Linux.

### Por que separamos do `Main`?
Antes, o `Main` cuidava de exibir texto (`System.out.println`), ler texto (`Scanner`), ter regras lógicas e iniciar o programa. Isso violava o princípio da Responsabilidade Única (Single Responsibility Principle).

Agora:
- Os **Controllers** ficam responsáveis APENAS por exibir os menus e enviar os dados preenchidos para o `Service`.
- O **Main** fica responsável APENAS por iniciar o programa e "injetar as dependências" (criar as classes e ligá-las umas nas outras).

### A classe `ConsoleUtils`
Criamos essa classe para resolver os dois maiores problemas de ler dados via terminal em Java:
1. **Crash por dados inválidos:** Se o sistema pedir um ID numérico e o usuário digitar "abc", o Java lança um `NumberFormatException` e desliga na hora. O `ConsoleUtils` captura esse erro com um `try-catch` dentro de um `while(true)`, pedindo para o usuário digitar novamente até acertar.
2. **Pulo de linha do Scanner:** Misturar `scanner.nextInt()` e `scanner.nextLine()` causa bugs bizarros onde o Java "pula" perguntas. O `ConsoleUtils` resolve isso lendo TUDO como texto (`nextLine()`) e convertendo internamente de forma segura.
