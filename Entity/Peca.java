public class Peca {
    // Dados basicos de uma peca
    private Long id;
    private String nome;
    private double preco;

    public Peca() {
    }

    public Peca(Long id, String nome, double preco) {
        // guarda os valores recebidos
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Peca [ID=" + id + ", Nome=" + nome + ", Preço=R$ " + preco + "]";
    }
}
