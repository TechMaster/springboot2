package vn.techmaster.demojpa.model.id;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RandomIDGenerator implements IdentifierGenerator {
  @Override
  public Serializable generate(
    SharedSessionContractImplementor session, Object obj) 
    throws HibernateException {
      RandomString randomString = new RandomString(10);
      return randomString.nextString();
  }
}
