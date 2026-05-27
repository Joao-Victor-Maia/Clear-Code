/**
 * Controller para Transações (pagamentos) — entrada pelo terminal.
 */
public class TransacaoController {
    private final TransacaoService transacaoService;
    private final ConsoleUtils console;

    public TransacaoController(TransacaoService transacaoService, ConsoleUtils console) {
        this.transacaoService = transacaoService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- TRANSAÇÕES ---");
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
        System.out.print("ID da OS: ");
        Long osId = console.lerLong();
        System.out.print("Valor (ex: 150.00): ");
        double valor = console.lerDouble();
        System.out.print("Forma de pagamento: ");
        String forma = console.lerString();

        try {
            Transacao t = new Transacao(null, osId, valor, forma);
            transacaoService.salvar(t);
            System.out.println("✅ Transação salva! ID: " + t.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Transações ---");
        transacaoService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Transacao t = transacaoService.buscarPorId(id);
        System.out.println(t != null ? t : "⚠️  Transação não encontrada.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        transacaoService.remover(id);
        System.out.println("🗑️  Transação removida.");
    }
}
