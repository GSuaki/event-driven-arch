package com.gsuaki.eventdriven.event.collaboration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;

import java.util.Random;

@Getter
@Wither
@ToString(of = "id")
@AllArgsConstructor
public final class Order {

  private final String id;
  private final String stock;
  private final Double amount;
  private final String exchange;
  private final Trader trader;

  public Order(final String stock, final Double amount, final String exchange, final Trader trader) {
    this.id = String.format("%d4", new Random().nextInt(10000));
    this.stock = stock;
    this.amount = amount;
    this.exchange = exchange;
    this.trader = trader;
  }
}
