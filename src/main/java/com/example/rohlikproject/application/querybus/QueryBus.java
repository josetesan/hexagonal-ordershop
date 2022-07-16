package com.example.rohlikproject.application.querybus;

public interface QueryBus {

  <T> T handle(Query<T> query) throws Exception;
}
