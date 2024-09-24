package com.onetuks.threekingdomwikiserver.adapter.out.persistence;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter.EventConverter;
import com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository.EventNeo4jRepository;
import com.onetuks.threekingdomwikiserver.application.port.out.EventPort;
import com.onetuks.threekingdomwikiserver.domain.event.Event;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Repository;

@Repository
public class EventNeo4jAdapter implements EventPort {

  private final EventNeo4jRepository repository;
  private final EventConverter converter;

  public EventNeo4jAdapter(EventNeo4jRepository repository, EventConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @Override
  public Event create(Event event) {
    return converter.toDomain(repository.save(converter.toNode(event)));
  }

  @Override
  public Event readById(String eventId) {
    return converter.toDomain(
        repository
            .findById(eventId)
            .orElseThrow(() -> new NoSuchElementException("Event not found")));
  }

  @Override
  public void update(Event event) {
    repository.save(converter.toNode(event));
  }

  @Override
  public void delete(String eventId) {
    repository.deleteById(eventId);
  }
}
