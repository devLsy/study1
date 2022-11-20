package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import paging.study.service.MenuService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MenuApiController {

    private final MenuService menuService;

    @PostMapping("/admin/menu/duplicateCheck")
    public int duplicateCheck(String menuCode) {
        int count = 0;
        try {
            count = menuService.findMenuCodeCnt(menuCode);
        } catch (MyBatisSystemException me) {
            me.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


}
