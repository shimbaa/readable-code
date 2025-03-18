package cleancode.studycafe.tobe.model.pass;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafeSeatPassesTest {

    @DisplayName("특정 이용권 타입에 해당하는 이용권을 검색 할 수 있다.")
    @Test
    void findPassByPassTypeTest() {
        //given
        StudyCafeSeatPasses passes = StudyCafeSeatPasses.of(List.of(
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 0, 0, 0),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 0, 0, 0),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 0, 0, 0),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 0, 0, 0)
        ));

        //when
        List<StudyCafeSeatPass> foundPasses = passes.findPassBy(StudyCafePassType.FIXED);

        //then
        assertThat(foundPasses)
            .hasSize(2)
            .allMatch(pass -> pass.getPassType() == StudyCafePassType.FIXED);
    }
}
