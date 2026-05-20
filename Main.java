import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUtils console = new ConsoleUtils(scanner);

        // =====================================================================
        // 1. INSTANCIANDO REPOSITÓRIOS (As Gavetas de Dados)
        // =====================================================================
        // Aqui é criado só UMA vez cada repositório. Eles são tipo gavetas onde
        // guardo os dados enquanto o programa estiver aberto.
        // Se criasse vários repositórios diferentes, os dados poderiam se
        // perder entre as partes do sistema. Por isso a ideia é criar um só e
        // passar para todo mundo usar.
        
        ClienteRepository clienteRepo = new ClienteRepository();
        VeiculoRepository veiculoRepo = new VeiculoRepository();
        MecanicoRepository mecanicoRepo = new MecanicoRepository();
        FornecedorRepository fornecedorRepo = new FornecedorRepository();
        PecaRepository pecaRepo = new PecaRepository();
        ServicoRepository servicoRepo = new ServicoRepository();
        OrdemServiceRepository ordemRepo = new OrdemServiceRepository();

        // =====================================================================
        // 2. INSTANCIANDO SERVICES (Os Cérebros / Regras de Negócio)
        // =====================================================================
        // Aqui os services são instanciados. Eles são os "cérebros" que sabem aplicar as
        // regras do sistema antes de salvar qualquer coisa.
        // Para isso, eu passo o repositório pronto para dentro do Service.
        // Assim o Service pode checar se o CPF está ok, se o nome foi preenchido,
        // e só então pedir ao repo pra guardar o dado.

        ClienteService clienteService = new ClienteService(clienteRepo);
        VeiculoService veiculoService = new VeiculoService(veiculoRepo, clienteRepo);
        MecanicoService mecanicoService = new MecanicoService(mecanicoRepo);
        FornecedorService fornecedorService = new FornecedorService(fornecedorRepo);
        PecaService pecaService = new PecaService(pecaRepo);
        ServicoService servicoService = new ServicoService(servicoRepo, veiculoRepo);
        OrdemServiceService ordemService = new OrdemServiceService(ordemRepo, veiculoRepo, mecanicoRepo);

        // =====================================================================
        // 3. INSTANCIANDO CONTROLLERS (A Interface com o Usuário / Terminal)
        // =====================================================================
        // Agora os controllers são instanciados. Eles são a parte que conversa com o
        // usuário no terminal. O controller não faz as regras, ele só pega o
        // que o usuário digitou e manda para o service certo.
        // O ConsoleUtils ajuda a ler o teclado sem travar se o usuário errar.

        ClienteController clienteController = new ClienteController(clienteService, console);
        VeiculoController veiculoController = new VeiculoController(veiculoService, console);
        MecanicoController mecanicoController = new MecanicoController(mecanicoService, console);
        FornecedorController fornecedorController = new FornecedorController(fornecedorService, console);
        PecaController pecaController = new PecaController(pecaService, console);
        ServicoController servicoController = new ServicoController(servicoService, console);
        OrdemServiceController ordemServiceController = new OrdemServiceController(ordemService, console);

        int opcao = -1;

        // =====================================================================
        // 4. LOOP PRINCIPAL DO PROGRAMA
        // =====================================================================
        // Enquanto o usuário não digitar 0, o menu vai aparecer de novo.
        // Cada opção abre uma parte diferente do sistema para cadastrar,
        // listar ou buscar informações.

        while (opcao != 0) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║     SISTEMA DA OFICINA - MENU    ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1 - Clientes                    ║");
            System.out.println("║  2 - Veículos                    ║");
            System.out.println("║  3 - Mecânicos                   ║");
            System.out.println("║  4 - Fornecedores                ║");
            System.out.println("║  5 - Peças                       ║");
            System.out.println("║  6 - Serviços                    ║");
            System.out.println("║  7 - Ordens de Serviço           ║");
            System.out.println("║  0 - Sair                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("  Escolha: ");
            opcao = console.lerInt();

            switch (opcao) {
                case 1 -> clienteController.exibirMenu();
                case 2 -> veiculoController.exibirMenu();
                case 3 -> mecanicoController.exibirMenu();
                case 4 -> fornecedorController.exibirMenu();
                case 5 -> pecaController.exibirMenu();
                case 6 -> servicoController.exibirMenu();
                case 7 -> ordemServiceController.exibirMenu();
                case 0 -> System.out.println("\nEncerrando o sistema. Até logo!");
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }

        scanner.close();
    }
}
