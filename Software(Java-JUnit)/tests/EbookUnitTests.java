import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EbookUnitTests {
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
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(831484100, ebook.getIsbn());
    }

    @Test
    public void testSetIsbn() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        ebook.setIsbn(1484138);
        assertEquals(1484138, ebook.getIsbn());
    }

    @Test
    public void testNullIsbn() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Ebook ebook = new Ebook(null, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    /**
     * Negative numbers partition
     */
    @Test
    public void testIsbnLowerInvalidPartition() {
        try{
            Ebook ebook = new Ebook(-20, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    /**
     * Valid numbers partition, from 1 to max int
     */
    @Test
    public void testIsbnValidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
            Ebook ebook = new Ebook(824872, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
            assertEquals(824872, ebook.getIsbn());
    }

    /**
     * Numbers above maximum java int number size
     */
    @Test
    public void testIsbnUpperInvalidPartition() {
        try{
            Ebook ebook = new Ebook(2147483647, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    @Test
    public void testGetName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Ebook.pdf", ebook.getName());
    }

    @Test
    public void testSetName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        ebook.setName("Ebook.epub");
        assertEquals("Ebook.epub", ebook.getName());
    }

    @Test
    public void testNullName(){
        try{
            Ebook ebook = new Ebook(831484100, null, "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookNameException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyName(){
        try{
            Ebook ebook = new Ebook(831484100, "", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookNameException.class, e.getClass());
        }
    }

    @Test
    public void testGetPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Publisher", ebook.getPublisher());
    }

    @Test
    public void testSetPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        ebook.setPublisher("PublisherTest");
        assertEquals("PublisherTest", ebook.getPublisher());
    }

    @Test
    public void testNullPublisher(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", null, "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyPublisher(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testGetAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Author", ebook.getAuthor());
    }

    @Test
    public void testSetAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        ebook.setAuthor("AuthorTest");
        assertEquals("AuthorTest", ebook.getAuthor());
    }

    @Test
    public void testNullAuthor(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", null, "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookAuthorException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyAuthor(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookAuthorException.class, e.getClass());
        }
    }

    @Test
    public void testGetHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("A1 B2 C3 H4", ebook.getHash());
    }

    @Test
    public void testSetHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        ebook.setHash("C3 B2 C1");
        assertEquals("C3 B2 C1", ebook.getHash());
    }

    @Test
    public void testGetSize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(25, ebook.getSize());
    }

    @Test
    public void testSetSize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        ebook.setSize(30);
        assertEquals(30, ebook.getSize());
    }

    /**
     *  Requisito 1
     *  Format - WB Cobertura de Condições (pdf, epub, invalid, null and special characters)
     */

    @Test
    public void testEbookPdfFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertTrue(ebook.getName().contains(".pdf"));
    }

    @Test
    public void testEbookEpubFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertTrue(ebook.getName().contains(".epub"));
    }

    @Test
    public void testEbookWrongFormat() {
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.txt", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEbookNullFormat() {
        try{
            Ebook ebook = new Ebook(831484100, "Ebook", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSpecialCharactersFormat() {
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.*+^", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    /**
     * Requisito 2
     * Size - BB Análise de valores limite (1-500)
     * Hash - WB Cobertura de condições (null, valid, special characters and empty)
     */

    @Test
    public void testEbookSizeBelowMinimum(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 0);
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSizeMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 1);
        assertEquals(1, ebook.getSize());
    }

    @Test
    public void testEbookSizeAboveMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 2);
        assertEquals(2, ebook.getSize());
    }

    @Test
    public void testEbookSizeBelowMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 499);
        assertEquals(499, ebook.getSize());
    }

    @Test
    public void testEbookSizeMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 500);
        assertEquals(500, ebook.getSize());
    }

    @Test
    public void testEbookSizeAboveMaximum(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 501);
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testEbookValidHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("A1 B2 C3 H4", ebook.getHash());
    }

    @Test
    public void testEbookNullHash(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", null, 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testEbookEmptyHash(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testEbookSpecialCharactersHash(){
        try{
            Ebook ebook = new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B* C!", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }
}
