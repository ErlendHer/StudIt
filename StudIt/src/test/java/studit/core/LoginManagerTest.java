package studit.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginManagerTest{

    @BeforeEach
    public void init() {
        UserManager.initialize();
    }

    @Test
    public void testMatch() {
        assertFalse(LoginManager.match("user", "aaaa"));
        assertTrue(LoginManager.match("user", "password"));
    }


}