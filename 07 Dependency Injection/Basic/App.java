import java.lang.reflect.Method;
import java.util.List;

class App {
  public static void main(String[] args) {
    //inspectFooBar();
    //getAllClassesInClassPath();
    demoComponentScan();
  }

  public static void inspectFooBar() {
    CustomClassLoader customClassLoader = new CustomClassLoader();
    try {
      Object obj = customClassLoader.findClass("foo.Bar").getDeclaredConstructor().newInstance();
      var objClass = obj.getClass();
      Method[] methods = objClass.getDeclaredMethods();
      Class[] interfaces = objClass.getInterfaces();

      System.out.println(String.format("Methods of %s class:", obj.getClass().getName()));
      for (Method method : methods) {
        System.out.println(method.getName());
        method.invoke(obj, null);
      }

      System.out.println(String.format("Interface of %s class:", obj.getClass().getName()));
      for (Class inter_face : interfaces) {

        System.out.println(inter_face.getName());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void getAllClassesInClassPath() {
    List<Class> classes = ClasspathInspector.getAllKnownClasses();
    for (Class aClass : classes) {
      System.out.println(aClass.getName());
    }
  }

  public static void demoComponentScan() {
    List<Class> classes = ClasspathInspector.componentScan();
    for (Class aClass : classes) {
      System.out.println(aClass.getName());
    }
  }
}