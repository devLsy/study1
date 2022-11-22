package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paging.study.domain.vo.MenuVO;
import paging.study.service.BoardService;
import paging.study.service.MenuService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final MenuService menuService;
    private final BoardService boardService;

    /**
     * 전체메뉴 리스트
     * @param model
     * @return
     */
    @GetMapping("/admin/menu/list")
    public String list(Model model) {
        List<MenuVO> menuList = menuService.findMenuList();
        model.addAttribute("list", menuList);
        return "member/admin/menu/menuList";
    }

    /**
     * 사용 메뉴 목록
     * @param model
     * @return
     */
    @GetMapping("/user/menu/list")
    public String userList(Model model) {
        List<MenuVO> useMenuList = menuService.findUserMenuList();
        model.addAttribute("list", useMenuList);
        return "member/user/menu/menuList";
    }

    /**
     * 메뉴등록폼
     * @param model
     * @return
     */
    @GetMapping("/admin/menu/new")
    public String createMenuForm(Model model) {
        model.addAttribute("menuForm", new MenuVO());
        List<MenuVO> menuList = menuService.findMenuList();
        model.addAttribute("menuList", menuList);
        return "member/admin/menu/createMenu";
    }

    /**
     * 메뉴등록
     * @param menuVO
     * @return
     */
    @PostMapping("/admin/menu/new")
    public String createMenu(@ModelAttribute("menuVO") @Validated MenuVO menuVO) {
        // 메뉴코드 중복값 체크 해야 함
        try {
            menuService.findMenuCodeCnt(menuVO.getMenuCode());
            menuService.createMenu(menuVO);
            boardService.addBoardField(menuVO.getMenuCode(), menuVO.getMenuType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/menu/list";
    }

    @PostMapping("/admin/menu/{menuSeq}/del")
    public String delMenu(@PathVariable("menuSeq") Long menuSeq) throws Exception {
        MenuVO menuDetail = menuService.findMenuDetail(menuSeq);
        menuService.delMenu(menuSeq);
        boardService.delBoardField(menuDetail.getMenuCode());
        return "redirect:/admin/menu/list";
    }

}



