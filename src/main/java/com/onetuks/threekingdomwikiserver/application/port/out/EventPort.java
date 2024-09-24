package com.onetuks.threekingdomwikiserver.application.port.out;

import com.onetuks.threekingdomwikiserver.domain.event.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPort {

  Event create(Event event);

  Event readById(String eventId);

  void update(Event event);

  void delete(String eventId);
}
