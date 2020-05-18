package lt.vu.usecases.RoomCleanings;

import lt.vu.interceptors.LoggedInvocation;

import java.io.Serializable;

public interface RoomCleaning extends Serializable {
    @LoggedInvocation
    public abstract String cleanRoom(Integer roomId);
}
