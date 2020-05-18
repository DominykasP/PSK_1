package lt.vu.usecases.RoomCleanings;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class FastRoomCleaning implements RoomCleaning {
    public String cleanRoom(Integer roomId) {
        System.out.println("Started fast room " + roomId + " cleaning");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Ended fast cleaning room " + roomId);
        return "Room clean";
    }
}
