public class Veiculo {
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private Long clienteId;

    public Veiculo() {}

    public Veiculo(Long id, String placa, String modelo, String marca, Long clienteId) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.clienteId = clienteId;
    }

    public Long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}
    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    public Long getClienteId() {return clienteId;}
    public void setClienteId(long clienteId) {this.clienteId = clienteId;}

    @Override
    public String toString() {
        return "Cliente [ID=" + id + ", Placa=" + placa + ", Modelo=" + modelo + ", Marca=" + marca + ", Cliente ID=" + clienteId + "]";
    }
}