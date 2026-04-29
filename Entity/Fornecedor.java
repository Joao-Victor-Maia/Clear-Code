public class Fornecedor {
    // Dados basicos do fornecedor
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String cnpj, String telefone, String email) {
        // guarda os valores recebidos
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
