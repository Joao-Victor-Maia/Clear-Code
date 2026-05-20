import java.util.List;

public class OrdemServiceService {
    // Esse service cuida das validações de ordens de serviço.
    // Ele garante que não seja criada uma OS sem um veículo ou mecânico válido.
    // Ele também é o "cérebro" do módulo de OS, onde ficam as regras de negócio.
    // Ele tem acesso ao repositório de OS, mas também precisa acessar os repositórios de veículos e mecânicos
    // para validar se os IDs informados existem de verdade.
    
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

        // Regra 1: o veículo precisa existir de verdade no banco de dados.
        // Não podemos abrir uma OS para um carro fantasma.
        if (ordem.getVeiculoId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID do veículo!");
        }
        if (veiculoRepo.buscarPorId(ordem.getVeiculoId()) == null) {
            throw new RegraNegocioException("Veículo não encontrado! O veículo deve ser cadastrado antes da OS.");
        }

        // Regra 2: o mecânico também precisa existir no banco de dados.
        if (ordem.getMecanicoId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID do mecânico!");
        }
        if (mecanicoRepo.buscarPorId(ordem.getMecanicoId()) == null) {
            throw new RegraNegocioException("Mecânico não encontrado! Atribua a OS a um mecânico válido.");
        }

        // Se chegou até aqui, todas as regras foram validadas.
        // Agora é seguro mandar a OS para o repositório salvar.
        return ordemServiceRepo.salvar(ordem);
    }

    public List<OrdemService> listar() {
        return ordemServiceRepo.listar();
    }

    public void remover(Long id) {
        ordemServiceRepo.remover(id);
    }
}
