public class Fornecedor extends EntidadeBase{
    //Dados basicos do fornecedor
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;

    public Fornecedor() {
        super();
    }

    public Fornecedor(Long id, String nome, String cnpj, String telefone, String email) {
        super(id);//guarda os valores recebidos
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Fornecedor [ID=" + id + ", Nome=" + nome + ", CNPJ=" + cnpj + ", Tel=" + telefone + ", Email=" + email
                + "]";
    }
}
