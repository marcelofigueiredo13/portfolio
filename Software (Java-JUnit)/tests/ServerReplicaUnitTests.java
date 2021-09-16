import exceptions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServerReplicaUnitTests {
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
    public void testGetPlace() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        assertEquals("Viseu", serverReplica.getPlace());
    }

    @Test
    public void testSetPlace() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        serverReplica.setPlace("ViseuTest");
        assertEquals("ViseuTest", serverReplica.getPlace());
    }

    @Test
    public void testNullPlace(){
        try{
            ServerReplica serverReplica = new ServerReplica(null, 40.66101, -7.90971);
        } catch (Exception e) {
            assertEquals(InvalidServerReplicaLocationException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyPlace(){
        try{
            ServerReplica serverReplica = new ServerReplica("", 40.66101, -7.90971);
        } catch (Exception e) {
            assertEquals(InvalidServerReplicaLocationException.class, e.getClass());
        }
    }

    @Test
    public void testGetLatitude() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        assertEquals(40.66101, serverReplica.getLatitude());
    }

    @Test
    public void testSetLatitude() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        serverReplica.setLatitude(40.000);
        assertEquals(40.000, serverReplica.getLatitude());
    }

    @Test
    public void testGetLongitude() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        assertEquals(-7.90971, serverReplica.getLongitude());
    }

    @Test
    public void testSetLongitude() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        serverReplica.setLongitude(-7.5);
        assertEquals(-7.5, serverReplica.getLongitude());
    }

    @Test
    public void testGetEbookCopies() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        assertTrue(serverReplica.getEbookCopies().isEmpty());
    }

    @Test
    public void testSetEbookCopies() throws InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        ArrayList<EbookCopy> ebookCopies = new ArrayList<>();
        ebookCopies.add(new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25)));
        serverReplica.setEbookCopies(ebookCopies);
        assertEquals(1, serverReplica.getEbookCopies().size());
    }

    @Test
    public void testGetEbookCopyValidCopy() throws InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);

        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        ArrayList<EbookCopy> ebookCopies = new ArrayList<>();
        ebookCopies.add(ebookCopy);

        serverReplica.setEbookCopies(ebookCopies);

        assertEquals(ebookCopy, serverReplica.getEbookCopy("Ebook.pdf"));
    }

    @Test
    public void testGetEbookCopyNoCopies() throws InvalidServerReplicaLocationException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);
        assertNull(serverReplica.getEbookCopy("Ebook.pdf"));
    }

    @Test
    public void testGetEbookCopyWrongCopy() throws InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);

        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));

        ArrayList<EbookCopy> ebookCopies = new ArrayList<>();
        ebookCopies.add(ebookCopy);

        serverReplica.setEbookCopies(ebookCopies);

        assertNull(serverReplica.getEbookCopy("Ebook.epub"));
    }

    @Test
    public void testAddEbookCopy() throws InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);

        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));
        EbookCopy ebookCopy1 = new EbookCopy(new Ebook(712940712, "Ebook1.epub", "Publisher1", "Author1", "A1 B2 C3 H4 J7", 45));

        serverReplica.addEbookCopy(ebookCopy);
        serverReplica.addEbookCopy(ebookCopy1);

        assertEquals(2, serverReplica.getEbookCopies().size());
    }

    @Test
    public void testAddMultipleEbookCopies() throws InvalidServerReplicaLocationException, InvalidBookSizeException, InvalidBookPublisherException, InvalidBookNameException, InvalidBookFormatException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookIsbnException, InvalidEbookException {
        ServerReplica serverReplica = new ServerReplica("Viseu", 40.66101, -7.90971);

        EbookCopy ebookCopy = new EbookCopy(new Ebook(831484100, "Ebook.pdf", "Publisher", "Author", "A1 B2 C3 H4", 25));
        EbookCopy ebookCopy1 = new EbookCopy(new Ebook(712940712, "Ebook1.epub", "Publisher1", "Author1", "A1 B2 C3 H4 J7", 45));

        ArrayList<EbookCopy> ebookCopies = new ArrayList<>();
        ebookCopies.add(ebookCopy);
        ebookCopies.add(ebookCopy1);

        serverReplica.addMultipleEbookCopies(ebookCopies);

        assertEquals(2, serverReplica.getEbookCopies().size());
    }
}
