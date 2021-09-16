import java.time.LocalDate;
import java.util.ArrayList;


public class main {

    public static void main(String args[])
    {

        Repositorio Ablazon = new Repositorio();
        Livro l1 = new Livro(123456, "Harry Potter", "J.K.Rowling", "HP Publisher", 1999);
        Repositorio.adicionaLivro(l1);
        Livro l2 = new Livro(234567, "1984", "George Orwell", "Antigona", 1930);
        Repositorio.adicionaLivro(l2);
        Ablazon.save(l1);
        Livros_Encomenda le1 = new Livros_Encomenda(l1, 2);
        Livros_Encomenda le2 = new Livros_Encomenda(l2, 1);
        Livros_Encomenda le3 = new Livros_Encomenda(l1, 3);
        Livros_Encomenda le4 = new Livros_Encomenda(l2, 2);
        Utilizador ut1 = new Utilizador("SF99", "abc123", "Simão Figueiredo", 123456789, "Rua do Volta Atrás, Portugal", "sf99@email.pt");
        Repositorio.adicionaUtilizador(ut1);
        Utilizador ut2 = new Utilizador("MB123", "abc123", "Marcelo Batista", 111111111, "Avenida Grande, Portugal", "mb99@email.pt");
        Repositorio.adicionaUtilizador(ut2);
        Ablazon.save(ut1);
        Funcionario fun1 = new Funcionario("def456", "João Batista", 234567890, "Avenida Reta, Portugal", "jbatista@ablazon.pt");
        Repositorio.adicionaFuncionario(fun1);
        Ablazon.save(fun1);
        LocalDate validade = LocalDate.now().plusMonths(3L);
        Voucher v1 = new Voucher("LFV", 0.2D, validade);
        Repositorio.adicionaVoucher(v1);
        Voucher v2 = new Voucher("PDC", 0.35D, validade);
        Repositorio.adicionaVoucher(v2);
        Ablazon.save(v1);
        ArrayList<Livros_Encomenda> Lista_Encomenda1 = new ArrayList();
        Lista_Encomenda1.add(le1);
        Lista_Encomenda1.add(le2);
        ArrayList<Livros_Encomenda> Lista_Encomenda2 = new ArrayList();
        Lista_Encomenda2.add(le3);
        Lista_Encomenda2.add(le4);
        LocalDate d_encomenda = LocalDate.now();
        Encomenda enc1 = new Encomenda(ut1, v1, d_encomenda, Lista_Encomenda1);
        Repositorio.adicionaEncomenda(enc1);
        Ablazon.save(enc1);
        Transportadora tp1 = new Transportadora("DHL", 345678901, "Rua da DHL, Europa", "dhl@transportes.pt");
        Repositorio.adicionaTransportadora(tp1);
        Ablazon.save(tp1);
        Entrega ent1 = new Entrega(enc1, tp1);
        Ablazon.save(ent1);
        fun1.EnviarEncomenda(1);
        tp1.RegistarEntrega(1, tp1);
        ut1.ReceberEncomenda(enc1);
        ut2.SubmeterEncomenda(Lista_Encomenda2, v2);
        fun1.ProcessarEncomenda(2);
        tp1.RegistarEntrega(2, tp1);
        ut2.ReceberEncomenda(Repositorio.devolveEncomenda(2));
        System.out.println(Repositorio.get(0));
        System.out.println(Repositorio.get(1));
        System.out.println(Repositorio.get(2));
    }
}