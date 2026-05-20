import java.util.List;

public class ClienteService {
    
    private final ClienteRepository repositorio;

    // Esse construtor é o tal de "Injeção de Dependência".
    // Em vez do Service criar o próprio repositório (new ClienteRepository()),
    // quem faz isso é o Main e passa pra cá. Assim todos usam o mesmo repositório
    // e os dados ficam sincronizados.
    public ClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Cliente salvar(Cliente cliente) {
        // Primeiro é verificado se o objeto veio vazio. Parece bobeira,
        // mas se deixar passar um nulo, o programa quebra lá embaixo
        // quando tentar ler o nome ou CPF.
        if (cliente == null) {
            throw new RegraNegocioException("O cliente não pode ser nulo.");
        }
        
        // Aqui verifica se o nome foi preenchido.
        // O ".trim()" remove espaços em branco das pontas — sem ele,
        // alguém poderia cadastrar um nome só com espaços (" ") e passaria.
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do cliente é obrigatório!");
        }
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new RegraNegocioException("O CPF do cliente é obrigatório!");
        }

        // Percore todos os clientes já cadastrados no banco e comparo o CPF.
        // Se encontrar um CPF igual, tenho que checar se não é o MESMO cliente
        // sendo atualizado (senão ele bloquearia a si mesmo ao editar).
        for (Cliente existente : repositorio.listar()) {
            if (existente.getCpf().equals(cliente.getCpf())) {
                
                // Se o cliente sendo salvo não tem ID (é novo) OU tem um ID
                // diferente do existente, então é outra pessoa tentando usar o mesmo CPF!
                if (cliente.getId() == null || !existente.getId().equals(cliente.getId())) {
                    throw new RegraNegocioException("Já existe um cliente com o CPF " + cliente.getCpf() + " cadastrado!");
                }
            }
        }

        // Se o código chegou aqui, é porque passou por TODOS os ifs sem
        // cair em nenhum "throw". Aí sim é seguro mandar salvar no banco!
        return repositorio.salvar(cliente);
    }

    // Esses métodos abaixo são simples, só repassam a chamada pro repositório.
    // Parecem inúteis, mas existem por um motivo: o Controller só fala com o Service, nunca direto com o Repository.
    public List<Cliente> listar() {
        return repositorio.listar();
    }

    public Cliente buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }

    public void remover(Long id) {
        repositorio.remover(id);
    }
}
