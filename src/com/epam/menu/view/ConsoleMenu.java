package com.epam.menu.view;



import com.epam.menu.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ConsoleMenu {

    private final static String COMMAND_HELPER = "Choose a parser";
    private final static String INVALID_INPUT = "Something is going wrong";

    private Controller controller = new Controller();

    public void start(){
        System.out.println(COMMAND_HELPER);
        System.out.println(controller.executeTask(readUserInput()));
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



    //этот метод в контроллер он должен показать результат

    private void showMenu(Map menu){


    }
}
