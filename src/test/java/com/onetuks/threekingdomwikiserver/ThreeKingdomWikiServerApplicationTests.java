package com.onetuks.threekingdomwikiserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile(value = "test")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ThreeKingdomWikiServerApplicationTests {

  @Test
  void contextLoads() {}
}
