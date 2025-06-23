package io.github.gleidsonmt.glad;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/03/2025
 */
@Deprecated(forRemoval = true)
public class StringUtils {

    public static String capitalize(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String formatDuration(LocalDateTime first, LocalDateTime second) {
        Period period = Period.between(first.toLocalDate(), second.toLocalDate());
        String create;

        if (period.getDays() == 0) {
            Duration duration = Duration.between(first, second);
            create = duration.getSeconds() / 60 / 60 + " hours ago";
        } else if (period.getYears() != 0) {
            create = period.getYears() + " years ago";
        } else  if (period.getMonths() != 0) {
            create = period.getMonths() + " months ago";
        } else {
            create = period.getDays() + " days ago";
        }

        return create;
    }
}
