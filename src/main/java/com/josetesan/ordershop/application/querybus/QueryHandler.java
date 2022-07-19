package com.josetesan.ordershop.application.querybus;

/**
 * @param <T>
 * @param <U>
 */
public interface QueryHandler<T, U extends Query<T>> {

  /**
   * @param query
   * @return
   * @throws Exception
   */
  T handle(U query) throws Exception;
}
