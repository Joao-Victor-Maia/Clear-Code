import java.util.ArrayList;
import java.util.List;

public class MecanicoRepository { // "banco" em memoria de mecanicos
    private final List<Mecanico> mecanicos = new ArrayList<>();
    private long proximoId = 1;

    public Mecanico salvar(Mecanico mecanico) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (mecanico == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (mecanico.getId() == null) {
            mecanico.setId(proximoId);
            proximoId++;
            mecanicos.add(mecanico);
            return mecanico;
        }

        // for: procura na lista um mecanico com o mesmo ID pra atualizar
        for (int i = 0; i < mecanicos.size(); i++) {
            Mecanico atual = mecanicos.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(mecanico.getId())) {
                mecanicos.set(i, mecanico);
                return mecanico;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        mecanicos.add(mecanico);
        return mecanico;
    }

    public List<Mecanico> listar() { // retorna todos
        return mecanicos;
    }

    public Mecanico buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada mecanico
        for (int i = 0; i < mecanicos.size(); i++) {
            Mecanico atual = mecanicos.get(i);
            // if: quando o ID bate, retorna o mecanico
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

        // for: procura o mecanico pelo ID pra remover pelo indice
        for (int i = 0; i < mecanicos.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(mecanicos.get(i).getId())) {
                mecanicos.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
