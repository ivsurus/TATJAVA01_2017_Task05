package com.epam.menu.controller;

import com.epam.menu.controller.command.Command;
import com.epam.menu.controller.command.CommandName;
import com.epam.menu.controller.command.impl.UseDOMParser;
import com.epam.menu.controller.command.impl.UseSAXParser;
import com.epam.menu.controller.command.impl.UseStAXParser;
import com.epam.menu.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

   private final Map<CommandName, Command> repository = new HashMap<>();

   CommandProvider() {
       repository.put(CommandName.STAX, new UseStAXParser());
       repository.put(CommandName.SAX, new UseSAXParser());
       repository.put(CommandName.DOM, new UseDOMParser());
       repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
   }

   Command getCommand(String name){
       CommandName commandName;
       Command command;
       try{
           commandName = CommandName.valueOf(name.toUpperCase());
           command = repository.get(commandName);
       }catch(IllegalArgumentException | NullPointerException e){
           command = repository.get(CommandName.WRONG_REQUEST);
       }
       return command;
   }
}
