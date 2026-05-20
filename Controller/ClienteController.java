// Essa é a camada que "conversa" com o usuário pelo terminal.
// Ela só faz duas coisas: mostrar textos na tela e ler o que o usuário digita.
// Nenhuma regra de negócio fica aqui! Se o CPF estiver errado, quem vai
// reclamar é o Service — o Controller só mostra o erro que veio de lá.
public class ClienteController {

    // Guardo o Service pra poder mandar ele salvar, listar, buscar e remover.
    private final ClienteService clienteService;

    // O ConsoleUtils é o nosso "escudo" contra digitações erradas.
    // Em vez de usar scanner.nextInt() direto (que crasharia), uso console.lerInt().
    private final ConsoleUtils console;

    // Construtor: recebe as dependências prontas do Main (Injeção de Dependência).
    // Assim o Controller não precisa saber como o Service ou o Console foram criados.
    public ClienteController(ClienteService clienteService, ConsoleUtils console) {
        this.clienteService = clienteService;
        this.console = console;
    }

    // Esse é o menu que aparece quando o usuário escolhe "1 - Clientes" no menu principal.
    // Ele fica num loop (while) até o usuário digitar 0 pra voltar.
    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = console.lerInt();

            // O switch com "->" é uma sintaxe mais moderna do Java (a partir do Java 14).
            // É a mesma coisa que o switch antigo com "case: break;", mas mais limpo.
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> remover();
                case 0 -> {} // Não faz nada, o while vai verificar e sair do loop
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        // Peço os dados ao usuário um por um
        System.out.print("Nome: ");
        String nome = console.lerString();
        System.out.print("CPF: ");
        String cpf = console.lerString();
        System.out.print("Telefone: ");
        String telefone = console.lerString();

        // Aqui é onde o try-catch brilha em conjunto com o Service.
        // Monta o objeto Cliente e manda pro Service salvar.
        // Se alguma regra for violada (ex: CPF duplicado), o Service vai lançar
        // uma RegraNegocioException que cai direto no catch abaixo.
        try {
            Cliente c = new Cliente(null, nome, cpf, telefone);
            clienteService.salvar(c);
            System.out.println("✅ Cliente salvo! ID: " + c.getId());
        } catch (RegraNegocioException e) {
            // A mensagem do erro vem prontinha do Service, só preciso exibir.
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Clientes ---");
        // O ".forEach(System.out::println)" é um atalho do Java 8:
        // pra cada cliente da lista, ele chama o System.out.println automaticamente.
        // Funciona porque a classe Cliente tem um toString() que formata a saída.
        clienteService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Cliente c = clienteService.buscarPorId(id);
        // Operador ternário: se "c" não for nulo, imprime o cliente. Se for, mostra aviso.
        // É a mesma coisa que um if/else, mas numa linha só.
        System.out.println(c != null ? c : "⚠️  Cliente não encontrado.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        clienteService.remover(id);
        System.out.println("🗑️  Cliente removido.");
    }
}
