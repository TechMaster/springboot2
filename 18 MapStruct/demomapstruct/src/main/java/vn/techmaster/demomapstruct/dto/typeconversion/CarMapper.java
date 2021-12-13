package vn.techmaster.demomapstruct.dto.typeconversion;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = UUID.class)
public interface CarMapper {
  CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

  @Mapping(source = "price", target = ".",  numberFormat = "$#.00")
  @Mapping(source = "manufactured_date", target = ".", dateFormat = "dd.MM.yyyy")
  @Mapping(target = "id", defaultExpression = "java( UUID.randomUUID().toString() )")
  CarDTO carToCarDto(Car car);
}
