package io.krafty.digilocker.utility;

import java.util.Random;

public class PasswordGenerator {

    private int numChars;
    private boolean hasDigits;
    private boolean hasUpperCase;
    private boolean hasSymbols;

    PasswordGenerator() {
        this(12);  //min default password size
    }

    PasswordGenerator(int numChars) {
        this.numChars = numChars;
        this.hasDigits = true;
        this.hasUpperCase = true;
        this.hasSymbols = true;
    }

    PasswordGenerator(int numChars, boolean hasUpperCase, boolean hasDigits, boolean hasSymbols) {
        this(numChars);
        this.hasUpperCase = hasUpperCase;
        this.hasDigits = hasDigits;
        this.hasSymbols = hasSymbols;
    }

    public String generate() {
        Random rand = new Random();
        StringBuffer sb = new StringBuffer(numChars);

        for (int i = 0; i < numChars; i++) {
            sb.append((char) (rand.nextInt(93) + '!'));  // All the printable ASCII characters from 33 - 126 inclusive.
        }
        return sb.toString();
    }
}
