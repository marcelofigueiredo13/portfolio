import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PdfUnitTests {
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
    public void testGetIsbn() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(831484100, pdf.getIsbn());
    }

    @Test
    public void testSetIsbn() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        pdf.setIsbn(1484138);
        assertEquals(1484138, pdf.getIsbn());
    }

    @Test
    public void testNullIsbn() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Pdf pdf = new Pdf(null, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    @Test
    public void testIsbnLowerInvalidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Pdf pdf = new Pdf(-20, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    @Test
    public void testIsbnValidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        Pdf pdf = new Pdf(824872, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(824872, pdf.getIsbn());
    }

    @Test
    public void testIsbnUpperInvalidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Pdf pdf = new Pdf(2147483647, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    @Test
    public void testGetName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Ebook.pdf", pdf.getName());
    }

    @Test
    public void testSetName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        pdf.setName("Ebook1.epub");
        assertEquals("Ebook1.epub", pdf.getName());
    }

    @Test
    public void testNullName(){
        try{
            Pdf pdf = new Pdf(831484100, null, "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookNameException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyName(){
        try{
            Pdf pdf = new Pdf(831484100, "", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookNameException.class, e.getClass());
        }
    }

    @Test
    public void testGetPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Publisher", pdf.getPublisher());
    }

    @Test
    public void testSetPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        pdf.setPublisher("PublisherTest");
        assertEquals("PublisherTest", pdf.getPublisher());
    }

    @Test
    public void testNullPublisher(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", null, "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyPublisher(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testGetAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Author", pdf.getAuthor());
    }

    @Test
    public void testSetAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        pdf.setAuthor("AuthorTest");
        assertEquals("AuthorTest", pdf.getAuthor());
    }

    @Test
    public void testNullAuthor(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", null, "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookAuthorException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyAuthor(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookAuthorException.class, e.getClass());
        }
    }

    @Test
    public void testGetHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("A1 B2 C3 H4", pdf.getHash());
    }

    @Test
    public void testSetHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        pdf.setHash("C3 B2 C1");
        assertEquals("C3 B2 C1", pdf.getHash());
    }

    @Test
    public void testGetSize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(25, pdf.getSize());
    }

    @Test
    public void testSetSize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        pdf.setSize(30);
        assertEquals(30, pdf.getSize());
    }

    @Test
    public void testPdfCorrectFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertTrue(pdf.getName().contains(".pdf"));
    }

    @Test
    public void testPdfWithEpubFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        }catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testPdfWrongFormat() {
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.txt", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testPdfNullFormat() {
        try{
            Pdf pdf = new Pdf(831484100, "Ebook", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testPdfSpecialCharactersFormat() {
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.*+^", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testPdfSizeBelowMinimum(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 0);
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testPdfSizeMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 1);
        assertEquals(1, pdf.getSize());
    }

    @Test
    public void testPdfSizeAboveMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2);
        assertEquals(2, pdf.getSize());
    }

    @Test
    public void testPdfSizeBelowMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 499);
        assertEquals(499, pdf.getSize());
    }

    @Test
    public void testPdfSizeMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 500);
        assertEquals(500, pdf.getSize());
    }

    @Test
    public void testPdfSizeAboveMaximum(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 501);
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testPdfValidHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("A1 B2 C3 H4", pdf.getHash());
    }

    @Test
    public void testPdfNullHash(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", null, 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testPdfEmptyHash(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testPdfSpecialCharactersHash(){
        try{
            Pdf pdf = new Pdf(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B* C!", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }
}
