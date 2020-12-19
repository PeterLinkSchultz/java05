package course.logger;

import course.CargoType;
import course.uploading.Request;

import java.util.ArrayList;

public class LoggerStats implements Logger<Request> {
    ArrayList<Data> data = new ArrayList<>();

    int totalUnloaded = 0;
    int totalInQueue = 0;
    int totalPenalty = 0;
    int modelDays = 0;
    int longQueue = 0;

    private int maxDelay = 0;
    private int summaryDelay = 0;

    @Override
    public void save(Request object, int toDay) {
        Data newData = new Data(
                object.getName(),
                object.getCargoType(),
                object.getPlannedStartDay(),
                object.getStartDay(),
                object.getPlannedStartHour(),
                object.getStartHour(),
                object.getPlannedLimit(),
                object.getFinalLimit(),
                object.getPenaltyDays()
        );

        int factLimit = object.getFinalLimit() - object.getPlannedLimit();
        if (factLimit > maxDelay) {
            maxDelay = factLimit;
        }
        summaryDelay += factLimit;

        totalUnloaded++;
        totalInQueue += object.getStartDay() - object.getPlannedStartDay();
        totalPenalty += newData.getPenaltyDays();

        data.add(newData);
    }

    @Override
    public void saveQueueSize(int size) {
        modelDays++;
        longQueue += size;
    }

    static class Data {
        private final String name;
        private final int timeInQueue;
        private final int overLimit;
        private final int penaltyDays;
        private final int plannedStartDay;
        private final int startDay;
        private final int plannedStartHour;
        private final int startHour;
        private final CargoType type;

        private String getTypeName() {
            return switch (type) {
                case Container -> "container";
                case Liquid -> "liquid";
                case Loose -> "loose";
            };
        }

        private Data(String name, CargoType type, int plannedStartDay, int startDay, int plannedStartHour, int startHour, int plannedLimit, int factLimit, int penaltyDays) {
            this.name = name;
            this.plannedStartDay = plannedStartDay;
            this.startDay = startDay;
            this.type = type;
            this.plannedStartHour = plannedStartHour;
            this.startHour = startHour;
            timeInQueue = startDay - plannedStartDay;
            overLimit = factLimit;
            this.penaltyDays = timeInQueue  + factLimit - plannedLimit + penaltyDays;
        }

        int getPenaltyDays() {
            return penaltyDays;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", type= " + getTypeName() +
                    ", timeInQueue=" + timeInQueue +
                    ", limit=" + overLimit +
                    ", penaltyDays=" + penaltyDays +
                    ", arriveDay=" + plannedStartDay +
                    ", arriveTime=" + plannedStartHour +
                    ", startDay=" + startDay +
                    ", startHour=" + startHour +
                    '}';
        }
    }

     public void print() {
        data.forEach(value -> System.out.println(value.toString()));
        System.out.println("Total unloaded: " + totalUnloaded);
        System.out.println("Total penalty: " + totalPenalty);
        System.out.println("Total in queue: " + totalInQueue);
        System.out.println("Average length of queue: " + (longQueue / (modelDays / 3) / 3));
        System.out.println("Max unloading delay: " + maxDelay);
        System.out.println("Average unloading delay: " + summaryDelay / totalUnloaded);
     }
}
