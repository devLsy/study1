package paging.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import paging.study.domain.Criteria;
import paging.study.domain.vo.UserVO;

import java.util.List;

@Mapper
public interface UserMapper {

    void createMember(UserVO memberVO);

    UserVO findMemberInfo(String userId);

    @Select("SELECT COUNT(*) FROM t_user WHERE use_yn = 'Y'")
    int findCountUseMember();

    @Select("SELECT COUNT(*) FROM t_user")
    int findCountAllUsers();

    List<UserVO> findAllMembersPaging(Criteria cri);

    List<UserVO> findUseMembersPaging(Criteria cri);

    void modifyMember(@Param("userSeq") Long userSeq, @Param("userVO") UserVO userVO);

}
