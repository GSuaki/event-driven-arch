package com.gsuaki.eventdriven.event.collaboration;

import com.gsuaki.eventdriven.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Wither
@AllArgsConstructor
@ToString(of = "id")
public class StockExchange implements MessageBus.OrderHandler, MessageBus.ExecutorHandler {

  private final String id;

  private final List<Order> outstandingOrders;

  public StockExchange(final String id) {
    this.id = id;
    this.outstandingOrders = new ArrayList<>();

    Logger.info("Stock Exchange %s created", id);

    MessageBus.subscribeToOrders(this);
    MessageBus.subscribeToExecutores(this);
  }

  @Override
  public void execute() {
    getOutstandingOrders()
        .stream()
        .limit(3)
        .forEach(this::execute);
  }

  @Override
  public void onOrderReceive(final Order order) {
    if (this.id.equalsIgnoreCase(order.getExchange())) {
      Logger.info("Exchange %s - Placing Order %s from Trader %s", id, order.getId(), order.getTrader().getId());
      this.outstandingOrders.add(order);
    }
  }

  private void execute(final Order order) {
    this.outstandingOrders.remove(order);
    MessageBus.publishToExecutions(order);
  }

  public List<Order> getOutstandingOrders() {
    return Collections.unmodifiableList(new ArrayList<>(outstandingOrders));
  }
}
