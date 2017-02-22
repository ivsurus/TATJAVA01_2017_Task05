package com.epam.menu.dao.impl;

import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.bean.menuName.MenuAttributeName;
import com.epam.menu.bean.menuName.MenuTagName;
import com.epam.menu.dao.ParserDAO;
import com.epam.menu.dao.exeption.DAOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StAXParserDAO implements ParserDAO {


    @Override
    public Map<Appetizer, List<Food>> parseMenu(String request) throws
            DAOException {
        Map<Appetizer, List<Food>> menu;
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(request);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            menu = getMenu(reader);
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new DAOException(e);
        }
        return menu;
    }


    private Map<Appetizer, List<Food>> getMenu(XMLStreamReader reader) throws
            XMLStreamException {

        Map<Appetizer, List<Food>> menu = new HashMap<>();
        Appetizer appetizer = null;
        List<Food> foodList = null;
        Food food = null;
        MenuTagName elementName = null;
        List<String> type = null;

        while (reader.hasNext()) {

            switch (reader.next()) {

                case XMLStreamConstants.START_ELEMENT:

                    elementName = MenuTagName.valueOf(reader.getLocalName().
                            toUpperCase());

                    switch (elementName) {
                        case FOOD:
                            food = new Food();
                            food.setId(reader.getAttributeValue(null,
                                    MenuAttributeName.ID.toString().toLowerCase()));
                            break;
                        case APPETIZER:
                            appetizer = new Appetizer();
                            foodList = new ArrayList<>();
                            appetizer.setName(reader.getAttributeValue(null,
                                    MenuAttributeName.NAME.toString().toLowerCase()));
                            break;
                        case TYPE:
                            type = new ArrayList<>();
                            type.add(reader.getAttributeValue(null, MenuAttributeName.
                                    ID.toString().toLowerCase()));
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:

                    String text = reader.getText().trim();

                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case NAME:
                            food.setName(text);
                            break;
                        case PRICE:
                            type.add(text);
                            break;
                        case DESCRIPTION:
                            type.add(text);
                            break;
                        case PORTION:
                            food.setPortion(text);
                            break;
                        case PICTURE:
                            food.setPicture(text);
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:

                    elementName = MenuTagName.valueOf(reader.getLocalName()
                            .toUpperCase());

                    switch (elementName) {
                        case FOOD:
                            foodList.add(food);
                            break;
                        case APPETIZER:
                            menu.put(appetizer, foodList);
                            break;
                        case TYPE:
                            food.setTypes(type.get(0), type.get(1), type.get(2));
                            break;
                    }
                    break;
            }
        }
        return menu;
    }
}



