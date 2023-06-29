package com.example.board.service;

import com.example.board.model.Account;
import com.example.board.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AccountRepository accountRepository;

    public boolean login(String id, String pw) {
        Optional<Account> account = Optional.ofNullable(this.accountRepository.findByIdAndPw(id,pw));
        return account.isPresent();
    }

}
