package vn.techmaster.differentdi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Foo {
  @Autowired @Lazy public Randomizer randomizer;
}
