package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    /**
     * 메인 페이지
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }


    /**
     * 관리자 메인
     * @return
     */
    @GetMapping("/admin/main")
    public String adminIndex() {
        return "member/admin/main";
    }

    /**
     * 사용자 메인
     * @return
     */
    @GetMapping("/user/main")
    public String userIndex() {
        return "member/user/main";
    }
}
