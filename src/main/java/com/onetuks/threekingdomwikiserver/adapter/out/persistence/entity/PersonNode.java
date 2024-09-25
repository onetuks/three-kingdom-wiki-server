package com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity;

import com.onetuks.threekingdomwikiserver.domain.person.Gender;
import com.onetuks.threekingdomwikiserver.domain.person.Job;
import com.onetuks.threekingdomwikiserver.domain.person.Nation;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Node(labels = "Person")
public class PersonNode {

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String personId;

  @Property(name = "name")
  private String name;

  @Property(name = "alias")
  private String alias;

  @Property(name = "job")
  private Job job;

  @Property(name = "gender")
  private Gender gender;

  @Property(name = "nation")
  private Nation nation;

  @Property(name = "birthYear")
  private Integer birthYear;

  @Property(name = "deathYear")
  private Integer deathYear;

  @Relationship(type = "RELATE_WITH", direction = Direction.OUTGOING)
  private Set<RelateWithRelation> relateWithRelations;

  public PersonNode(
      String personId,
      String name,
      String alias,
      Job job,
      Gender gender,
      Nation nation,
      Integer birthYear,
      Integer deathYear,
      Set<RelateWithRelation> relateWithRelations) {
    this.personId = personId;
    this.name = name;
    this.alias = alias;
    this.job = job;
    this.gender = gender;
    this.nation = nation;
    this.birthYear = birthYear;
    this.deathYear = deathYear;
    this.relateWithRelations = relateWithRelations;
  }
}
