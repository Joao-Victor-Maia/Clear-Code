import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// O uso de Generics <T> permite que esta classe sirva para qualquer Entidade
public abstract class RepositoryGenerico<T extends EntidadeBase> {

    // Alta Performance: Substituindo List (O(N)) por Map (O(1))
    // O LinkedHashMap preserva a ordem que os itens foram inseridos
    protected final Map<Long, T> dados = new LinkedHashMap<>();
    protected long proximoId = 1;

    public T salvar(T entidade) {
        if (entidade == null) {
            return null;
        }

        // Se for novo (id null), gera e seta o novo ID
        if (entidade.getId() == null) {
            entidade.setId(proximoId);
            proximoId++;
        }

        // Adiciona ou atualiza no Map (Performance O(1))
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    public List<T> listar() {
        // Retorna todos os valores do Map convertidos para uma Lista
        return new ArrayList<>(dados.values());
    }

    public T buscarPorId(Long id) {
        if (id == null) {
            return null;
        }
        // Busca instantânea no Map (Performance O(1))
        return dados.get(id);
    }

    public boolean remover(Long id) {
        if (id == null) {
            return false;
        }
        // Remoção instantânea no Map (Performance O(1))
        return dados.remove(id) != null;
    }
}
