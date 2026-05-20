/**
 * Esse controller conversa com o usuário pelo terminal.
 * Ele só pega o que o usuário digita, chama o service e mostra o resultado.
 */
public class PecaController {
    private final PecaService pecaService;
    private final ConsoleUtils console;

    public PecaController(PecaService pecaService, ConsoleUtils console) {
        this.pecaService = pecaService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- PEÇAS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todas");
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
        System.out.print("Nome da peça: ");
        String nome = console.lerString();
        System.out.print("Preço (ex: 49.90): ");
        double preco = console.lerDouble();

        try {
            Peca p = new Peca(null, nome, preco);
            pecaService.salvar(p);
            System.out.println("✅ Peça salva! ID: " + p.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Peças ---");
        pecaService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Peca p = pecaService.buscarPorId(id);
        System.out.println(p != null ? p : "⚠️  Peça não encontrada.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        pecaService.remover(id);
        System.out.println("🗑️  Peça removida.");
    }
}
