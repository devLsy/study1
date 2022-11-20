package paging.study.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.vo.ReplyVO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
class ReplyServiceTest {

    @Autowired ReplyService replyService;

    @Test @Commit
    @DisplayName("insertReply")
    public void insertReply() {
        //given
        try {
            for (int i = 0; i < 200; i++) {
                ReplyVO replyVO = new ReplyVO(42111L, "test", "writer");
                replyService.insertReply(replyVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // when

        //then
    }

}