package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studit.core.LoginManager;

public class LoginManagerTest{

    @BeforeEach
    public void init() {
        LoginManager.initialize();
    }

    @Test
    public void testMatch() {
        assertFalse(LoginManager.match("user", "aaaa"));
        assertTrue(LoginManager.match("user", "password"));
    }

    @Test
    public void testWriteReadFromFile() {
        String string = "test bla bla";
        String filename = "testFile.txt";
        try{
            LoginManager.writeToFile(string, filename);
            assertEquals(LoginManager.readFromFile(filename), string);
        }
        catch (Exception e){ fail("Writing or reading to file failed"); }
    }


}