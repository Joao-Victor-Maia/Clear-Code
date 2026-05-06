import java.util.Scanner;

public class Main {

    // --- Scanner compartilhado por todos os métodos ---
    private static final Scanner scanner = new Scanner(System.in);

    // --- Repositórios ---
    private static final ClienteRepository     clienteRepo      = new ClienteRepository();
    private static final VeiculoRepository     veiculoRepo      = new VeiculoRepository();
    private static final MecanicoRepository    mecanicoRepo     = new MecanicoRepository();
    private static final FornecedorRepository  fornecedorRepo   = new FornecedorRepository();
    private static final PecaRepository        pecaRepo         = new PecaRepository();
    private static final ServicoRepository     servicoRepo      = new ServicoRepository();
    private static final OrdemServiceRepository ordemRepo       = new OrdemServiceRepository();

    // --- Services ---
    private static final ClienteService    clienteService    = new ClienteService(clienteRepo);
    private static final VeiculoService    veiculoService    = new VeiculoService(veiculoRepo, clienteRepo);
    private static final MecanicoService   mecanicoService   = new MecanicoService(mecanicoRepo);
    private static final FornecedorService fornecedorService = new FornecedorService(fornecedorRepo);
    private static final PecaService       pecaService       = new PecaService(pecaRepo);
    private static final ServicoService    servicoService    = new ServicoService(servicoRepo, veiculoRepo);
    private static final OrdemServiceService ordemService    = new OrdemServiceService(ordemRepo, veiculoRepo, mecanicoRepo);

    // =========================================================
    //  PONTO DE ENTRADA
    // =========================================================
    public static void main(String[] args) {
        int opcao = -1;

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
            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuVeiculos();
                case 3 -> menuMecanicos();
                case 4 -> menuFornecedores();
                case 5 -> menuPecas();
                case 6 -> menuServicos();
                case 7 -> menuOrdens();
                case 0 -> System.out.println("\nEncerrando o sistema. Até logo!");
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }

        scanner.close();
    }

    // =========================================================
    //  MENU: CLIENTES
    // =========================================================
    private static void menuClientes() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    try {
                        Cliente c = new Cliente(null, nome, cpf, telefone);
                        clienteService.salvar(c);
                        System.out.println("✅ Cliente salvo! ID: " + c.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Clientes ---");
                    clienteService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = lerLong();
                    Cliente c = clienteService.buscarPorId(id);
                    System.out.println(c != null ? c : "⚠️  Cliente não encontrado.");
                }
                case 4 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    clienteService.remover(id);
                    System.out.println("🗑️  Cliente removido.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  MENU: VEÍCULOS
    // =========================================================
    private static void menuVeiculos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- VEÍCULOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("ID do Cliente dono: ");
                    Long clienteId = lerLong();

                    try {
                        Veiculo v = new Veiculo(null, placa, modelo, marca, clienteId);
                        veiculoService.salvar(v);
                        System.out.println("✅ Veículo salvo! ID: " + v.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Veículos ---");
                    veiculoService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = lerLong();
                    Veiculo v = veiculoService.buscarPorId(id);
                    System.out.println(v != null ? v : "⚠️  Veículo não encontrado.");
                }
                case 4 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    veiculoService.remover(id);
                    System.out.println("🗑️  Veículo removido.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  MENU: MECÂNICOS
    // =========================================================
    private static void menuMecanicos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MECÂNICOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Especialidade: ");
                    String especialidade = scanner.nextLine();

                    try {
                        Mecanico m = new Mecanico(null, nome, especialidade);
                        mecanicoService.salvar(m);
                        System.out.println("✅ Mecânico salvo! ID: " + m.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Mecânicos ---");
                    mecanicoService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = lerLong();
                    Mecanico m = mecanicoService.buscarPorId(id);
                    System.out.println(m != null ? m : "⚠️  Mecânico não encontrado.");
                }
                case 4 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    mecanicoService.remover(id);
                    System.out.println("🗑️  Mecânico removido.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  MENU: FORNECEDORES
    // =========================================================
    private static void menuFornecedores() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- FORNECEDORES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    try {
                        Fornecedor f = new Fornecedor(null, nome, cnpj, telefone, email);
                        fornecedorService.salvar(f);
                        System.out.println("✅ Fornecedor salvo! ID: " + f.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Fornecedores ---");
                    fornecedorService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = lerLong();
                    Fornecedor f = fornecedorService.buscarPorId(id);
                    System.out.println(f != null ? f : "⚠️  Fornecedor não encontrado.");
                }
                case 4 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    fornecedorService.remover(id);
                    System.out.println("🗑️  Fornecedor removido.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  MENU: PEÇAS
    // =========================================================
    private static void menuPecas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- PEÇAS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todas");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da peça: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço (ex: 49.90): ");
                    double preco = lerDouble();

                    try {
                        Peca p = new Peca(null, nome, preco);
                        pecaService.salvar(p);
                        System.out.println("✅ Peça salva! ID: " + p.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Peças ---");
                    pecaService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = lerLong();
                    Peca p = pecaService.buscarPorId(id);
                    System.out.println(p != null ? p : "⚠️  Peça não encontrada.");
                }
                case 4 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    pecaService.remover(id);
                    System.out.println("🗑️  Peça removida.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  MENU: SERVIÇOS
    // =========================================================
    private static void menuServicos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- SERVIÇOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Valor (ex: 150.00): ");
                    double valor = lerDouble();
                    System.out.print("ID do Veículo: ");
                    Long veiculoId = lerLong();

                    try {
                        Servico s = new Servico(null, descricao, valor, veiculoId);
                        servicoService.salvar(s);
                        System.out.println("✅ Serviço salvo! ID: " + s.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Serviços ---");
                    servicoService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = lerLong();
                    Servico s = servicoService.buscarPorId(id);
                    System.out.println(s != null ? s : "⚠️  Serviço não encontrado.");
                }
                case 4 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    servicoService.remover(id);
                    System.out.println("🗑️  Serviço removido.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  MENU: ORDENS DE SERVIÇO
    // =========================================================
    private static void menuOrdens() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- ORDENS DE SERVIÇO ---");
            System.out.println("1 - Abrir nova OS");
            System.out.println("2 - Listar todas");
            System.out.println("3 - Remover");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do Veículo: ");
                    Long veiculoId = lerLong();
                    System.out.print("ID do Mecânico: ");
                    Long mecanicoId = lerLong();
                    System.out.print("Status inicial (ex: ABERTA): ");
                    String status = scanner.nextLine();

                    try {
                        OrdemService os = new OrdemService(null, veiculoId, mecanicoId, status);
                        ordemService.salvar(os);
                        System.out.println("✅ OS aberta! ID: " + os.getId());
                    } catch (RegraNegocioException e) {
                        System.out.println("❌ Erro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Ordens de Serviço ---");
                    ordemService.listar().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID para remover: ");
                    Long id = lerLong();
                    ordemService.remover(id);
                    System.out.println("🗑️  OS removida.");
                }
                case 0 -> {}
                default -> System.out.println("⚠️  Opção inválida.");
            }
        }
    }

    // =========================================================
    //  UTILITÁRIOS DE LEITURA SEGURA
    //  (evitam crash do programa quando o usuário digita errado)
    // =========================================================

    /** Lê um int e já consome o \n restante do buffer. */
    private static int lerInt() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Digite um número inteiro válido: ");
            }
        }
    }

    /** Lê um Long e já consome o \n restante do buffer. */
    private static Long lerLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Digite um número válido: ");
            }
        }
    }

    /** Lê um double e já consome o \n restante do buffer. */
    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Digite um valor numérico válido (ex: 49.90): ");
            }
        }
    }
}
