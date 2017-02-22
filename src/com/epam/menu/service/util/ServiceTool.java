package com.epam.menu.service.util;


import java.io.File;

public class ServiceTool {

    private final static String XML_EXTENSION = "xml";
    private final static String DELIMITER = "\\.";


    public static synchronized boolean isRequestValid(String request){
        String extension = request.split(DELIMITER)[1];
        File menuFile = new File(request);
        return (menuFile.exists()) && (menuFile.length() != 0) &&
                extension.equalsIgnoreCase(XML_EXTENSION);
    }

}
