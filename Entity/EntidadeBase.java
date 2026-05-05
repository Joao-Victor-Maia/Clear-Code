public abstract class EntidadeBase {

    protected Long id;

    // Construtor vazio necessario para o RepositoryGenerico
    public EntidadeBase() {
    }

    public EntidadeBase(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}