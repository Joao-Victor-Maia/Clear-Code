import java.util.List;

// Esse service organiza as regras de negócio das transações.
// Ele verifica se o pagamento está correto antes de mandar salvar.
public class TransacaoService {
    private final TransacaoRepository transacaoRepo;
    private final OrdemServiceRepository ordemServiceRepo;

    public TransacaoService(TransacaoRepository transacaoRepo, OrdemServiceRepository ordemServiceRepo) {
        this.transacaoRepo = transacaoRepo;
        this.ordemServiceRepo = ordemServiceRepo;
    }

    public Transacao salvar(Transacao transacao) {
        if (transacao == null) {
            throw new RegraNegocioException("A transação não pode ser nula.");
        }

        if (transacao.getFormaPagamento() == null || transacao.getFormaPagamento().trim().isEmpty()) {
            throw new RegraNegocioException("A forma de pagamento é obrigatória!");
        }
        
        // Regra importante: o valor da transação precisa ser maior que zero.
        if (transacao.getValor() <= 0) {
            throw new RegraNegocioException("O valor da transação deve ser maior que zero!");
        }

        // Regra de integridade: a transação deve estar vinculada a uma OS real.
        if (transacao.getOsId() == null) {
            throw new RegraNegocioException("É obrigatório informar o ID da Ordem de Serviço!");
        }
        if (ordemServiceRepo.buscarPorId(transacao.getOsId()) == null) {
            throw new RegraNegocioException("Ordem de Serviço não encontrada! A transação não pode ser vinculada a uma OS inexistente.");
        }

        return transacaoRepo.salvar(transacao);
    }

    public List<Transacao> listar() {
        return transacaoRepo.listar();
    }

    public Transacao buscarPorId(Long id) {
        return transacaoRepo.buscarPorId(id);
    }

    public void remover(Long id) {
        transacaoRepo.remover(id);
    }
}
