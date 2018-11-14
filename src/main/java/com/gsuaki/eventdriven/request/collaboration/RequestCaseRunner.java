package com.gsuaki.eventdriven.request.collaboration;

import com.gsuaki.eventdriven.Logger;

public final class RequestCaseRunner {

  public static void run() throws Exception {

    Logger.line();
    new StockExchange("Dow Jones");
    new StockExchange("NASDAQ");

    Logger.line();
    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    Logger.line();
    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    Logger.line();
    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    Thread.sleep(2000);

    Logger.line();
    traderTwo.placeOrder("APPLE", 500D, "Dow Jones");
    traderTwo.placeOrder("B2W", 500D, "Dow Jones");

    Logger.line();
    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    Thread.sleep(2000);

    Logger.line();
    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    Logger.line();
    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    Thread.sleep(3000);

    Logger.line();
    Logger.info("Closing market, was a pleasure");
    System.exit(0);
  }

  public static void runLazyInit() throws Exception {

    Logger.line();
    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    Logger.line();
    new StockExchange("NASDAQ");

    Logger.line();
    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    Logger.line();
    ServiceLocator.getExchanges().forEach(StockExchange::execute);

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

    Logger.line();
    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    Logger.line();
    Thread.sleep(2000);

    Logger.line();
    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    Logger.line();
    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    Thread.sleep(3000);

    Logger.line();
    Logger.info("Closing market, was a pleasure");
    System.exit(0);
  }
}
