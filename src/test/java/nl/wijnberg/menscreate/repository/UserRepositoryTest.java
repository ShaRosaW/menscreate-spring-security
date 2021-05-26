//package nl.wijnberg.menscreate.repository;
//
//import nl.wijnberg.menscreate.domain.User;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.util.Optional;
//
//@Disabled
//@DataJpaTest
//public class UserRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    UserRepository userRepository;
//
//
//
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
//        Optional<User> found = userRepository.findById(user.getId());
//
//        //then
//        String expected = "shar shar@gmail.com password";
//        String actual = found.get().getUsername();
//        Assertions.assertEquals(expected, actual);
////        assertThat(found.get().getUsername().equals(user.getUsername()));
////        assertThat(found.get().getUsername()).isEqualTo(user);
//    }
//}