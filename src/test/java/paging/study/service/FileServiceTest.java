package paging.study.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import paging.study.domain.vo.FileVO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class FileServiceTest {
    @Autowired FileService fileService;

    @Test
    @DisplayName("del")
    @Commit
    public void del() {
        fileService.fileDel(35L);
    }

}