public class AutenticacaoRepository extends RepositoryGenerico<Autenticacao> { 
    // Repositório em memória para cargos e permissões.
    // Ele herda salvar(), listar(), buscarPorId() e remover() do RepositoryGenerico.

    public Autenticacao buscarPorCargo(String cargo) { // procura pelo cargo
        // Se o cargo vier vazio ou nulo, não faz sentido buscar.
        if (cargo == null || cargo.trim().isEmpty()) {
            return null;
        }

        // Usa o listar() do RepositoryGenerico para verificar os cargos.
        for (Autenticacao atual : listar()) {
            // Se o cargo bater, retorna esse registro.
            if (cargo.equalsIgnoreCase(atual.getCargo())) {
                return atual;
            }
        }

        // Se não encontrou, retorna null.
        return null;
    }

    public boolean podeAcessar(String cargo, String recurso) { // verifica acesso
        // Busca o cargo e verifica as permissões desse cargo.
        Autenticacao autenticacao = buscarPorCargo(cargo);
        if (autenticacao == null) {
            return false;
        }

        return autenticacao.podeAcessar(recurso);
    }
}
