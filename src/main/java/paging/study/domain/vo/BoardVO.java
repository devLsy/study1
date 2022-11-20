package paging.study.domain.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@ToString
@NoArgsConstructor
public class BoardVO {

    private Long boardSeq;
    private String title;
    private String contents;
    private String name;
    private List<Map<String, Object>> mapList = new ArrayList<>();
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public BoardVO(String title, String contents, String name, List<Map<String, Object>> mapList) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.mapList = mapList;
    }
}
