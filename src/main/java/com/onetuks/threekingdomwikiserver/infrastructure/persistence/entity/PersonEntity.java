package com.onetuks.threekingdomwikiserver.infrastructure.persistence.entity;

import com.onetuks.threekingdomwikiserver.common.type.Gender;
import com.onetuks.threekingdomwikiserver.common.type.Job;
import com.onetuks.threekingdomwikiserver.common.type.Nation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Node(labels = "Person")
public class PersonEntity {

  @Id @GeneratedValue private Long personId;

  private String name;
  private String alias;
  private Job job;
  private Gender gender;
  private Nation nation;
  private Integer birthYear;
  private Integer deathYear;

  //  private Long documentId;
  //  private Long documentVersion;

  public PersonEntity(
      Long personId,
      String name,
      String alias,
      Job job,
      Gender gender,
      Nation nation,
      Integer birthYear,
      Integer deathYear
      //      Long documentId, Long documentVersion
      ) {
    this.personId = personId;
    this.name = name;
    this.alias = alias;
    this.job = job;
    this.gender = gender;
    this.nation = nation;
    this.birthYear = birthYear;
    this.deathYear = deathYear;
    //    this.documentId = documentId;
    //    this.documentVersion = documentVersion;
  }
}
