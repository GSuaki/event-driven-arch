package com.gsuaki.eventdriven.request.collaboration;

public final class RequestCaseRunner {

  public static void run() {
    final StockExchange exchangeOne = new StockExchange("Dow Jones");
    final StockExchange exchangeTwo = new StockExchange("NASDAQ");

    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    traderTwo.placeOrder("APPLE", 500D, "Dow Jones");
    traderTwo.placeOrder("B2W", 500D, "Dow Jones");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);
  }

  public static void runLazyInit() {
    new StockExchange("NASDAQ");

    final Trader traderOne = new Trader("Gabriel");
    final Trader traderTwo = new Trader("Peter");

    traderOne.placeOrder("SAMSUNG", 1500D, "NASDAQ");
    traderOne.placeOrder("B2W", 1500D, "NASDAQ");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    traderOne.placeOrder("MELI", 5000D, "Dow Jones");
    traderOne.placeOrder("APPLE", 2500D, "Dow Jones");

    new StockExchange("Dow Jones");

    traderTwo.placeOrder("APPLE", 500D, "Dow Jones");
    traderTwo.placeOrder("B2W", 500D, "Dow Jones");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);

    traderOne.placeOrder("APPLE", 500D, "Dow Jones");

    ServiceLocator.getExchanges().forEach(StockExchange::execute);
  }
}
