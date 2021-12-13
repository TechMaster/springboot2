package vn.techmaster.demomapstruct.dto.annotation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FilmMapper {
  FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

  @ToEntity
  FilmDTO filmToDTO(Film film);
}
