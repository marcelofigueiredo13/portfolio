import java.time.LocalDate;

public class Entrega {
    private int numero;
    private String link_feedback;
    private LocalDate data_entrega;
    private Encomenda encomenda;
    private Transportadora transportadora;

    public Entrega(Encomenda encomenda, Transportadora transportadora) {
        this.encomenda = encomenda;
        this.transportadora = transportadora;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLink_feedback() {
        return link_feedback;
    }

    public void setLink_feedback(String link_feedback) {
        this.link_feedback = link_feedback;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public void MostrarEntrega(){
        System.out.println("\nNúmero da Entrega: " + numero + "\nData da Entrega: " + data_entrega + "\nLink Feedback: " + link_feedback);
        System.out.println("Encomenda nº" + encomenda.getNumero() + " entregue por " + transportadora.getNome() + " a " + encomenda.getUtilizador().getNome());
    }
}
