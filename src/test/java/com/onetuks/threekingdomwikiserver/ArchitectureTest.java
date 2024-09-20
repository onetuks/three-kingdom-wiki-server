package com.onetuks.threekingdomwikiserver;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

class ArchitectureTest extends ThreeKingdomWikiServerApplicationTests {

  JavaClasses javaClasses;

  @BeforeEach
  void setUp() {
    javaClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests()) // 테스트 클래스는 이 검증에서 제외
            .importPackages(getClass().getPackageName());
  }

  @Nested
  class ClassNameTest {

    @Test
    @DisplayName("controller 패키지 안에 있는 클래스는 Controller로 끝난다.")
    void controller_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..controller")
              .should()
              .haveSimpleNameEndingWith("Controller")
              .andShould()
              .beAnnotatedWith(Controller.class);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("service 패키지 안에 있는 클래스는 Service로 끝난다.")
    void service_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..service")
              .should()
              .haveSimpleNameEndingWith("Service")
              .andShould()
              .beAnnotatedWith(Service.class)
              .orShould()
              .beAnnotatedWith(Component.class);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("repository 패키지 안에 있는 클래스는 Repository로 끝난다.")
    void repository_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..repository")
              .should()
              .haveSimpleNameEndingWith("Repository")
              .andShould()
              .beAnnotatedWith(Repository.class);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("config 패키지 안에 있는 클래스는 Config로 끝난다.")
    void config_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..config")
              .should()
              .haveSimpleNameEndingWith("Config")
              .andShould()
              .beAnnotatedWith(Configuration.class);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("request 패키지 안에 있는 클래스는 Request로 끝난다.")
    void request_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..request")
              .should()
              .haveSimpleNameEndingWith("Request");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("response 패키지 안에 있는 클래스는 Response로 끝난다.")
    void response_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..response")
              .should()
              .haveSimpleNameEndingWith("Response")
              .orShould()
              .haveSimpleNameEndingWith("Responses");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("param 패키지 안에 있는 클래스는 Param로 끝난다.")
    void param_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..param")
              .should()
              .haveSimpleNameEndingWith("Param");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("result 패키지 안에 있는 클래스는 Result로 끝난다.")
    void result_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..result")
              .should()
              .haveSimpleNameEndingWith("Result");

      rule.check(javaClasses);
    }
  }

  @Nested
  class MethodNameTest {

    @Test
    @DisplayName("Controller에서는 get, post, patch, put, delete로 시작하는 메서드 이름을 사용한다.")
    void controller_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .resideInAPackage("..controller")
              .should()
              .haveNameMatching("^(get|post|patch|put|delete).*");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("Repository에서는 create, read, update, delete, find, count, exists로 시작하는 메서드 이름을 사용한다.")
    void repository_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .resideInAPackage("..repository")
              .should()
              .haveNameMatching("^(create|read|update|delete|find|count|exists).*");

      rule.check(javaClasses);
    }
  }

  @Nested
  class DependencyTest {

    @Test
    @DisplayName("Service는 Controller를 의존하면 안 된다")
    void service_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.noClasses()
              .that()
              .resideInAPackage("..service")
              .should()
              .dependOnClassesThat()
              .resideInAPackage("..controller");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("Entity는 오직 Repository와 Converter에 의해서만 의존한다")
    void entity_HaveDependency_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..entity")
              .should()
              .onlyHaveDependentClassesThat()
              .resideInAnyPackage(
                  "..repository..", "..converter..", "..entity..", "..vo..", "..fixture..");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("Entity 는 entity, vo, enum 이외에 아무것도 의존하지 않는다.")
    void entity_NoDependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..entity")
              .and()
              .haveSimpleNameNotStartingWith("Q")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage(
                  "java..",
                  "jakarta..",
                  "lombok..",
                  "..hibernate..",
                  "..entity..",
                  "..vo..",
                  "..enums..",
                  "..common..",
                  "..annotation..");

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("Controller 는 Service 만 의존한다.")
    void controller_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAPackage("..controller")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage(
                  "..service",
                  "..dto..",
                  "..model",
                  "..util..",
                  "..enums..",
                  "..springframework..",
                  "..slf4j..",
                  "jakarta..",
                  "java..");

      rule.check(javaClasses);
    }
  }
}
