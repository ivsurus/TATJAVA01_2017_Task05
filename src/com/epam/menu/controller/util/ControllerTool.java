package com.epam.menu.controller.util;


import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ControllerTool {

    //нужна ли тут блокировка

    public static synchronized String createResponseForUser(Map menu){
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<Appetizer, List<Food>>> foodIterator = menu.entrySet().iterator();
        while (foodIterator.hasNext()){
            Map.Entry<Appetizer, List<Food>> pair = foodIterator.next();
            builder.append(pair.getKey().getName()+"\n");
            for (Food food : pair.getValue()) {
                builder.append("FOOD ID: " + food.getId()+"\n");
                builder.append("FOOD PICTURE: " + food.getPicture()+"\n");
                builder.append("FOOD NAME: " + food.getName()+"\n");
                builder.append("FOOD PORTION: " + food.getPortion()+"\n");
                builder.append("Количество типов " + food.getTypes().size()+"\n");
                Iterator<Map.Entry<String, Map<String,String>>> typesIterator = food.getTypes().entrySet().iterator();
                while (typesIterator.hasNext()){
                    Map.Entry<String, Map<String,String>> typesPair = typesIterator.next();
                    builder.append("TYPE ID: " + typesPair.getKey()+"\n");
                    Iterator<Map.Entry<String,String>> typeIterator = typesPair.getValue().entrySet().iterator();
                    while(typeIterator.hasNext()){
                        Map.Entry<String,String> typePair = typeIterator.next();
                        builder.append("TYPE DESCRIPTION: " + typePair.getKey()+"\n");
                        builder.append("TYPE PRICE: " + typePair.getValue()+"\n");
                    }
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
