package course;

import course.logger.Logger;
import course.logger.LoggerStats;
import course.table.Route;
import course.table.TablePoint;
import course.uploading.Request;
import course.uploading.UnloadingPoint;
import course.uploading.Uploader;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Logger<Request> logger = new LoggerStats();
        Request.setLogger(logger);
        Uploader.logger = logger;
        TablePoint tablePoint = new TablePoint();
        tablePoint.generateRoutes(100);
        UnloadingPoint unloadingPoint = new UnloadingPoint(25, 25, 25);

        int days = 30;
        int currentDay = 0;
        int step = 2;

        while (currentDay <= days) {
            int finalCurrentDay = currentDay;

            ArrayList<Route> routes = tablePoint.getTodayRoutes(currentDay);
            routes.forEach(route -> {
                unloadingPoint.addRequestToUnloading(route, finalCurrentDay);
            });
            unloadingPoint.startUnloading(currentDay);

            currentDay += step;
        }

        logger.print();
    }
}
