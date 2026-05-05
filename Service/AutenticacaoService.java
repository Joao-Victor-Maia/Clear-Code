import java.util.List;

public class AutenticacaoService {
    private final AutenticacaoRepository repositorio;

    public AutenticacaoService(AutenticacaoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Autenticacao salvar(Autenticacao autenticacao) {
        if (autenticacao == null) {
            throw new RegraNegocioException("A autenticação não pode ser nula.");
        }

        if (autenticacao.getCargo() == null || autenticacao.getCargo().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do cargo é obrigatório!");
        }

        // Validação de Cargo Único
        Autenticacao existente = repositorio.buscarPorCargo(autenticacao.getCargo());
        if (existente != null && (autenticacao.getId() == null || !existente.getId().equals(autenticacao.getId()))) {
            throw new RegraNegocioException("Já existe um cargo com este nome cadastrado!");
        }

        return repositorio.salvar(autenticacao);
    }

    public boolean podeAcessar(String cargo, String recurso) {
        if (cargo == null || recurso == null) {
            return false;
        }
        return repositorio.podeAcessar(cargo, recurso);
    }

    public List<Autenticacao> listar() {
        return repositorio.listar();
    }

    public Autenticacao buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void remover(Long id) {
        repositorio.remover(id);
    }
}
