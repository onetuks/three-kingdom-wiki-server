package com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Node(labels = "Event")
public class EventNode {

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String eventId;

  @Property(name = "eventName")
  private String eventName;

  @Property(name = "happenedYear")
  private Integer happenedYear;

  public EventNode(String eventId, String eventName, Integer happenedYear) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.happenedYear = happenedYear;
  }
}
