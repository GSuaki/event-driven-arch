package com.gsuaki.eventdriven;

public final class Logger {

  public static void info(final String message, final Object... data) {
    System.out.println(String.format(message, data));
  }

  public static void line() {
    System.out.println();
  }
}
