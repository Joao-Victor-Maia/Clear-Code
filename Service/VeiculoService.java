import java.util.List;

public class VeiculoService {
    private final VeiculoRepository veiculoRepo;
    private final ClienteRepository clienteRepo;

    public VeiculoService(VeiculoRepository veiculoRepo, ClienteRepository clienteRepo) {
        this.veiculoRepo = veiculoRepo;
        this.clienteRepo = clienteRepo;
    }

    public Veiculo salvar(Veiculo veiculo) {
        if (veiculo == null) {
            throw new RegraNegocioException("O veículo não pode ser nulo.");
        }

        // Validações de preenchimento
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new RegraNegocioException("A placa do veículo é obrigatória!");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new RegraNegocioException("O modelo do veículo é obrigatório!");
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty()) {
            throw new RegraNegocioException("A marca do veículo é obrigatória!");
        }

        // Validação de Placa Única
        for (Veiculo existente : veiculoRepo.listar()) {
            if (existente.getPlaca().equalsIgnoreCase(veiculo.getPlaca()) 
                && (veiculo.getId() == null || !existente.getId().equals(veiculo.getId()))) {
                throw new RegraNegocioException("Já existe um veículo com a placa " + veiculo.getPlaca() + " cadastrado!");
            }
        }

        // Regra de Integridade: O veículo PRECISA pertencer a um cliente cadastrado
        if (veiculo.getClienteId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID do cliente dono do veículo!");
        }
        if (clienteRepo.buscarPorId(veiculo.getClienteId()) == null) {
            throw new RegraNegocioException("Cliente dono do veículo não encontrado no banco de dados!");
        }

        return veiculoRepo.salvar(veiculo);
    }

    public List<Veiculo> listar() {
        return veiculoRepo.listar();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepo.buscarPorId(id);
    }

    public void remover(Long id) {
        veiculoRepo.remover(id);
    }
}
