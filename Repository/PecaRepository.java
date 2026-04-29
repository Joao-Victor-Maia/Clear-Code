import java.util.ArrayList;
import java.util.List;

public class PecaRepository { // "banco" em memoria de pecas
    private final List<Peca> pecas = new ArrayList<>();
    private long proximoId = 1;

    public Peca salvar(Peca peca) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (peca == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (peca.getId() == null) {
            peca.setId(proximoId);
            proximoId++;
            pecas.add(peca);
            return peca;
        }

        // for: procura na lista uma peca com o mesmo ID pra atualizar
        for (int i = 0; i < pecas.size(); i++) {
            Peca atual = pecas.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(peca.getId())) {
                pecas.set(i, peca);
                return peca;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        pecas.add(peca);
        return peca;
    }

    public List<Peca> listar() { // retorna todas
        return pecas;
    }

    public Peca buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada peca
        for (int i = 0; i < pecas.size(); i++) {
            Peca atual = pecas.get(i);
            // if: quando o ID bate, retorna a peca
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

        // for: procura a peca pelo ID pra remover pelo indice
        for (int i = 0; i < pecas.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(pecas.get(i).getId())) {
                pecas.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
