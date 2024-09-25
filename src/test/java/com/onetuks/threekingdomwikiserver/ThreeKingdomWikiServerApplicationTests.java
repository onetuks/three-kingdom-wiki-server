package com.onetuks.threekingdomwikiserver;

import com.onetuks.threekingdomwikiserver.application.EventService;
import com.onetuks.threekingdomwikiserver.application.LocationService;
import com.onetuks.threekingdomwikiserver.application.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile(value = "test")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class ThreeKingdomWikiServerApplicationTests {

  @Autowired public PersonService personService;
  @Autowired public EventService eventService;
  @Autowired public LocationService locationService;
}
