package com.example.javaconcurrence.Genericity;

public class Container<K, V> {
    private K key;
    private V value;

    public Container(K k, V v){
        this.key = k;
        this.value = v;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setKey(K k){
        this.key = k;
    }

    public void setValue(V v){
        this.value = v;
    }

    public static void main(String[] args) {
        Container<String, String> c1 = new Container<>("name", "hello");
        Container<String, Integer> c2 = new Container<>("age", 22);
        Container<Double, Double> c3 = new Container<>(1.1, 1.3);

        System.out.println(c1.getKey() + " : " + c1.getValue());
        System.out.println(c2.getKey() + " : " + c2.getValue());
        System.out.println(c3.getKey() + " : " + c3.getValue());
    }
}
