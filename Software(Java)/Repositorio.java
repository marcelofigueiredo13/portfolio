import java.time.LocalDate;
import java.util.ArrayList;

public class Repositorio {
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Utilizador> utilizadores = new ArrayList();
    private static ArrayList<Funcionario> funcionarios = new ArrayList();
    private static ArrayList<Voucher> vouchers = new ArrayList<>();
    private static ArrayList<Encomenda> encomendas = new ArrayList();
    private static ArrayList<Transportadora> transportadoras = new ArrayList();
    private static ArrayList<Entrega> entregas = new ArrayList();
    private static ArrayList<Object> objetos = new ArrayList<>();

    public Repositorio(){

    }

    public static void adicionaLivro(Livro livro){
        if (LivroRepetido(livro) == false){
            livros.add(livro);
            System.out.println("Livro Adicionado Com Sucesso!");
        }
        else
            System.out.println("Livro Repetido!");
    }

    public static void adicionaUtilizador(Utilizador utilizador) {
        if(utilizadores.size() == 0)
            utilizador.setNumero(1);
        else
            utilizador.setNumero(utilizadores.get(utilizadores.size()-1).getNumero() + 1);

        if(UtilizadorRepetido(utilizador) == false)
        {
            utilizadores.add(utilizador);
            System.out.println("Utilizador Adicionado Com Sucesso!");
        }
        else
            System.out.println("Utilizador Repetido!");
    }

    public static void adicionaFuncionario(Funcionario funcionario){
        if(funcionarios.size() == 0)
            funcionario.setNumero(1);
        else
            funcionario.setNumero(funcionarios.get(funcionarios.size()-1).getNumero() + 1);

        if(FuncionarioRepetido(funcionario) == false){
            funcionarios.add(funcionario);
            System.out.println("Funcionário Adicionado Com Sucesso!");
        }
        else
            System.out.println("Funcionário Repetido!");
    }

    public static void adicionaVoucher(Voucher voucher){
        if(ValidadeVoucher(voucher) == true && VoucherRepetido(voucher) == false){
            vouchers.add(voucher);
            System.out.println("Voucher Adicionado Com Sucesso!");
        }
        else
            System.out.println("Erro Ao Adicionar Voucher!");
    }

    public static Boolean adicionaEncomenda(Encomenda encomenda){
        if(encomendas.size() == 0)
            encomenda.setNumero(1);
        else
            encomenda.setNumero(encomendas.get(encomendas.size()-1).getNumero() + 1);

        if(encomenda.getEstado() != "entregue")
        {
            encomenda.setEstado("submetida");
            encomenda.setData_prevista(encomenda.getData_encomenda().plusMonths(1));
        }

        if(EncomendaValida(encomenda) == true && EncomendaRepetida(encomenda)== false) {
            encomendas.add(encomenda);
            String aux;
            aux = "Ablazon.pt/Tracking/Encomenda" + encomenda.getNumero();
            System.out.println("Encomenda Adicionada Com Sucesso!");
            encomenda.setLink_acompanhamento(aux);
            return true;
        }
        else
        {
            System.out.println("Erro Ao Adicionar Encomenda!");
            return false;
        }
    }

    public static int devolveNumeroUltimaEncomenda()
    {
        if(encomendas.size() == 0)
            return 1;
        else
            return encomendas.get(encomendas.size() - 1).getNumero();
    }

    public static int devolveNumeroUltimaEntrega()
    {
        if(entregas.size() == 0)
            return 1;
        else
            return entregas.get(entregas.size() - 1).getNumero();
    }

    public static void adicionaTransportadora(Transportadora transportadora) {
        if(transportadoras.size() == 0)
            transportadora.setNumero(1);
        else
            transportadora.setNumero(transportadoras.get(transportadoras.size()-1).getNumero() + 1);

        if(TransportadoraRepetida(transportadora) == false) {
            transportadoras.add(transportadora);
            System.out.println("Transportadora Adicionada Com Sucesso!");
        }
        else
            System.out.println("Transportadora Repetida!");
    }

    public static void adicionaEntrega(Entrega entrega) {
        if(entregas.size() == 0)
            entrega.setNumero(1);
        else
            entrega.setNumero(entregas.get(entregas.size()-1).getNumero() + 1);

        if(EntregaRepetida(entrega) == false) {
            entregas.add(entrega);
            System.out.println("Entrega Adicionada Com Sucesso!");
        }
        else
            System.out.println("Entrega Repetida!");
    }

    public static Utilizador devolveUtilizador(int n_util) {
        for(int i = 0; i < utilizadores.size(); ++i) {
            Utilizador ut = utilizadores.get(i);
            if (ut.getNumero() == n_util) {
                return ut;
            }
        }
        return null;
    }

    public static Livro devolveLivro(int isbn) {
        for(int i = 0; i < livros.size(); ++i) {
            Livro l = livros.get(i);
            if (l.getIsbn() == isbn) {
                return l;
            }
        }
        return null;
    }

    public static Funcionario devolveFuncionario(int n_func) {
        for(int i = 0; i < funcionarios.size(); ++i) {
            Funcionario f = funcionarios.get(i);
            if (f.getNumero() == n_func) {
                return f;
            }
        }
        return null;
    }

    public static Voucher devolveVoucher(String codigo) {
        for(int i = 0; i < vouchers.size(); ++i) {
            Voucher v = vouchers.get(i);
            if (v.getCodigo() == codigo) {
                return v;
            }
        }
        return null;
    }

    public static Encomenda devolveEncomenda(int n_enc) {
        for(int i = 0; i < encomendas.size(); ++i) {
            Encomenda e = encomendas.get(i);
            if (e.getNumero() == n_enc) {
                return e;
            }
        }
        return null;
    }

    public static Transportadora devolveTransportadora(int n_trans) {
        for(int i = 0; i < transportadoras.size(); ++i) {
            Transportadora tp = transportadoras.get(i);
            if (tp.getNumero() == n_trans) {
                return tp;
            }
        }
        return null;
    }

    public static Entrega devolveEntrega(Encomenda enc) {
        for(int i = 0; i < entregas.size(); ++i) {
            Entrega ent = entregas.get(i);
            if (ent.getEncomenda()== enc) {
                return ent;
            }
        }
        return null;
    }

    private static Boolean UtilizadorRepetido(Utilizador utilizador){
        for(int i = 0; i < utilizadores.size(); i++) {
            Utilizador ut = utilizadores.get(i);
            if (ut.getNumero() == utilizador.getNumero() || ut.getUsername() == utilizador.getUsername() || ut.getTelefone() == utilizador.getTelefone() || ut.getEmail() == utilizador.getEmail()) {
                return true;
            }
        }
        return false;
    }

    private static Boolean LivroRepetido(Livro livro){
        for(int i = 0; i < livros.size(); i++) {
            Livro liv = livros.get(i);
            if (liv.getIsbn() == livro.getIsbn() || (liv.getTitulo() == livro.getTitulo() && liv.getAutor() == livro.getAutor() && liv.getAno_publicacao() == livro.getAno_publicacao() && liv.getEditora() == livro.getEditora())) {
                return true;
            }
        }
        return false;
    }

    private static Boolean FuncionarioRepetido(Funcionario funcionario){
        for(int i = 0; i < funcionarios.size(); i++) {
            Funcionario func = funcionarios.get(i);
            if (func.getNumero() == funcionario.getNumero() || func.getTelefone() == funcionario.getTelefone() || func.getEmail() == funcionario.getEmail()) {
                return true;
            }
        }
        return false;
    }

    private static Boolean ValidadeVoucher(Voucher voucher){
        if(voucher.getValidade().compareTo(LocalDate.now()) >= 0){
            return true;
        }
        return false;
    }

    private static Boolean VoucherRepetido(Voucher voucher){
        for(int i = 0; i < vouchers.size(); i++) {
            Voucher vou = vouchers.get(i);
            if (vou.getCodigo() == voucher.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    private static Boolean EncomendaValida(Encomenda encomenda){
        if(encomenda.getNumero() == 0 || encomenda.getUtilizador() == null || encomenda.getData_encomenda() == null)
            return false;
        else
            return true;
    }

    private static Boolean EncomendaRepetida(Encomenda encomenda){
        for(int i = 0; i < encomendas.size(); i++) {
            Encomenda enc = encomendas.get(i);
            if (enc.getNumero() == encomenda.getNumero() || enc.getLink_acompanhamento() == encomenda.getLink_acompanhamento()) {
                return true;
            }
        }
        return false;
    }

    private static Boolean TransportadoraRepetida(Transportadora transportadora){
        for(int i = 0; i < transportadoras.size(); i++) {
            Transportadora trans = transportadoras.get(i);
            if (trans.getNumero() == transportadora.getNumero() || trans.getTelefone() == transportadora.getTelefone() || trans.getEmail() == transportadora.getEmail()) {
                return true;
            }
        }
        return false;
    }

    private static Boolean EntregaRepetida(Entrega entrega){
        for(int i = 0; i < entregas.size(); i++) {
            Entrega ent = entregas.get(i);
            if (ent.getNumero() == entrega.getNumero() || ent.getLink_feedback() == entrega.getLink_feedback()) {
                return true;
            }
        }
        return false;
    }

    public void adicionaObjeto(Object objeto){this.objetos.add(objeto);}

    public void save(Object object){
        if(object instanceof Utilizador){
            Utilizador utilizador = (Utilizador) object;
            adicionaObjeto(utilizador);
            System.out.println("Utilizador Adicionado!");
        }

        if(object instanceof Funcionario){
            Funcionario funcionario = (Funcionario) object;
            adicionaObjeto(funcionario);
            System.out.println("Funcionário Adicionado!");
        }

        if(object instanceof Entrega){
            Entrega entrega = (Entrega) object;
            adicionaObjeto(entrega);
            System.out.println("Entrega Adicionada!");
        }

        if(object instanceof Transportadora){
            Transportadora transportadora = (Transportadora) object;
            adicionaObjeto(transportadora);
            System.out.println("Transportadora Adicionada!");
        }

        if(object instanceof Encomenda){
            Encomenda encomenda = (Encomenda) object;
            adicionaObjeto(encomenda);
            System.out.println("Encomenda Adicionada!");
        }

        if(object instanceof Voucher){
            Voucher voucher = (Voucher) object;
            adicionaObjeto(voucher);
            System.out.println("Voucher Adicionado!");
        }

        if(object instanceof Livro){
            Livro livro = (Livro) object;
            adicionaObjeto(livro);
            System.out.println("Livro Adicionado!");
        }
    }

    public static Object get(Integer posicao){
        for(int i = 0; i < objetos.size(); ++i) {
            Object obj = objetos.get(i);
            if(posicao == i)
                return  obj;
        }
        return null;
    }

}
