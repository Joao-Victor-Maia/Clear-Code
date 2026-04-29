import java.util.ArrayList;
import java.util.List;

public class OrdemServiceRepository { // "banco" em memoria de ordens
    private final List<OrdemService> ordens = new ArrayList<>();
    private long proximoId = 1;

    public OrdemService salvar(OrdemService ordem) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (ordem == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (ordem.getId() == null) {
            ordem.setId(proximoId);
            proximoId++;
            ordens.add(ordem);
            return ordem;
        }

        // for: procura na lista uma ordem com o mesmo ID pra atualizar
        for (int i = 0; i < ordens.size(); i++) {
            OrdemService atual = ordens.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(ordem.getId())) {
                ordens.set(i, ordem);
                return ordem;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        ordens.add(ordem);
        return ordem;
    }

    public List<OrdemService> listar() { // retorna todas
        return ordens;
    }

    public OrdemService buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada ordem
        for (int i = 0; i < ordens.size(); i++) {
            OrdemService atual = ordens.get(i);
            // if: quando o ID bate, retorna a ordem
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

        // for: procura a ordem pelo ID pra remover pelo indice
        for (int i = 0; i < ordens.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(ordens.get(i).getId())) {
                ordens.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
