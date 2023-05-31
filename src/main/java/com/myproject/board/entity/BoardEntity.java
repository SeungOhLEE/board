package com.myproject.board.entity;

import com.myproject.board.dto.BoardDTO;
import com.myproject.board.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//DB작업
//DB의 테이블 역할을 하는 클래스 = 하나의 테이블 객체 구성 spring data jpa에서 필수
//service와 repository단에서만 사용을 하는 개념
@Entity
@Getter
@Setter
@Table(name = "board_table")  //특정 테이블 이름 설정
public class BoardEntity extends BaseEntity {
    @Id // pk 칼럼 지정, 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private  Long id;

    @Column(length = 20, nullable = false) //크기가 20 이고, null일수 있다를 false => null값 허용 x
    private String boardWriter;

    @Column //크기 255, null가능 => 기본 옵션
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;


    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {     //boardDTO에 담긴 값들을 -> boardEntity로 옮겨 담는 과정
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateBoardEntity(BoardDTO boardDTO) {
         //boardDTO에 담긴 값들을 -> boardEntity로 옮겨 담는 과정
            BoardEntity boardEntity = new BoardEntity();
            boardEntity.setId(boardDTO.getId());
            boardEntity.setBoardWriter(boardDTO.getBoardWriter());
            boardEntity.setBoardPass(boardDTO.getBoardPass());
            boardEntity.setBoardTitle(boardDTO.getBoardTitle());
            boardEntity.setBoardContents(boardDTO.getBoardContents());
            boardEntity.setBoardHits(0);
            return boardEntity;
    }
}















