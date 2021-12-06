package vn.techmaster.defaultlog.dto;

import java.util.Set;
//Sử dụng tính năng mới của Java 16, rất phù hợp cho DTO
public record FilmRequest(String id, String title, Set<String> genres) {
}
