
# 1. Trước khi có ORM
## 1.1 Làm quen với JDBC

Mục tiêu khi học JPA không cần phải biết JDCB. Tuy nhiên JPA kế thừa trên JDBC, do đó
nếu bạn muốn biết JDBC là gì thì có thể chạy file test [JDBCTest.java](src/test/java/vn/techmaster/demojpa/JDBCTest.java)

## 1.2 DAO

Data Access Object (DAO) Pattern là một trong những Pattern thuộc nhóm cấu trúc (Structural Pattern).  Mẫu thiết kế DAO được sử dụng để phân tách logic lưu trữ dữ liệu trong một lớp riêng biệt. Theo cách này, các service được che dấu về cách các hoạt động cấp thấp để truy cập cơ sở dữ liệu được thực hiện. Nó còn được gọi là nguyên tắc Tách logic (Separation of Logic).

Xem bài viết này [Hướng dẫn Java Design Pattern – DAO](https://gpcoder.com/4935-huong-dan-java-design-pattern-dao/)

Code ví dụ:
1. [Book.java mô tả entity](src/main/java/vn/techmaster/demojpa/dao/Book.java)
2. [Dao.java abstract DAO](src/main/java/vn/techmaster/demojpa/dao/Dao.java)
3. [BookDao.java implementation](src/main/java/vn/techmaster/demojpa/dao/BookDao.java)
4. [DaoTest.java unit test](src/main/java/vn/techmaster/demojpa/dao/DaoTest.java)



# 2. ORM - JPA
## 2.0 Kết nối nhiều database

### Postgresql
```
docker run --name pg -p 5432:5432 -e POSTGRES_USER=demo -e POSTGRES_PASSWORD=toiyeuhanoi123- -d postgres:14.1-alpine
```
## 2.1 Định nghĩa Entity

### @Table, @Entity

- [Primary key @Id](src/main/java/vn/techmaster/demojpa/model/id/ReadMe.md)

- [@NaturalId](src/main/java/vn/techmaster/demojpa/model/naturalid/ReadMe.md)

- [@Embeddable - @Embedded](src/main/java/vn/techmaster/demojpa/model/embedd/ReadMe.md)

## 2.2 Định nghĩa Relation
