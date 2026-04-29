import java.util.ArrayList;
import java.util.List;

public class AutenticacaoRepository { // "banco" em memoria de cargos/permissoes
    private final List<Autenticacao> autenticacoes = new ArrayList<>();
    private long proximoId = 1;

    public Autenticacao salvar(Autenticacao autenticacao) { // cria ou atualiza
        // if: valida se o objeto veio nulo
        if (autenticacao == null) {
            return null;
        }

        // if: se nao tem ID, entao e um cadastro novo (gera ID e adiciona)
        if (autenticacao.getId() == null) {
            autenticacao.setId(proximoId);
            proximoId++;
            autenticacoes.add(autenticacao);
            return autenticacao;
        }

        // for: procura na lista um registro com o mesmo ID pra atualizar
        for (int i = 0; i < autenticacoes.size(); i++) {
            Autenticacao atual = autenticacoes.get(i);
            // if: achou o mesmo ID, troca o item da lista
            if (atual.getId().equals(autenticacao.getId())) {
                autenticacoes.set(i, autenticacao);
                return autenticacao;
            }
        }

        // se nao achou o ID na lista, adiciona mesmo assim no final
        autenticacoes.add(autenticacao);
        return autenticacao;
    }

    public List<Autenticacao> listar() { // retorna todos
        return autenticacoes;
    }

    public Autenticacao buscarPorId(Long id) { // procura pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return null;
        }

        // for: percorre a lista e compara o ID de cada registro
        for (int i = 0; i < autenticacoes.size(); i++) {
            Autenticacao atual = autenticacoes.get(i);
            // if: quando o ID bate, retorna o registro
            if (id.equals(atual.getId())) {
                return atual;
            }
        }

        // se nao encontrou, retorna null
        return null;
    }

    public Autenticacao buscarPorCargo(String cargo) { // procura pelo cargo
        // if: valida se o texto veio vazio/nulo
        if (cargo == null || cargo.trim().isEmpty()) {
            return null;
        }

        // for: percorre e compara o nome do cargo (ignora maiuscula/minuscula)
        for (int i = 0; i < autenticacoes.size(); i++) {
            Autenticacao atual = autenticacoes.get(i);
            // if: quando o cargo bate, retorna o registro
            if (cargo.equalsIgnoreCase(atual.getCargo())) {
                return atual;
            }
        }

        // se nao encontrou, retorna null
        return null;
    }

    public boolean podeAcessar(String cargo, String recurso) { // verifica acesso
        // busca o cargo e usa a lista de permissoes de dentro dele
        Autenticacao autenticacao = buscarPorCargo(cargo);
        if (autenticacao == null) {
            return false;
        }

        return autenticacao.podeAcessar(recurso);
    }

    public boolean remover(Long id) { // remove pelo id
        // if: valida se o id veio nulo
        if (id == null) {
            return false;
        }

        // for: procura o registro pelo ID pra remover pelo indice
        for (int i = 0; i < autenticacoes.size(); i++) {
            // if: se o ID bate, remove e retorna true
            if (id.equals(autenticacoes.get(i).getId())) {
                autenticacoes.remove(i);
                return true;
            }
        }

        // se nao removeu nada, retorna false
        return false;
    }
}
