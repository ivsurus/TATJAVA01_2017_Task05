package com.epam.menu.controller;


import com.epam.menu.controller.command.Command;

public final class Controller{

    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.split(" ")[0];
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }

}
