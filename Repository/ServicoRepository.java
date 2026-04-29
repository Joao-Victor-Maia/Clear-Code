import java.util.ArrayList;
import java.util.List;

public class ServicoRepository { // "banco" em memoria de servicos
    private final List<Servico> servicos = new ArrayList<>();
    private long proximoId = 1;

    public Servico salvar(Servico servico) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (servico == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (servico.getId() == null) {
            servico.setId(proximoId);
            proximoId++;
            servicos.add(servico);
            return servico;
        }

        // for: procura na lista um servico com o mesmo ID pra atualizar
        for (int i = 0; i < servicos.size(); i++) {
            Servico atual = servicos.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(servico.getId())) {
                servicos.set(i, servico);
                return servico;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        servicos.add(servico);
        return servico;
    }

    public List<Servico> listar() { // retorna todos
        return servicos;
    }

    public Servico buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada servico
        for (int i = 0; i < servicos.size(); i++) {
            Servico atual = servicos.get(i);
            // if: quando o ID bate, retorna o servico
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

        // for: procura o servico pelo ID pra remover pelo indice
        for (int i = 0; i < servicos.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(servicos.get(i).getId())) {
                servicos.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
