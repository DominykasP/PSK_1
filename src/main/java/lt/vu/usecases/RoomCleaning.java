package lt.vu.usecases;

import lt.vu.entities.Room;
import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class RoomCleaning implements Serializable {
    @LoggedInvocation
    public String cleanRoom(Integer roomId) {
        System.out.println("Started room " + roomId + " cleaning");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Ended cleaning room " + roomId);
        return "Room clean";
    }
}
