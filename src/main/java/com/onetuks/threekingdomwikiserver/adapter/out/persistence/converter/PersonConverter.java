package com.onetuks.threekingdomwikiserver.adapter.out.persistence.converter;

import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.PersonNode;
import com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity.RelateWithRelation;
import com.onetuks.threekingdomwikiserver.domain.person.Person;
import com.onetuks.threekingdomwikiserver.domain.person.RelateWith;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

  public PersonNode toNode(Person domain) {
    return new PersonNode(
        domain.personId(),
        domain.name(),
        domain.alias(),
        domain.job(),
        domain.gender(),
        domain.nation(),
        domain.birthYear(),
        domain.deathYear(),
        toRelation(domain.relateWiths()));
  }

  public Person toDomain(PersonNode node) {
    return new Person(
        node.getPersonId(),
        node.getName(),
        node.getAlias(),
        node.getJob(),
        node.getGender(),
        node.getNation(),
        node.getBirthYear(),
        node.getDeathYear(),
        toDomain(node.getRelateWithRelations()));
  }

  private Set<RelateWithRelation> toRelation(Set<RelateWith> relateWiths) {
    return relateWiths.stream()
        .map(
            relateWith ->
                new RelateWithRelation(
                    relateWith.relateWithId(),
                    relateWith.relateWithTypes(),
                    new PersonNode(
                        relateWith.targetPersonId(),
                        relateWith.targetPersonName(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        Set.of())))
        .collect(Collectors.toSet());
  }

  private Set<RelateWith> toDomain(Set<RelateWithRelation> relations) {
    return relations.stream()
        .map(
            relation ->
                new RelateWith(
                    relation.getRelateWithId(),
                    relation.getRelateWithTypes(),
                    relation.getTargetPersonNode().getPersonId(),
                    relation.getTargetPersonNode().getName()))
        .collect(Collectors.toSet());
  }
}
