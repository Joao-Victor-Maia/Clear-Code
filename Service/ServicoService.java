import java.util.List;

public class ServicoService {
    private final ServicoRepository servicoRepo;
    private final VeiculoRepository veiculoRepo;

    public ServicoService(ServicoRepository servicoRepo, VeiculoRepository veiculoRepo) {
        this.servicoRepo = servicoRepo;
        this.veiculoRepo = veiculoRepo;
    }

    public Servico salvar(Servico servico) {
        if (servico == null) {
            throw new RegraNegocioException("O serviço não pode ser nulo.");
        }

        if (servico.getDescricao() == null || servico.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição do serviço é obrigatória!");
        }
        
        // Regra Crítica: Valor não pode ser negativo
        if (servico.getValor() < 0) {
            throw new RegraNegocioException("O valor do serviço não pode ser negativo!");
        }

        // Regra de Integridade: Veículo precisa existir
        if (servico.getVeiculoId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID do veículo para este serviço!");
        }
        if (veiculoRepo.buscarPorId(servico.getVeiculoId()) == null) {
            throw new RegraNegocioException("Veículo não encontrado no banco de dados!");
        }

        return servicoRepo.salvar(servico);
    }

    public List<Servico> listar() {
        return servicoRepo.listar();
    }

    public Servico buscarPorId(Long id) {
        return servicoRepo.buscarPorId(id);
    }

    public void remover(Long id) {
        servicoRepo.remover(id);
    }
}
