package com.gsuaki.eventdriven.request.collaboration;

import com.gsuaki.eventdriven.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;

import java.util.ArrayList;
import java.util.List;

@Getter
@Wither
@AllArgsConstructor
@ToString(of = "id")
public class Trader {

  private final String id;

  private final List<Order> outstandingOrders;

  public Trader(final String id) {
    this.id = id;
    this.outstandingOrders = new ArrayList<>();

    Logger.info("Creating Trader %s", id);

    ServiceLocator.registerTrader(this);
  }

  public void placeOrder(final String stock, final Double amount, final String exchange) {
    final Order order = new Order(stock, amount, this);

    Logger.info("Trader %s - Published Order %s for Exchange %s", id, order.getId(), exchange);

    this.outstandingOrders.add(order);
    ServiceLocator.exchangeFor(exchange).onOrderReceive(order);
  }

  public void onOrderExecuted(final Order order) {
    if (this.outstandingOrders.contains(order)) {

      Logger.info("Executed Order %s from Trader %s", id, order.getId());

      this.outstandingOrders.remove(order);
    }
  }
}
