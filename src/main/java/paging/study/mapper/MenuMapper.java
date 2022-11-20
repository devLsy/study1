package paging.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import paging.study.domain.vo.MenuVO;

import java.util.List;

@Mapper
public interface MenuMapper {
    //등록
    void createMenu(MenuVO menuVO);
    //전체메뉴목록
    List<MenuVO> findMenuList();
    //상세
    MenuVO findMenuDetail(Long menuSeq);
    //수정
    void modifyMenu(@Param("menuSeq") Long menuSeq, @Param("menuVO") MenuVO menuVO);

    //사용중메뉴목록
    List<MenuVO> findUserMenuList();
    
    //삭제
    void delMenu(Long menuSeq);

    //메뉴코드 cnt 조회
    int findMenuCodeCnt(String menuCode);
}
