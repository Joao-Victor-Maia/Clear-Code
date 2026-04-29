import java.util.ArrayList;
import java.util.List;

public class TransacaoRepository { // "banco" em memoria de transacoes
    private final List<Transacao> transacoes = new ArrayList<>();
    private long proximoId = 1;

    public Transacao salvar(Transacao transacao) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (transacao == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (transacao.getId() == null) {
            transacao.setId(proximoId);
            proximoId++;
            transacoes.add(transacao);
            return transacao;
        }

        // for: procura na lista uma transacao com o mesmo ID pra atualizar
        for (int i = 0; i < transacoes.size(); i++) {
            Transacao atual = transacoes.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(transacao.getId())) {
                transacoes.set(i, transacao);
                return transacao;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        transacoes.add(transacao);
        return transacao;
    }

    public List<Transacao> listar() { // retorna todas
        return transacoes;
    }

    public Transacao buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada transacao
        for (int i = 0; i < transacoes.size(); i++) {
            Transacao atual = transacoes.get(i);
            // if: quando o ID bate, retorna a transacao
            if (id.equals(atual.getId())) {
                return atual;
            }
        }

        // se nao encontrou, retorna null
        return null;
    }

    public boolean remover(Long id) { // remove pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return false;
        }

        // for: procura a transacao pelo ID pra remover pelo indice
        for (int i = 0; i < transacoes.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(transacoes.get(i).getId())) {
                transacoes.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
