package com.onetuks.threekingdomwikiserver.domain.person;

import lombok.Getter;

@Getter
public enum Nation {
  WEI("위"),
  SHU("촉"),
  WU("오"),
  QUN("중"),
  HAN("한"),
  JIN("진");

  private final String koreanName;

  Nation(String koreanName) {
    this.koreanName = koreanName;
  }
}
