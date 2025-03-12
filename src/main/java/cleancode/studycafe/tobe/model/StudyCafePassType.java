package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.exception.AppException;
import java.util.Arrays;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권", "1"),
    WEEKLY("주 단위 이용권", "2"),
    FIXED("1인 고정석", "3");

    private final String description;
    private final String choiceValue;

    StudyCafePassType(String description, String choiceValue) {
        this.description = description;
        this.choiceValue = choiceValue;
    }

    public static StudyCafePassType from(String userInput) {
        return Arrays.stream(values())
                .filter(passType -> passType.choiceValue.equals(userInput))
                .findFirst()
                .orElseThrow(() -> new AppException("잘못된 입력입니다."));
    }
}
