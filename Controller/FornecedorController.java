/**
 * Esse controller conversa com o usuário pelo terminal.
 * Ele só pega o que o usuário digita, chama o service e mostra o resultado.
 */
public class FornecedorController {
    private final FornecedorService fornecedorService;
    private final ConsoleUtils console;

    public FornecedorController(FornecedorService fornecedorService, ConsoleUtils console) {
        this.fornecedorService = fornecedorService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- FORNECEDORES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = console.lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> remover();
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = console.lerString();
        System.out.print("CNPJ: ");
        String cnpj = console.lerString();
        System.out.print("Telefone: ");
        String telefone = console.lerString();
        System.out.print("Email: ");
        String email = console.lerString();

        try {
            Fornecedor f = new Fornecedor(null, nome, cnpj, telefone, email);
            fornecedorService.salvar(f);
            System.out.println("✅ Fornecedor salvo! ID: " + f.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Fornecedores ---");
        fornecedorService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Fornecedor f = fornecedorService.buscarPorId(id);
        System.out.println(f != null ? f : "⚠️  Fornecedor não encontrado.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        fornecedorService.remover(id);
        System.out.println("🗑️  Fornecedor removido.");
    }
}
