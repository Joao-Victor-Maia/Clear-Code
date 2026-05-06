public class Mecanico extends EntidadeBase {
    //Dados basicos do mecanico
    private String nome;
    private String especialidade;

    public Mecanico() {
        super();
    }

    public Mecanico(Long id, String nome, String especialidade) { 
        //guarda os valores recebidos
        super(id);
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Mecanico [ID=" + id + ", Nome=" + nome + ", Especialidade=" + especialidade + "]";
    }
}
