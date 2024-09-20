package com.onetuks.threekingdomwikiserver.application.person;

import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.domain.person.PersonRepository;
import com.onetuks.threekingdomwikiserver.domain.person.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonApplicationService {

  private final PersonService personService;
  private final PersonRepository personRepository;

  public PersonApplicationService(PersonService personService, PersonRepository personRepository) {
    this.personService = personService;
    this.personRepository = personRepository;
  }

  @Transactional
  public Person createPerson(Person domain) {
    return personRepository.create(domain);
  }

  @Transactional
  public Person editPerson(long personId, Person person) {
    return personRepository.update(personService.changePersonDetails(personId, person));
  }

  @Transactional(readOnly = true)
  public Person searchPerson(long personId) {
    return personRepository.read(personId);
  }

  public void removePerson(long personId) {
    personRepository.delete(personId);
  }
}
