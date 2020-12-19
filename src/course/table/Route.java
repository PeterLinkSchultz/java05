package course.table;

import course.CargoType;

public class Route {
    public String name;
    public int arriveDay;
    public int arriveHour;
    CargoType cargoType;
    Double weight;
    public Integer timeLimit;

    public Route(String name, int arriveDay, int arriveHour, CargoType type, Double weight, Integer limit) {
        this.name = name;
        this.arriveDay = arriveDay;
        this.arriveHour = arriveHour;
        this.cargoType = type;
        this.weight = weight;
        this.timeLimit = limit;
    }

    public CargoType getCargoType() {
        return cargoType;
    }
}
