package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import paging.study.domain.Criteria;
import paging.study.domain.paging.PageMaker;
import paging.study.domain.vo.BoardVO;
import paging.study.domain.vo.MenuVO;
import paging.study.service.BoardService;
import paging.study.service.FileService;
import paging.study.service.MenuService;

import javax.activation.CommandMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class
BoardController {

    private final BoardService boardService;
    private final MenuService menuService;
    private final FileService fileService;

    /**
     * content list
     * @param cri
     * @param model
     * @return
     */
    @GetMapping("admin/board/list")
    public String listPaging(@ModelAttribute("cri") Criteria cri, Model model) {
        List<BoardVO> list = boardService.findBoardListPaging(cri);
        int boardCount = boardService.findBoardCount();
        List<MenuVO> menuList = menuService.findMenuList();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", new PageMaker(boardCount, cri));
        model.addAttribute("menuList", menuList);
        return "board/boardList";
    }

    /**
     * modify form
     * @param boardSeq
     * @return
     */
    @GetMapping("/admin/board/{boardSeq}/edit")
    public String modifyForm(@PathVariable("boardSeq") Long boardSeq, Model model) {
        BoardVO findContent = boardService.findBoardDetail(boardSeq);
        model.addAttribute("boardForm", findContent);
        return "board/updateBoard";

    }

    @GetMapping("/admin/board/new")
    public String createForm(Model model) {
        List<MenuVO> menuList = menuService.findMenuList();
        model.addAttribute("boardForm", new BoardVO());
        model.addAttribute("menuList", menuList);
        return "board/createBoard";
    }

//    @PostMapping("/admin/board/new")
//    public String create(BoardVO boardVO) {
//        boardService.insertBoard(boardVO);
//        return "redirect:/admin/board/list";
//    }

    @PostMapping("/admin/board/new")
    public String create(BoardVO boardVO,
                         @RequestParam(value = "file", required = false) MultipartFile file,
                         @RequestParam Map<String, Object> map) throws IOException
    {
        boardService.insertBoard(map);
        // board_seq를 세팅해야 함(자동채번)
        // 잘 되지 않아서 우선 select 한번 더 하는걸로 임시로 변경
        Long maxBoardSeq = boardService.findMaxBoardSeq();
        
        // 화면에서 첨부파일 메뉴를 등록안 한 상태면 npe 발생
        
        if (!file.isEmpty()) {
            log.info("파일 있음");
            fileService.saveFile(maxBoardSeq, file);
        } else {
            log.info("파일 없음");
            return "redirect:/admin/board/list";
        }

        return "redirect:/admin/board/list";
    }

    @GetMapping("/admin/board/find")
    public String dynamicFindList(@RequestParam("param") Map<String, Object> param, Model model) throws Exception{
        List<Map<String, Object>> list = boardService.findTest(param);
        model.addAttribute("list", list);
        return "/board/list";
    }


    /**
     * content modify
     * @param boardSeq
     * @param boardVO
     * @return
     */
    @PostMapping("/admin/board/{boardSeq}/edit")
    public String modify(@PathVariable("boardSeq") Long boardSeq, BoardVO boardVO) {
        boardService.updateBoard(boardSeq, boardVO);
        return "redirect:/admin/board/list";
    }

    /**
     * del content
     * @param boardSeq
     * @return
     */
    @PostMapping("/board/del")
    public String del(@RequestParam("boardSeq") Long boardSeq) {
        boardService.deleteBoard(boardSeq);
        return "redirect:/board/list";
    }

    /**
     * 사용 게시글 목록
     *
     * @param model
     * @return
     */
    @GetMapping("/board/list")
    public String useBoardList(@ModelAttribute("cri") Criteria cri, Model model) {
        List<BoardVO> list = boardService.findBoardListUseY(cri);
        int useboardCount = boardService.findUseBoardCount();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", new PageMaker(useboardCount, cri));
        return "member/user/board/boardList";
    }







}
