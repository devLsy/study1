package paging.study.domain.enu;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
// DB 데이터 타입
public enum ColumnType {
    BIGINT("BIGINT"),
    BOOLEAN("BOOLEAN"),
    CHARACTER_VARYING("CHARACTER VARYING"),
    DOUBLE_PRECISION("DOUBLE PRECISION"),
    INTEGER("INTEGER"),
    TIMESTAMP("TIMESTAMP"),
    TIMESTAMP_WITH_TIME_ZONE("TIMESTAMP WITH TIME ZONE");

    private String value;

    /**
     * param이 enum 범위에 있는지 체크
     * @param column
     * @return
     */
    public static boolean valueCheck(String column) {
        ColumnType columnType = Arrays.stream(ColumnType.values())
                .filter(v -> v.value.equals(column))
                .findAny()
                .orElse(null);

        if (columnType != null) {
            return true;
        } else {
            return false;
        }
    }
}





