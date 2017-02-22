package com.epam.menu.runner;

import com.epam.menu.view.ConsoleMenu;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException, SAXException {
        new ConsoleMenu().start();
    }

}
