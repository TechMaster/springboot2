# Demo các kỹ thuật Java Reflection để thực hiện cơ chế Dependency Injection

Ví dụ này mới chỉ demo các chức năng:

1. Cho một chuỗi tên class, nạp vào một class đó và kiểm tra kiểu, danh sách method...
2. Liệt kê các class trong class path
3. Lấy danh sách các class có chứa annotation `@Component`

Chức năng chưa hoàn thiện inject một đối tượng theo 3 phương pháp: property , setter và constructor.

Chức năng này có thể thực hiện bằng một số phương pháp:

1. Sử dụng AspectJ để can thiệp sau khi constructor của một class thực thi.
2. Sử dụng một singleton Application Context, sau khi nạp xong các đối tượng cần thiết vào bộ nhớ, tiến hành lắp ráp các đối tượng dựa vào annotation (inject dependency). Có lẽ Spring Boot dùng phương pháp này.