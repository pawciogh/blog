package com.example.blog.services;


import com.example.blog.models.Account;
import com.example.blog.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

@Autowired
    private AccountRepository accountRepository;

public Account save(Account account) {
        return accountRepository.save(account);
    }

}