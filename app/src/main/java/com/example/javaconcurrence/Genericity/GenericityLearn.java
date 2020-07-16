package com.example.javaconcurrence.Genericity;

import java.util.ArrayList;

public class GenericityLearn {
    // 语法糖(Syntactic Sugar)：Java中最常用的语法糖主要有泛型、边长参数、条件编译、自动拆装箱、内部类等

    // 泛型的目的：Java 泛型就是把一种语法糖，通过泛型使得在编译阶段完成一些类
    //型转换的工作，避免在运行时强制类型转换而出现 ClassCastException ，即类型转换异常。

    // 泛型的好处：
    //①类型安全。类型错误现在在编译期间就被捕获到了，而不是在运行时当作
    //java.lang.ClassCastException展示出来，将类型检查从运行时挪到编译时有助于开
    //发者更容易找到错误，并提高程序的可靠性。
    //②消除了代码中许多的强制类型转换，增强了代码的可读性。
    //③为较大的优化带来了可能。

    // 泛型类派生子类
    //当创建了带泛型声明的接口、父类之后，可以为该接口创建实现类，或者从该父类
    //派生子类，需要注意：使用这些接口、父类派生子类时不能再包含类型形参，需要
    //传入具体的类型。
    //错误的方式：
    //public class A extends Container<K, V>{}
    //正确的方式：
    //public class A extends Container<Integer, String>{}
    //也可以不指定具体的类型，此时系统会把K,V形参当成Object类型处理。
    //public class A extends Container{}

    // 泛型构造器
    /*public class Person{
        public <T> Person(T t){
            System.out.println(t);
        }
    }*/
    //隐式
    //new Person(22);
    //显示
    //new<String> Person("hello")

    //Person<String> a = new <Integer>Person<>(15); 这种语法不允许，会直接编译报错！
    //public class Person<E> {
    //  public <T> Person(T t) {
    //      System.out.println(t);
    //  }
    //}

    //类型通配符?
    //List<?> c = new ArrayList<String>();
    //编译器报错
    //c.add(new Object());
        //但是并不能把元素加入到其中。因为程序无法确定c集合中元素的类型，所以不能向其添加对象。

        // 上限通配符
            //如果想限制使用泛型类别时，只能用某个特定类型或者是其子类型才能实例化该类
            //型时，可以在定义类型时，使用extends关键字指定这个类型必须是继承某个类，
            //或者实现某个接口，也可以是这个类或接口本身。
            //它表示集合中的所有元素都是Shape类型或者其子类
            //List<? extends Shape>
        // 下线通配符
            //如果想限制使用泛型类别时，只能用某个特定类型或者是其父类型才能实例化该类
            //型时，可以在定义类型时，使用super关键字指定这个类型必须是是某个类的父
            //类，或者是某个接口的父接口，也可以是这个类或接口本身。
            //它表示集合中的所有元素都是Circle类型或者其父类
            //List <? super Circle>

    // 类型擦除
    Class c1 = new ArrayList<Integer>().getClass();
    Class c2 = new ArrayList<String>().getClass();
    // 程序输出true
    //从Java泛型这一概念提出的目
    //的来看，其只是作用于代码编译阶段，在编译过程中，对于正确检验泛型结果后，
    //会将泛型的相关信息擦出，也就是说，成功编译过后的class文件中是不包含任何泛
    //型信息的。泛型信息不会进入到运行时阶段。

    //在静态方法、静态初始化块或者静态变量的声明和初始化中不允许使用类型形参。
    //由于系统中并不会真正生成泛型类，所以instanceof运算符后不能使用泛型类。
}
