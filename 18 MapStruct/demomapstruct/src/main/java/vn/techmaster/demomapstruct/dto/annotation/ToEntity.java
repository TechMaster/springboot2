package vn.techmaster.demomapstruct.dto.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.mapstruct.Mapping;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "creationDate", expression = "java(new java.util.Date())")
public @interface ToEntity { }
