package nl.wijnberg.menscreate.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserTest {

    private User user;

    @BeforeEach
    public void setUp(){
        this.user = new User("shar", "sharon@gmail.com", "password");
        this.user.setId(4L);
        this.user.setUsername("shar");

    }

    @Test
    public void testGetUsernameByConstructor() {
        String expected = "shar";
        String actual = this.user.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById(){
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




//
//    @Test
//    public void nameShouldNotBeNull() {
//
//        // arrange
//        User userNull = new User();
//
//        // act
//
//        // assert
//
//    }


}
