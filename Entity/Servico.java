public class Servico {
    private Long id;
    private String descricao;
    private double valor;
    private Long veiculoId;

    public Servico() {}
    
    public Servico(long id, String descricao, double valor, Long veiculoId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.veiculoId = veiculoId;
    }

    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getdescricao() {return descricao;}
    public void setdescricao(String descricao) {this.descricao = descricao;}
    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}
    public Long getVeiculoId() {return veiculoId;}
    public void setVeiculoId(Long veiculoId) {this.veiculoId = veiculoId;}

    @Override
    public String toString() {
        return "Peca [ID=" + id + ", descricao=" + descricao + ", Valor=R$ " + valor + ", Veículo ID=" + veiculoId + "]";
    }
}