package cleancode.studycafe.tobe.model.pass;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class StudyCafeSeatPassTest {

    @DisplayName("특정 이용권 유형만 라커를 사용할 수 있다.")
    @ParameterizedTest
    @EnumSource(StudyCafePassType.class)
    void checkIsAbleToUseLockerAccordingToPassType(StudyCafePassType passType) {
        //given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(passType, 1, 100, 0);

        //when
        boolean cannotUseLocker = pass.cannotUseLocker();

        //then
        if (passType == StudyCafePassType.FIXED) {
            assertThat(cannotUseLocker).isFalse();
        } else {
            assertThat(cannotUseLocker).isTrue();
        }
    }
}
