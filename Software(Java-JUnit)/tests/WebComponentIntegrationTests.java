import exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebComponentIntegrationTests {
    @BeforeAll
    public static void setUpBeforeAllTests(){
    }

    @BeforeEach
    public void setUp(){
    }

    @AfterEach
    public void tearUp(){
    }

    @AfterAll
    public static void tearDownAfterClass() {
        System.setOut(System.out);
        End e = new End();
    }

    /**
     * Requisito 5
     * visualizeBook - WB Cobertura de Decisões (Visualizing Ebook, No Loan and  InvalidUser)
     */
    @Test
    public void testVisualizeValidEbook() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExpiredException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Autho2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook.epub");

        assertEquals("Success: Visualizing Ebook", library.visualizeEbook("User", "Ebook.epub"));
    }

    @Test
    public void testVisualizeEbookWithInexistentLoan() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Autho2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        try{
            library.visualizeEbook("User", "Ebook.pdf");
        } catch (Exception e) {
            assertEquals(NoLoanForThisBookException.class, e.getClass());
        }
    }

    @Test
    public void testVisualizeEbookWithInvalidUser() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Autho2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        try{
            library.visualizeEbook("User1", "Ebook.epub");
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    /**
     * Requisito 6
     * Until Loan Date and User is Active (Not Canceled)
     * visualizeBook - WB Cobertura de Decisões (Success, Account Canceled and Loan Expired)
     */

    @Test
    public void testVisualizeValidEbookValidLoanDateUserActive() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExpiredException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Autho2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook.epub");

        assertEquals("Success: Visualizing Ebook", library.visualizeEbook("User", "Ebook.epub"));
    }

    @Test
    public void testVisualizeEbookUserAccountCanceled() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        user.setActive(false);

        library.addUser(user);

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Autho2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        try{
            library.visualizeEbook("User", "Ebook.epub");
        } catch (Exception e) {
            assertEquals(UserAccountIsCanceledException.class, e.getClass());
        }
    }

    @Test
    public void testVisualizeEbookLoanExpired() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        library.addUser(user);

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Autho2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        Loan loan = new Loan(user, new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        loan.setStartDate(LocalDate.now().minusMonths(1));
        loan.setLimitDate(LocalDate.now().minusMonths(1));

        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(loan);

        library.setLoans(loans);

        try{
            library.visualizeEbook("User", "Ebook.epub");
        } catch (Exception e) {
            assertEquals(LoanExpiredException.class, e.getClass());
        }
    }

}
