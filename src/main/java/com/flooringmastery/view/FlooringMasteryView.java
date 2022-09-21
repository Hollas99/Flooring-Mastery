package com.flooringmastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;

public class FlooringMasteryView {
    private final UserIO USER_IO;

    public FlooringMasteryView(UserIO USER_IO) {
        this.USER_IO = USER_IO;
    }
    
    //Display line
    public void displayLine(String line) {
        this.USER_IO.displayLine(line);
    }
    
    //Display information
    public void displayInformationalLine(String line) {
        displayLine(String.format("|| %s ||", line));
    }

    //Displays any error text
    public void displayErrorLine(String line) {
        displayLine(String.format("! %s !", line));
    }
    

    //Displays action text
    private void displaySolicitationLine(String line) {
        displayLine(String.format("-- %s --", line));
    }
    
    //Display header text
    public void displayAroundContents(String header, String... contents) {
        displayLine("\n");
        displayLine(String.format("__ %s __", header));
        Arrays.stream(contents).forEach(line -> displayLine(line));
    }
    
    //Get user input (integer)
    public int getInt(String prompt, Predicate<Integer> constraint, String errorText) {
        int receivedInt = -1;
        boolean invalid = true;
        while (invalid) {
            displaySolicitationLine(prompt);
            try {
                receivedInt = Integer.parseInt(USER_IO.getLine());
                invalid = !constraint.test(receivedInt);
            } catch (NumberFormatException ex) {
            }
            if (invalid) {
                displayErrorLine(errorText);
            }
        }
        
        return receivedInt;
    }

    //Get user input (String)
    public String getString(String prompt, Predicate<String> constraint, String errorText) {
        String receivedString = "";
        boolean invalid = true;
        while (invalid) {
            displaySolicitationLine(prompt);
            receivedString = USER_IO.getLine();
            invalid = !constraint.test(receivedString);
            if (invalid) {
                displayErrorLine(errorText);
            }
        }
        
        return receivedString;
    }

    //Gets user input (BigDecimal)
    public BigDecimal getBigDecimal(String prompt, Predicate<BigDecimal> constraint, String errorText) {
        BigDecimal receivedInput = BigDecimal.ZERO;
        boolean invalid = true;
        while (invalid) {
            displaySolicitationLine(prompt);
            try {
                receivedInput = new BigDecimal(USER_IO.getLine());
                invalid = !constraint.test(receivedInput);
            } catch (Exception ex) {
            }
            if (invalid) {
                displayErrorLine(errorText);
            }
        }
        return receivedInput;
    }

    //Gets user input (Local Date)
    public LocalDate getLocalDate(String prompt, Predicate<LocalDate> constraint, String errorText) {
        LocalDate receivedInput = LocalDate.EPOCH;
        boolean invalid = true;
        while (invalid) {
            displaySolicitationLine(prompt);
            try {
                receivedInput = LocalDate.parse(USER_IO.getLine());
                invalid = !constraint.test(receivedInput);
            } catch (Exception ex) {
            }
            if (invalid) {
                displayErrorLine(errorText);
            }
        }
        return receivedInput;
    }
}
