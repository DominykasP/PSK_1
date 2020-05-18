package lt.vu.usecases.RoomCleanings;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class SlowRoomCleaning implements RoomCleaning {
    public String cleanRoom(Integer roomId) {
        System.out.println("Started slow room " + roomId + " cleaning");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Ended slow cleaning room " + roomId);
        return "Room clean";
    }
}
