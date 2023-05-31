package com.myproject.board.service;


import com.myproject.board.dto.BoardDTO;
import com.myproject.board.dto.MemberDTO;
import com.myproject.board.entity.BoardEntity;
import com.myproject.board.entity.MemberEntity;
import com.myproject.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// DTO -> Entity (Entity class에서 작업)
// Entity -> DTO //(DTO calss에서 작업) entity클래스는 서비스 클래스 단까지만 가게한다 이프로젝트에서는
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {     //DTO객체를 Entity객체로 옮겨 담는 메서드
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() { //Entity객체를 DTO객체로 옮겨 담는 메서드
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        //위의 Entity에 담긴 것들을 DTO list로 옮겨 줘야함
        for(BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;

    }

    @Transactional  //이것을 추가해야함 실제로 만드는 메서드에 대해서 영속성 context처리
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity); //BoardDTO로 변환 -> get이니까 client에서 보려면
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO updateForm(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            return null;
        }
    }
    @Transactional
    public void update(BoardDTO boardDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardDTO.getId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();

            // 작성자 이메일 변경 방지
            boardDTO.setBoardWriter(boardEntity.getBoardWriter());

            // 게시글 내용 업데이트
            boardEntity.setBoardTitle(boardDTO.getBoardTitle());
            boardEntity.setBoardContents(boardDTO.getBoardContents());
            boardRepository.save(boardEntity.toUpdateBoardEntity(boardDTO));
        }
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
