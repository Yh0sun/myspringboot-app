package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Account;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    //ctrl + shift + f10
    @Test @Disabled
    public void crud() throws Exception{

        Account account=new Account();

        account.setUsername("spring100");
        account.setPassword("1234");

        Account savedAccount = accountRepository.save(account);
        System.out.println("ID "+ savedAccount.getId());
        System.out.println("username "+ savedAccount.getUsername());
    }

    @Test @Disabled
    public void finder() throws  Exception {
        //있든 없든 체크 x
        Optional<Account> optional = accountRepository.findById(1L);
        if(optional.isPresent()) {
            Account account = optional.get();
            System.out.println("Account " + account.getUsername());
        }

        Account acct = accountRepository.findById(1L).orElse(new Account());
        System.out.println("Account " + acct.getUsername());

        //suplier의 추상메서드 T get()
        Account acct2 = accountRepository.findById(2L).orElseGet(() -> new Account());
        System.out.println("Account2 " + acct2.getUsername());

        //null 값 나오는게 싫으면 Account 생성자 함수 만들어주기 new Account("")

        //null 이면 not found 404 오류
     //   Account acct3 = accountRepository.findById(3L).orElseThrow(() ->new RuntimeException("Account Not Found"));

    }

}
