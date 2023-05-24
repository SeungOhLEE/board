package com.myproject.board.repository;

import com.myproject.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=? 조회하고자 하는 게시글의 조회수+1 해당 쿼리 정의
    @Modifying
    @Query(value = "update  BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id") //BoardEntity기준으로 한 쿼리 b라는 약어로 :id는 param의 id와 매칭된다.
    void updateHits(@Param("id") Long id);












}
