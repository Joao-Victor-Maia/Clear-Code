import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemService {
    private Long id;
    private Long veiculoId;
    private Long mecanicoId;
    private String status;
    private Date dataAbertura;
    private List<Servico> servicos = new ArrayList<>();
    private List<Peca> pecas = new ArrayList<>();

    public OrdemService () {}

    public OrdemService(Long id, Long veiculoId, Long mecanicoId, String status) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.mecanicoId = mecanicoId;
        this.status = status;
        this.dataAbertura = new Date();
    }

    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public Long getVeiculoId() {return veiculoId;}
    public void setVeiculoId(Long veiculoId) {this.veiculoId = veiculoId;}
    public Long getMecanicoId() {return mecanicoId;}
    public void setMecanicoId(Long mecanicoId) {this.mecanicoId = mecanicoId;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public Date getDataAbertura() {return dataAbertura;}
    public List<Servico> getServicos() {return servicos;}
    public List<Peca> getPecas() {return pecas;}

    public double getValorTotal() {
        double totalServicos = servicos.stream().mapToDouble(Servico::getValor).sum();
        double totalPecas = pecas.stream().mapToDouble(Peca::getPreco).sum();
        return totalServicos + totalPecas;
    }

    @Override
    public String toString() {
        return "OS [ID=" + id + ", Veículo ID=" + veiculoId + ", Mecânico ID=" + mecanicoId + ", Status=" + status + ", Total= " + getValorTotal();
    }
}
