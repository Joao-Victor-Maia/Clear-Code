import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemService {
    // Representa uma ordem de servico (OS) com servicos e pecas
    private Long id;
    private Long veiculoId;
    private Long mecanicoId;
    private String status;
    private Date dataAbertura;
    private List<Servico> servicos = new ArrayList<>();
    private List<Peca> pecas = new ArrayList<>();

    public OrdemService() {
    }

    public OrdemService(Long id, Long veiculoId, Long mecanicoId, String status) {
        // guarda os dados da OS e coloca a data de agora
        this.id = id;
        this.veiculoId = veiculoId;
        this.mecanicoId = mecanicoId;
        this.status = status;
        this.dataAbertura = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

        // for: soma o valor de todos os servicos
        for (int i = 0; i < servicos.size(); i++) {
            total += servicos.get(i).getValor();
        }

        // for: soma o preco de todas as pecas
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
