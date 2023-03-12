package ui;

import persistence.JsonWriter;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new AccountKeeper();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
