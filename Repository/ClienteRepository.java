import java.util.ArrayList;
import java.util.List;

public class ClienteRepository { // "banco" em memoria de clientes
    private final List<Cliente> clientes = new ArrayList<>();
    private long proximoId = 1;

    public Cliente salvar(Cliente cliente) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (cliente == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (cliente.getId() == null) {
            cliente.setId(proximoId);
            proximoId++;
            clientes.add(cliente);
            return cliente;
        }

        // for: procura na lista um cliente com o mesmo ID pra atualizar
        for (int i = 0; i < clientes.size(); i++) {
            Cliente atual = clientes.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(cliente.getId())) {
                clientes.set(i, cliente);
                return cliente;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> listar() { // retorna todos
        return clientes;
    }

    public Cliente buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada cliente
        for (int i = 0; i < clientes.size(); i++) {
            Cliente atual = clientes.get(i);
            // if: quando o ID bate, retorna o cliente
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

        // for: procura o cliente pelo ID pra remover pelo indice
        for (int i = 0; i < clientes.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(clientes.get(i).getId())) {
                clientes.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
