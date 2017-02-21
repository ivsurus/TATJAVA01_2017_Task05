package com.epam.menu.service.factory;

import com.epam.menu.service.ParserService;
import com.epam.menu.service.impl.DOMParserServiceImpl;
import com.epam.menu.service.impl.SAXParserServiceImpl;
import com.epam.menu.service.impl.StAXParserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ParserService parserDOMService = new DOMParserServiceImpl();
    private final ParserService parserSAXService = new SAXParserServiceImpl();
    private final ParserService parserStAXService = new StAXParserServiceImpl();


    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public ParserService getDOMParserService(){
        return parserDOMService;
    }
    public ParserService getSAXParserService(){
        return parserSAXService;
    }
    public ParserService getStAXParserService(){
        return parserStAXService;
    }


}
