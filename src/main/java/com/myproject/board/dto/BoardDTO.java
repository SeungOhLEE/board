package com.myproject.board.dto;

import com.myproject.board.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

//Data Transfer Object, VO, Bean 거의 동일한 목적       Entity는 조금 다름
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits; //조회수
    private LocalDateTime boardCreatedTime; //글작성시간
    private LocalDateTime boardUpdatedTime; //글수정시간

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId((boardEntity.getId()));
        boardDTO.setBoardWriter((boardEntity.getBoardWriter()));
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }

}
