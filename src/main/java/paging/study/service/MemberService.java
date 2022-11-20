package paging.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.Criteria;
import paging.study.domain.vo.BoardVO;
import paging.study.mapper.BoardMapper;
import paging.study.mapper.MemberMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberMapper memberMapper;

    public void insert(List<String> column, List<String> column_value) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("column", column);
        param.put("column_value", column_value);
        memberMapper.insertMem(param);
    }

    public List<Map<String, Object>> findMember(List<String> column, String tableName) {
        Map<String, Object> mapperParam = new HashMap<String, Object>();
        mapperParam.put("column", column);
        mapperParam.put("tableName", tableName);
        return memberMapper.findMember(mapperParam);
    }





}
