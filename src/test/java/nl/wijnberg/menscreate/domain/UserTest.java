package nl.wijnberg.menscreate.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UserTest {

    @Test
    public void nameShouldBeTheSame() {

        // arrange
        User user = new User("sharon", "sharon@gmail.com", "password");

        // assert
        assertEquals("sharon", user.getUsername());
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
