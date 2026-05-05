import java.util.List;

public class PecaService {
    private final PecaRepository repositorio;

    public PecaService(PecaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Peca salvar(Peca peca) {
        if (peca == null) {
            throw new RegraNegocioException("A peça não pode ser nula.");
        }

        if (peca.getNome() == null || peca.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da peça é obrigatório!");
        }
        
        // Regra de Negócio Crítica: Preço não pode ser negativo
        if (peca.getPreco() < 0) {
            throw new RegraNegocioException("O preço da peça não pode ser negativo!");
        }

        return repositorio.salvar(peca);
    }

    public List<Peca> listar() {
        return repositorio.listar();
    }

    public Peca buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void remover(Long id) {
        repositorio.remover(id);
    }
}
