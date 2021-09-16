import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EpubUnitTests {
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
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(831484100, epub.getIsbn());
    }

    @Test
    public void testSetIsbn() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        epub.setIsbn(1484138);
        assertEquals(1484138, epub.getIsbn());
    }

    @Test
    public void testNullIsbn() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Epub epub = new Epub(null, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    /**
     * Negative numbers partition
     */
    @Test
    public void testIsbnLowerInvalidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Epub epub = new Epub(-20, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    /**
     * Valid numbers partition, from 1 to max int
     */
    @Test
    public void testIsbnValidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        Epub epub = new Epub(824872, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(824872, epub.getIsbn());
    }

    /**
     * Numbers above maximum java int number size
     */
    @Test
    public void testIsbnUpperInvalidPartition() throws InvalidBookIsbnException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException {
        try{
            Epub epub = new Epub(2147483647, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookIsbnException.class, e.getClass());
        }
    }

    @Test
    public void testGetName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Ebook.epub", epub.getName());
    }

    @Test
    public void testSetName() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        epub.setName("Ebook1.epub");
        assertEquals("Ebook1.epub", epub.getName());
    }

    @Test
    public void testNullName(){
        try{
            Epub epub = new Epub(831484100, null, "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookNameException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyName(){
        try{
            Epub epub = new Epub(831484100, "", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookNameException.class, e.getClass());
        }
    }

    @Test
    public void testGetPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Publisher", epub.getPublisher());
    }

    @Test
    public void testSetPublisher() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        epub.setPublisher("PublisherTest");
        assertEquals("PublisherTest", epub.getPublisher());
    }

    @Test
    public void testNullPublisher(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", null, "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyPublisher(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookPublisherException.class, e.getClass());
        }
    }

    @Test
    public void testGetAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("Author", epub.getAuthor());
    }

    @Test
    public void testSetAuthor() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        epub.setAuthor("AuthorTest");
        assertEquals("AuthorTest", epub.getAuthor());
    }

    @Test
    public void testNullAuthor(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", null, "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookAuthorException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyAuthor(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "", "A1 B2 C3 H4", 25);
        } catch (Exception e){
            assertEquals(InvalidBookAuthorException.class, e.getClass());
        }
    }

    @Test
    public void testGetHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("A1 B2 C3 H4", epub.getHash());
    }

    @Test
    public void testSetHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        epub.setHash("C3 B2 C1");
        assertEquals("C3 B2 C1", epub.getHash());
    }

    @Test
    public void testGetSize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals(25, epub.getSize());
    }

    @Test
    public void testSetSize() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        epub.setSize(30);
        assertEquals(30, epub.getSize());
    }

    @Test
    public void testEpubWithPdfFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        try{
            Epub epub = new Epub(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEpubCorrectFormat() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertTrue(epub.getName().contains(".epub"));
    }

    @Test
    public void testEpubWrongFormat() {
        try{
            Epub epub = new Epub(831484100, "Ebook.txt", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEpubNullFormat() {
        try{
            Epub epub = new Epub(831484100, "Ebook", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }

    @Test
    public void testEpubSpecialCharactersFormat() {
        try{
            Epub epub = new Epub(831484100, "Ebook.*+^", "Publisher", "Author", "A1 B2 C3 H4", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookFormatException.class, e.getClass());
        }
    }



    @Test
    public void testEpubSizeBelowMinimum(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 0);
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testEpubSizeMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 1);
        assertEquals(1, epub.getSize());
    }

    @Test
    public void testEpubSizeAboveMinimum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 2);
        assertEquals(2, epub.getSize());
    }

    @Test
    public void testEpubSizeBelowMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 499);
        assertEquals(499, epub.getSize());
    }

    @Test
    public void testEpubSizeMaximum() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 500);
        assertEquals(500, epub.getSize());
    }

    @Test
    public void testEpubSizeAboveMaximum(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 501);
        } catch (Exception e) {
            assertEquals(InvalidBookSizeException.class, e.getClass());
        }
    }

    @Test
    public void testEpubValidHash() throws InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException {
        Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B2 C3 H4", 25);
        assertEquals("A1 B2 C3 H4", epub.getHash());
    }

    @Test
    public void testEpubNullHash(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", null, 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testEpubEmptyHash(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }

    @Test
    public void testEpubSpecialCharactersHash(){
        try{
            Epub epub = new Epub(831484100, "Ebook.epub", "Publisher", "Author", "A1 B* C!", 25);
        } catch (Exception e) {
            assertEquals(InvalidBookHashException.class, e.getClass());
        }
    }
}
