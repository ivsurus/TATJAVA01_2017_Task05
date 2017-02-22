package com.epam.menu.controller.command.impl;

import com.epam.menu.bean.Appetizer;
import com.epam.menu.bean.Food;
import com.epam.menu.controller.command.Command;
import com.epam.menu.controller.constant.ControllerConstant;
import com.epam.menu.controller.util.ControllerTool;
import com.epam.menu.service.ParserService;
import com.epam.menu.service.exeption.ServiceException;
import com.epam.menu.service.factory.ServiceFactory;

import java.util.List;
import java.util.Map;


public final class UseStAXParser implements Command {

    @Override
    public String execute(String request) {
        request = request.split("_")[1];
        Map <Appetizer, List<Food>> menu;
        ServiceFactory serviceObjectFactory = ServiceFactory.getInstance();
        ParserService parserService = serviceObjectFactory.getStAXParserService();
        try {
            menu = parserService.parseMenu(request);
        } catch (ServiceException e) {
            return ControllerConstant.UNSUCCESSFUL_OPERATION;
        }
        return ControllerTool.createResponseForUser(menu);
    }

}
