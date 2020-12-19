package course.uploading;

import course.logger.Logger;
import course.table.Route;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Uploader {
    private Integer freeCount;
    ArrayDeque<Request> queue = new ArrayDeque<>();
    ArrayList<Request> executable = new ArrayList<>();

    public static Logger<Request> logger;

    public Uploader(Integer maxCount) {
        this.freeCount = maxCount;
    }

    public void createRequest(Route route, int toDay) {
        queue.push(new Request(route, toDay));
    }

    private void loadFreeChannels() {
        while (freeCount != 0 && !queue.isEmpty()) {
            Request readyRequest = queue.pollLast();
            readyRequest.plannedStart();
            executable.add(readyRequest);
            freeCount--;
        }
    }

    private void loadFromQueue(int toDay, int startHour) {
        if (!queue.isEmpty()) {
            Request readyRequest = queue.pollLast();
            readyRequest.start(toDay, startHour);
            executable.add(readyRequest);
            freeCount--;
        }
    }

    private void unloadAndLoad(int toDay) {
        ArrayList<Request> subList = new ArrayList<>();

        if (!executable.isEmpty()) {
            executable.forEach(request -> {
                if (request.getDayOfEnd() <= toDay) {
                    subList.add(request);
                }
            });
            executable.removeIf(request -> request.getDayOfEnd() <= toDay);
        }

        while (!subList.isEmpty()) {
            Request endedRequest = subList.remove(0);
            loadFromQueue(toDay, endedRequest.end(toDay));
            freeCount++;
        }
    }

    public void executeRequests(int toDay) {
        loadFreeChannels();
        unloadAndLoad(toDay);
        logger.saveQueueSize(queue.size());
    }
}
