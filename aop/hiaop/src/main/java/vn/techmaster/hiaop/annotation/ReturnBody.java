package vn.techmaster.hiaop.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)// method
public @interface ReturnBody {
  String value() default "JSON";
}
