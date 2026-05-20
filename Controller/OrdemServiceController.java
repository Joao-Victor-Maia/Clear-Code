/**
 * Esse controller conversa com o usuário pelo terminal.
 * Ele só pega o que o usuário digita, chama o service e mostra o resultado.
 */
public class OrdemServiceController {
    private final OrdemServiceService ordemService;
    private final ConsoleUtils console;

    public OrdemServiceController(OrdemServiceService ordemService, ConsoleUtils console) {
        this.ordemService = ordemService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- ORDENS DE SERVIÇO ---");
            System.out.println("1 - Abrir nova OS");
            System.out.println("2 - Listar todas");
            System.out.println("3 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = console.lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> remover();
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    private void cadastrar() {
        System.out.print("ID do Veículo: ");
        Long veiculoId = console.lerLong();
        System.out.print("ID do Mecânico: ");
        Long mecanicoId = console.lerLong();
        System.out.print("Status inicial (ex: ABERTA): ");
        String status = console.lerString();

        try {
            OrdemService os = new OrdemService(null, veiculoId, mecanicoId, status);
            ordemService.salvar(os);
            System.out.println("✅ OS aberta! ID: " + os.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Ordens de Serviço ---");
        ordemService.listar().forEach(System.out::println);
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        ordemService.remover(id);
        System.out.println("🗑️  OS removida.");
    }
}
