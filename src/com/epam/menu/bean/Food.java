package com.epam.menu.bean;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Food implements Serializable {


    private String id;
    private String picture;
    private String name;
    private String portion;
    private Map<String, Map<String,String >> types = new HashMap<>();


    public Food(){}

    public final Map getTypes(){
        return types;
    }

    public final void setTypes(String id, String description, String price){
        Map<String,String > type = new HashMap<>();
        type.put(description, price);
        types.put(id, type);
    }
    public final String getId() {
        return id;
    }
    public final void setId(String id) {
        this.id = id;
    }
    public final String getName() {
        return name;
    }
    public final  void setName(String name) {
        this.name = name;
    }
    public  final String getPicture() {
        return picture;
    }
    public  final void setPicture(String picture) {
        this.picture = picture;
    }
    public  final String getPortion() {
        return portion;
    }
    public  final void setPortion(String portion) {
        this.portion = portion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != null ? !id.equals(food.id) : food.id != null) return false;
        if (picture != null ? !picture.equals(food.picture) : food.picture != null)
            return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (portion != null ? !portion.equals(food.portion) : food.portion != null)
            return false;
        return types != null ? types.equals(food.types) : food.types == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        return result;
    }
}