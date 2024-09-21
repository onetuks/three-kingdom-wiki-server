package com.onetuks.threekingdomwikiserver.domain.person;

import lombok.Getter;

@Getter
public enum Job {
  EMPEROR("황제"),
  KING("왕"),
  GENERAL("장군"),
  OFFICER("관리"),
  STRATEGIST("전략가"),
  REBEL("반란군"),
  MERCHANT("상인"),
  SCHOLAR("학자"),
  PEASANT("농민"),
  EUNUCH("관료"),
  UNKNOWN("미상");

  private final String name;

  Job(String name) {
    this.name = name;
  }
}
