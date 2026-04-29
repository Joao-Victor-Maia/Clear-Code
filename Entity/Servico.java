public class Servico {
    // Dados basicos de um servico feito no veiculo
    private Long id;
    private String descricao;
    private double valor;
    private Long veiculoId;

    public Servico() {
    }

    public Servico(Long id, String descricao, double valor, Long veiculoId) {
        // guarda os valores recebidos
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.veiculoId = veiculoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
