package paging.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.Criteria;
import paging.study.domain.vo.BoardVO;
import paging.study.mapper.BoardMapper;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    @Transactional
    public void insertBoard(Map<String, Object> map) {

        Map<String, Object> mapperParam = new HashMap<String, Object>();
        List<Object> columns = new ArrayList<>();
        List<Object> column_values = new ArrayList<>();

        for (Object obj: map.keySet()) {
            columns.add(obj);
            column_values.add(map.get(obj));
        }
        mapperParam.put("column", columns);
        mapperParam.put("column_value", column_values);

        boardMapper.insertBoard(mapperParam);
    }

    public List<Map<String, Object>> findTest(Map<String, Object> param) {
        return boardMapper.findTest(param);
    }


    public Long findMaxBoardSeq() {
        return boardMapper.findMaxBoardSeq();
    }

    public List<BoardVO> findBoardListPaging(Criteria cri) {
        return boardMapper.findBoardListPaging(cri);
    }

    public List<BoardVO> findBoardListUseY(Criteria cri) {
        return boardMapper.findBoardListUseYByPaging(cri);
    }

    public BoardVO findBoardDetail(Long boardId) {
        return boardMapper.findBoardDetail(boardId);
    }

    public int findBoardCount() {
        return boardMapper.findBoardCount();
    }

    public int findUseBoardCount() {
        return boardMapper.findUseBoardCount();
    }

    @Transactional
    public void updateBoard(Long boardSeq, BoardVO boardVO) {
        boardMapper.updateBoard(boardSeq, boardVO);
    }

    @Transactional
    public void deleteBoard(Long boardSeq) {
        boardMapper.deleteBoard(boardSeq);
    }

    @Transactional
    public void addBoardField(String boardField, String menuType) {
        boardMapper.addBoardField(boardField, menuType);
    }
    @Transactional
    public void delBoardField(String boardField) {
        boardMapper.delBoardField(boardField);
    }



}
