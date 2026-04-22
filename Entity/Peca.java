public class Peca {
    private Long id;
    private String nome;
    private double preco;

    public Peca() {}
    
    public Peca(long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public double getPreco() {return preco;}
    public void setPreco(double preco) {this.preco = preco;}

    @Override
    public String toString() {
        return "Peca [ID=" + id + ", Nome=" + nome + ", Preço=R$ " + preco + "]";
    }
}
