package com.basic.myspringboot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LamdaTest {

    @Test
    public void iterable(){
        List<User> users=Arrays.asList(new User("hs1",100), new User("hs2",200),new User("hs3",300));
        for(User user:users){
            System.out.println("user = "+user);
        }

        //Anonymous Inner 클래스 사용
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println("익명 내부 클래스 - user = "+user);
            }
        });

        //Lambda Expression
        users.forEach( user -> System.out.println("람다 - user = "+user));

        //Method Reference
        users.forEach(System.out::println);   //System.out.println(user)
    }


    @Test @Disabled
    public void runnable() throws Exception {

        //1. MyRunnable 클래스 사용
        Thread t1 = new Thread(new MyRunnable());
        t1.start();

        //2. Anonymous Inner 클래스 사용
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2. Anonymous Inner 클래스 사용");
            }
        });
        t2.start();

        //3. Lambda Expression
        //Runnable이 가진 run() 메서드를 재정의하는 구문을 람다식으로 표현
        Thread t3 = new Thread(() -> System.out.println("3. Lambda Expression"));
        t3.start();
    }

   @Setter
   @Getter
   @AllArgsConstructor
   @ToString   //모델의 정보 찍어줌 (user의 이름 나이 찍어줌) == python def __str__(self)
    class User{
       private String name;
       private int age;


// @AllArgsConstructor 대체
//       public User(String name,int age){
//           this.name=name;
//           this.age=age;
//       }
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("1. Runnable 구현 클래스 따로 만듬");
        }
    }
}