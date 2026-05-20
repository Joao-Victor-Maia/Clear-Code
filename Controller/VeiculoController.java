/**
 * Esse controller conversa com o usuário pelo terminal.
 * Ele só pega o que o usuário digita, chama o service e mostra o resultado.
 */
public class VeiculoController {
    private final VeiculoService veiculoService;
    private final ConsoleUtils console;

    public VeiculoController(VeiculoService veiculoService, ConsoleUtils console) {
        this.veiculoService = veiculoService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- VEÍCULOS ---");
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
        System.out.print("Placa: ");
        String placa = console.lerString();
        System.out.print("Modelo: ");
        String modelo = console.lerString();
        System.out.print("Marca: ");
        String marca = console.lerString();
        System.out.print("ID do Cliente dono: ");
        Long clienteId = console.lerLong();

        try {
            Veiculo v = new Veiculo(null, placa, modelo, marca, clienteId);
            veiculoService.salvar(v);
            System.out.println("✅ Veículo salvo! ID: " + v.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Veículos ---");
        veiculoService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Veiculo v = veiculoService.buscarPorId(id);
        System.out.println(v != null ? v : "⚠️  Veículo não encontrado.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        veiculoService.remover(id);
        System.out.println("🗑️  Veículo removido.");
    }
}
