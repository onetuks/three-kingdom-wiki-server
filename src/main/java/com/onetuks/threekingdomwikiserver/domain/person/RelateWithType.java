package com.onetuks.threekingdomwikiserver.domain.person;

import lombok.Getter;

@Getter
public enum RelateWithType {
  SIBLING("형제"),
  SPOUSE("배우자"),
  PARENT("부모"),
  CHILD("자식"),
  GRAND_PARENT("조부모"),
  GRAND_CHILD("손주"),
  KINDRED("친척"),
  IN_LAW("사돈"),
  FRIEND("친구"),
  ENEMY("적"),
  MASTER("스승"),
  STUDENT("제자"),
  COLLEAGUE("동료"),
  RIVAL("경쟁자"),
  SUBORDINATE("부하"),
  SUPERIOR("상사"),
  OTHER("기타");

  private final String name;

  RelateWithType(String name) {
    this.name = name;
  }
}
