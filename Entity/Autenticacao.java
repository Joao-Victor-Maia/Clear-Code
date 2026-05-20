import java.util.ArrayList;
import java.util.List;

public class Autenticacao extends EntidadeBase {
        // Dados do cargo e suas permissões
    private String cargo;
    private List<String> permissoes = new ArrayList<>();

    public Autenticacao() {
        super();
    }

    public Autenticacao(Long id, String cargo) {
        super(id);
        // Guarda o cargo recebido.
        this.cargo = cargo;
    }

    // Os métodos getId() e setId() foram removidos pois agora são herdados de EntidadeBase

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void adicionarPermissao(String permissao) {
        // Se a permissão vier vazia, ignora.
        if (permissao == null || permissao.trim().isEmpty()) {
            return;
        }

        // Só adiciona se ainda não tiver essa permissão.
        if (!permissoes.contains(permissao)) {
            permissoes.add(permissao);
        }
    }

    public void removerPermissao(String permissao) {
        // Remove a permissão da lista se ela existir.
        permissoes.remove(permissao);
    }

    public boolean podeAcessar(String recurso) {
        // Se o recurso vier vazio, não dá pra permitir acesso.
        if (recurso == null || recurso.trim().isEmpty()) {
            return false;
        }

        // Verifica se essa permissão está na lista.
        return permissoes.contains(recurso);
    }

    @Override
    public String toString() {
        // Monta uma string simples para mostrar a autenticação
        return "Autenticacao [ID=" + id + ", Cargo=" + cargo + ", Permissoes=" + permissoes + "]";
    }
}
