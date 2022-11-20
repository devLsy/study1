package paging.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.vo.MenuVO;
import paging.study.mapper.MenuMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuMapper menuMapper;

    /**
     * 등록
     * @param menuVO
     */
    @Transactional
    public void createMenu(MenuVO menuVO) throws Exception {
        menuMapper.createMenu(menuVO);
    }

    /**
     * 메뉴코드 count
     * @param menuCode
     * @return
     */
    public int findMenuCodeCnt(String menuCode) throws Exception {
        return menuMapper.findMenuCodeCnt(menuCode);
    }

    /**
     * 전체목록
     * @return
     */
    public List<MenuVO> findMenuList() {
        return menuMapper.findMenuList();
    }

    /**
     * 사용 메뉴목록
     * @return
     */
    public List<MenuVO> findUserMenuList() {
        return menuMapper.findUserMenuList();
    }

    /**
     * 상세
     * @param menuSeq
     * @return
     */
    public MenuVO findMenuDetail(Long menuSeq) {
        return menuMapper.findMenuDetail(menuSeq);
    }

    /**
     * 수정
     * @param menuSeq
     * @param menuVO
     */
    @Transactional
    public void modifyMenu(Long menuSeq, MenuVO menuVO) throws Exception {
        menuMapper.modifyMenu(menuSeq, menuVO);
    }

    /**
     * 삭제
     * @param menuSeq
     */
    @Transactional
    public void delMenu(Long menuSeq) throws Exception {
        menuMapper.delMenu(menuSeq);
    }
}
