package io.krafty.digilocker.utility

class PasswordGeneratorTest extends GroovyTestCase {

    void testGenerate() {
        def pwdGenerator = new PasswordGenerator();
        def expectedChars = 12;
        assertToString(pwdGenerator.generate().length(), String.valueOf(expectedChars));
    }
}
