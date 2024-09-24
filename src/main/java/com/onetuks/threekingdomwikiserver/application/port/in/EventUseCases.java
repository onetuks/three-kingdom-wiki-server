package com.onetuks.threekingdomwikiserver.application.port.in;

import com.onetuks.threekingdomwikiserver.application.command.event.EventAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.event.EventEditCommand;
import com.onetuks.threekingdomwikiserver.domain.event.Event;

public interface EventUseCases {

  String addEvent(EventAddCommand command);

  void editEvent(EventEditCommand command);

  Event searchEventById(String eventId);

  void removeEvent(String eventId);
}
