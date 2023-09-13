package ru.sber.beautifulcode.textchecker.maper;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.mapstruct.Mapper;
import ru.sber.beautifulcode.textchecker.model.ConstraintViolation;
import ru.sber.beautifulcode.textchecker.model.Report;

@Mapper
public interface ConstraintViolationMapper {

    default Report convert(@NonNull final Set<ConstraintViolation> result) {
        var reportBuilder = Report.builder();
        if (result.isEmpty()) {
            return getReport(reportBuilder, true);
        }
        var correct = isCorrect(result);
        return getReport(reportBuilder, correct);
    }

    @NonNull
    private Report getReport(final Report.@NonNull ReportBuilder reportBuilder, final boolean correct) {
        return reportBuilder
            .correct(correct)
            .build();
    }

    private boolean isCorrect(@NonNull final Set<ConstraintViolation> result) {
        return result.stream()
            .allMatch(ConstraintViolation::isValid);
    }
}