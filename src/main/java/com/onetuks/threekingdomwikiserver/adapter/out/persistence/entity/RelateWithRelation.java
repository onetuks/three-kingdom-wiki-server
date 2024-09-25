package com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity;

import com.onetuks.threekingdomwikiserver.domain.person.RelateWithType;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RelationshipProperties
public class RelateWithRelation {

  @Id @GeneratedValue private Long id;

  @GeneratedValue(UUIDStringGenerator.class)
  private String relateWithId;

  @Property(name = "relateWithTypes")
  private Set<RelateWithType> relateWithTypes;

  @TargetNode private PersonNode targetPersonNode;

  public RelateWithRelation(
      String relateWithId, Set<RelateWithType> relateWithTypes, PersonNode targetPersonNode) {
    this.relateWithId = relateWithId;
    this.relateWithTypes = relateWithTypes;
    this.targetPersonNode = targetPersonNode;
  }
}
