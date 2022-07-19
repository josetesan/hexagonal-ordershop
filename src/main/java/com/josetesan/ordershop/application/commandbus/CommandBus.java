package com.josetesan.ordershop.application.commandbus;

/** */
public interface CommandBus {

  /**
   * @param command
   * @throws Exception
   */
  void handle(Command command) throws Exception;
}
