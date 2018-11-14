package com.gsuaki.eventdriven.request.collaboration;

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
public class StockExchange {

  private final String id;

  private final List<Order> outstandingOrders;

  public StockExchange(final String id) {
    this.id = id;
    this.outstandingOrders = new ArrayList<>();

    Logger.info("Creating Stock Exchange %s", id);

    ServiceLocator.registerExchange(this);
  }

  public void execute() {
    Logger.info("Executing out standing orders from %s", id);

    getOutstandingOrders()
        .stream()
        .limit(3)
        .forEach(this::execute);
  }

  public void onOrderReceive(final Order order) {
    Logger.info("Placing Order %s on Stock Exchange %s from Trader %s", order.getId(), id, order.getTrader().getId());
    this.outstandingOrders.add(order);
  }

  private void execute(final Order order) {
    this.outstandingOrders.remove(order);
    ServiceLocator.traderFor(order.getTrader().getId()).onOrderExecuted(order);
  }

  public List<Order> getOutstandingOrders() {
    return Collections.unmodifiableList(new ArrayList<>(outstandingOrders));
  }
}
