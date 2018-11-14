package com.gsuaki.eventdriven.request.collaboration;

import com.gsuaki.eventdriven.Logger;

public final class RequestCaseRunner {

  public static void run() throws Exception {
    new StockExchange("Dow Jones");
    new StockExchange("NASDAQ");

    Logger.info("");

    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    Logger.info("");

    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    Logger.info("");
    Thread.sleep(2000);

    traderTwo.placeOrder("APPLE", 500D, "Dow Jones");
    traderTwo.placeOrder("B2W", 500D, "Dow Jones");

    Logger.info("");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    Logger.info("");
    Thread.sleep(2000);

    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    Logger.info("");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);
  }

  public static void runLazyInit() {
    new StockExchange("NASDAQ");

    Logger.info("");

    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    Logger.info("");

    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    Logger.info("");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    Logger.info("");

    new StockExchange("Dow Jones");
  }
}
