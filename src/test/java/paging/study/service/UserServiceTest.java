package paging.study.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.vo.UserVO;

import javax.persistence.ManyToOne;

@SpringBootTest
@Slf4j
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    
    @Test
    @DisplayName("UserServiceTest")
    @Commit
    public void UserServiceTest() {
        //given
//        UserVO admin = userService.findMemberInfo("admin");
        UserVO userVO = new UserVO("hor", "thor", "1234", "860221", "010-1234-5678", "3", "Y");
        userService.joinUser(userVO);
        // when
//        System.out.println("admin = " + admin.toString());
        //then
//        Assertions.assertThat(admin.getUserName()).isEqualTo("슈퍼관리자");
    }

    @Test
    @DisplayName("createUser")
    @Commit
    public void createUser() throws Exception {
        //givin
        for (int i = 0; i < 2000; i++) {
            UserVO userVO = new UserVO();
            userVO.setUserId(i + "_id");
            userVO.setUserPassword("1234");
            userVO.setUserName(i + "_name");
            userVO.setUserBirth("1986" + i + "0");
            userVO.setUserPhone("010-1234-5678");
            if (i % 2 == 0) {
                userVO.setUseYn("Y");
                userVO.setUserLevel("3");
            } else {
                userVO.setUseYn("N");
                userVO.setUserLevel("2");
            }
        userService.joinUser(userVO);
        }
        //when

        //then
    }

}