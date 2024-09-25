package com.onetuks.threekingdomwikiserver.adapter.out.persistence;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter.PersonConverter;
import com.onetuks.threekingdomwikiserver.adapter.out.persistence.repository.PersonNeo4jRepository;
import com.onetuks.threekingdomwikiserver.application.port.out.PersonPort;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Repository;

@Repository
public class PersonNeo4jAdapter implements PersonPort {

  private final PersonNeo4jRepository repository;
  private final PersonConverter converter;

  public PersonNeo4jAdapter(PersonNeo4jRepository repository, PersonConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @Override
  public Person create(Person person) {
    return converter.toDomain(repository.save(converter.toNode(person)));
  }

  @Override
  public Person readById(String personId) {
    return converter.toDomain(
        repository
            .findById(personId)
            .orElseThrow(() -> new NoSuchElementException("Person not found")));
  }

  @Override
  public void update(Person person) {
    converter.toDomain(repository.save(converter.toNode(person)));
  }

  @Override
  public void delete(String personId) {
    repository.deleteById(personId);
  }
}
