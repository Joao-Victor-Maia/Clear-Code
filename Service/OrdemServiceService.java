import java.util.List;

public class OrdemServiceService {
    
    // Este Service é interessante porque ele se comunica com 3 repositórios diferentes!
    // Ele é um verdadeiro "Maestro/Chef de cozinha"
    private final OrdemServiceRepository ordemServiceRepo;
    private final VeiculoRepository veiculoRepo;
    private final MecanicoRepository mecanicoRepo;

    public OrdemServiceService(OrdemServiceRepository ordemServiceRepo, 
                               VeiculoRepository veiculoRepo, 
                               MecanicoRepository mecanicoRepo) {
        this.ordemServiceRepo = ordemServiceRepo;
        this.veiculoRepo = veiculoRepo;
        this.mecanicoRepo = mecanicoRepo;
    }

    public OrdemService salvar(OrdemService ordem) {
        if (ordem == null) {
            throw new RegraNegocioException("A ordem de serviço não pode ser nula.");
        }

        // Regra 1: O veículo precisa EXISTIR de verdade no banco de dados. 
        // Não podemos abrir uma OS para um carro fantasma!
        if (ordem.getVeiculoId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID do veículo!");
        }
        if (veiculoRepo.buscarPorId(ordem.getVeiculoId()) == null) {
            throw new RegraNegocioException("Veículo não encontrado! O veículo deve ser cadastrado antes da OS.");
        }

        // Regra 2: O mecânico precisa EXISTIR de verdade.
        if (ordem.getMecanicoId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID do mecânico!");
        }
        if (mecanicoRepo.buscarPorId(ordem.getMecanicoId()) == null) {
            throw new RegraNegocioException("Mecânico não encontrado! Atribua a OS a um mecânico válido.");
        }

        // Passou pelas regras pesadas? Então salva!
        return ordemServiceRepo.salvar(ordem);
    }

    public List<OrdemService> listar() {
        return ordemServiceRepo.listar();
    }

    public void remover(Long id) {
        ordemServiceRepo.remover(id);
    }
}
