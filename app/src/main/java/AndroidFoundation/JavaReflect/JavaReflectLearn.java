package AndroidFoundation.JavaReflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class JavaReflectLearn {
    //反射机制定义
        //Java反射机制是在运行状态中，对于任意一个类，都能够知道这个类中的所有属性
        //和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取
        //的信息以及动态调用对象的方法的功能称为java语言的反射机制。
    //反射机制功能
        //1.在运行时判断任意一个对象所属的类。
        //2.在运行时构造任意一个类的对象。
        //3.在运行时判断任意一个类所具有的成员变量和方法。
        //4.在运行时调用任意一个对象的方法。
        //5.生成动态代理。

    //获得Class对象
    //第一种方式 通过Class类的静态方法——forName()来实现
    Class<?> class0;

    {
        try {
            class0 = Class.forName("com.lvr.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //第二种方式 通过类的class属性
    Class<?> class1 = Person.class;
    //第三种方式 通过对象getClass方法
    Person person = new Person();
    Class<?> class3 = person.getClass();

    //获取class对象的属性、方法、构造函数等
    //获取class对象的成员变量
        Field[] allFields = class1.getDeclaredFields(); // 获取class对象的所有属性
        Field[] publicFields = class1.getFields(); // 获取class对象的public属性
        //Field ageField = class2.getDeclaredField("age"); // 获取class指定属性
        //Field desField = class2.getField("des"); // 获取class指定的public属性
    //获取class对象的方法
        Method[] methods = class1.getDeclaredMethods(); // 获取class对象的所有声名方法
        Method[] allMethods = class1.getMethods(); // 获取class对象的所有public方法，包括父类的方法
        //Method method = class2.getMethod("info", String.class);//返回次Class对象对应类的、带指定形参列表的public方法
        //Method declaredMethod = class1.getDeclaredMethod("info", String.class);//返回次Class对象对应类的、带指定形参列表的方法
    //获取class对象的构造函数
        Constructor<?>[] allConstructors = class1.getDeclaredConstructors();//获取class对象的所有声明构造函数
        Constructor<?>[] publicConstructors = class1.getConstructors();//获取class对象public构造函数
        //Constructor<?> constructor = class1.getDeclaredConstructor(String.class);//获取指定声明构造函数
        //Constructor publicConstructor = class1.getConstructor(String.class);//获取指定声明的public构造函数
    //其他方法
        Annotation[] annotations = (Annotation[]) class1.getAnnotations();//获取class对象的所有注解
        Annotation annotation = (Annotation) class1.getAnnotation(Deprecated.class);//获取class对象指定注解
        java.lang.reflect.Type genericSuperclass = class1.getGenericSuperclass();//获取class对象的直接超类的 Type
        Type[] interfaceTypes = class1.getGenericInterfaces();//获取class对象的所有接口的type集合
    //获取class对象的信息
        boolean isPrimitive = class1.isPrimitive();//判断是否是基础类型
        boolean isArray = class1.isArray();//判断是否是集合类
        boolean isAnnotation = class1.isAnnotation();//判断是否是注解类
        boolean isInterface = class1.isInterface();//判断是否是接口类
        boolean isEnum = class1.isEnum();//判断是否是枚举类
        boolean isAnonymousClass = class1.isAnonymousClass();//判断是否是匿名内部类
        boolean isAnnotationPresent = class1.isAnnotationPresent(Deprecated.class);//判断是否被某个注解类修饰
        String className = class1.getName();//获取class名字 包含包名路径
        Package aPackage = class1.getPackage();//获取class的包信息
        String simpleName = class1.getSimpleName();//获取class类名
        int modifiers = class1.getModifiers();//获取class访问权限
        Class<?>[] declaredClasses = class1.getDeclaredClasses();//内部类
        Class<?> declaringClass = class1.getDeclaringClass();//外部类

    //通过Java反射生成并操作对象
    //生成类的实例对象：
        //1 使用Class对象的newInstance()方法（使用默认构造器）来创建该Class对象对应类的实例
        //Object object = class1.newInstance();
        //2 先使用Class对象获取指定的Constructor对象，再调用Constructor对象的
        //newInstance()方法来创建该Class对象对应类的实例。通过这种方式可以选择使用指定的构造器来创建实例。
        //Constructor<?> constructor = class1.getDeclaredConstructor(String.class);
        //obj = constructor.newInstance("Hello");

    //调用类的方法：
        //1.通过Class对象的getMethods()方法或者getMethod()方法获得指定方法，返回Method数组或对象。
        //2.调用Method对象中的 Object invoke(Object obj, Object... args) 方法。
        //第一个参数对应调用该方法的实例对象，第二个参数对应该方法的参数。
        // 生成新的对象：用newInstance()方法
        //Object obj = class1.newInstance();
        //首先需要获得与该方法对应的Method对象
        //Method method = class1.getDeclaredMethod("setAge", int.class);
        //调用指定的函数并传递参数
        //method.invoke(obj, 28);
    //当通过Method的invoke()方法来调用对应的方法时，Java会要求程序必须有调用
    //该方法的权限。如果程序确实需要调用某个对象的private方法，则可以先调用
    //Method对象的如下方法。
    //setAccessible(boolean flag)：将Method对象的acessible设置为指定的布尔值。
    //值为true，指示该Method在使用时应该取消Java语言的访问权限检查；值为
    //false，则知识该Method在使用时要实施Java语言的访问权限检查。

    //访问成员变量值
        //1.通过Class对象的getFields()方法或者getField()方法获得指定方法，返回Field数
        //组或对象。
        //2.Field提供了两组方法来读取或设置成员变量的值：
        //getXXX(Object obj):获取obj对象的该成员变量的值。此处的XXX对应8种基本类型。如果该成员变量的类型是引用类型，则取消get后面的XXX。
        //setXXX(Object obj,XXX val)：将obj对象的该成员变量设置成val值。

        //生成新的对象：用newInstance()方法
        //Object obj = class1.newInstance();
        //获取age成员变量
        //Field field = class1.getField("age");
        //将obj对象的age的值设置为10
        //field.setInt(obj, 10);
        //获取obj对象的age的值
        //field.getInt(obj);

    //代理模式：给某个对象提供一个代理对象，并由代理对象控制对于原对象的访问，即客
    //户不直接操控原对象，而是通过代理对象间接地操控原对象。
        //代理实现分为：
            //1 静态代理：代理类是在编译时就实现好的。也就是说 Java 编译完成后代理类是一个实际的 class 文件。
            //2 动态代理：代理类是在运行时生成的。也就是说 Java 编译完之后并没有实际的class 文件，而是在运行时动态生成的类字节码，并加载到JVM中。

    //使用反射来获取泛型信息
    //field.getType()只对普通类型的 Field 有效。如果该 Field 的类型是有泛型限制的类
    //型，如 Map<String, Integer> 类型，则不能准确地得到该 Field 的泛型参数。

    //为了获得指定 Field 的泛型类型，应先使用如下方法来获取指定 Field 的类型。
    // 获得 Field 实例的泛型类型
    //Type type = f.getGenericType();
    //然后将 Type 对象强制类型转换为 ParameterizedType 对象，ParameterizedType
    //代表被参数化的类型，也就是增加了泛型限制的类型。ParameterizedType 类提供了如下两个方法。
    //getRawType()：返回没有泛型信息的原始类型。
    //getActualTypeArguments()：返回泛型参数的类型。
}
