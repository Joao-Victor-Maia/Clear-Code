import java.util.List;

public class FornecedorService {
    private final FornecedorRepository repositorio;

    public FornecedorService(FornecedorRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new RegraNegocioException("O fornecedor não pode ser nulo.");
        }

        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do fornecedor é obrigatório!");
        }
        
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty()) {
            throw new RegraNegocioException("O CNPJ do fornecedor é obrigatório!");
        }

        // Validação de CNPJ Único
        for (Fornecedor existente : repositorio.listar()) {
            if (existente.getCnpj().equals(fornecedor.getCnpj()) 
                && (fornecedor.getId() == null || !existente.getId().equals(fornecedor.getId()))) {
                throw new RegraNegocioException("Já existe um fornecedor com este CNPJ cadastrado!");
            }
        }

        return repositorio.salvar(fornecedor);
    }

    public List<Fornecedor> listar() {
        return repositorio.listar();
    }

    public Fornecedor buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void remover(Long id) {
        repositorio.remover(id);
    }
}
