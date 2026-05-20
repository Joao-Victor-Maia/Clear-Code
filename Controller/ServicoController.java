/**
 * A camada Controller gerencia a entrada e saída de dados no terminal.
 * Ela não contém regras lógicas da oficina, apenas "conversa" com o usuário,
 * formata as informações e entrega mastigadas para o Service processar.
 */
public class ServicoController {
    private final ServicoService servicoService;
    private final ConsoleUtils console;

    public ServicoController(ServicoService servicoService, ConsoleUtils console) {
        this.servicoService = servicoService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- SERVIÇOS ---");
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
        System.out.print("Descrição: ");
        String descricao = console.lerString();
        System.out.print("Valor (ex: 150.00): ");
        double valor = console.lerDouble();
        System.out.print("ID do Veículo: ");
        Long veiculoId = console.lerLong();

        try {
            Servico s = new Servico(null, descricao, valor, veiculoId);
            servicoService.salvar(s);
            System.out.println("✅ Serviço salvo! ID: " + s.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Serviços ---");
        servicoService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Servico s = servicoService.buscarPorId(id);
        System.out.println(s != null ? s : "⚠️  Serviço não encontrado.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        servicoService.remover(id);
        System.out.println("🗑️  Serviço removido.");
    }
}
