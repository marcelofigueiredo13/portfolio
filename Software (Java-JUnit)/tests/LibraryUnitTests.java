import exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryUnitTests {
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

    @Test
    public void testGetServerReplicas() {
        Library library = new Library();

        assertEquals(0, library.getServerReplicas().size());
    }

    @Test
    public void testSetServerReplicas() throws InvalidServerReplicaLocationException {
        Library library = new Library();

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);
        assertEquals(3, library.getServerReplicas().size());
    }

    @Test
    public void testGetUsers(){
        Library library = new Library();

        assertEquals(0, library.getUsers().size());
    }

    @Test
    public void testSetUsers() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        User user2 = new User(39.66101, -6.90971, "User2", "abc123", 53);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        library.setUsers(users);
        assertEquals(2, library.getUsers().size());
    }

    @Test
    public void testGetLoans() {
        Library library = new Library();

        assertEquals(0, library.getLoans().size());
    }

    @Test
    public void testSetLoans() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        User user2 = new User(39.66101, -6.90971, "User2", "abc123", 53);
        EbookCopy ebookCopy2 = new EbookCopy(new Ebook(6483610, "Ebook.epub", "Publisher2", "Author2", "B2 C3 H4 K8", 52));

        Loan loan2 = new Loan(user2, ebookCopy2);

        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(loan);
        loans.add(loan2);

        library.setLoans(loans);
        assertEquals(2, library.getLoans().size());
    }

    @Test
    public void testGetTerms(){
        Library library = new Library();

        assertEquals(0, library.getTerms().size());
    }

    @Test
    public void testSetTerms() throws InvalidTermDataException {
        Library library = new Library();

        Term term = new Term("Publisher", "Responsability Term");

        ArrayList<Term> terms = new ArrayList<>();
        terms.add(term);

        library.setTerms(terms);
        assertEquals(1, library.getTerms().size());
    }

    @Test
    public void testAddServerReplica() throws InvalidServerReplicaLocationException {
        Library library = new Library();

        library.addServerReplica(new ServerReplica("Lisbon", 38.71667, -9.13333));
        assertEquals(1, library.getServerReplicas().size());
    }

    @Test
    public void testAddUser() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));
        library.addUser(new User(38.66101, -5.90971, "User2", "123abc", 34));
        assertEquals(2, library.getUsers().size());
    }

    @Test
    public void testAddTerm() throws InvalidTermDataException {
        Library library = new Library();

      library.addTerm(new Term("Publisher", "Responsability Term"));
      library.addTerm(new Term("Publisher2", "Share Term"));
      library.addTerm(new Term("Publisher3", "Term and Conditions"));
      assertEquals(3, library.getTerms().size());
    }

    /**
     * Requisito 3
     * requestLoan - WB Cobertura de Cobertura de Condições/Decisões (new loan, EbookCopy Not Found, Account Canceled and InvalidUserName)
     * Location - Cópia do Livro e User no Mesmo Lugar | Cópia do Livro e User em Lugares Diferentes | Mesma Cópia do Livro em Lugares Diferentes do Lugar do User
     */

    @Test
    public void testRequestValidLoan() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        assertEquals("New Loan Created", library.requestLoan("User", "Ebook2.pdf"));
    }

    @Test
    public void testRequestLoanInvalidEbookCopy() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        try{
            library.requestLoan("User", "Ebook.txt");
        } catch (Exception e) {
            assertEquals(EbookCopyNotFoundException.class, e.getClass());
        }
    }

    @Test
    public void testRequestLoanInvalidUserName() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        try{
            library.requestLoan("User Dont Exists", "Ebook.epub");
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testRequestLoanUserAccountCanceled() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);

        user.setActive(false);

        library.addUser(user);

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        try{
            library.requestLoan("User", "Ebook2.pdf");
        } catch (Exception e) {
            assertEquals(UserAccountIsCanceledException.class, e.getClass());
        }
    }

    @Test
    /**
     * Cópia do livro e Utilizador Em Viseu
     */
    public void testRequestLoanUserAndEbookCopyInSamePlace() throws InvalidServerReplicaLocationException, InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);

        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);

        library.setServerReplicas(serverReplicas);

        assertEquals("New Loan Created", library.requestLoan("User", "Ebook.epub"));
    }

    @Test
    /**
     * Cópia do livro em Lisboa e Utilizador Em Viseu
     */
    public void testRequestLoanValidUserAndEbookCopyInDifferentPlaces() throws InvalidServerReplicaLocationException, InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Lisbon", 38.71667, -9.13333);

        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);

        library.setServerReplicas(serverReplicas);

        assertEquals("New Loan Created", library.requestLoan("User", "Ebook.epub"));
    }

    @Test
    /**
     * Mesma Cópia do livro em Lisboa e no Porto e Utilizador Em Viseu
     */
    public void testRequestLoanValidUserAndSameEbookCopyExistsInDifferentPlaces() throws InvalidServerReplicaLocationException, InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica1 =  new ServerReplica("Porto", 41.14961, -8.61099);

        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25));

        serverReplica.addEbookCopy(ebookCopy);
        serverReplica1.addEbookCopy(ebookCopy);

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica1);

        library.setServerReplicas(serverReplicas);

        assertEquals("New Loan Created", library.requestLoan("User", "Ebook.epub"));
    }

    @Test
    /**
     * User está no ponto que representa o meio entre todos os servidors, sendo que cada um deles contém a cópia do livro
     */
    public void testRequestLoanUserNotInAnyServerPlace() throws InvalidUserNameException, EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidServerReplicaLocationException, InvalidUserPasswordException, InvalidUserAgeException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        library.addUser(new User(40.176885, -8.556702, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica1 =  new ServerReplica("Porto", 41.14961, -8.61099);
        ServerReplica serverReplica2 = new ServerReplica("Viseu", 40.66101, -7.90971);

        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25));

        serverReplica.addEbookCopy(ebookCopy);
        serverReplica1.addEbookCopy(ebookCopy);
        serverReplica2.addEbookCopy(ebookCopy);

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica1);
        serverReplicas.add(serverReplica2);

        library.setServerReplicas(serverReplicas);

        assertEquals("New Loan Created", library.requestLoan("User", "Ebook.epub"));
    }

    /**
     * Requisito 4
     * signTerm - WB Cobertura de Condições/Decisões(term signed, InvalidTerm, AccountCanceled and InvalidUserName)
     */
    @Test
    public void testSignValidTerm() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidTermPublisherException, UserAccountIsCanceledException, InvalidTermDataException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        library.addTerm(new Term("Publisher", "Copyright Term"));

        assertEquals("Term signed", library.signTerm("User", "Publisher"));
    }

    @Test
    public void testSignTermInvalidPublisher() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        try{
            library.signTerm("User", "Publisher");
        } catch (Exception e) {
            assertEquals(InvalidTermPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testSignTermInvalidUserName() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidTermDataException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        library.addTerm(new Term("Publisher", "Copyright Term"));

        try{
            library.signTerm("User1", "Publisher");
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testSignTermUserAccountCanceled() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidTermDataException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        user.setActive(false);

        library.addUser(user);

        library.addTerm(new Term("Publisher", "Copyright Term"));

        try{
            library.signTerm("User", "Publisher");
        } catch (Exception e) {
            assertEquals(UserAccountIsCanceledException.class, e.getClass());
        }
    }

    @Test
    public void testSignTermTwice() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidTermDataException, InvalidTermPublisherException, UserAccountIsCanceledException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        library.addTerm(new Term("Publisher", "Copyright Term"));

        library.signTerm("User", "Publisher");

        assertEquals("Term already signed", library.signTerm("User", "Publisher"));
    }

    @Test
    public void testSignTwoDifferentTerms() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidTermDataException, InvalidTermPublisherException, UserAccountIsCanceledException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        library.addTerm(new Term("Publisher", "Copyright Term"));
        library.addTerm(new Term("Publisher2", "Loan Conditions Term"));

        assertEquals("Term signed", library.signTerm("User", "Publisher"));
        assertEquals("Term signed", library.signTerm("User", "Publisher2"));
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
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

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
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

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
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

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
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

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
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

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
    public void testVisualizeEbookLoanExpired() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, NoLoanForThisBookException, LoanExpiredException, InvalidBookIsbnException, InvalidEbookException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        library.addUser(user);

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

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

    /**
     * Requisito 7
     * extendLoan - WB Cobertura de Decisões (Extend loan once, Extend twice, More than twice, Invalid User, Account Canceled and Inexistent Loan)
     */

    @Test
    public void testExtendLoanValid() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExtensionLimitReachedException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        assertEquals("Loan limit date extended to " + LocalDate.now().plusDays(14), library.extendLoan("User", "Ebook2.pdf"));
    }

    @Test
    public void testExtendLoanTwice() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExtensionLimitReachedException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        library.extendLoan("User", "Ebook2.pdf");
        assertEquals("Loan limit date extended to " + LocalDate.now().plusDays(21), library.extendLoan("User", "Ebook2.pdf"));
    }

    @Test
    public void testExtendLoanMoreThanTwice() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExtensionLimitReachedException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        library.extendLoan("User", "Ebook2.pdf");
        library.extendLoan("User", "Ebook2.pdf");

        try{
            library.extendLoan("User", "Ebook2.pdf");

        } catch (Exception e) {
            assertEquals(LoanExtensionLimitReachedException.class, e.getClass());
        }
    }

    @Test
    public void testExtendLoanInvalidUserName() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExtensionLimitReachedException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        try{
            library.extendLoan("User2", "Ebook2.pdf");
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testExtendLoanAccountCanceled() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExtensionLimitReachedException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        library.addUser(user);

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        user.setActive(false);

        try{
            library.extendLoan("User", "Ebook2.pdf");
        } catch (Exception e) {
            assertEquals(UserAccountIsCanceledException.class, e.getClass());
        }
    }

    @Test
    public void testExtendLoanInexistentLoan() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidServerReplicaLocationException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException, UserAccountIsCanceledException, EbookCopyNotFoundException, InvalidLoanDataException, NoLoanForThisBookException, LoanExtensionLimitReachedException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);


        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        try{
            library.extendLoan("User", "Ebook1.pdf");
        } catch (Exception e) {
            assertEquals(NoLoanForThisBookException.class, e.getClass());
        }
    }

    /**
     * Requisito 8
     * downloadEbook - WB Cobertura Decisões (Valid Download, Invalid User, Account Canceled, Inexistent Loan and Limit Date Expired)
     */

    @Test
    public void testDownloadEbookValid() throws InvalidUserNameException, EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, NoLoanForThisBookException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidServerReplicaLocationException, InvalidEbookException, InvalidUserPasswordException, InvalidUserAgeException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);

        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        assertEquals("Ebook download successful", library.downloadEbook("User", "Ebook2.pdf"));
    }

    @Test
    public void testDownloadEbookInvalidUserName() throws InvalidUserNameException, EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, NoLoanForThisBookException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidServerReplicaLocationException, InvalidEbookException, InvalidUserPasswordException, InvalidUserAgeException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);

        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        try {
            library.downloadEbook("User2", "Ebook2.pdf");
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testDownloadEbookAccountCanceled() throws InvalidUserNameException, EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, NoLoanForThisBookException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidServerReplicaLocationException, InvalidEbookException, InvalidUserPasswordException, InvalidUserAgeException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);

        library.addUser(user);

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);

        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        user.setActive(false);

        try{
            library.downloadEbook("User", "Ebook2.pdf");
        } catch (Exception e) {
            assertEquals(UserAccountIsCanceledException.class, e.getClass());
        }
    }

    @Test
    public void testDownloadEbookInexistentLoan() throws InvalidUserNameException, EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, NoLoanForThisBookException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidServerReplicaLocationException, InvalidEbookException, InvalidUserPasswordException, InvalidUserAgeException {
        Library library = new Library();

        library.addUser(new User(40.66101, -7.90971, "User", "1234567", 21));

        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ServerReplica serverReplica2 = new ServerReplica("Lisbon", 38.71667, -9.13333);
        ServerReplica serverReplica3 = new ServerReplica("Porto", 41.14961, -8.61099);

        serverReplica.addEbookCopy(new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica2.addEbookCopy(new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));

        ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
        serverReplicas.add(serverReplica);
        serverReplicas.add(serverReplica2);
        serverReplicas.add(serverReplica3);

        library.setServerReplicas(serverReplicas);

        library.requestLoan("User", "Ebook2.pdf");

        try{
            library.downloadEbook("User", "Ebook.pdf");
        } catch (Exception e) {
            assertEquals(NoLoanForThisBookException.class, e.getClass());
        }
    }

    @Test
    public void testDownloadEbookLoanLimitDateExpired() throws InvalidUserNameException, EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, NoLoanForThisBookException, InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidServerReplicaLocationException, InvalidEbookException, InvalidUserPasswordException, InvalidUserAgeException {
        Library library = new Library();

        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        library.addUser(user);

        Loan loan = new Loan(user, new EbookCopy(new Ebook(643864239, "Ebook2.pdf", "Publisher2", "Author2", "C3 H4 J7 Z0", 25)));
        loan.setLimitDate(LocalDate.now().minusMonths(1));

        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(loan);

        library.setLoans(loans);
        
        assertEquals("Illegal download detected", library.downloadEbook("User", "Ebook2.pdf"));
        assertEquals(false, user.getActive());
    }
}
