package cleancode.studycafe.tobe.io;

import static org.assertj.core.api.Assertions.*;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputHandlerTest {


    @DisplayName("사용자 입력값에 따라 알맞은 이용권이 선택되어야 한다.")
    @Test
    void testGetPassTypeSelectingUserAction_HOURLY() {
        //given
        InputHandler inputHandler = new InputHandler();
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // when
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(passType).isEqualTo(StudyCafePassType.HOURLY);
    }

    @DisplayName("사용자 입력값에 따라 알맞은 이용권이 선택되어야 한다.")
    @Test
    void testGetPassTypeSelectingUserAction_WEEKLY() {
        //given
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(passType).isEqualTo(StudyCafePassType.WEEKLY);
    }

    @DisplayName("사용자 입력값에 따라 알맞은 이용권이 선택되어야 한다.")
    @Test
    void testGetPassTypeSelectingUserAction_FIXED() {
        //given
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputHandler inputHandler = new InputHandler();

        // when
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        // then
        assertThat(passType).isEqualTo(StudyCafePassType.FIXED);
    }

    @DisplayName("사용자 입력값에 따라 알맞은 이용권이 선택되어야 한다.")
    @Test
    void testGetPassTypeSelectingUserAction_INVALID() {
        //given
        String input = "4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputHandler inputHandler = new InputHandler();

        // when then
        assertThatThrownBy(inputHandler::getPassTypeSelectingUserAction)
            .isInstanceOf(AppException.class);
    }
}
