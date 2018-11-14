package com.gsuaki.eventdriven;

import com.gsuaki.eventdriven.event.collaboration.EventCaseRunner;
import com.gsuaki.eventdriven.request.collaboration.RequestCaseRunner;

import java.util.stream.Stream;

public class Application {

  public static void main(final String args[]) throws Exception {

    if (Stream.of(args).anyMatch("--request"::equalsIgnoreCase)) {
      Logger.info("\n\n\nRunning request collaboration scenario");
      RequestCaseRunner.run();
    }

    if (Stream.of(args).anyMatch("--request-lazy"::equalsIgnoreCase)) {
      Logger.info("\n\n\nRunning request collaboration scenario");
      RequestCaseRunner.runLazyInit();
    }

    if (Stream.of(args).anyMatch("--event"::equalsIgnoreCase)) {
      Logger.info("\n\n\nRunning event collaboration scenario");
      EventCaseRunner.run();
    }
  }
}
