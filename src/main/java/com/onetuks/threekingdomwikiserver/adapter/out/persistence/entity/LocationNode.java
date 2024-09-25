package com.onetuks.threekingdomwikiserver.adapter.out.persistence.entity;

import com.onetuks.threekingdomwikiserver.domain.location.Coordinate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Node(labels = "Location")
public class LocationNode {

  @Id
  @GeneratedValue(UUIDStringGenerator.class)
  private String locationId;

  @Property(name = "locationName")
  private String locationName;

  @Property(name = "xRate")
  private Double xRate;

  @Property(name = "yRate")
  private Double yRate;

  public LocationNode(String locationId, String locationName, Coordinate coordinate) {
    this.locationId = locationId;
    this.locationName = locationName;
    this.xRate = coordinate.xRate();
    this.yRate = coordinate.yRate();
  }
}
