import java.util.ArrayList;
import java.util.List;

public class FornecedorRepository { // "banco" em memoria de fornecedores
    private final List<Fornecedor> fornecedores = new ArrayList<>();
    private long proximoId = 1;

    public Fornecedor salvar(Fornecedor fornecedor) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (fornecedor == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (fornecedor.getId() == null) {
            fornecedor.setId(proximoId);
            proximoId++;
            fornecedores.add(fornecedor);
            return fornecedor;
        }

        // for: procura na lista um fornecedor com o mesmo ID pra atualizar
        for (int i = 0; i < fornecedores.size(); i++) {
            Fornecedor atual = fornecedores.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(fornecedor.getId())) {
                fornecedores.set(i, fornecedor);
                return fornecedor;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        fornecedores.add(fornecedor);
        return fornecedor;
    }

    public List<Fornecedor> listar() { // retorna todos
        return fornecedores;
    }

    public Fornecedor buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada fornecedor
        for (int i = 0; i < fornecedores.size(); i++) {
            Fornecedor atual = fornecedores.get(i);
            // if: quando o ID bate, retorna o fornecedor
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

        // for: procura o fornecedor pelo ID pra remover pelo indice
        for (int i = 0; i < fornecedores.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(fornecedores.get(i).getId())) {
                fornecedores.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
