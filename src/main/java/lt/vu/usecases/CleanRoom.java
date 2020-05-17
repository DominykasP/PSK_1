package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class CleanRoom implements Serializable {
    @Inject
    private RoomCleaning roomCleaning;
    private Map<Integer, CompletableFuture<String>> cleaningStatuses = new HashMap<>();

    @LoggedInvocation
    public String cleanRoom() throws ExecutionException, InterruptedException {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer roomId = Integer.parseInt(requestParameters.get("roomId"));

        if (!cleaningStatuses.containsKey(roomId) || cleaningStatuses.get(roomId).isDone()) {
            cleaningStatuses.put(roomId, CompletableFuture.supplyAsync(() -> roomCleaning.cleanRoom(roomId)));
        }

        return "roomDetails?faces-redirect=true&roomId=" + roomId;
    }

    @LoggedInvocation
    public String getCleaningStatus(Integer roomId) throws ExecutionException, InterruptedException {
        if (!cleaningStatuses.containsKey(roomId) || cleaningStatuses.get(roomId) == null) {
            return "No cleaning";
        } else if (cleaningStatuses.get(roomId).isDone()) {
            return cleaningStatuses.get(roomId).get();
        } else {
            return "Cleaning in progress";
        }
    }
}
