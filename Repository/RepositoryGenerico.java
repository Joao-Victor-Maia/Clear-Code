import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Aqui foi onde a ficha caiu pra mim sobre Genéricos no Java.
// O "<T>" funciona como um "coringa". Quando eu escrever ClienteRepository
// e disser que ele extends RepositoryGenerico<Cliente>, o Java vai entender
// que em todo lugar que tiver "T" aqui dentro, na prática é "Cliente".
// Isso evita ter que criar um repositório do zero pra cada entidade!
public abstract class RepositoryGenerico<T extends EntidadeBase> {

    // Aprendi que o Map é muito mais rápido que uma List pra buscar por ID.
    // Na List, teria que percorrer um por um até achar. No Map,
    // é como um índice de dicionário: você vai direto na palavra sem ler tudo.
    // O LinkedHashMap especificamente mantém a ordem de inserção,
    // então o primeiro cadastrado sempre aparece primeiro na listagem.
    protected final Map<Long, T> dados = new LinkedHashMap<>();

    // Esse contador começa em 1 e vai subindo a cada novo registro.
    // É ele quem garante que cada objeto tenha um ID único e crescente.
    protected long proximoId = 1;

    public T salvar(T entidade) {
        // Se vier nulo, não tem nada pra salvar, retorna nulo mesmo
        if (entidade == null) {
            return null;
        }

        // Se o ID ainda é nulo, significa que é um cadastro novo.
        // Aí gera o próximo ID disponível e já incremento o contador pra próxima vez.
        // O "++" depois do proximoId significa: "usa o valor atual, depois incrementa"
        if (entidade.getId() == null) {
            entidade.setId(proximoId);
            proximoId++;
        }

        // O ".put()" é como uma caixa postal: chave (ID) -> valor (objeto).
        // Se já existir um objeto com aquele ID, ele substitui. Isso serve tanto
        // pra criar quanto pra atualizar — dois em um!
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    public List<T> listar() {
        // O Map guarda só os pares chave-valor, então aqui eu pego só os
        // valores (os objetos em si) e converto pra uma List normal pra facilitar
        return new ArrayList<>(dados.values());
    }

    public T buscarPorId(Long id) {
        // Protejo contra ID nulo pra não gerar erro inesperado
        if (id == null) {
            return null;
        }
        // O ".get(id)" vai direto no objeto sem percorrer nada — isso é O(1)!
        return dados.get(id);
    }

    public boolean remover(Long id) {
        if (id == null) {
            return false;
        }
        // O ".remove()" retorna o objeto removido, ou null se não achou.
        // Então eu verifico se veio algo diferente de nulo pra saber se funcionou.
        return dados.remove(id) != null;
    }
}
