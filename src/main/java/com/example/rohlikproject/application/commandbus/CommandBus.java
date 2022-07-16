package com.example.rohlikproject.application.commandbus;

/** */
public interface CommandBus {

  /**
   * @param command
   * @throws Exception
   */
  void handle(Command command) throws Exception;
}
