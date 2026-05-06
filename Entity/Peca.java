public class Peca extends EntidadeBase {
    // Dados basicos de uma peca
    private String nome;
    private double preco;

    public Peca() {
        super();
    }

    public Peca(Long id, String nome, double preco) {
        super(id); // guarda os valores recebidos
        this.nome = nome;
        this.preco = preco;
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
