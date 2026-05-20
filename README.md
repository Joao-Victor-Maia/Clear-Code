# Arquitetura do Projeto: Oficina Mecânica

https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white

Este documento descreve a arquitetura em camadas utilizada no projeto, baseada nos princípios de Clean Architecture e N-Tier Architecture (Arquitetura em N-Camadas).

A divisão do código em diferentes pacotes (pastas) tem como objetivo principal a **separação de responsabilidades**. Cada camada tem um papel muito específico e regras estritas de comunicação.

---

## 1. O Fluxo de Comunicação (Regra de Ouro)

O fluxo da informação em uma arquitetura limpa sempre acontece de **Fora para Dentro**. A regra fundamental é: as camadas mais internas (como as Entities) não devem saber da existência das camadas mais externas (como o Terminal ou Banco de Dados).

**Fluxo Correto:**
`Main` ➔ `Controller` ➔ `Service` ➔ `Repository` ➔ `Entity`

* ❌ O Repositório **nunca** exibe mensagens no terminal (`System.out.println()`).
* ❌ A Entidade **nunca** chama o Repositório para buscar coisas.
* ❌ O Controller **não** deve ter regras complexas de negócio ("ifs" validando regras da oficina).

---

## 2. Descrição das Camadas

### 📦 Entity (Entidades ou Modelos)
* **O que é:** São as classes que representam os "objetos do mundo real" dentro do sistema (Ex: `Cliente`, `Veiculo`, `Peca`).
* **Responsabilidade:** Guardar apenas os atributos (dados) e métodos estritamente ligados ao estado do próprio objeto. Elas **não** contêm lógica de negócio complexa nem chamadas externas.
* **Com quem fala:** Com ninguém. É a camada mais baixa e isolada do sistema.

### 📦 Repository (Repositórios ou Camada de Acesso a Dados)
* **O que é:** É o seu "Banco de Dados" ou gerenciador de dados. (Ex: `ClienteRepository`).
* **Responsabilidade:** Seu único trabalho é fazer o CRUD (Create, Read, Update, Delete) — ou seja, inserir, buscar, atualizar e deletar dados em memória, em arquivos de texto, ou num Banco de Dados SQL/NoSQL real. O Repositório é "burro": ele faz o que mandam e não questiona regras.
* **Com quem fala:** Ele conhece as `Entities` (para poder manipulá-las) e só responde aos chamados do `Service`.

### 📦 Service (Serviços ou Camada de Regra de Negócio)
* **O que é:** É o "Coração" do software. (Ex: `ClienteService`).
* **Responsabilidade:** É aqui que moram as **Regras de Negócio**. Por exemplo: *"Um cliente só pode abrir uma Ordem de Serviço se o veículo já estiver cadastrado e sem pendências"*. O Service verifica as condições e toma decisões lógicas antes de permitir que as coisas aconteçam.
* **Com quem fala:** O Service recebe comandos do `Controller`/`Main`, executa cálculos/validações lógicas, e então pede para o `Repository` buscar ou salvar os resultados.

### 📦 Controller (Interface ou Porta de Entrada)
* **O que é:** É a camada de contato com o usuário (via Terminal/Console usando o `ConsoleUtils`).
* **Responsabilidade:** Exibir menus, `System.out.println` e coletar dados digitados. O Controller **não tem regras de negócio**, ele apenas formata os dados e envia adiante.
* **Com quem fala:** Repassa os dados preenchidos diretamente para a camada de `Service` trabalhar.

### 📦 Main (Orquestrador)
* **O que é:** O ponto de partida de tudo.
* **Responsabilidade:** Configurar o sistema. É aqui que fazemos a Injeção de Dependência (instanciamos os repositórios, passamos para os services, e passamos para os controllers).
* **Com quem fala:** Conhece todo mundo na hora de instanciar (`new`), mas depois apenas repassa a execução para o Menu Principal.
