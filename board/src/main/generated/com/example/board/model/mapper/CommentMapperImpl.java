package com.example.board.model.mapper;

import com.example.board.model.Comment;
import com.example.board.model.dto.CommentDto;
import com.example.board.model.dto.CommentDto.CommentDtoBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-04T05:54:19+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toComment(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDto.getId() );
        comment.setBoardId( commentDto.getBoardId() );
        comment.setUserId( commentDto.getUserId() );
        comment.setContentOfComment( commentDto.getContentOfComment() );
        comment.setUpdatedAt( commentDto.getUpdatedAt() );

        comment.setCreatedAt( commentDto.getCreatedAt() != null ? commentDto.getCreatedAt() : java.time.LocalDateTime.now() );
        comment.setDeleted( com.example.board.global.enums.EBoard.EDeletionStatus.EXIST.getStatus() );

        return comment;
    }

    @Override
    public CommentDto toCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.id( comment.getId() );
        commentDto.boardId( comment.getBoardId() );
        commentDto.userId( comment.getUserId() );
        commentDto.contentOfComment( comment.getContentOfComment() );
        commentDto.createdAt( comment.getCreatedAt() );
        commentDto.updatedAt( comment.getUpdatedAt() );
        commentDto.deleted( comment.getDeleted() );

        return commentDto.build();
    }
}
