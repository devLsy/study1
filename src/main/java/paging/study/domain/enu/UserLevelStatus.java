package paging.study.domain.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserLevelStatus {
    SUPER_ADMIN("1"),
    ADMIN("2"),
    MEMBER("3");
    
    private String value;
}
