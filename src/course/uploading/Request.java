package course.uploading;

import course.CargoType;
import course.logger.Logger;
import course.table.Route;

import java.util.Random;

public class Request {
    private final String name;

    private final int plannedLimit; // planned time of execute
    private final int finalLimit; // fact time of execute
    private final int plannedStartDay; // equals create day
    private int startDay;
    private final int plannedStartHour; // equals create hour
    private int startHour;
    private final int penaltyDays;
    private final CargoType type;

    private static Logger<Request> logger;

    public static void setLogger(Logger<Request> logger) {
        Request.logger = logger;
    }

    Request(Route route, int toDay) {
        name = route.name;
        plannedLimit = route.timeLimit;
        type = route.getCargoType();

        finalLimit = route.timeLimit + (new Random()).nextInt(11);
        plannedStartDay = route.arriveDay;
        plannedStartHour = route.arriveHour;
        penaltyDays = toDay - route.arriveDay;
    }

    public void start(int startDay, int startHour) {
        this.startDay = startDay;
        this.startHour = startHour;
    }

    public void plannedStart() {
        this.startDay = plannedStartDay;
        this.startHour = plannedStartHour;
    }

    public String getName() {
        return name;
    }

    public CargoType getCargoType() {
        return type;
    }

    public int end(int toDay) {
        logger.save(this, toDay);
        // logging info for stats

        return startHour;
    }

    public int getDayOfEnd() {
        return startDay + finalLimit;
    }

    public int getPlannedStartDay() {
        return plannedStartDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getPlannedStartHour() {
        return plannedStartHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getPlannedLimit() {
        return plannedLimit;
    }

    public int getFinalLimit() {
        return finalLimit;
    }

    public int getPenaltyDays() {
        return penaltyDays;
    }
}
