package vn.techmaster.demomapstruct.dto.simple;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPersonMapper {
  UserPersonMapper INSTANCE = Mappers.getMapper(UserPersonMapper.class);
  @Mapping(target="first_name", source="firstname")
  @Mapping(target="last_name", source="lastname")
  Person UserToPerson(User user);
}
