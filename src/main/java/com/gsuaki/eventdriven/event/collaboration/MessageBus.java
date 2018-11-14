package com.gsuaki.eventdriven.event.collaboration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class MessageBus {

  private static final Map<String, Collection<Object>> orderEventQueue = new HashMap<>();
  private static final Map<String, Collection<Object>> executionEventQueue = new HashMap<>();

  private static final Map<String, OrderHandler> orderHandlers = new HashMap<>();
  private static final Map<String, ExecutionHandler> executionsHandlers = new HashMap<>();

  private static final Collection<ExecutorHandler> executorsHandlers = new ArrayList<>();

  static {
    Executors
        .newScheduledThreadPool(1)
        .scheduleAtFixedRate(() -> {
          try {
            if (!orderEventQueue.isEmpty() && !orderHandlers.isEmpty()) {
              orderEventQueue.forEach((key, events) -> {
                if (orderHandlers.containsKey(key)) {
                  final OrderHandler handler = orderHandlers.get(key);
                  events.forEach(order -> handler.onOrderReceive((Order) order));
                  events.clear();
                }
              });
            }
          } catch (Throwable t) {
          }
        }, 1, 1, TimeUnit.SECONDS);

    Executors
        .newScheduledThreadPool(1)
        .scheduleAtFixedRate(() -> {
          try {
            if (!executionEventQueue.isEmpty() && !executionsHandlers.isEmpty()) {
              executionEventQueue.forEach((key, events) -> {
                if (executionsHandlers.containsKey(key)) {
                  final ExecutionHandler handler = executionsHandlers.get(key);
                  events.forEach(order -> handler.onOrderExecuted((Order) order));
                  events.clear();
                }
              });
            }
          } catch (Throwable t) {
          }
        }, 1, 1, TimeUnit.SECONDS);

    Executors
        .newScheduledThreadPool(1)
        .scheduleWithFixedDelay(() -> {
          try {
            executorsHandlers.forEach(ExecutorHandler::execute);
          } catch (Throwable t) {
          }
        }, 500, 500, TimeUnit.MILLISECONDS);
  }

  public static void subscribeToOrders(final OrderHandler handler) {
    orderHandlers.put(handler.getId(), handler);
  }

  public static void subscribeToExecutions(final ExecutionHandler handler) {
    executionsHandlers.put(handler.getId(), handler);
  }

  public static void subscribeToExecutores(final ExecutorHandler handler) {
    executorsHandlers.add(handler);
  }

  public static void publishToOrders(final Order order) {
    final String key = order.getExchange();

    final Collection<Object> aDefault = orderEventQueue.getOrDefault(key, new ArrayList<>());
    aDefault.add(order);

    orderEventQueue.put(key, aDefault);
  }

  public static void publishToExecutions(final Order order) {
    final String key = order.getTrader().getId();

    final Collection<Object> aDefault = executionEventQueue.getOrDefault(key, new ArrayList<>());
    aDefault.add(order);

    executionEventQueue.put(key, aDefault);
  }

  public interface OrderHandler {
    String getId();

    void onOrderReceive(final Order order);
  }

  public interface ExecutionHandler {
    String getId();

    void onOrderExecuted(final Order order);
  }

  public interface ExecutorHandler {
    void execute();
  }
}
