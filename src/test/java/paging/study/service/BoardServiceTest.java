package paging.study.service;

import ch.qos.logback.classic.db.names.TableName;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Columns;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.Criteria;
import paging.study.domain.vo.BoardVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
@Transactional
class BoardServiceTest {
    @Autowired BoardService boardService;
    
    @Test
    @DisplayName("insert")
    @Commit
    public void insert() {
        List<String> column = new ArrayList<>();
        List<String> column_value = new ArrayList<>();

        column.add("title");
        column.add("contents");
        column.add("name");

        column_value.add("제목입니다.");
        column_value.add("내용입니다.");
        column_value.add("작성자겠죠?");

        Map<String, Object> param = new HashMap<>();
        param.put("column", column);
        param.put("column_value", column_value);
        boardService.insertBoard(param);
    }

    @Test
    @DisplayName("findTest")
    @Commit
    public void findTest() throws Exception {
        Map<String, Object> param = new HashMap<>();
        List<Object> Columns = new ArrayList<>();
        Columns.add("board_seq");
        Columns.add("title");
        Columns.add("contents");
        String TableName = "t_board";

        param.put("column", Columns);
        param.put("tableName", TableName);
        boardService.findTest(param);
    }

    @Test
    @DisplayName("addField")
    @Commit
    public void addField() {
        //given
        boardService.addBoardField("gasi", "int");
        // when

        //then
    }
}