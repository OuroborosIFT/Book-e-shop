package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.dao.UserRepository;
import com.dsu.arslan.coursework.domain.User;
import com.dsu.arslan.coursework.dto.UserDTO;
import com.dsu.arslan.coursework.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each test");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all test");
    }

    @Test
    void checkFindByName() {
        //have
        String name = "Maga";
        User expectedUser = User.builder().id(1L).username(name).build();

        Mockito.when(userRepository.findFirstByUsername(Mockito.anyString())).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByUsername(name);

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

    }

    @Test
    void checkFindByNameExact() {
        //have
        String name = "Maga";
        User expectedUser = User.builder().id(1L).username(name).build();

        Mockito.when(userRepository.findFirstByUsername(Mockito.eq(name))).thenReturn(expectedUser);

        //execute
        User actualUser = userService.findByUsername(name);
        User rndUser = userService.findByUsername(UUID.randomUUID().toString());

        //check
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);

        Assertions.assertNull(rndUser);

    }

    @Test
    void checkSaveIncorrectPassword(){
        //have
        UserDTO userDto = UserDTO.builder()
                .password("password")
                .matchingPassword("another")
                .build();

        //execute
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                userService.save(userDto);
            }
        });

    }

    @Test
    void checkSave(){
        //have
        UserDTO userDto = UserDTO.builder()
                .username("name")
                .email("email")
                .password("password")
                .matchingPassword("password")
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        //execute
        boolean result = userService.save(userDto);

        //check
        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());

    }

}
