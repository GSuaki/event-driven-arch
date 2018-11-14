package com.gsuaki.eventdriven.event.collaboration;

import com.gsuaki.eventdriven.Logger;

public final class EventCaseRunner {

  public static void run() throws InterruptedException {

    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    Logger.info("");

    new StockExchange("NASDAQ");

    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    Logger.info("");
    Thread.sleep(2000);

    new StockExchange("Dow Jones");

    traderTwo.placeOrder("APPLE", 500D, "Dow Jones");
    traderTwo.placeOrder("B2W", 500D, "Dow Jones");

    Logger.info("");
    Thread.sleep(2000);

    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    Logger.info("");
    Thread.sleep(3000);
    System.exit(0);
  }
}
