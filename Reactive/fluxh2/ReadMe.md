# WebFlux + JPA + H2

Chúng ta dùng WebFlux vì non-blocking I/O
Tuy nhiên thực tế JPA + H2 lại là blocking I/O.

Trong bài viết này [Concurrency in Spring WebFlux](https://www.baeldung.com/spring-webflux-concurrency) có đoạn viết

> We have seen so far that reactive programming really shines in a completely non-blocking environment with just a few threads. But, this also means that, if there is indeed a part that is blocking, it will result in far worse performance. This is because a blocking operation can freeze the event loop entirely.

Dịch ra tiếng Việt
> Chúng ta đã thấy lập trình reactive thực sự toả sáng trong môi trường hoàn toàn non-blocking với chỉ vài threads. Tuy nhiên điều đó đồng nghĩa với thực tế nếu có một thành phần block thì tốc độ tổng thể sẽ trở nên rất tệ hại vì tác vụ blocking sẽ làm tắc nghẽn cả event-loop !
> 
Trong ví dụ này tôi sử dụng WebFlux nhưng vẫn còn phần Controller sử dụng khai báo @RestController cổ điển và JPA kết nối H2.

Chúng ta cần chuyển toàn bộ các thành phần blocking sang non-blocking