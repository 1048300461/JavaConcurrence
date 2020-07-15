package com.example.javaconcurrence.JavaReflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {
    private Map<String, Integer> score;

    public static void main(String[] args) throws Exception{
        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");
        // 直接使用getType()取出Field类型只对普通类型的Field有效
        Class<?> a = f.getType();
        // 下面将看到仅输出java.util.Map
        System.out.println("score的类型是：" + a);
        // 获得Field类型f的泛型类型
        Type gType = f.getGenericType();

        // 如果gType类型是ParameterizedType对象
        if(gType instanceof ParameterizedType){
            // 强制类型转换
            ParameterizedType pType = (ParameterizedType)gType;
            // 获取原始类型
            Type rType = pType.getRawType();
            System.out.println("原始类型是：" + rType);
            // 取得泛型类型的泛型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型类型是：");
            for(int i = 0; i < tArgs.length; i++){
                System.out.println("第" + i + "个泛型是：" + tArgs[i]);
            }
        }else{
            System.out.println("获取泛型类型出错！");
        }

        //从上面的运行结果可以看出，直接使用 Field 的 getType() 方法只能获取普通类型
        //的 Field 的数据类型：对于增加了泛型参数的类型的 Field，应该使用
        //getGenericType() 方法来取得其类型。
        //Type 也是 java.lang.reflect 包下的一个接口，该接口代表所有类型的公共高级接
        //口，Class 是 Type 接口的实现类。Type 包括原始类型、参数化类型、数组类型、
        //类型变量和基本类型等。

    }
}
