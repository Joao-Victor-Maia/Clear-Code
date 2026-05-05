import java.util.List;

public class MecanicoService {
    private final MecanicoRepository repositorio;

    public MecanicoService(MecanicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Mecanico salvar(Mecanico mecanico) {
        if (mecanico == null) {
            throw new RegraNegocioException("O mecânico não pode ser nulo.");
        }

        if (mecanico.getNome() == null || mecanico.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do mecânico é obrigatório!");
        }
        
        if (mecanico.getEspecialidade() == null || mecanico.getEspecialidade().trim().isEmpty()) {
            throw new RegraNegocioException("A especialidade do mecânico é obrigatória!");
        }

        return repositorio.salvar(mecanico);
    }

    public List<Mecanico> listar() {
        return repositorio.listar();
    }

    public Mecanico buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void remover(Long id) {
        repositorio.remover(id);
    }
}
