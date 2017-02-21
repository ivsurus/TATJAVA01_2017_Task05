package com.epam.menu.service;


import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.service.exeption.ServiceException;

import java.util.List;
import java.util.Map;


public interface ParserService {

    Map<Appetizer,List<Food>> parseMenu(String request) throws ServiceException;
}
