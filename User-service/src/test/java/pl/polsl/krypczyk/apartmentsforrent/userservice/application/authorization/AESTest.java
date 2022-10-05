package pl.polsl.krypczyk.apartmentsforrent.userservice.application.authorization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.polsl.krypczyk.apartmentsforrent.userservice.infrastructure.authorization.AES;

import java.util.Objects;

class AESTest {

    @Test
    void testEncryptPassword_WithValidData() {
        //GIVEN
        var encryptedPassword = "H6LsgM/nxKwv+1yR3wlLxg==";
        var password = "Admin";

        //WHEN
        var expected = AES.encrypt(password);

        //THEN
        Assertions.assertEquals(expected, encryptedPassword);
    }

    @Test
    void testEncryptPassword_WitInvalidData() {
        //GIVEN
        String password = null;

        //WHEN
        var expected = AES.encrypt(password);

        //THEN
        Assertions.assertTrue(Objects.isNull(expected));
    }

    @Test
    void testDecryptPassword_WithValidData() {
        //GIVEN
        var encryptedPassword = "H6LsgM/nxKwv+1yR3wlLxg==";
        var password = "Admin";

        //WHEN
        var expected = AES.decrypt(encryptedPassword);

        //THEN
        Assertions.assertEquals(expected, password);
    }

    @Test
    void testDecryptPassword_WithInvalidData() {
        //GIVEN
        String password = null;

        //WHEN
        var expected = AES.encrypt(password);

        //THEN
        Assertions.assertTrue(Objects.isNull(expected));
    }
}