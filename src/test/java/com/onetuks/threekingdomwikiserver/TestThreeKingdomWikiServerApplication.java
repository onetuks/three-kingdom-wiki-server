package com.onetuks.threekingdomwikiserver;

import org.springframework.boot.SpringApplication;

public class TestThreeKingdomWikiServerApplication {

  public static void main(String[] args) {
    SpringApplication.from(ThreeKingdomWikiServerApplication::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}
