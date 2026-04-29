public class Veiculo {
    // Dados basicos do veiculo e o dono (clienteId)
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private Long clienteId;

    public Veiculo() {
    }

    public Veiculo(Long id, String placa, String modelo, String marca, Long clienteId) {
        // guarda os valores recebidos
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        // monta uma string simples pra mostrar o objeto
        return "Veiculo [ID=" + id + ", Placa=" + placa + ", Modelo=" + modelo + ", Marca=" + marca + ", Cliente ID="
                + clienteId + "]";
    }
}
