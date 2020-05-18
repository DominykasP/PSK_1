package lt.vu.usecases.PriceCalculations;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.time.LocalDateTime;

@ApplicationScoped
public class PriceCalculator {
    public Long calculateTotalPrice(LocalDateTime dateFrom, LocalDateTime dateTo, Integer nightPrice) {
        long daysBetween = Duration.between(dateFrom, dateTo).toDays();
        return nightPrice * daysBetween;
    }
}
