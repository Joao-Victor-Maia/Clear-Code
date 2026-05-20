import java.util.List;

// Esse service cuida das validações de veículos.
// Ele garante que não seja criado um veículo sem placa, modelo ou marca, e que não haja dois veículos com a mesma placa. Ele também garante que o veículo esteja associado a um cliente válido.

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

        // Verifica se os campos obrigatórios estão preenchidos
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new RegraNegocioException("A placa do veículo é obrigatória!");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new RegraNegocioException("O modelo do veículo é obrigatório!");
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().trim().isEmpty()) {
            throw new RegraNegocioException("A marca do veículo é obrigatória!");
        }

        // Verifica se não existe outro veículo com a mesma placa.
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
