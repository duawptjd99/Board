package com.example.board.model.mapper;

import com.example.board.model.Board;
import com.example.board.model.dto.BoardDto;
import com.example.board.model.dto.BoardDto.BoardDtoBuilder;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-04T05:54:19+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board toBoard(BoardDto boardDto) {
        if ( boardDto == null ) {
            return null;
        }

        Board board = new Board();

        board.setId( boardDto.getId() );
        board.setUpdatedAt( boardDto.getUpdatedAt() );
        board.setUserId( boardDto.getUserId() );
        board.setTitle( boardDto.getTitle() );

        board.setContent( com.example.board.global.utils.StringUtil.extractContent(boardDto.getContent()) );
        board.setCreatedAt( boardDto.getCreatedAt() != null ? boardDto.getCreatedAt() : java.time.LocalDateTime.now() );
        board.setDeleted( com.example.board.global.enums.EBoard.EDeletionStatus.EXIST.getStatus() );

        return board;
    }

    @Override
    public BoardDto toBoardDto(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDtoBuilder boardDto = BoardDto.builder();

        boardDto.updatedAt( board.getUpdatedAt() );
        boardDto.id( board.getId() );
        boardDto.userId( board.getUserId() );
        boardDto.title( board.getTitle() );
        boardDto.content( board.getContent() );
        boardDto.createdAt( board.getCreatedAt() );
        boardDto.deleted( board.getDeleted() );

        return boardDto.build();
    }

    @Override
    public BoardDto toBoardDto(Board board, List<String> convertList) {
        if ( board == null && convertList == null ) {
            return null;
        }

        BoardDtoBuilder boardDto = BoardDto.builder();

        if ( board != null ) {
            boardDto.updatedAt( board.getUpdatedAt() );
            boardDto.id( board.getId() );
            boardDto.userId( board.getUserId() );
            boardDto.title( board.getTitle() );
            boardDto.createdAt( board.getCreatedAt() );
            boardDto.deleted( board.getDeleted() );
        }
        boardDto.content( convertList.isEmpty() ? board.getContent() : com.example.board.global.utils.StringUtil.combineContent(board.getContent(),convertList) );

        return boardDto.build();
    }
}
