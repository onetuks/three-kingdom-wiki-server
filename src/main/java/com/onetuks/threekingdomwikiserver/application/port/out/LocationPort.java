package com.onetuks.threekingdomwikiserver.application.port.out;

import com.onetuks.threekingdomwikiserver.domain.location.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationPort {

  String create(Location location);

  Location readById(String locationId);

  void update(Location location);

  void delete(String locationId);
}
