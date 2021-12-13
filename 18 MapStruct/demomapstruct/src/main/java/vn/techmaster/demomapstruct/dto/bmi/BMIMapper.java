package vn.techmaster.demomapstruct.dto.bmi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BMIMapper {
  BMIMapper INSTANCE = Mappers.getMapper(BMIMapper.class);
    
    @Mapping(source = "height_inch", target = "height_cm", qualifiedByName = "inchToCm")
    @Mapping(source = "weight_pound", target = "weight_kg", qualifiedByName = "poundToKg")
    public BMIMetric imperial_to_metric(BMIImperial bmiImperial);
    
    @Named("inchToCm") 
    public static float inchToCm(float inch) { 
        return inch * 2.54f; 
    }

    @Named("poundToKg") 
    public static float poundToKg(float pound) { 
        return pound * 0.453592f; 
    }
}
