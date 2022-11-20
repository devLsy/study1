package paging.study.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class LoginForm {
    private String userId;
    private String userPassword;

    public LoginForm(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
