package com.onetuks.threekingdomwikiserver.infrastructure.persistence.repository;

import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.domain.person.PersonRepository;
import com.onetuks.threekingdomwikiserver.infrastructure.persistence.converter.PersonConverter;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Repository;

@Repository
public class PersonEntityRepository implements PersonRepository {

  private final PersonEntityNeo4jRepository repository;
  private final PersonConverter converter;

  public PersonEntityRepository(PersonEntityNeo4jRepository repository, PersonConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @Override
  public Person create(Person person) {
    return converter.toDomain(repository.save(converter.toEntity(person)));
  }

  @Override
  public Person read(Long personId) {
    return converter.toDomain(
        repository
            .findById(personId)
            .orElseThrow(() -> new NoSuchElementException("Person not found")));
  }

  @Override
  public Person update(Person person) {
    return converter.toDomain(repository.save(converter.toEntity(person)));
  }

  @Override
  public void delete(Long personId) {
    repository.deleteById(personId);
  }
}
