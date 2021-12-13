# Hướng dẫn MapStruct

## Cài đặt

Bổ xung file [pom.xml](pom.xml)
```xml
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct</artifactId>
  <version>1.5.0.Beta1</version>
</dependency>
<dependency>
  <groupId>org.mapstruct</groupId>
  <artifactId>mapstruct-processor</artifactId>
  <version>1.5.0.Beta1</version>
</dependency>
```

## Các tính năng MapStruct cung cấp khi chuyển đổi từ source sang target
1. Thay đổi tên trường
2. Thay đổi kiểu number -> string, date -> string...
3. Tính toán lại giá trị
4. Sử dụng annotation để tái sử dụng

Hãy vào file [MapStructTest.java](src/test/java/vn/techmaster/demomapstruct/MapStructTest.java)


## Tham khảo
https://www.baeldung.com/mapstruct-custom-mapper
https://www.baeldung.com/mapstruct
https://techlab.bol.com/mapstruct-optional-fields/
https://auth0.com/blog/how-to-automatically-map-jpa-entities-into-dtos-in-spring-boot-using-mapstruct/
https://springframework.guru/using-mapstruct-with-project-lombok/