package com.gsuaki.eventdriven.request.collaboration;

import java.util.*;

public final class ServiceLocator {

  private static final Map<String, Trader> traders = new HashMap<>();
  private static final Map<String, StockExchange> exchanges = new HashMap<>();

  public static void registerTrader(final Trader trader) {
    traders.put(trader.getId(), trader);
  }

  public static void registerExchange(final StockExchange exchange) {
    exchanges.put(exchange.getId(), exchange);
  }

  public static Trader traderFor(final String id) {
    return Optional.ofNullable(traders.get(id)).orElseThrow(() -> new RuntimeException("trader.unavailable"));
  }

  public static StockExchange exchangeFor(final String id) {
    return Optional.ofNullable(exchanges.get(id)).orElseThrow(() -> new RuntimeException("exchange.unavailable"));
  }

  public static Collection<Trader> getTraders() {
    return Collections.unmodifiableCollection(traders.values());
  }

  public static Collection<StockExchange> getExchanges() {
    return Collections.unmodifiableCollection(exchanges.values());
  }
}
