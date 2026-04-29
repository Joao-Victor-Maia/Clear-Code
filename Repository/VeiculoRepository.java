import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository { // "banco" em memoria de veiculos
    private final List<Veiculo> veiculos = new ArrayList<>();
    private long proximoId = 1;

    public Veiculo salvar(Veiculo veiculo) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (veiculo == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (veiculo.getId() == null) {
            veiculo.setId(proximoId);
            proximoId++;
            veiculos.add(veiculo);
            return veiculo;
        }

        // for: procura na lista um veiculo com o mesmo ID pra atualizar
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo atual = veiculos.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(veiculo.getId())) {
                veiculos.set(i, veiculo);
                return veiculo;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        veiculos.add(veiculo);
        return veiculo;
    }

    public List<Veiculo> listar() { // retorna todos
        return veiculos;
    }

    public Veiculo buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada veiculo
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo atual = veiculos.get(i);
            // if: quando o ID bate, retorna o veiculo
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

        // for: procura o veiculo pelo ID pra remover pelo indice
        for (int i = 0; i < veiculos.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(veiculos.get(i).getId())) {
                veiculos.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
