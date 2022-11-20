package paging.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import paging.study.domain.vo.BoardVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    void insertMem(Map<String, Object> param);

    List<Map<String, Object>> findMember(Map<String, Object> param);



}
