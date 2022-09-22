package com.flooringmastery.view;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    private final Scanner USER_IO = new Scanner(System.in);
    
    @Override
    public void displayLine(String line) {
        System.out.println(line);
    }

    @Override
    public String getLine() {
        return USER_IO.nextLine();
    }

    @Override
    public void close() {
        USER_IO.close();
    }
}
