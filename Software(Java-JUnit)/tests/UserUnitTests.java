import exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserUnitTests {
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
    public void testGetLatitude() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        assertEquals(40.66101, user.getLatitude());
    }

    @Test
    public void testSetLatitude() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        user.setLatitude(35.123);
        assertEquals(35.123, user.getLatitude());
    }

    @Test
    public void testGetLongitude() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        assertEquals(-7.90971, user.getLongitude());
    }

    @Test
    public void testSetLongitude() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        user.setLongitude(0.123);
        assertEquals(0.123, user.getLongitude());
    }

    @Test
    public void testGetName() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        assertEquals("User", user.getName());
    }

    @Test
    public void testSetName() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "1234567", 21);
        user.setName("UserTest");
        assertEquals("UserTest", user.getName());
    }

    @Test
    public void testNullName(){
        try{
            User user = new User(40.66101, -7.90971, null, "1234567", 21);
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyName(){
        try{
            User user = new User(40.66101, -7.90971, "", "1234567", 21);
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testSpecialCharactersName(){
        try{
            User user = new User(40.66101, -7.90971, "User#!", "1234567", 21);
        } catch (Exception e) {
            assertEquals(InvalidUserNameException.class, e.getClass());
        }
    }

    @Test
    public void testGetPassword() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 21);
        assertEquals("abc123", user.getPassword());
    }

    @Test
    public void testSetPassword() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 21);
        user.setPassword("a1b2c3");
        assertEquals("a1b2c3", user.getPassword());
    }

    @Test
    public void testNullPassword(){
        try{
            User user = new User(40.66101, -7.90971, "User", null, 21);
        } catch (Exception e) {
            assertEquals(InvalidUserPasswordException.class, e.getClass());
        }
    }

    @Test
    public void testEmptyPassword(){
        try{
            User user = new User(40.66101, -7.90971, "User", "", 21);
        } catch (Exception e) {
            assertEquals(InvalidUserPasswordException.class, e.getClass());
        }
    }

    @Test
    public void testGetAge() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 21);
        assertEquals(21, user.getAge());
    }

    @Test
    public void testSetAge() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 21);
        user.setAge(30);
        assertEquals(30, user.getAge());
    }

    @Test
    public void testAgeBelowMinimum(){
        try{
            User user = new User(40.66101, -7.90971, "User", "abc123", 17);
        } catch (Exception e) {
            assertEquals(InvalidUserAgeException.class, e.getClass());
        }
    }

    @Test
    public void testAgeMinimum() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 18);
        assertEquals(18, user.getAge());
    }

    @Test
    public void testAgeAboveMinimum() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 19);
        assertEquals(19, user.getAge());
    }

    @Test
    public void testAgeBelowMaximum() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 98);
        assertEquals(98, user.getAge());
    }

    @Test
    public void testAgeMaximum() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 99);
        assertEquals(99, user.getAge());
    }

    @Test
    public void testAgeAboveMaximum() {
        try{
            User user = new User(40.66101, -7.90971, "User", "abc123", 100);
        } catch (Exception e) {
            assertEquals(InvalidUserAgeException.class, e.getClass());
        }
    }

    @Test
    public void testGetActive() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 21);
        assertTrue(user.getActive());
    }

    @Test
    public void testSetActive() throws InvalidUserAgeException, InvalidUserPasswordException, InvalidUserNameException {
        User user = new User(40.66101, -7.90971, "User", "abc123", 21);
        user.setActive(false);
        assertFalse(user.getActive());
    }
}
