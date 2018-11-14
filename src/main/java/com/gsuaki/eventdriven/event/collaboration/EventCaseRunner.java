package com.gsuaki.eventdriven.event.collaboration;

import com.gsuaki.eventdriven.Logger;

public final class EventCaseRunner {

  public static void run() throws InterruptedException {

    Logger.line();
    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    Logger.line();
    new StockExchange("NASDAQ");

    Logger.line();
    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    Logger.line();
    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    Logger.line();
    Thread.sleep(2000);

    Logger.line();
    new StockExchange("Dow Jones");

    Logger.line();
    traderTwo.placeOrder("APPLE", 500D, "Dow Jones");
    traderTwo.placeOrder("B2W", 500D, "Dow Jones");

    Thread.sleep(2000);

    Logger.line();
    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    Thread.sleep(3000);

    Logger.line();
    Logger.info("Closing market, was a pleasure");
    System.exit(0);
  }
}
