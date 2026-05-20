import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemService extends EntidadeBase {
    // Representa uma ordem de serviço (OS) com serviços e peças
    private Long veiculoId;
    private Long mecanicoId;
    private String status;
    private Date dataAbertura;
    private List<Servico> servicos = new ArrayList<>();
    private List<Peca> pecas = new ArrayList<>();

    public OrdemService() {
        super();
    }

    public OrdemService(Long id, Long veiculoId, Long mecanicoId, String status) {
        super(id); // guarda o ID da OS e anota a data de abertura
        this.veiculoId = veiculoId;
        this.mecanicoId = mecanicoId;
        this.status = status;
        this.dataAbertura = new Date();
    }

    // Os métodos getId() e setId() foram removidos pois agora são herdados de EntidadeBase

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getMecanicoId() {
        return mecanicoId;
    }

    public void setMecanicoId(Long mecanicoId) {
        this.mecanicoId = mecanicoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public double getValorTotal() {
        double total = 0;

        // Soma o valor de todos os serviços
        for (int i = 0; i < servicos.size(); i++) {
            total += servicos.get(i).getValor();
        }

        // Soma o preço de todas as peças
        for (int i = 0; i < pecas.size(); i++) {
            total += pecas.get(i).getPreco();
        }

        return total;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "OrdemService [ID=" + id + ", Veiculo ID=" + veiculoId + ", Mecanico ID=" + mecanicoId + ", Status="
                + status + ", Data=" + dataAbertura + ", Total=R$ " + getValorTotal() + "]";
    }
}
