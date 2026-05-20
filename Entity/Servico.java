public class Servico extends EntidadeBase {
    // Dados básicos de um serviço feito no veículo
    private String descricao;
    private double valor;
    private Long veiculoId;

    public Servico() {
        super();
    }

    public Servico(Long id, String descricao, double valor, Long veiculoId) {
        super(id); // guarda os valores recebidos
        this.descricao = descricao;
        this.valor = valor;
        this.veiculoId = veiculoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Servico [ID=" + id + ", Descricao=" + descricao + ", Valor=R$ " + valor + ", Veiculo ID=" + veiculoId
                + "]";
    }
}
