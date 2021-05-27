package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.MensCreateApplication;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 *
 * Tried to set up for repository test, with a mock for the database.
 *  Commented out, it's not working yet.
 *  Failed test is also set up, slightly different in repository test class and commented out.
 *
 */

//@DataJpaTest
//@SpringJUnitConfig
//@AutoConfigureTestDatabase(replace = Replace.ANY)
//@ContextConfiguration(classes = {MensCreateApplication.class})
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    private TestEntityManager entityManager;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
//        user = new User("shar", "shar@mail.com", "password");
        user.setId(4L);
        user.setUsername("shar");
        user.setEmail("shar@mail.com");
        user.setPassword("password");

//        Mockito
//                .when(userRepository.findByUsername(user.getUsername()))
//                .thenReturn(Optional.ofNullable(user));
    }

    @Test
    public void getUserById_ShouldReturnUsername() {
        Mockito
                .when(userRepository.existsById(4L))
                .thenReturn(true);

        Mockito
                .when(userRepository.findById(4L))
                .thenReturn(Optional.of(user));

        Assertions.assertEquals(userServiceImpl.getUserById(4L).getUsername(), user.getUsername());
    }

    @Test
    public void whenUserByIdNotFound_ShouldReturn404() {

        Mockito
                .when(userRepository.existsById(4L))
                .thenReturn(true);

        Mockito
                .when(userRepository.existsById(5L))
                .thenReturn(false);

        Mockito
                .when(userRepository.findById(6L))
                .thenReturn(null);

        User noUser = userServiceImpl.getUserById(user.getId());

        Assertions.assertNull(noUser, "This gives a 404, RecordNotFoundException");
    }

    @Test
    public void testGetUserByUsername(){
        //given
        user = new User("shar", "shar@mail.com", "password");

        Mockito
                .when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.ofNullable(user));

        String name = "shar";
        String expected = "shar";
//        String expected = "shar shar@gmail.com password";

        //when
        Optional<User> found = userServiceImpl.getUserByUsername(name);

        //then
        Assertions.assertEquals(expected, found.get().getUsername());

    }


//    @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.NONE)
//    @Test
//    public void whenFindById_thenReturnUser(){
//
//        //given
//        User user = new User("shar", "shar@mail.com", "password");
//        user.setId(4L);
////        entityManager.equals(user);
//        entityManager.persist(user);
//        entityManager.flush();
//
//        //when
//        Optional<User> found = userRepository.findById(user.getUsername());
//
//        //then
////        assertThat(found.get().getUsername().equals(user.getUsername()));
//        assertThat(found.get().getUsername()).isEqualTo(user);
//    }


}