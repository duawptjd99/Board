package com.example.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    private Long boardId;

    private String userId;

    private String contentOfComment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String deleted;

}
