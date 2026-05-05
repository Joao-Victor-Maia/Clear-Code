import java.util.List;

public class ClienteService {
    
    // O Service guarda uma "instância" do repositório. O Main não falará mais com o repositório.
    private final ClienteRepository repositorio;

    // Construtor: Exige que passemos um repositório para o Service funcionar
    public ClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Cliente salvar(Cliente cliente) {
        // Validação 1: Evitar que o objeto inteiro venha vazio
        if (cliente == null) {
            throw new RegraNegocioException("O cliente não pode ser nulo.");
        }
        
        // Validação 2: Regras de preenchimento obrigatório
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do cliente é obrigatório!");
        }
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new RegraNegocioException("O CPF do cliente é obrigatório!");
        }

        // Validação 3: Regra de Negócio Crítica (CPF Único no sistema)
        // O Service pede os dados do banco e verifica
        for (Cliente existente : repositorio.listar()) {
            if (existente.getCpf().equals(cliente.getCpf())) {
                
                // Se o CPF bater, e NÃO for uma atualização do mesmo cliente (ID diferente), então barra!
                if (cliente.getId() == null || !existente.getId().equals(cliente.getId())) {
                    throw new RegraNegocioException("Já existe um cliente com o CPF " + cliente.getCpf() + " cadastrado!");
                }
            }
        }

        // Se o código chegou até aqui sem cair em nenhum "throw", então é seguro salvar no Banco!
        return repositorio.salvar(cliente);
    }

    // Os métodos abaixo não tem regra de negócio complexa, 
    // mas servem para o Service fazer a "ponte" entre o Main e o Repository
    public List<Cliente> listar() {
        return repositorio.listar();
    }

    public Cliente buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void remover(Long id) {
        repositorio.remover(id);
    }
}
