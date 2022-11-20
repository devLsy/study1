package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import paging.study.domain.Criteria;
import paging.study.domain.paging.PageMaker;
import paging.study.domain.vo.UserVO;
import paging.study.service.UserService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원관리
     * @param model
     * @return
     */
    @GetMapping("/admin/user/list")
    public String AdminUserListPaging(@ModelAttribute("cri") Criteria cri, Model model) {
        List<UserVO> allMembers = userService.findAllMembersPaging(cri);
        int countAllUsers = userService.findCountAllUsers();
        model.addAttribute("list", allMembers);
        model.addAttribute("pageMaker", new PageMaker(countAllUsers, cri));
        return "member/admin/member/memberList";
    }
    
    /**
     * 회원가입 폼
     * @return
     */
    @GetMapping("/member/join")
    public String join() {
        return "member/join";
    }

    /**
     * 회원가입 
     * @param userVO
     * @return
     */
    @PostMapping("/member/join")
    public String join(@ModelAttribute("userVO")UserVO userVO) {
        userService.joinUser(userVO);
        return "redirect:/";
    }

    /**
     * 로그인 폼
     * @param userVO
     * @return
     */
    @GetMapping("/member/login")
    public String loginForm(@ModelAttribute("userVO") UserVO userVO) {
        return "member/login/login";
    }

    /**
     * 로그인 결과
     * @return
     */
    @GetMapping("/member/login/result")
    public String loginResult() {
        return "member/login/loginSuccess";
    }

    /**
     * 로그아웃 결과
     * @return
     */
    @GetMapping("/member/logout/result")
    public String logoutResult() {
        return "member/logout/logout";
    }

    /**
     * 접근거부
     * @return
     */
    @GetMapping("/member/denied")
    public String denied() {
        return "member/denied";
    }

    /**
     * 사용 회원 목록
     * @param model
     * @return
     */
    @GetMapping("/user/list")
    public String userListPaging(@ModelAttribute("cri") Criteria cri, Model model) {
        List<UserVO> list = userService.findUseMembersPaging(cri);
        int useMemberCount = userService.findCountUseMember();
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", new PageMaker(useMemberCount, cri));
        return "member/user/memberList";
    }

    /**
     * 수정
     * @param userSeq
     * @param userVO
     */
    @PostMapping("/admin/user/{userSeq}/edit")
    public void modifyUser(@PathVariable Long userSeq, UserVO userVO) {
        userService.modifyMember(userSeq, userVO);
    }
}
