`@NaturalId` đánh dấu trường có tính duy nhất như email, số di động, số ISBN, căn cước công dân.

`@NaturalId` khác gì với `@Id` ?

`@Id` là id thường do hệ thống tự sinh nó cần phải đảm bảo 2 tính chất:
1. Unique - Duy nhất.
2. Constant - Không bao giờ thay đổi. Nếu thay đổi sẽ ảnh hưởng đến rất nhiều bảng khác tham chiếu đến.

`@NaturalId` không phải id do hệ thống tự sinh. Nó có tính duy nhất.
Hầu như không đổi nhưng không có nghĩa là không bao giờ đổi.
Ví dụ người dùng thay đổi email hoặc số di động cá nhân.
Việc thay đổi giá trị `@NaturalId` cần không ảnh hưởng đến các bảng khác vì không nên dùng `@NaturalId` làm primary key.

Có thể truy vấn đối tượng từ trong bảng qua NaturalId như sau

```java
Session session = em.unwrap(Session.class);
Person p2 = session.byNaturalId(Person.class).using("email", "cuong@techmaster.vn").load();
```


https://thorben-janssen.com/naturalid-good-way-persist-natural-ids-hibernate/