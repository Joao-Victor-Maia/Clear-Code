import java.util.ArrayList;
import java.util.List;

public class Autenticacao {
    // Dados do cargo e suas permissoes
    private Long id;
    private String cargo;
    private List<String> permissoes = new ArrayList<>();

    public Autenticacao() {
    }

    public Autenticacao(Long id, String cargo) {
        // guarda os valores recebidos
        this.id = id;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        // if: nao adiciona texto vazio/nulo
        if (permissao == null || permissao.trim().isEmpty()) {
            return;
        }

        // if: evita adicionar a mesma permissao duas vezes
        if (!permissoes.contains(permissao)) {
            permissoes.add(permissao);
        }
    }

    public void removerPermissao(String permissao) {
        // remove (se nao existir, nao acontece nada)
        permissoes.remove(permissao);
    }

    public boolean podeAcessar(String recurso) {
        // if: se o recurso vier vazio, nao tem acesso
        if (recurso == null || recurso.trim().isEmpty()) {
            return false;
        }

        // verifica se a permissao esta na lista
        return permissoes.contains(recurso);
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Autenticacao [ID=" + id + ", Cargo=" + cargo + ", Permissoes=" + permissoes + "]";
    }
}
