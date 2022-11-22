package paging.study.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import paging.study.domain.Criteria;
import paging.study.domain.vo.BoardVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

//    void insertBoard(BoardVO boardVO);
    // 동적으로 추가되는 필드를 DB에 저장해야 함
    void insertBoard(Map<String, Object> map);

    List<Map<String, Object>> findTest(Map<String, Object> map);

    List<BoardVO> findBoardListPaging(Criteria cri);

    BoardVO findBoardDetail(Long boardId);

    List<BoardVO> findBoardListUseYByPaging(Criteria cri);

    @Select("SELECT COUNT(*) FROM t_board")
    int findBoardCount();

    @Select("SELECT COUNT(*) FROM t_board")
    int findUseBoardCount();

    @Select("SELECT max(board_seq) FROM t_board")
    public Long findMaxBoardSeq();

    void updateBoard(Long boardSeq, BoardVO boardVO);

    @Delete("DELETE FROM t_board WHERE board_seq = #{boardSeq}")
    void deleteBoard(Long boardSeq);

//    void addBoardField(String boardField);
    void addBoardField(@Param("boardField") String boardField, @Param("menuType") String menuType);

    void delBoardField(String boardField);
}
