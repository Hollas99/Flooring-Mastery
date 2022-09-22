package com.flooringmastery.view;

public interface UserIO {

	//Display text
    public void printLine(String line);
    
    //Get text from user
    public String getLine();

    public void close();
}
