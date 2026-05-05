public class Main {
    public static void main(String[] args) {
        // 1. Instanciamos os Repositórios
        ClienteRepository clienteRepo = new ClienteRepository();
        VeiculoRepository veiculoRepo = new VeiculoRepository();

        // 2. NOVA ARQUITETURA: Instanciamos os Services passando os Repositórios para eles
        ClienteService clienteService = new ClienteService(clienteRepo);

        System.out.println("=== TESTE DA CAMADA SERVICE ===");

        // Teste A: Tentando salvar um cliente COM SUCESSO
        try {
            Cliente clienteValido = new Cliente(null, "João Silva", "123.456.789-00", "11999999999");
            
            // Repare: Chamamos o SERVICE, e não mais o repositório
            clienteService.salvar(clienteValido);
            System.out.println("✅ Cliente salvo com sucesso! ID: " + clienteValido.getId());

        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro ao salvar: " + e.getMessage());
        }

        // Teste B: Tentando salvar um cliente SEM NOME (Regra deve bloquear)
        try {
            System.out.println("\nTentando salvar cliente sem nome...");
            Cliente clienteInvalido = new Cliente(null, "", "000.111.222-33", "9999");
            
            // O Service vai bloquear e disparar o RegraNegocioException antes de chegar no Repositório!
            clienteService.salvar(clienteInvalido);
            System.out.println("✅ Cliente salvo com sucesso!"); // Essa linha não vai rodar

        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro ao salvar (BLOQUEADO PELO SERVICE): " + e.getMessage());
        }

        // Teste C: Tentando salvar um cliente COM CPF DUPLICADO (Regra deve bloquear)
        try {
            System.out.println("\nTentando salvar cliente com CPF duplicado...");
            Cliente clienteDuplicado = new Cliente(null, "Maria", "123.456.789-00", "8888"); // Mesmo CPF do João
            
            clienteService.salvar(clienteDuplicado);
            System.out.println("✅ Cliente salvo com sucesso!"); // Essa linha não vai rodar

        } catch (RegraNegocioException e) {
            System.out.println("❌ Erro ao salvar (BLOQUEADO PELO SERVICE): " + e.getMessage());
        }
    }
}
