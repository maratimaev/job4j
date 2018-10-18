package ru.job4j.thread;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 18.10.2018
 */
public class NewThread implements Runnable {
    Thread t;
    String name;

    NewThread(String name) {
        this.name = name;
        //t = new Thread(this, name);
        System.out.println("Child created " + t);
        //t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " Child " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " Child interrupt");
        }
        System.out.println(name + " Child stop");
    }

    public void start() {
        t = new Thread(this, name);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class ThreadDemo {


//    public static void main(String[] args) {
//        NewThread ob1 = new NewThread("one");
//        ob1.start();
//        NewThread ob2 = new NewThread("two");
//        ob2.start();
//        NewThread ob3 = new NewThread("three");
//        ob3.start();
//
//        System.out.println("start one " + ob1.t.isAlive());
//        System.out.println("start two " + ob2.t.isAlive());
//        System.out.println("start three " + ob3.t.isAlive());
//
//
//        try {
//            System.out.println("waiting");
//            ob1.t.join();
//            ob2.t.join();
//            ob3.t.join();
//        } catch (InterruptedException e) {
//            System.out.println("main interrupt");
//        }
//
//        System.out.println("start one " + ob1.t.isAlive());
//        System.out.println("start two " + ob2.t.isAlive());
//        System.out.println("start three " + ob3.t.isAlive());
//
//
//        //        try {
////            for(int i = 0; i < 5; i++) {
////                System.out.println("Main " + i);
////                Thread.sleep(500);
////            }
////        }catch (InterruptedException e) {
////            System.out.println("Main interrupted");
////        }
//        System.out.println("Main stop");
//    }
}
