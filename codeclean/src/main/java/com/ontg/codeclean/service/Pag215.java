package com.ontg.codeclean.service;

/**
 * Java对象多态性实现
 * 工厂创建对象
 */
interface Fruit{                    //定义一个水果的接口
    public void eat();              //定义吃水果的方法
}

class Apple implements Fruit{       //定义子类Apple

    @Override//覆写eat()方法
    public void eat() {
        System.out.println("正在吃苹果...");
    }
}

class Orange implements Fruit{      //定义子类Orange

    @Override//覆写eat()方法
    public void eat() {
        System.out.println("正在吃橙子...");
    }
}

class Banana implements Fruit{      //定义子类Banana


    @Override//覆写eat()方法
    public void eat() {
        System.out.println("正在吃香蕉...");
    }
}


//定义水果的工厂类，负责生产不同的水果类对象
class Factory{
    /**
     * 定义工厂类的获取产品对象的方法，根据用户传递的不同参数，
     * 返回不同对象
     * @param class_name
     * @return
     */
    public static Fruit getInstance(String class_name){
        Fruit fruit = null;                 //定义接口对象，初始化为null
        switch (class_name){
            case "Apple":                   //若用户选择Apple
                fruit = new Apple();        //根据对象的向上转型原理，将Apple实例赋值给fruit对象
                break;
            case "Orange":                 //若用户选择Orange
                fruit = new Orange();      //将Orange实例化对象向上转型赋值给fruit对象
                break;
            case "Banana":                  //用户选择Banana
                fruit = new Banana();       //将Banana的实例化对象向上转型赋值给fruit对象
                break;
            default:
                fruit = null;               //否则设置fruit对象为null
                break;
        }
        return fruit;           //返回生产出的fruit对象
    }
}


public class Pag215 {
    public static void main(String[] args) {
        Fruit apple = Factory.getInstance("Apple");     //生产Apple水果
        Fruit orange = Factory.getInstance("Orange");   //生产Orange水果
        Fruit banana = Factory.getInstance("Banana");   //生产Banana水果

        apple.eat();            //分别吃水果
        orange.eat();
        banana.eat();
    }
}
