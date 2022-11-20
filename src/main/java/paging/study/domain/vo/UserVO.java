package paging.study.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class UserVO {
    private Long userSeq;
    private String userId;
    private String userName;
    private String userPassword;
    private String userBirth;
    private String userPhone;
    private String userLevel;
    private String useYn;
    private String regDate;
    private String updateDate;

    public UserVO(String userId, String userName, String userPassword, String userBirth, String userPhone, String userLevel, String useYn) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userPhone = userPhone;
        this.userLevel = userLevel;
        this.useYn = useYn;
    }
}
