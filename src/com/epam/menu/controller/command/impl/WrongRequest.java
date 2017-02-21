package com.epam.menu.controller.command.impl;


import com.epam.menu.controller.command.Command;
import com.epam.menu.controller.constant.ControllerConstant;

public final class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return ControllerConstant.WRONG_REQUEST;
    }
}
