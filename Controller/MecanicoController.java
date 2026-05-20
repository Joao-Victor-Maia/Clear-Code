/**
 * Esse controller conversa com o usuário pelo terminal.
 * Ele só pega o que o usuário digita, chama o service e mostra o resultado.
 */
public class MecanicoController {
    private final MecanicoService mecanicoService;
    private final ConsoleUtils console;

    public MecanicoController(MecanicoService mecanicoService, ConsoleUtils console) {
        this.mecanicoService = mecanicoService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MECÂNICOS ---");
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
        System.out.print("Especialidade: ");
        String especialidade = console.lerString();

        try {
            Mecanico m = new Mecanico(null, nome, especialidade);
            mecanicoService.salvar(m);
            System.out.println("✅ Mecânico salvo! ID: " + m.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Mecânicos ---");
        mecanicoService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Mecanico m = mecanicoService.buscarPorId(id);
        System.out.println(m != null ? m : "⚠️  Mecânico não encontrado.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        mecanicoService.remover(id);
        System.out.println("🗑️  Mecânico removido.");
    }
}
