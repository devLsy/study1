package paging.study.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
@NoArgsConstructor
public class MenuVO {
    private Long menuSeq;
    @NotBlank(message = "메뉴코드는 필수값입니다.")
    private String menuCode;
    @NotBlank(message = "메뉴명은 필수값입니다.")
    private String menuName;
    @NotBlank(message = "메뉴타입은 필수값입니다.")
    private String menuType;
    private String useYn;
    private String regDate;
    private String updateDate;

    public MenuVO(String menuCode, String menuName, String menuType, String useYn) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuType = menuType;
        this.useYn = useYn;
    }
}
