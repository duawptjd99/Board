package com.example.board.model.mapper;

import com.example.board.model.Account;
import com.example.board.model.dto.AccountDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-04T05:54:19+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toAccount(AccountDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Account account = new Account();

        account.setUserId( accountDto.getUserId() );
        account.setUserEmail( accountDto.getUserEmail() );
        account.setUserPw( accountDto.getUserPw() );
        account.setUserName( accountDto.getUserName() );

        return account;
    }
}
