package com.onetuks.threekingdomwikiserver.application;

import com.onetuks.threekingdomwikiserver.application.command.event.EventAddCommand;
import com.onetuks.threekingdomwikiserver.application.command.event.EventEditCommand;
import com.onetuks.threekingdomwikiserver.application.port.in.EventUseCases;
import com.onetuks.threekingdomwikiserver.application.port.out.EventPort;
import com.onetuks.threekingdomwikiserver.domain.event.Event;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventUseCases {

  private final EventPort eventPort;

  public EventService(EventPort eventPort) {
    this.eventPort = eventPort;
  }

  @Override
  public String addEvent(EventAddCommand command) {
    return eventPort.create(new Event(null, command.eventName(), command.happenedYear())).eventId();
  }

  @Override
  public void editEvent(EventEditCommand command) {
    eventPort.update(new Event(command.eventId(), command.eventName(), command.happenedYear()));
  }

  @Override
  public Event searchEventById(String eventId) {
    return eventPort.readById(eventId);
  }

  @Override
  public void removeEvent(String eventId) {
    eventPort.delete(eventId);
  }
}
