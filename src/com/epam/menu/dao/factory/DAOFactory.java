package com.epam.menu.dao.factory;


import com.epam.menu.dao.ParserDAO;
import com.epam.menu.dao.impl.DOMParserDAO;
import com.epam.menu.dao.impl.SAXParserDAO;
import com.epam.menu.dao.impl.StAXParserDAO;

public final class DAOFactory {


    private static final DAOFactory instance = new DAOFactory();

    private final ParserDAO xmlDOMParserDAOImpl = new DOMParserDAO();
    private final ParserDAO xmlSAXParserDAOImpl = new SAXParserDAO();
    private final ParserDAO xmlStAXParserDAOImpl = new StAXParserDAO();


    private DAOFactory(){}


    public static DAOFactory getInstance(){
        return instance;
    }

    public ParserDAO getDOMParserDAO(){
        return xmlDOMParserDAOImpl;
    }
    public ParserDAO getSAXParserDAO(){
        return xmlSAXParserDAOImpl;
    }
    public ParserDAO getStAXParser(){
        return xmlStAXParserDAOImpl;
    }

}
