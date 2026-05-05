# Arquitetura do Projeto: Oficina Mecânica

Este documento descreve a arquitetura em camadas utilizada no projeto, baseada nos princípios de Clean Architecture e N-Tier Architecture (Arquitetura em N-Camadas).

A divisão do código em diferentes pacotes (pastas) tem como objetivo principal a **separação de responsabilidades**. Cada camada tem um papel muito específico e regras estritas de comunicação.

---

## 1. O Fluxo de Comunicação (Regra de Ouro)

O fluxo da informação em uma arquitetura limpa sempre acontece de **Fora para Dentro**. A regra fundamental é: as camadas mais internas (como as Entities) não devem saber da existência das camadas mais externas (como o Terminal ou Banco de Dados).

**Fluxo Correto:**
`Main / Controller` ➔ `Service` ➔ `Repository` ➔ `Entity`

* ❌ O Repositório **nunca** exibe mensagens no terminal (`System.out.println()`).
* ❌ A Entidade **nunca** chama o Repositório para buscar coisas.
* ❌ O Main **não** deve ter regras complexas de negócio ("ifs" validando cálculo de taxas).

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

### 📦 Controller / Main (Interface ou Porta de Entrada)
* **O que é:** É a camada de contato com o usuário (no nosso caso atual, via Terminal/Console com o `Scanner`). Em sistemas modernos, pode ser uma API REST (HTTP) ou botões na tela do celular.
* **Responsabilidade:** Exibir informações e coletar dados do usuário. Ela converte o "texto" que a pessoa digita em algo que o sistema entenda e repassa o comando.
* **Com quem fala:** Ele repassa as intenções do usuário diretamente para a camada de `Service` (ex: "O usuário clicou em cadastrar, aqui estão os dados. Service, faça sua mágica!").
