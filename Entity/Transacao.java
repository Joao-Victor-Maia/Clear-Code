import java.util.Date;

public class Transacao {
    private Long id;
    private Long osId;
    private double valor;
    private Date data;
    private String formaPagamento;

    public Transacao() {}

    public Transacao(Long id, Long osId, double valor, String formaPagamento) {
        this.id = id;
        this.osId = osId;
        this.valor = valor;
        this.data = new Date();
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public Long getosId() {return osId;}
    public void setosId(Long osId) {this.osId = osId;}
    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}
    public Date getData() {return data;}
    public String getFormaPagamento() {return formaPagamento;}
    public void setFormaPagamento(String formaPagamento) {this.formaPagamento = formaPagamento;}

    @Override
    public String toString() {
        return "Transação [ID=" + id + ", OS=" + osId + ", Valor=R$ " + valor + ", Forma=" +formaPagamento + ", Data=" + data + "]";
    }
}
