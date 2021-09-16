import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EbookCopyUnitTests {
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
    public void testGetEbookCopy() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException {
        Ebook ebook1 = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        EbookCopy ebookCopy = new EbookCopy(ebook1);
        assertEquals(ebook1, ebookCopy.getEbookCopy());
    }

    @Test
    public void testSetEbookCopy() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidEbookException {
        Ebook ebook1 = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        Ebook ebook2 = new Ebook(9834639, "Ebook.epub", "Publisher2", "Author2", "U1 C2 K3 P4", 58);

        EbookCopy ebookCopy = new EbookCopy(ebook1);
        ebookCopy.setEbookCopy(ebook2);
        assertEquals(ebook2, ebookCopy.getEbookCopy());
    }

    @Test
    public void testEbookCopyNullEbook(){
        try{
            EbookCopy ebookCopy = new EbookCopy(null);
        } catch (Exception e) {
            assertEquals(InvalidEbookException.class, e.getClass());
        }
    }

    @Test
    public void testGetEbookCopyIsbn() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));
        assertEquals(831484100, ebookCopy.getEbookCopy().getIsbn());
    }

    @Test
    public void testSetEbookCopyIsbn() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        ebookCopy.getEbookCopy().setIsbn(1484138);
        assertEquals(1484138, ebookCopy.getEbookCopy().getIsbn());
    }

    @Test
    public void testGetEbookCopyName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals("Ebook.pdf", ebookCopy.getEbookCopy().getName());
    }

    @Test
    public void testSetEbookCopyName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        ebookCopy.getEbookCopy().setName("Ebook.epub");
        assertEquals("Ebook.epub", ebookCopy.getEbookCopy().getName());
    }

    @Test
    public void testGetEbookCopyPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals("Publisher", ebookCopy.getEbookCopy().getPublisher());
    }

    @Test
    public void testSetEbookCopyPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        ebookCopy.getEbookCopy().setPublisher("PublisherTest");
        assertEquals("PublisherTest", ebookCopy.getEbookCopy().getPublisher());
    }

    @Test
    public void testGetEbookCopyAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals("Author", ebookCopy.getEbookCopy().getAuthor());
    }

    @Test
    public void testSetEbookCopyAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        ebookCopy.getEbookCopy().setAuthor("AuthorTest");
        assertEquals("AuthorTest", ebookCopy.getEbookCopy().getAuthor());
    }

    @Test
    public void testGetEbookCopyHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals("A1 B2 C3 H4", ebookCopy.getEbookCopy().getHash());
    }

    @Test
    public void testSetEbookCopyHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        ebookCopy.getEbookCopy().setHash("C3 B2 C1");
        assertEquals("C3 B2 C1", ebookCopy.getEbookCopy().getHash());
    }

    @Test
    public void testGetEbookCopySize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals(2, ebookCopy.getEbookCopy().getSize());
    }

    @Test
    public void testSetEbookCopySize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        ebookCopy.getEbookCopy().setSize(30);
        assertEquals(30, ebookCopy.getEbookCopy().getSize());
    }

    @Test
    public void testEbookPdfFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertTrue(ebookCopy.getEbookCopy().getName().contains(".pdf"));
    }

    @Test
    public void testEbookEpubFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertTrue(ebookCopy.getEbookCopy().getName().contains(".epub"));
    }

    @Test
    public void testEbookWrongFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.txt", "Publisher", "Author", "A1 B2 C3 H4", 2));
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEbookNullFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook", "Publisher", "Author", "A1 B2 C3 H4", 2));
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSpecialCharactersFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.*+^", "Publisher", "Author", "A1 B2 C3 H4", 2));
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSizeBelowMinimum(){
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 0));
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSizeMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 1));
        assertEquals(1, ebookCopy.getEbookCopy().getSize());
    }

    @Test
    public void testEbookSizeAboveMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals(2, ebookCopy.getEbookCopy().getSize());
    }

    @Test
    public void testEbookSizeBelowMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 499));
        assertEquals(499, ebookCopy.getEbookCopy().getSize());
    }

    @Test
    public void testEbookSizeMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 500));
        assertEquals(500, ebookCopy.getEbookCopy().getSize());
    }

    @Test
    public void testEbookSizeAboveMaximum(){
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 501));
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testEbookValidHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2));
        assertEquals("A1 B2 C3 H4", ebookCopy.getEbookCopy().getHash());
    }

    @Test
    public void testEbookNullHash(){
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", null, 2));
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testEbookEmptyHash(){
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "", 2));
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSpecialCharactersHash(){
        try{
            EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B* C!", 2));
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }
}
