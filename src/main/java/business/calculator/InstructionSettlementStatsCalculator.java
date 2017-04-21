package business.calculator;

import model.Rank;
import model.instruction.Instruction;
import model.instruction.TradeAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * Defines mapping between dates and the trade amount of those dates, based on instructions
 */
public class InstructionSettlementStatsCalculator {

    private final static Predicate<Instruction> outgoingInstructionsPredicate =
            instruction -> instruction.getAction().equals(TradeAction.BUY);

    private final static Predicate<Instruction> incomingInstructionsPredicate =
            instruction -> instruction.getAction().equals(TradeAction.SELL);


    public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<Instruction> instructions) {
        return calculateDailyAmount(instructions, outgoingInstructionsPredicate);
    }


    public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<Instruction> instructions) {
        return calculateDailyAmount(instructions, incomingInstructionsPredicate);
    }


    public static Map<LocalDate, LinkedList<Rank>> calculateDailyOutgoingRanking(Set<Instruction> instructions) {
        return calculateRanking(instructions, outgoingInstructionsPredicate);
    }


    public static Map<LocalDate, LinkedList<Rank>> calculateDailyIncomingRanking(Set<Instruction> instructions) {
        return calculateRanking(instructions, incomingInstructionsPredicate);
    }

    private static Map<LocalDate, BigDecimal> calculateDailyAmount(
            Set<Instruction> instructions, Predicate<Instruction> predicate)
    {
        return instructions.stream()
                .filter(predicate)
                .collect(groupingBy(Instruction::getSettlementDate,
                    mapping(Instruction::getTradeAmount,
                        reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    private static Map<LocalDate, LinkedList<Rank>> calculateRanking(
            Set<Instruction> instructions, Predicate<Instruction> predicate)
    {
        final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();

        instructions.stream()
            .filter(predicate)
            .collect(groupingBy(Instruction::getSettlementDate, toSet()))
            .forEach((date, dailyInstructionSet) -> {
                final AtomicInteger rank = new AtomicInteger(1);

                final LinkedList<Rank> ranks = dailyInstructionSet.stream()
                    .sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
                    .map(instruction -> new Rank(rank.getAndIncrement(), instruction.getEntity(), date))
                    .collect(toCollection(LinkedList::new));

                ranking.put(date, ranks);
            });

        return ranking;
    }
}
