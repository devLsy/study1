package paging.study.domain.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// 게시판 메뉴 타입
public enum MenuType {
    TEXT("T"),
    TEXT_AREA("TA"),
    FILE("F"),
    SELECT_BOX("S"),
    CHECK_BOX("CH");
    
    private String value;
}
