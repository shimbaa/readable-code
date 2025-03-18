package cleancode.studycafe.tobe.model.order;

import static org.assertj.core.api.Assertions.*;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StudyCafePassOrderTest {

    @DisplayName("가격과 할인률에 따른 총가격이 정상적으로 계산되어야 한다. 라커 사용 안하는 경우.")
    @ParameterizedTest
    @CsvSource({
        "100, 0, 100",   // price 100, discountRate 0, expectedTotalPrice 100
        "200, 0, 200",   // price 200, discountRate 0, expectedTotalPrice 200
        "300, 0.1, 270",  // price 300, discountRate 10, expectedTotalPrice 270
        "150, 0.5, 75"    // price 150, discountRate 50, expectedTotalPrice 75
    })
    void totalPriceTest(int price, double discountRate, int expectedTotalPrice) {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 1, price, discountRate);
        StudyCafePassOrder order = StudyCafePassOrder.of(pass, null);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    @DisplayName("가격과 할인률에 따른 총가격이 정상적으로 계산되어야 한다. 라커 사용 하는 경우.")
    @ParameterizedTest
    @CsvSource({
        "100, 0, 10, 110",   // price 100, discountRate 0, lockerPrice 10, expectedTotalPrice 110
        "200, 0, 10, 210",   // price 200, discountRate 0, lockerPrice 10, expectedTotalPrice 210
        "300, 0.1, 10, 280",  // price 300, discountRate 10, lockerPrice 10, expectedTotalPrice 280
        "150, 0.5, 10, 85"    // price 150, discountRate 50, lockerPrice 10, expectedTotalPrice 85
    })
    void totalPriceTestWithLocker(int price, double discountRate, int lockerPrice, int expectedTotalPrice) {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 1, price, discountRate);
        StudyCafePassOrder order = StudyCafePassOrder.of(pass, StudyCafeLockerPass.of(StudyCafePassType.FIXED, 1, lockerPrice));

        // when
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }
}
