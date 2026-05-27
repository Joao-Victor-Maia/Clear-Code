/**
 * Controller para Autenticacao (cargos e permissões) — entrada pelo terminal.
 */
public class AutenticacaoController {
    private final AutenticacaoService autenticacaoService;
    private final ConsoleUtils console;

    public AutenticacaoController(AutenticacaoService autenticacaoService, ConsoleUtils console) {
        this.autenticacaoService = autenticacaoService;
        this.console = console;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- AUTENTICAÇÃO ---");
            System.out.println("1 - Cadastrar cargo");
            System.out.println("2 - Listar cargos");
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
        System.out.print("Nome do cargo: ");
        String cargo = console.lerString();
        System.out.print("Permissões (separadas por vírgula, ex: REQ_OS,EDITAR): ");
        String perms = console.lerString();

        try {
            Autenticacao a = new Autenticacao(null, cargo);
            if (perms != null && !perms.trim().isEmpty()) {
                String[] arr = perms.split(",");
                for (String p : arr) {
                    a.adicionarPermissao(p.trim());
                }
            }
            autenticacaoService.salvar(a);
            System.out.println("✅ Cargo salvo! ID: " + a.getId());
        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Cargos ---");
        autenticacaoService.listar().forEach(System.out::println);
    }

    private void buscar() {
        System.out.print("ID: ");
        Long id = console.lerLong();
        Autenticacao a = autenticacaoService.buscarPorId(id);
        System.out.println(a != null ? a : "⚠️  Cargo não encontrado.");
    }

    private void remover() {
        System.out.print("ID para remover: ");
        Long id = console.lerLong();
        autenticacaoService.remover(id);
        System.out.println("🗑️  Cargo removido.");
    }
}
