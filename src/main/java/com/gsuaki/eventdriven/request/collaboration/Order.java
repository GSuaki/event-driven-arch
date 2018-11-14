package com.gsuaki.eventdriven.request.collaboration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;

import java.util.UUID;

@Getter
@Wither
@ToString(of = "id")
@AllArgsConstructor
public final class Order {

  private final UUID id;
  private final String stock;
  private final Double amount;
  private final Trader trader;

  public Order(final String stock, final Double amount, final Trader trader) {
    this.id = UUID.randomUUID();
    this.stock = stock;
    this.amount = amount;
    this.trader = trader;
  }
}
