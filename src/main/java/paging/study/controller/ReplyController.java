package paging.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import paging.study.domain.Criteria;
import paging.study.domain.paging.PageMaker;
import paging.study.domain.vo.ReplyVO;
import paging.study.service.ReplyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/list")
    public Map<String, Object> replyListPaging(@RequestParam(value = "boardId", required = false) Long boardId, Criteria cri) {
        Map<String, Object> map = new HashMap<>();
        List<ReplyVO> replyList = replyService.findReplyListPaging(boardId, cri);
        int replyCount = replyService.findCount(boardId);
        map.put("replyList", replyList);
        map.put("pageMaker", new PageMaker(replyCount, cri));
        return map;
    }

    @PostMapping("/reply/insert")
    public void replyInsert(ReplyVO replyVO) {
        try {
            replyService.insertReply(replyVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/reply/update")
    public void replyUpdate(@RequestParam("replyId") Long replyId, @RequestParam("replyContent") String replyContent) {
        try {
            replyService.updateReply(replyId, replyContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/reply/{replyId}/del")
    public void replyDel(@RequestParam("replyId") Long replyId) {
        try {
            replyService.delReply(replyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
