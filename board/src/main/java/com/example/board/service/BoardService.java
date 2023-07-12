package com.example.board.service;

import com.example.board.global.EConstant;
import com.example.board.model.Board;
import com.example.board.model.dto.BoardDto;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardDto findById(Long id, ArrayList<String> convertList) {
        Board board = this.boardRepository.findById(id).orElse(null);
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .userId(board.getUserId())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
        if(convertList.size()>0&&convertList.get(0).isEmpty()!=true) {
            boardDto.setContent(combineContent(board.getContent(), convertList));
        }

        return boardDto;
    }

    public String extractContent(String content) {
        Document doc = Jsoup.parse(content);
        Elements images = doc.select("img");
        for (int i = 0; i < images.size(); i++) {
            images.get(i).attr("src", "[image-" + i + "]");
        }

        return doc.toString();
    }

    public String combineContent(String content, ArrayList<String> convertList) {
        Document doc = Jsoup.parse(content);
        Elements images = doc.select("img");
        for (int i = 0; i < images.size(); i++) {
            images.get(i).attr("src", convertList.get(i).toString());
        }

        return doc.toString();
    }


    public List<BoardDto> findAll() {
        List<Board> boardList = this.boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (int i = 0; i < boardList.size(); i++) {
            BoardDto boardListDto = BoardDto.builder()
                    .id(boardList.get(i).getId())
                    .title(boardList.get(i).getTitle())
                    .content(boardList.get(i).getContent())
                    .createdAt(boardList.get(i).getCreatedAt())
                    .updatedAt(boardList.get(i).getUpdatedAt())
                    .userId(boardList.get(i).getUserId())
                    .build();
            boardDtoList.add(boardListDto);
        }

        return boardDtoList;
    }

    public Page<BoardDto> findAll(Pageable pageable) {
        Page<Board> boardPage = this.boardRepository.findAllByDeleted("Y", pageable);
        Page<BoardDto> boardPageDto = boardPage.map(m -> BoardDto.builder()
                .id(m.getId())
                .title(m.getTitle())
                .content(m.getContent())
                .userId(m.getUserId())
                .createdAt(m.getCreatedAt())
                .updatedAt(m.getUpdatedAt())
                .build());

        return boardPageDto;
    }

    public Board create(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle())
                .setContent(extractContent(boardDto.getContent()))
                .setCreatedAt(LocalDateTime.now())
                .setUserId(boardDto.getUserId())
                .setDeleted(EConstant.EDeletionStatus.exist.getStatus());

        return this.boardRepository.save(board);
    }

    public boolean delete(BoardDto boardDto) {
        try {
            Optional<Board> board = Optional.ofNullable(this.boardRepository.findById(boardDto.getId()).orElse(null));
            Board deleteBoard = board.get();
            deleteBoard.setDeleted(EConstant.EDeletionStatus.delete.getStatus());
            this.boardRepository.save(deleteBoard);

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean update(BoardDto boardDto) {
        Board board = new Board();
        board.setId(boardDto.getId())
                .setTitle(boardDto.getTitle())
                .setContent(extractContent(boardDto.getContent()))
                .setCreatedAt(this.boardRepository.findById(board.getId()).get().getCreatedAt())
                .setUpdatedAt(LocalDateTime.now())
                .setUserId(boardDto.getUserId())
                .setDeleted(EConstant.EDeletionStatus.exist.getStatus());

        try {
            this.boardRepository.save(board);

            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
