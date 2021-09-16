import exceptions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TermUnitTest {
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
    public void testGetPublisher() throws InvalidTermDataException {
        Term term = new Term("Publisher", "Responsability Term");
        assertEquals("Publisher", term.getPublisher());
    }

    @Test
    public void testSetPublisher() throws InvalidTermDataException {
        Term term = new Term("Publisher", "Responsability Term");
        term.setPublisher("PublisherTest");
        assertEquals("PublisherTest", term.getPublisher());
    }

    @Test
    public void testNullPublisher(){
        try{
            Term term = new Term(null, "Responsability Term");
        } catch (Exception e) {
            assertEquals(InvalidTermDataException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyPublisher(){
        try{
            Term term = new Term("", "Responsability Term");
        } catch (Exception e) {
            assertEquals(InvalidTermDataException.class, e.getClass());
        }
    }

    @Test
    public void testGetTerm() throws InvalidTermDataException {
        Term term = new Term("Publisher", "Responsability Term");
        assertEquals("Responsability Term", term.getTerm());
    }

    @Test
    public void testSetTerm() throws InvalidTermDataException {
        Term term = new Term("Publisher", "Responsability Term");
        term.setTerm("Responsability Term Test");
        assertEquals("Responsability Term Test", term.getTerm());
    }

    @Test
    public void testNullTerm(){
        try{
            Term term = new Term("Publisher", null);
        } catch (Exception e) {
            assertEquals(e.getClass(), InvalidTermDataException.class);
        }
    }

    @Test
    public void testEmptyTerm(){
        try{
            Term term = new Term("Publisher", "");
        } catch (Exception e) {
            assertEquals(e.getClass(), InvalidTermDataException.class);
        }
    }
}
