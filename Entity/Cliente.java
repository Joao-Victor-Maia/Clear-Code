public class Cliente extends EntidadeBase {
        //Dados basicos do cliente
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente() {        
        super();
    }

    public Cliente(Long id, String nome, String cpf, String telefone) {
        super(id); //guarda os valores recebidos
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Cliente [ID=" + id + ", Nome=" + nome + ", CPF=" + cpf + ", Tel=" + telefone + "]";
    }
}
