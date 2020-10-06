package studit.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoginManagerTest{

    @Test
    public void testMatch() {
        assertFalse(LoginManager.match("user", "aaaa"));
        assertTrue(LoginManager.match("user", "password"));
    }

}
