package paging.study.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class FileVO {

    private Long fileSeq;
    private Long boardSeq;
    private String orgFileName;
    private String saveFileName;
    private String filePath;
    private String regDate;

    public FileVO(Long boardSeq, String orgFileName, String saveFileName, String filePath) {
        this.boardSeq = boardSeq;
        this.orgFileName = orgFileName;
        this.saveFileName = saveFileName;
        this.filePath = filePath;
    }
}
