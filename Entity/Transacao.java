import java.util.Date;

public class Transacao extends EntidadeBase {
    // Essa classe representa um pagamento ligado a uma OS.
    // Ela guarda qual OS foi paga, o valor, a data e como foi pago.
    private Long osId;
    private double valor;
    private Date data;
    private String formaPagamento;

    public Transacao() {
        super();
    }

    public Transacao(Long id, Long osId, double valor, String formaPagamento) {
        super(id); // guarda o ID e permite que o construtor crie o objeto com data
        this.osId = osId;
        this.valor = valor;
        this.data = new Date();
        this.formaPagamento = formaPagamento;
    }

    public Long getOsId() {
        return osId;
    }

    public void setOsId(Long osId) {
        this.osId = osId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        // Monta uma string simples para mostrar a transação
        return "Transacao [ID=" + id + ", OS=" + osId + ", Valor=R$ " + valor + ", Forma=" + formaPagamento
                + ", Data=" + data + "]";
    }
}
