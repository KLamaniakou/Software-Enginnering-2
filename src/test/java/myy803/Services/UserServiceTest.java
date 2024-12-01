package myy803.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import myy803.socialbookstore.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.mappers.UserMapper;

class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("testUser");
        user.setPassword("plainPassword");
    }

    @Test
    void testSaveUser() {
        // Arrange
        String encodedPassword = "encodedPassword";
        when(bCryptPasswordEncoder.encode("plainPassword")).thenReturn(encodedPassword);

        // Act
        userService.saveUser(user);

        // Assert
        assertEquals(encodedPassword, user.getPassword());
        verify(userMapper, times(1)).save(user);
    }

    @Test
    void testIsUserPresent_UserExists() {
        // Arrange
        when(userMapper.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        boolean result = userService.isUserPresent(user);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsUserPresent_UserDoesNotExist() {
        // Arrange
        when(userMapper.findByUsername("testUser")).thenReturn(Optional.empty());

        // Act
        boolean result = userService.isUserPresent(user);

        // Assert
        assertFalse(result);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // Arrange
        when(userMapper.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        UserDetails result = userService.loadUserByUsername("testUser");

        // Assert
        assertEquals(user, result);
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        // Arrange
        when(userMapper.findByUsername("testUser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("testUser");
        });
    }

    @Test
    void testFindById_UserExists() {
        // Arrange
        when(userMapper.findByUsername("testUser")).thenReturn(Optional.of(user));

        // Act
        User result = userService.findById("testUser");

        // Assert
        assertEquals(user, result);
    }

    @Test
    void testFindById_UserDoesNotExist() {
        // Arrange
        when(userMapper.findByUsername("testUser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.findById("testUser");
        });
    }
}
