package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();


    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();
            outputHandler.askPassTypeSelection();

            StudyCafePassType selectedType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> selectedTypePasses = studyCafeFileHandler.readStudyCafePassesFrom(
                    selectedType);

            outputHandler.showPassListForSelection(selectedTypePasses);
            StudyCafePass selectedPass = inputHandler.getUserSelectedPassAmong(selectedTypePasses);
            outputHandler.showPassOrderSummary(selectedPass, null);

            // 고정석인 경우 라커 선택 로직
            if (selectedPass.isSamePassTypeWith(StudyCafePassType.FIXED)) {
                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                        .filter(option ->
                                option.getPassType() == selectedPass.getPassType()
                                        && option.getDuration() == selectedPass.getDuration()
                        )
                        .findFirst()
                        .orElse(null);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (
                AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (
                Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
