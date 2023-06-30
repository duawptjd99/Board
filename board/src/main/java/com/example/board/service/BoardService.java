package com.example.board.service;

import com.example.board.model.Board;
import com.example.board.model.dto.BoardListDto;
import com.example.board.repository.AccountRepository;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final AccountRepository accountRepository;

    public List<BoardListDto> findAll() {
        List<Board> boardList = this.boardRepository.findAll();
        List<BoardListDto> boardDtoList = new ArrayList<>();
        for (int i = 0; i < boardList.size(); i++) {
            BoardListDto boardListDto = BoardListDto.builder()
                    .title(boardList.get(i).getTitle())
                    .content(boardList.get(i).getContent())
                    .createdAt(boardList.get(i).getCreatedAt())
                    .updatedAt(boardList.get(i).getUpdatedAt())
                    .userName(accountRepository.findByUserId(boardList.get(i).getUserId()).getUserName())
                    .build();
            boardDtoList.add(boardListDto);
        }
        return boardDtoList;
    }

}
