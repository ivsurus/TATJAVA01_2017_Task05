package com.epam.menu.dao.impl;

import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.bean.menuName.MenuAttributeName;
import com.epam.menu.bean.menuName.MenuTagName;
import com.epam.menu.dao.ParserDAO;
import com.epam.menu.dao.exeption.DAOException;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DOMParserDAO implements ParserDAO{

    private final static String MENU = "menu.xml";

    @Override
    public Map<Appetizer, List<Food>> parseMenu(String request)
                                                throws DAOException {
        DOMParser parser = new DOMParser();
        Map<Appetizer, List<Food>> menu;
        try {
            parser.parse(MENU);
            Document document = parser.getDocument();
            menu = getMenu(document);
        } catch (SAXException | IOException e) {
            throw new DAOException(e);
        }
            return menu;
    }

    private Map getMenu(Document document) throws SAXException, IOException{
        Map<Appetizer, List<Food>> menu = new HashMap<>();
        Element appertizersElement = document.getDocumentElement();
        NodeList appertizerNodes = appertizersElement.getElementsByTagName
                (MenuTagName.APPETIZER.toString().toLowerCase());
        addAppetizer(menu, appertizerNodes);
        return menu;
    }

    private void addAppetizer(Map menu, NodeList appertizerNodes){
        List<Food> foodList;
        Appetizer appetizer;
        for (int i = 0; i < appertizerNodes.getLength(); i++) {
            appetizer = new Appetizer();
            Element appertizerElement = (Element) appertizerNodes.item(i);
            appetizer.setName(appertizerElement.getAttribute(MenuAttributeName
                    .NAME.toString().toLowerCase()));
            NodeList foodNodes = appertizerElement.getElementsByTagName(MenuTagName
                    .FOOD.toString().toLowerCase());
            foodList = getFoodList(foodNodes);
            menu.put(appetizer,foodList);
        }
    }

    private List<Food> getFoodList(NodeList foodNodes){
        List<Food> foodList = new ArrayList<>();
        Food food;
        for (int i = 0; i < foodNodes.getLength(); i++ ){
            food = new Food();
            Element foodElement = (Element) foodNodes.item(i);
            food.setId(foodElement.getAttribute(MenuAttributeName.ID
                    .toString().toLowerCase()));
            food.setName(getSingleChild(foodElement, MenuTagName.NAME
                    .toString().toLowerCase()).getTextContent().trim());
            food.setPicture(getSingleChild(foodElement, MenuTagName.PICTURE
                    .toString().toLowerCase()).getTextContent().trim());
            food.setPortion(getSingleChild(foodElement, MenuTagName.PORTION
                    .toString().toLowerCase()).getTextContent().trim());
            foodList.add(food);
            NodeList typeNodes = foodElement.getElementsByTagName(MenuTagName
                    .TYPE.toString().toLowerCase());
            addType(food, typeNodes);
        }
        return foodList;
    }

    private void addType(Food food, NodeList typeNodes){
        List<String> typeList;
        for (int i=0; i < typeNodes.getLength(); i++) {
            Element typeElement = (Element) typeNodes.item(i);
            typeList = new ArrayList<>();
            typeList.add(typeElement.getAttribute(MenuAttributeName.ID.toString()
                    .toLowerCase()));
            typeList.add(getSingleChild(typeElement, MenuTagName.DESCRIPTION.toString()
                    .toLowerCase()).getTextContent().trim());
            typeList.add(getSingleChild(typeElement, MenuTagName.PRICE.toString()
                    .toLowerCase()).getTextContent().trim());
            food.setTypes(typeList.get(0),typeList.get(1),typeList.get(2));
        }
    }

    private Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }
}
