## Bắt đầu
Trong [pom.xml](pom.xml) cần bổ xung thư viện Spring Boot AOP

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-aop</artifactId>
  <version>2.5.6</version>
</dependency>
```

Bổ xung annotation `@EnableAspectJAutoProxy` vào class được annotate bởi `@Configuration` hoặc `@SpringBootApplication`

Ý nghĩa của `@EnableAspectJAutoProxy` là kích hoạt các annotation AspectJ

## Tạo AspectJ component

Xem file [ServiceAspect.java](src/main/java/vn/techmaster/hiaop/service/ServiceAspect.java)

```java
@Aspect
@Component
public class ServiceAspect {
}
```

## Khác biệt giữa @After, @AfterReturning, @AfterThrowing


## Thứ tự các loại advise chạy

```
Before Point Cut:void vn.techmaster.hiaop.service.Account.doSomething()
Before method:void vn.techmaster.hiaop.service.Account.doSomething()
do something
After Returning method:void vn.techmaster.hiaop.service.Account.doSomething()
After method:void vn.techmaster.hiaop.service.Account.doSomething()
After Point Cut:void vn.techmaster.hiaop.service.Account.doSomething()
```

## Tham khảo
https://howtodoinjava.com/spring-aop-tutorial/
https://stackoverflow.com/questions/5714411/getting-the-java-lang-reflect-method-from-a-proceedingjoinpoint

https://stackoverflow.com/questions/21275819/how-to-get-a-methods-annotation-value-from-a-proceedingjoinpoint/21275958

https://www.baeldung.com/spring-aop-get-advised-method-info


https://howtodoinjava.com/spring-aop/aspectj-pointcut-expressions/

https://blog.jayway.com/2015/09/08/defining-pointcuts-by-annotations/

https://www.baeldung.com/spring-aop-annotation