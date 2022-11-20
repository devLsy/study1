package paging.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import paging.study.domain.Criteria;
import paging.study.domain.vo.ReplyVO;

import java.util.List;

@Mapper
public interface ReplyMapper {

    void insertReply(ReplyVO replyVO);

    List<ReplyVO> findReplyListPaging(@Param("boardId") Long boardId, @Param("cri") Criteria cri);

    List<ReplyVO> findListNotPaging(Long boardId);

    int findCount(Long boardId);

    ReplyVO findReplyDetail(Long replyId);

    void updateReply(@Param("replyId") Long replyId, @Param("replyContent") String replyContent);

    void delReply(Long replyId);
}
