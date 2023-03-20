# Universal Platform Resource Server

This is a spring boot starter with base configuration for resource server. Adding dependency to this library enables security on 
target resource server. 

Additional method security can be provided by ***@Secured***, ***@PreAuthorize*** and ***@PostAuthorize*** annotations.
This library also provides custom ***PermissionEvaluator*** which supports three levels of access levels based on permissions 
and can be configured in such way:

 - Application level:
```java
@PreAuthorize("hasPermission(null, T(com.softarex.universalplatform.domain.AccessLevel).APPLICATION, 'ANY_APPLICATION_LEVEL_PERMISSION}")
public User getUser(Integer userId) {
//          ...
          return user; 
}
```

 - Account level:
```java
@PreAuthorize("hasPermission(#accountId, T(com.softarex.universalplatform.domain.AccessLevel).APPLICATION, 'ANY_ACCOUNT_LEVEL_PERMISSION}")
public User getUser(Integer accountId, Integer userId) {
//          ...
          return user; 
}
``` 
 
 - Sub account level:
```java
@PreAuthorize("hasPermission(#subAccountId, T(com.softarex.universalplatform.domain.AccessLevel).APPLICATION, 'ANY_SUB_ACCOUNT_LEVEL_PERMISSION}")
public User getUser(Integer subAccountId, Integer userId) {
//          ...
          return user; 
}
``` 

Http security configuration and Permission evaluator can be overridden simply by providing ***@Bean*** of such type.

Maven dependency:
```xml
<dependency> 
    <groupId>com.softarex.universalplatform.security</groupId>
    <artifactId>universal-platform-resource-server</artifactId>
    <version>${version}</version>
</dependency>
```

Properties sample:
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: {uri to OAuth2 server host, ex: http://sftrx:9090}
      client:
        registration:
          universal-platform:
            provider: universal-platform
            client-id: {client-id}
            client-secret: {client-secret}
            authorization-grant-type: {supported by OAuth2.1 specification}
            redirect-uri: {"http://{host}/login/oauth2/code/{provider}"
            scope: openid,core
        provider:
          universal-platform:
            issuer-uri: {uri to OAuth2 server host, ex: http://sftrx:9090}
```