package com.epam.menu.service.impl;

import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.dao.ParserDAO;
import com.epam.menu.dao.exeption.DAOException;
import com.epam.menu.dao.factory.DAOFactory;
import com.epam.menu.service.ParserService;
import com.epam.menu.service.exeption.ServiceException;
import com.epam.menu.service.util.ServiceTool;
import java.util.List;
import java.util.Map;


public class StAXParserServiceImpl implements ParserService {

    private final static String WRONG_REQUEST = "Some problems with the data source";

    @Override
    public Map<Appetizer, List<Food>> parseMenu(String request) throws ServiceException {
        Map<Appetizer, List<Food>> menu;

        if (ServiceTool.isRequestValid(request)) {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            ParserDAO staxParserDAO = daoObjectFactory.getStAXParser();
            try {
                menu = staxParserDAO.parseMenu(request);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            return menu;
        } else {
            throw new ServiceException(WRONG_REQUEST);
        }
    }
}
