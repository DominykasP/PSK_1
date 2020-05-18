package lt.vu.decorators;

import lt.vu.usecases.RoomCleanings.RoomCleaning;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Date;

@Decorator
public abstract class BedSheetsCleaningDecorator implements RoomCleaning {
    @Inject @Delegate @Any
    RoomCleaning roomCleaning;

    public String cleanRoom(Integer roomId) {
        Date today = new Date();
        if (today.getDay() == 2) {
            System.out.println("Additionally changing bed sheets");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return roomCleaning.cleanRoom(roomId);
    }
}
