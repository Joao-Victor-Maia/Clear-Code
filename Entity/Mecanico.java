public class Mecanico {
    private Long id;
    private String nome;
    private String especialidade;

    public Mecanico() {}

    public Mecanico(Long id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getEspecialidade() {return especialidade;}
    public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}

    @Override
    public String toString() {
        return "Mecanico [ID=" + id + ", Nome=" + nome + ", Especialidade=" + especialidade + "]";
    }
}
    
