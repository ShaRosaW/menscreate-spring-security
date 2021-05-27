package nl.wijnberg.menscreate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {

    private User user;

    @BeforeEach
    public void setUp(){
        this.user = new User("shar", "sharon@gmail.com", "password");
        this.user.setId(4L);
        this.user.setUsername("shar");

    }

    @Test
    @DisplayName("Test to see if actual username is equal to given username")
    public void testGetUsernameByConstructor() {
        String expected = "shar";
        String actual = this.user.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test to see if actual user id is equal to given id")
    public void testGetUserById(){
        Long expected = 4L;
        Long actual = this.user.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void usernameShouldBeTheSame() {

        // arrange
        User user = new User("shar", "sharon@gmail.com", "password");

        // assert
        assertEquals("shar", user.getUsername());
    }

}
