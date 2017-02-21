package com.epam.menu.dao;


import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.dao.exeption.DAOException;

import java.util.List;
import java.util.Map;


public interface ParserDAO {
    Map<Appetizer,List<Food>> parseMenu(String request) throws DAOException;
}
