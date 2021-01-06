import java.time.LocalDate;

public class Transportadora {
    private int numero;
    private String nome;
    private int telefone;
    private String morada;
    private String email;

    public Transportadora(String nome, int telefone, String morada, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.morada = morada;
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void MostrarTransportadora(){
        System.out.println("\nNúmero da Transportadora: " + numero + "\nNome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nMorada:" + morada);
    }

    public void RegistarEntrega(int n_enc, Transportadora tp) {
        LocalDate d_entrega = LocalDate.now();

        Encomenda enc = Repositorio.devolveEncomenda(n_enc);

        if (enc.getEstado() == "enviada") {
            enc.setEstado("entregue");
            enc.setData_entrega(d_entrega);

            Entrega ent = new Entrega(enc, tp);
            ent.setNumero(Repositorio.devolveNumeroUltimaEntrega() + 1);
            String aux = "Ablazon.pt/QuestionarioFeedback/Encomenda" + enc.getNumero();
            ent.setLink_feedback(aux);
            ent.setData_entrega(d_entrega);
            Repositorio.adicionaEntrega(ent);
            ent.MostrarEntrega();
        }
        else
            System.out.println("\nNão foi possível enviar a encomenda!");
    }
}
