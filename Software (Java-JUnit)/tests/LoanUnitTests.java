import exceptions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanUnitTests {
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
    public void testGetUser() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidLoanDataException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        assertEquals(user, loan.getUser());
    }

    @Test
    public void testSetUser() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        User user2 = new User(40.01, -7.71, "User2", "7654321", 30);
        loan.setUser(user2);

        assertEquals(user2, loan.getUser());
    }

    @Test
    public void testNullUser() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try {
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

            Loan loan = new Loan(null, ebookCopy);
        } catch (InvalidLoanDataException | InvalidBookIsbnException | InvalidEbookException e) {
            assertEquals(InvalidLoanDataException.class, e.getClass());
        }
    }

    @Test
    public void testGetEbookCopy() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        assertEquals(ebookCopy, loan.getEbookCopy());
    }

    @Test
    public void testSetEbookCopy() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        EbookCopy ebookCopy1 = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));
        loan.setEbookCopy(ebookCopy1);

        assertEquals(ebookCopy1, loan.getEbookCopy());
    }

    @Test
    public void testNullEbookCopy() {
        try {
            User user = new User(40.66101, -7.90971, "User", "1234567", 21);

            Loan loan = new Loan(user, null);
        } catch (Exception e) {
            assertEquals(InvalidLoanDataException.class, e.getClass());
        }
    }

    @Test
    public void testGetStartDate() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        assertEquals(LocalDate.now(), loan.getStartDate());
    }

    @Test
    public void testSetStartDate() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        loan.setStartDate(LocalDate.now().plusMonths(1));

        assertEquals(LocalDate.now().plusMonths(1), loan.getStartDate());
    }

    @Test
    public void testGetLimitDate() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        assertEquals(LocalDate.now().plusDays(7), loan.getLimitDate());
    }

    @Test
    public void testSetLimitDate() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        loan.setLimitDate(LocalDate.now().plusYears(1));

        assertEquals(LocalDate.now().plusYears(1), loan.getLimitDate());
    }

    @Test
    public void testGetExtended() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        assertEquals(0, loan.getExtended());
    }

    @Test
    public void testSetExtended() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidLoanDataException, InvalidBookIsbnException, InvalidEbookException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        Loan loan = new Loan(user, ebookCopy);

        loan.setExtended(2);

        assertEquals(2, loan.getExtended());
    }
}
