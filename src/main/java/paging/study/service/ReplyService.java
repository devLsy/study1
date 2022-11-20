package paging.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paging.study.domain.Criteria;
import paging.study.domain.vo.ReplyVO;
import paging.study.mapper.ReplyMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyMapper replyMapper;

    @Transactional
    public void insertReply(ReplyVO replyVO) throws Exception {
        replyMapper.insertReply(replyVO);
    }

    public List<ReplyVO> findReplyListPaging(Long boardId, Criteria cri) {
        return replyMapper.findReplyListPaging(boardId, cri);
    }

    public List<ReplyVO> findListNotPaging(Long boardId) {
        return replyMapper.findListNotPaging(boardId);
    }

    public ReplyVO findReplyDetail(Long replyId) {
        return replyMapper.findReplyDetail(replyId);
    }

    public int findCount(Long replyId) {
        return replyMapper.findCount(replyId);
    }

    @Transactional
    public void updateReply(Long replyId, String  replyContent) throws Exception{
        replyMapper.updateReply(replyId, replyContent);
    }

    @Transactional
    public void delReply(Long replyId) throws Exception{
        replyMapper.delReply(replyId);
    }

}
