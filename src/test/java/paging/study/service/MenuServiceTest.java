package paging.study.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.enu.MenuType;
import paging.study.domain.vo.MenuVO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class MenuServiceTest {

    @Autowired MenuService menuService;

    @Test
    @DisplayName("createMenu")
    @Commit
    public void createMenu() throws Exception {

        MenuVO menuVO = new MenuVO("title", "제목", MenuType.TEXT.getValue(), "Y");
        menuService.createMenu(menuVO);
    }

    @Test
    @DisplayName("delColuml")
    @Commit
    public void delColuml() throws Exception {
        //given
        menuService.delMenu(4L);

        // when

        //then
    }

    @Test
    @DisplayName("duplicateCheck")
    @Commit
    public void duplicateCheck() throws Exception {
        //givin
//        int cnt = menuService.findMenuCodeCnt("filed");
//        log.info("cnt : " + cnt);



        //when

        //then
//        assertThat(file).isEqualTo("file");
    }

}