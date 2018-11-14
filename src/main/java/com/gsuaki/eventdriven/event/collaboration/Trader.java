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
public class Trader implements MessageBus.ExecutionHandler {

  private final String id;

  private final List<Order> outstandingOrders;

  public Trader(final String id) {
    this.id = id;
    this.outstandingOrders = new ArrayList<>();

    Logger.info("Trader %s created", id);

    MessageBus.subscribeToExecutions(this);
  }

  public void placeOrder(final String stock, final Double amount, final String exchange) {
    final Order order = new Order(stock, amount, exchange, this);

    Logger.info("Trader %s - Published Order %s for Exchange %s", id, order.getId(), exchange);

    this.outstandingOrders.add(order);
    MessageBus.publishToOrders(order);
  }

  @Override
  public void onOrderExecuted(final Order order) {
    if (this.outstandingOrders.contains(order)) {

      Logger.info("Trader %s - Order %s executed", id, order.getId());

      this.outstandingOrders.remove(order);
    }
  }

  public List<Order> getOutstandingOrders() {
    return Collections.unmodifiableList(outstandingOrders);
  }
}
