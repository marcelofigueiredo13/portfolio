import java.time.LocalDate;
import java.util.ArrayList;

public class Utilizador {
    private int numero;
    private String username;
    private String password;
    private String nome;
    private int telefone;
    private String morada;
    private String email;
    private int encomendas_recebidas;

    public Utilizador(String username, String password, String nome, int telefone, String morada, String email) {
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getEncomendas_recebidas() {
        return encomendas_recebidas;
    }

    public void setEncomendas_recebidas(int encomendas_recebidas) {
        this.encomendas_recebidas = encomendas_recebidas;
    }

    public void MostrarUtilizador(){
        System.out.println("\nNúmero de Utilizador: " + numero + "\nUsername: " + username + "\nPalavra-passe: " + password + "\nNome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nMorada: " + morada);
    }

    public void SubmeterEncomenda(ArrayList<Livros_Encomenda> le, Voucher voucher){
        if(voucher == null){
            Encomenda enc = new Encomenda((Utilizador) Repositorio.devolveUtilizador(numero), voucher, LocalDate.now(), le);
            enc.setNumero(Repositorio.devolveNumeroUltimaEncomenda() + 1);
            enc.setEstado("submetida");
            enc.setVoucher(voucher);
            enc.setData_encomenda(LocalDate.now());
            String aux = "Ablazon.pt/Tracking/Encomenda" + enc.getNumero();
            enc.setLink_acompanhamento(aux);
            Repositorio.adicionaEncomenda(enc);
            //enc.MostrarEncomenda();
            System.out.println("Utilizador " + enc.getUtilizador().getUsername() + " submeteu a encomenda nº " + enc.getNumero() + " com sucesso!");
        }
        else{
            Encomenda enc = new Encomenda((Utilizador) Repositorio.devolveUtilizador(numero), voucher, LocalDate.now(), le);
            enc.setNumero(Repositorio.devolveNumeroUltimaEncomenda() + 1);
            enc.setEstado("submetida");
            enc.setVoucher(voucher);
            enc.setData_encomenda(LocalDate.now());
            String aux = "Ablazon.pt/Tracking/Encomenda" + enc.getNumero();
            enc.setLink_acompanhamento(aux);
            Repositorio.adicionaEncomenda(enc);
            //enc.MostrarEncomenda();
            System.out.println("Utilizador " + enc.getUtilizador().getUsername() + " submeteu a encomenda nº " + enc.getNumero() + " com sucesso!");
            System.out.println("\nDesconto: " + voucher.getDesconto()*100 + "% válido até " + voucher.getValidade());
        }
    }

    public void ReceberEncomenda(Encomenda encomenda)
    {
        if(encomenda.getEstado() != "entregue")
        {
            System.out.println("\nA tranportadora não registou a entrega desta encomenda!");
        }
        else{
            if((Utilizador) Repositorio.devolveUtilizador(numero) == encomenda.getUtilizador())
            {
                encomenda.getUtilizador().setEncomendas_recebidas(encomenda.getUtilizador().getEncomendas_recebidas() + 1);
                System.out.println("\nO utilizador " + encomenda.getUtilizador().getUsername() + " recebeu a encomenda nº " + encomenda.getNumero());
                Entrega ent = Repositorio.devolveEntrega(encomenda);
                System.out.println("\nTambém avaliou o serviço em " + ent.getLink_feedback());
            }
            else
                System.out.println("\nNão foi o utilizador " + Repositorio.devolveUtilizador(numero).getUsername()  + " que submeteu esta encomenda!");
        }
    }

}
