import exceptions.*;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main {
    public static void  main(String args[]) throws InvalidBookNameException, InvalidBookFormatException, InvalidBookSizeException, InvalidBookHashException, EbookCopyNotFoundException, InvalidUserAgeException, InvalidUserNameException, InvalidUserPasswordException, InvalidLoanDataException, UserAccountIsCanceledException, InvalidTermPublisherException, NoLoanForThisBookException, LoanExpiredException, LoanExtensionLimitReachedException, InvalidBookAuthorException, InvalidBookPublisherException, InvalidServerReplicaLocationException, InvalidTermDataException, MalformedURLException, InvalidBookIsbnException, InvalidEbookException {
        Ebook e1 = new Ebook(12345, "wada.epub", "Porto Editora", "Fernando Pessoa", "A1 B2 C3 D4 E5", 12);
        Ebook e2 = new Ebook(12345, "abc.epub", "Porto Editora", "Fernando Pessoa", "A1 B2 C3 D4 E5", 12);
        EbookCopy ec1 = new EbookCopy(e1);
        EbookCopy ec2 = new EbookCopy(e1);
        EbookCopy ec3 = new EbookCopy(e2);

        ArrayList<EbookCopy> ebookCopies = new ArrayList<>();
        ebookCopies.add(ec1);
        ebookCopies.add(ec2);

        ServerReplica sr1 = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica sr2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica sr3 = new ServerReplica("Porto", 41.14961, -8.61099);

        User user = new User(40.633626, -8.004954, "Marcelo", "123", 22);

        sr1.addEbookCopy(ec3);
        sr2.addMultipleEbookCopies(ebookCopies);
        //System.out.println(sr1.getEbookCopy("wada.epub"));
        //System.out.println(sr1.getEbookCopy("wada.pdf"));
        //System.out.println(sr2.getEbookCopy("aaa"));

        Library server = new Library();
        server.addUser(user);
        server.addServerReplica(sr1);
        server.addServerReplica(sr3);
        server.addServerReplica(sr2);
        System.out.println(server.requestLoan("Marcelo", "wada.epub"));

        Term t1 = new Term("Porto Editora", "Não partilhar livro");
        server.addTerm(t1);
        System.out.println(server.signTerm("Marcelo", "Porto Editora"));

        System.out.println(server.visualizeEbook("Marcelo", "wada.epub"));

        System.out.println(server.extendLoan("Marcelo", "wada.epub"));
        System.out.println(server.extendLoan("Marcelo", "wada.epub"));
        //System.out.println(server.extendLoan("Marcelo", "wada.epub"));

        System.out.println(server.downloadEbook("Marcelo", "wada.epub"));
        System.out.println(server.visualizeEbook("Marcelo", "Ebook"));
    }
}
