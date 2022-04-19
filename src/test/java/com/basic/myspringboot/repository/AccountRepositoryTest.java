package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test //ctrl + shift + f10
    public void crud() throws Exception{

        Account account=new Account();

        account.setUsername("spring3");
        account.setPassword("1234");

        Account savedAccount = accountRepository.save(account);
        System.out.println("ID "+ savedAccount.getId());
        System.out.println("username "+ savedAccount.getUsername());
    }
}
