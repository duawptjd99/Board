package com.example.board.service;

import com.example.board.factory.AccountFactory;
import com.example.board.model.Account;
import com.example.board.model.dto.AccountDto;
import com.example.board.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService {

    private final AccountRepository accountRepository;

    public boolean signUp(AccountDto accountDto) {
        Account account = AccountFactory.convertAccount(accountDto);

        if (this.accountRepository.findByUserId(accountDto.getUserId()) == null) {
            this.accountRepository.save(account);

            return true;
        }
        log.info("Exist account");

        return false;
    }
}
