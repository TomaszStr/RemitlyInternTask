package remitlyIntern;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RolePolicyCheckerTest {

    @Test
    void verifyInvalidPolicy1() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/invalidPol1.json"));
        assertFalse(checker.verifyPolicy(json));

    }
    @Test
    void verifyInvalidPolicy2() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/invalidPol2.json"));
        assertFalse(checker.verifyPolicy(json));
    }
    @Test
    void verifyInvalidPolicy3() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/invalidPol3.json"));
        assertFalse(checker.verifyPolicy(json));
    }
    @Test
    void verifyInvalidPolicy4() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/invalidPol4.json"));
        assertFalse(checker.verifyPolicy(json));
    }
    @Test
    void verifyInvalidPolicy5() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/invalidPol5.json"));
        assertFalse(checker.verifyPolicy(json));
    }
    @Test
    void verifyValidPolicy1() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/validPol1.json"));
        assertTrue(checker.verifyPolicy(json));
    }
    @Test
    void verifyValidPolicy2() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/validPol2.json"));
        assertTrue(checker.verifyPolicy(json));
    }
    @Test
    void verifyValidPolicy3() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/validPol3.json"));
        assertTrue(checker.verifyPolicy(json));
    }
    @Test
    void verifyValidPolicy4() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/validPol4.json"));
        assertTrue(checker.verifyPolicy(json));
    }
    @Test
    void verifyValidPolicy5() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/validPol4.json"));
        assertTrue(checker.verifyPolicy(json));
    }
    @Test
    void wrongFormat1() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/wrongFormat1.json"));
        assertThrows(RuntimeException.class,()->checker.verifyPolicy(json));
    }
    @Test
    void wrongFormat2() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/wrongFormat2.json"));
        assertThrows(RuntimeException.class,()->checker.verifyPolicy(json));
    }
    @Test
    void wrongFormat3() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/wrongFormat3.json"));
        assertThrows(RuntimeException.class,()->checker.verifyPolicy(json));
    }
    @Test
    void wrongFormat4() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/wrongFormat4.json"));
        assertThrows(RuntimeException.class,()->checker.verifyPolicy(json));
    }
    @Test
    void wrongFormat5() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/wrongFormat5.json"));
        assertThrows(RuntimeException.class,()->checker.verifyPolicy(json));
    }
    @Test
    void wrongFormat6() throws IOException {
        RolePolicyChecker checker = new RolePolicyChecker();
        String json = Files.readString(Path.of("src/test/resources/wrongFormat6.json"));
        assertThrows(RuntimeException.class,()->checker.verifyPolicy(json));
    }
}