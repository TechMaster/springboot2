package vn.techmaster.tinyrest.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)// method
public @interface ResponseBody {
  String value();
}