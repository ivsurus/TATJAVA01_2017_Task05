package com.epam.menu.view;

import com.epam.menu.controller.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMenu {

    private final static String COMMAND_HELPER = "Input command to choose a parser:" +
            " 'DOM' or 'SAX' or 'StAX'";
    private final static String INVALID_INPUT = "Something is going wrong";
    private final static String MENU = "menu.xml";
    private final static String DELIMITER = "_";

    private Controller controller = new Controller();

    public void start(){
        System.out.println(COMMAND_HELPER);
        System.out.println(controller.executeTask(readUserInput()+ DELIMITER + MENU));
    }

    private String readUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = reader.readLine().toUpperCase();
        } catch (IOException e) {
            System.out.println(INVALID_INPUT);
        }
        return input;
    }
}
