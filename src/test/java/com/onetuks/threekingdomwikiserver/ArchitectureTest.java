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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

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
    @DisplayName("web 패키지 안에 있는 클래스는 RestController 로 끝난다.")
    void controller_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..web")
              .should()
              .haveSimpleNameEndingWith("RestController")
              .andShould()
              .beAnnotatedWith(RestController.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("application 패키지 안에 있는 클래스는 ApplicationService 로 끝난다.")
    void service_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..application..")
              .should()
              .haveSimpleNameEndingWith("ApplicationService")
              .andShould()
              .beAnnotatedWith(Service.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("repository 패키지 안에 있는 클래스는 Repository 로 끝난다.")
    void repository_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..repository")
              .and()
              .haveSimpleNameNotContaining("Neo4j")
              .should()
              .haveSimpleNameEndingWith("Repository")
              .andShould()
              .beAnnotatedWith(Repository.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("converter 패키지 안에 있는 클래스는 Converter 로 끝난다.")
    void converter_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..converter")
              .should()
              .haveSimpleNameEndingWith("Converter")
              .andShould()
              .beAnnotatedWith(Component.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("config 패키지 안에 있는 클래스는 Config 로 끝난다.")
    void config_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..config")
              .should()
              .haveSimpleNameEndingWith("Config")
              .andShould()
              .beAnnotatedWith(Configuration.class)
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("dto 패키지 안에 있는 클래스는 Response(s) 혹은 Request 로 끝난다.")
    void response_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..dto")
              .should()
              .haveNameMatching(".*(Response|Responses|Request)$")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("entity 패키지 안에 있는 클래스는 Entity 로 끝난다.")
    void entity_ClassNamePostfix_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..entity")
              .should()
              .haveSimpleNameEndingWith("Entity")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }
  }

  @Nested
  class MethodNameTest {

    @Test
    @DisplayName("Controller 에서는 get, post, patch, put, delete 로 시작하는 메서드 이름을 사용한다.")
    void controller_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .haveSimpleNameEndingWith("Controller")
              .should()
              .haveNameMatching("^(get|post|patch|put|delete).*")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("ApplicationService 에서는 create, search, edit, remove 로 시작하는 메서드 이름을 사용한다.")
    void applicationService_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .haveSimpleNameEndingWith("ApplicationService")
              .should()
              .haveNameMatching("^(create|search|edit|remove).*")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName(
        "Repository 에서는 create, read, update, delete, find, count, exists 로 시작하는 메서드 이름을 사용한다.")
    void repository_MethodNamePrefix_Test() {
      ArchRule rule =
          ArchRuleDefinition.methods()
              .that()
              .arePublic()
              .and()
              .areDeclaredInClassesThat()
              .resideInAPackage("..repository")
              .should()
              .haveNameMatching("^(create|read|update|delete|find|count|exists).*")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }
  }

  @Nested
  class DependencyTest {

    @Test
    @DisplayName("domain 패키지는 오직 type 패키지만 의존한다")
    void domain_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAPackage("..domain")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage("..type", "..springframework")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("application 패키지는 domain 패키지와 persistence 패키지만 의존한다")
    void application_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAPackage("..application")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage("..domain", "..persistence")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("persistence 패키지는 domain 패키지만 의존한다")
    void infrastructure_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAPackage("..persistence")
              .should()
              .onlyDependOnClassesThat()
              .resideInAnyPackage("..domain", "..persistence", "..springframework", "..lombok")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("web 패키지는 persistence 패키지는 의존하지 않는다")
    void controller_DependOn_Test() {
      ArchRule rule =
          ArchRuleDefinition.noClasses()
              .that()
              .resideInAPackage("..web")
              .should()
              .dependOnClassesThat()
              .resideInAPackage("..persistence")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("Entity 는 persistence 패키지에서만 의존한다")
    void entity_HaveDependency_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..entity")
              .should()
              .onlyHaveDependentClassesThat()
              .resideInAnyPackage("..persistence..")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }

    @Test
    @DisplayName("dto 는 오직 web 패키지에서만 의존한다")
    void dto_HaveDependency_Test() {
      ArchRule rule =
          ArchRuleDefinition.classes()
              .that()
              .resideInAnyPackage("..dto")
              .should()
              .onlyHaveDependentClassesThat()
              .resideInAnyPackage("..web")
              .allowEmptyShould(true);

      rule.check(javaClasses);
    }
  }
}
