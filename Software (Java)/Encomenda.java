import java.time.LocalDate;
import java.util.ArrayList;

public class Encomenda {
    private int numero;
    private static ArrayList<Livros_Encomenda> le;
    private Utilizador utilizador;
    private Voucher voucher;
    private LocalDate data_encomenda;
    private LocalDate data_prevista;
    private LocalDate data_entrega;
    private String estado;
    private String link_acompanhamento;

    public Encomenda(Utilizador utilizador, Voucher voucher, LocalDate data_encomenda, ArrayList<Livros_Encomenda> le) {
        this.utilizador = utilizador;
        this.voucher = voucher;
        this.data_encomenda = data_encomenda;
        this.le = le;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public static ArrayList<Livros_Encomenda> getLe() {
        return le;
    }

    public static void setLe(ArrayList<Livros_Encomenda> le) {
        Encomenda.le = le;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public LocalDate getData_encomenda() {
        return data_encomenda;
    }

    public void setData_encomenda(LocalDate data_encomenda) {
        this.data_encomenda = data_encomenda;
    }

    public LocalDate getData_prevista() {
        return data_prevista;
    }

    public void setData_prevista(LocalDate data_prevista) {
        this.data_prevista = data_prevista;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLink_acompanhamento() {
        return link_acompanhamento;
    }

    public void setLink_acompanhamento(String link_acompanhamento) {
        this.link_acompanhamento = link_acompanhamento;
    }

    public void MostrarEncomenda(){
        if(voucher == null){
            if(estado == null){
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda);
                System.out.println("A encomenda não foi submetida ou ainda não foi processada!\n");
            }
            if(estado == "submetida") {
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi submetida!\n");
            }
            if(estado == "processada") {
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi processada!\n");
            }
            if(estado == "enviada"){
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi enviada!");
            }
            if(estado == "entregue"){
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi entregue!\nData Entrega: " + data_entrega);
            }
            for (int i = 0; i < le.size(); i++) {
                le.get(i).MostrarLivros_Encomenda();
            }
        }
        else{
            if(estado == null){
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda);
                System.out.println("A encomenda não foi submetida ou ainda não foi processada!\n");
            }
            if(estado == "submetida") {
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi submetida!\n");
            }
            if(estado == "processada") {
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi processada!\n");
            }
            if(estado == "enviada"){
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi enviada!");
            }
            if(estado == "entregue"){
                System.out.println("\nNúmero da Encomenda: " + numero + "\nUtilizador: " + utilizador.getNome() + "\nData da encomenda: " + data_encomenda + "\nData prevista: " + data_prevista + "\nLink acompanhamento: " + link_acompanhamento);
                System.out.println("A encomenda já foi entregue!\nData Entrega: " + data_entrega);
            }
            for (int i = 0; i < le.size(); i++) {
                le.get(i).MostrarLivros_Encomenda();
            }
            System.out.println("\nDesconto: " + voucher.getDesconto()*100 + "% válido até " + voucher.getValidade());
        }
    }
}