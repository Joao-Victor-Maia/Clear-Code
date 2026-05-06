public class AutenticacaoRepository extends RepositoryGenerico<Autenticacao> { 
    // Todos os métodos de CRUD (salvar, remover, listar, buscarPorId) foram herdados!

    public Autenticacao buscarPorCargo(String cargo) { // procura pelo cargo
        // if: valida se o texto veio vazio/nulo
        if (cargo == null || cargo.trim().isEmpty()) {
            return null;
        }

        // Utiliza o listar() que vem do RepositoryGenerico
        for (Autenticacao atual : listar()) {
            // if: quando o cargo bate, retorna o registro
            if (cargo.equalsIgnoreCase(atual.getCargo())) {
                return atual;
            }
        }

        // se não encontrou, retorna null
        return null;
    }

    public boolean podeAcessar(String cargo, String recurso) { // verifica acesso
        // busca o cargo e usa a lista de permissões de dentro dele
        Autenticacao autenticacao = buscarPorCargo(cargo);
        if (autenticacao == null) {
            return false;
        }

        return autenticacao.podeAcessar(recurso);
    }
}
