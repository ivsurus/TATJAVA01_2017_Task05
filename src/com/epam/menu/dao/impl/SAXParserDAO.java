package com.epam.menu.dao.impl;

import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.bean.menuName.MenuAttributeName;
import com.epam.menu.bean.menuName.MenuTagName;
import com.epam.menu.dao.ParserDAO;
import com.epam.menu.dao.exeption.DAOException;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SAXParserDAO extends DefaultHandler implements ParserDAO{

    private Map<Appetizer, List<Food>> appetizersMap = new HashMap<>();
    private List<Food> foodList;
    private Appetizer appetizer;
    private Food food;
    private List <String> type;
    private StringBuilder text;

    private final static String MENU = "menu.xml";

    @Override
    public Map<Appetizer, List<Food>> parseMenu(String request) throws DAOException {
        Map<Appetizer, List<Food>> menu;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXParserDAO handler = new SAXParserDAO();
            reader.setContentHandler(handler);
            reader.parse(new InputSource("menu.xml"));
            // включение проверки действительности
            reader.setFeature("http://xml.org/sax/features/validation", true);
            // включение обработки пространств имен
            reader.setFeature("http://xml.org/sax/features/namespaces", true);
            // включение канонизации строк
            reader.setFeature("http://xml.org/sax/features/string-interning", true);
            // отключение обработки схем
            reader.setFeature("http://apache.org/xml/features/validation/schema", false);
            menu = handler.getAppetizersMap();
        } catch (SAXException | IOException e){
            throw new DAOException();
        }
        return menu;
    }


    public Map getAppetizersMap() {
        return appetizersMap;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("startElement -> " + "uri: " + uri + ", localName: " + localName + ", qName: " + qName);
        text = new StringBuilder();

        if (qName.equals(MenuTagName.FOOD.toString().toLowerCase())){
            food = new Food();
            food.setId(attributes.getValue(MenuAttributeName.ID.toString().toLowerCase()));
        }

        if (qName.equals(MenuTagName.TYPE.toString().toLowerCase())){
            type = new ArrayList<>();
            type.add(attributes.getValue(MenuAttributeName.ID.toString().toLowerCase()));
        }

        if (qName.equals(MenuTagName.APPETIZER.toString().toLowerCase())){
            appetizer = new Appetizer();
            appetizer.setName(attributes.getValue(MenuAttributeName.NAME.toString().toLowerCase()));
            foodList = new ArrayList<>();
        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase());

        switch(tagName){

            case APPETIZER:
                appetizersMap.put(appetizer, foodList);
                break;

            case TYPE:
                food.setTypes(type.get(0), type.get(1), type.get(2));
                break;
            case NAME:
                food.setName(text.toString());
                break;

            case PRICE:
                type.add((text.toString()));
                break;

            case DESCRIPTION:
                type.add(text.toString());
                break;

            case PORTION:
                food.setPortion(text.toString());
                break;

            case PICTURE:
                food.setPicture(text.toString());
                break;

            case FOOD:
                foodList.add(food);
                break;
        }
    }
    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
        throw (exception);
    }


}

