package com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.EventNode;
import com.onetuks.threekingdomwikiserver.domain.event.Event;
import org.springframework.stereotype.Component;

@Component
public class EventConverter {

  public EventNode toNode(Event domain) {
    return new EventNode(domain.eventId(), domain.eventName(), domain.happenedYear());
  }

  public Event toDomain(EventNode node) {
    return new Event(node.getEventId(), node.getEventName(), node.getHappenedYear());
  }
}
