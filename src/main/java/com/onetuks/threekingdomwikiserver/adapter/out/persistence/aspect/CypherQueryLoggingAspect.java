package com.onetuks.threekingdomwikiserver.adapter.out.persistence.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CypherQueryLoggingAspect {

  private static final Logger log = LoggerFactory.getLogger(CypherQueryLoggingAspect.class);

  @Around("@annotation(org.springframework.data.neo4j.repository.query.Query)")
  public Object logQueryExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    if (method.isAnnotationPresent(Query.class)) {
      Query queryAnnotation = method.getAnnotation(Query.class);
      String cypherQuery = queryAnnotation.value();

      log.info("{}\n{}", method.getName(), cypherQuery);
    }

    return joinPoint.proceed();
  }
}
