import business.generator.DailyReportGenerator;
import model.instruction.Instruction;
import utils.DummyInstructionGenerator;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<Instruction> instructions = DummyInstructionGenerator.generateDummyInstructions();
        final DailyReportGenerator dailyReportGenerator = new DailyReportGenerator();

        System.out.println(dailyReportGenerator.generateInstructionsReport(instructions));
    }
}
