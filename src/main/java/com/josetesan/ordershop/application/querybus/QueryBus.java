package com.josetesan.ordershop.application.querybus;

public interface QueryBus {

  <T> T handle(Query<T> query) throws Exception;
}
