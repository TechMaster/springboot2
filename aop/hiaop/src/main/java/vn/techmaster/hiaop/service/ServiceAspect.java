package vn.techmaster.hiaop.service;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import vn.techmaster.hiaop.annotation.ReturnBody;
import vn.techmaster.hiaop.exception.FilmException;

@Aspect
@Component
public class ServiceAspect {
  private static final char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ',
      'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
      'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ',
      'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ',
      'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ',
      'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

  private static final char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O',
      'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A', 'a', 'D',
      'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a',
      'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
      'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
      'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

  @Before(value = "execution(* Account.*(..))")
  public void beforeDoSomething(JoinPoint joinPoint) {
    System.out.println("Before method:" + joinPoint.getSignature());
  }

  @After(value = "execution(* Account.*(..))")
  public void afterDoSomething(JoinPoint joinPoint) {
    System.out.println("After method:" + joinPoint.getSignature());
  }

  /*
   * Khác biệt giữa @After vs @AfterReturning
   * https://stackoverflow.com/questions/61863727/spring-aop-after-vs-
   * afterreturning-precedence
   */
  @AfterReturning("execution(* Account.doSomething(..))")
  public void afterDoSomethingReturning(JoinPoint joinPoint) {
    System.out.println("After Returning method:" + joinPoint.getSignature());
  }

  @After(value = "execution(* Account.transfer(..)) and args(amount)")
  public void afterTransfer(JoinPoint joinPoint, int amount) {
    System.out.println("After method:" + joinPoint.getSignature());
    System.out.println(amount);
  }

  // @Pointcut("execution(* vn.techmaster.hiaop.service.*.*(..))")
  @Pointcut("within(vn.techmaster.hiaop.service..*)")
  public void all_methods_in_service() {
  }

  @Before("all_methods_in_service()")
  public void advice_before_all_methods_in_service(JoinPoint joinPoint) {
    System.out.println("Before Point Cut:" + joinPoint.getSignature());
  }

  @After("all_methods_in_service()")
  public void advice_after_all_methods_in_service(JoinPoint joinPoint) {
    System.out.println("After Point Cut:" + joinPoint.getSignature());
  }


  @AfterThrowing(value = "execution(* Account.transfer(..)) and args(amount)", throwing = "ex")
  public void transfer_exception(JoinPoint joinPoint, int amount, Exception ex) {
    System.out.println(ex);
  }

  @AfterThrowing(pointcut="execution(* FilmService.*(..))", throwing = "ex")
  public void film_exception(JoinPoint joinPoint, FilmException ex) throws Throwable{
    System.out.println(ex.getMessage());
  }

  @Around("@annotation(vn.techmaster.hiaop.annotation.Benchmark)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();

    Object proceed = joinPoint.proceed();

    long executionTime = System.currentTimeMillis() - start;

    System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
    return proceed;
  }

  // https://howtodoinjava.com/spring-aop/aspectj-after-returning-annotation-example/
  @Around("@annotation(vn.techmaster.hiaop.annotation.ReturnBody)")
  public Object process_return_body(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    ReturnBody returnbody_annotation = method.getAnnotation(ReturnBody.class);
    String return_type = returnbody_annotation.value();
    Object result = joinPoint.proceed();
    String response = "";

    if (return_type.equals("JSON")) {
      Gson g = new Gson();
      response = g.toJson(result);
    } else if (return_type.equals("XML")) {
      XmlMapper xmlMapper = new XmlMapper();
      response = xmlMapper.writeValueAsString(result);
    }
    System.out.println(response);
    return result;
  }

  @Around("@annotation(vn.techmaster.hiaop.annotation.RemoveAccent) && execution (String *.*(..))")
  public String removeAccent(ProceedingJoinPoint joinPoint) throws Throwable {
    String accent_string = (String) joinPoint.proceed();    
    return ServiceAspect.removeAccent(accent_string);
  }

  public static char removeAccent(char ch) {
    int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
    if (index >= 0) {
      ch = DESTINATION_CHARACTERS[index];
    }
    return ch;
  }

  public static String removeAccent(String str) {
    StringBuilder sb = new StringBuilder(str);
    for (int i = 0; i < sb.length(); i++) {
      sb.setCharAt(i, removeAccent(sb.charAt(i)));
    }
    return sb.toString();
  }

}
