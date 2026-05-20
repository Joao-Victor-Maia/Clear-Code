// O "abstract" aqui significa que ninguém pode criar um objeto EntidadeBase diretamente,
// ela existe só pra ser herdada — tipo um molde que não serve sozinho.
public abstract class EntidadeBase {

    // Usei "protected" em vez de "private" porque as classes filhas
    // precisam enxergar esse campo. Com "private" elas não conseguiriam acessar.
    protected Long id;

    // Precisei desse construtor vazio porque o Java exige quando a classe
    // tem também um construtor com parâmetro. Sem ele, algumas coisas quebram.
    public EntidadeBase() {
    }

    // Esse construtor permite criar uma entidade já com ID definido
    public EntidadeBase(Long id) {
        this.id = id;
    }

    // Getter: só lê o ID sem deixar ninguém mexer diretamente na variável
    public Long getId() {
        return id;
    }

    // Setter: a única forma "oficial" de alterar o ID
    public void setId(Long id) {
        this.id = id;
    }
}