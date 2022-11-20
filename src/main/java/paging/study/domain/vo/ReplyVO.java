package paging.study.domain.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

// 댓글 용도
@Getter @Setter
@ToString
@NoArgsConstructor
public class ReplyVO {

    private Long replyId;
    private Long boardId;
    private String replyContent;
    private String replyWriter;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ReplyVO(Long boardId, String replyContent, String replyWriter) {
        this.boardId = boardId;
        this.replyContent = replyContent;
        this.replyWriter = replyWriter;
    }
}
