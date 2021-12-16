# Embeddable - Embedded
Có những khi, chúng ta cần tái sử dụng nhiều lần 
```
.
├── Audit.java <- Cấu trúc dữ liệu sẽ dùng chung (@Embeddable)
├── LoggedUser.java 
├── Order.java <- Nhúng Audit vào (@Embedded)
└── Post.java <- Nhúng Audit vào (@Embedded)
```
Hãy vào http://localhost:8080/h2-console để xem cấu trúc bảng e_order và e_post sinh ra bao gồm các trường của [Audit.java](src/main/java/vn/techmaster/demojpa/model/embedd/Audit.java)

![](images/embedded.jpg)
