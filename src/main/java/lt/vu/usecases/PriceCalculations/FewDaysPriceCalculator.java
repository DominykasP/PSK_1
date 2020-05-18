package lt.vu.usecases.PriceCalculations;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.Duration;
import java.time.LocalDateTime;

@ApplicationScoped
@Specializes
public class FewDaysPriceCalculator extends PriceCalculator {
    public Long calculateTotalPrice(LocalDateTime dateFrom, LocalDateTime dateTo, Integer nightPrice) {
        long daysBetween = Duration.between(dateFrom, dateTo).toDays();
        return daysBetween > 7 ?  nightPrice * daysBetween : nightPrice * (daysBetween + 1);
    }
}
