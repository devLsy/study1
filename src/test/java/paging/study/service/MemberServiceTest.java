package paging.study.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @DisplayName("insert")
    @Commit
    public void insert() {
        List<String> column = new ArrayList<>();
        column.add("user_id");
        column.add("user_password");
        column.add("name");
        // 이 방법은 컬럼과 컬럼값의 순서가 완전히 일치하는지 체크를 해봐야 된다.
        List<String> column_value = new ArrayList<>();
        column_value.add("아이언맨");
        column_value.add("비밀번호");
        column_value.add("로다주");
        memberService.insert(column, column_value);
    }

    @Test
    @DisplayName("find")
    public void find() {
        Map<String, Object> mapperParam = new HashMap<>();
        List<String> columns = new ArrayList<>();
        columns.add("user_seq");
        columns.add("user_id");
        columns.add("user_name");
        String tb = "t_user";
        memberService.findMember(columns, tb);
    }

}