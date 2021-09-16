import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class WebComponentUnitTests {
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
    public void testVisualizeNullEbook(){
        WebComponent webComponent;
        webComponent = new StubWebComponent();
        assertEquals("Error: Null Ebook Provided", webComponent.visualizeBook(null));
    }

    @Test
    public void testVisualizeEbookWithoutName(){
        WebComponent webComponent;
        webComponent = new StubWebComponent();
        assertEquals("Error: Empty Ebook Provided", webComponent.visualizeBook(""));
    }

    @Test
    public void testVisualizeEbookWithInvalidFormat(){
        WebComponent webComponent;
        webComponent = new StubWebComponent();
        assertEquals("Error: Invalid Ebook Format", webComponent.visualizeBook("Ebook.txt"));
    }

    @Test
    public void testVisualizeEbookWithValidFormat(){
        WebComponent webComponent;
        webComponent = new StubWebComponent();
        assertEquals("Success: Visualizing Ebook", webComponent.visualizeBook("Ebook.pdf"));
    }
}
