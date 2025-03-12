package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    private static final String PASS_LIST_CSV_DIRECTORY = "src/main/resources/cleancode/studycafe/pass-list.csv";
    private static final String LOCKER_CSV_DIRECTORY = "src/main/resources/cleancode/studycafe/locker.csv";
    private static final String FILE_READ_ERROR_COMMENT = "파일을 읽는데 실패했습니다.";
    private List<StudyCafePass> studyCafePasses;

    public StudyCafeFileHandler() {
        this.studyCafePasses = readStudyCafePassFile();
    }

    public List<StudyCafePass> readStudyCafePassesFrom(StudyCafePassType selectedPassType) {
            return studyCafePasses.stream()
                    .filter(studyCafePass -> studyCafePass.isSamePassTypeWith(selectedPassType))
                    .toList();
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_CSV_DIRECTORY));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return lockerPasses;
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR_COMMENT, e);
        }
    }


    private List<StudyCafePass> readStudyCafePassFile() {
        List<StudyCafePass> passes = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PASS_LIST_CSV_DIRECTORY));
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                passes.add(studyCafePass);
            }
            return passes;
        } catch (IOException e) {
            throw new RuntimeException(FILE_READ_ERROR_COMMENT, e);
        }
    }
}
