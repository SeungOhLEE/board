package com.myproject.board.controller;


import com.myproject.board.dto.BoardDTO;
import com.myproject.board.dto.MemberDTO;
import com.myproject.board.service.BoardService;
import com.myproject.board.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    @GetMapping("/board/save")
    public String saveForm() {
        return "save";
    }



    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "main";
    }

    @GetMapping("/board/")
    public String findAll(Model model) {
        //DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id, Model model) {
        /*
        *   해당 게시글의 조회수를 하나 올리고
        *   게시글 데이터를 가져와서 detail.html에 출력
        * */
        boardService.updateHits(id);      //조회수 올리기
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }



    /*@GetMapping("/board/update/{id}")
    public String editForm(@PathVariable Long id, Model model, HttpSession session) {
        BoardDTO boardDTO = boardService.findById(id);
        String boardWriter = boardDTO.getBoardWriter();
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (boardWriter != null && boardWriter.equals(loginEmail)) {
            model.addAttribute("board", boardDTO);
            return "boardupdate";
        } else {
            // 권한이 없는 사용자는 에러 페이지로 리다이렉트하거나 다른 처리를 수행할 수 있습니다.
            return "error";
        }
    }*/

    /*@PostMapping("/board/update/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute BoardDTO boardDTO, HttpSession session) {
        String boardWriter = (String) session.getAttribute("loginEmail");
        boardDTO.setBoardWriter(boardWriter);
        boardService.update(boardDTO);
        return "redirect:/board/" + id;
    }*/
    @GetMapping("/board/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.updateForm(id);
        model.addAttribute("board", boardDTO);
        return "boardupdate";
    }
    @PostMapping("/board/update/")
    public String edit(@ModelAttribute BoardDTO boardDTO) {
        boardService.update(boardDTO);
        return "redirect:/board/";
    }




    /*@PostMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        BoardDTO boardDTO = boardService.findById(id);
        String boardWriter = boardDTO.getBoardWriter();
        String loginEmail = (String) session.getAttribute("loginEmail");

        if (boardWriter != null && boardWriter.equals(loginEmail)) {
            boardService.deleteById(id);
            return "redirect:/board/";
        } else {
            // 권한이 없는 사용자는 에러 페이지로 리다이렉트하거나 다른 처리를 수행할 수 있습니다.
            return "error";
        }
    }*/

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/board/";
    }
}
