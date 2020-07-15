package com.example.javaconcurrence.JavaReflect;

import java.util.concurrent.ExecutionException;

public class ObjectFactory {
    //String string = (String) ObjectFactory.getInstance("java.util.Date");
    //上面代码在编译时不会有任何问题，但是运行时将抛出ClassCastException异常，
    //因为程序试图将一个Date对象转换成String对象。
    public static Object getInstance1(String name){
        try{
            // 创建指定类对应的Class对象
            Class cls = Class.forName(name);
            //返回使用该Class对象创建的实例
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    //传入一个Class<T>参数，这是一个泛型化的
    //Class对象，调用该Class对象的newInstance()方法将返回一个T对象。
    //String instance = ObjectFactory.getInstance(String.class);
    //通过传入 String.class 便知道T代表String，所以返回的对象是String类型的，避免强制类型转换。
    public static <T> T getInstance(Class<T> cls){
        try{
            // 返回使用该Class对象创建的实例
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
