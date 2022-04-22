package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test @Disabled
    public void crud() throws Exception{

        User user=new User();

        user.setName("yhs2");
        user.setEmail("yhs2@n.com");

        User saved = userRepository.save(user);
        System.out.println("name : "+ saved.getName());
        System.out.println("email :  "+ saved.getEmail());
    }

    @Test
    public void finder() throws  Exception {
        //있든 없든 체크 x
        Optional<User> optional = userRepository.findByEmail("yhs1@n.com");
        if(optional.isPresent()) {
            User u1 = optional.get();
            System.out.println(" yhs1 " + u1.getName());
        }

        User u2 = userRepository.findByEmail("yhs2@n.com").orElse(new User());
        System.out.println("yhs2 " + u2.getName());

        //suplier의 추상메서드 T get()
        User u3 = userRepository.findByEmail("yhs3@n.com").orElseGet(() -> new User());
        System.out.println("yhs3 " + u3.getName());

        //null 값 나오는게 싫으면 Account 생성자 함수 만들어주기 new Account("")

        //null 이면 not found 404 오류
        User u4 = userRepository.findByEmail("yhs3@n.com").orElseThrow(() ->new RuntimeException("User Not Found"));
    }
}
