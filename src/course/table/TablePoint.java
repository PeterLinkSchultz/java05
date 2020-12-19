package course.table;

import course.CargoType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TablePoint {
    public List<Route> list = new ArrayList<Route>();

    public TablePoint() {
        /*
        list.add(new Route("Route 1", 1, 16, CargoType.Container, 6.6, 4));
        list.add(new Route("Route 3", 2, 15, CargoType.Container, 6.6, 2));
        list.add(new Route("Route 5", 3, 14, CargoType.Container, 6.6, 2));

         */
    }

    public ArrayList<Route> getTodayRoutes(int day) {
        ArrayList<Route> temp = new ArrayList<>();

        while (!list.isEmpty() && list.get(0).arriveDay <= day) {
            temp.add(list.remove(0));
        }

        return temp;
    }

    public void generateRoutes(int n) {
        Random random = new Random();
        CargoType type = CargoType.Container;

        for (int i = 0; i < n; i++) {
            switch (random.nextInt(2)) {
                case 0 -> type = CargoType.Container;
                case 1 -> type = CargoType.Liquid;
                case 2 -> type = CargoType.Loose;
            }
            int day = random.nextInt(30);
            int devDays = random.nextInt(15) - 7;
            int time = random.nextInt(24);
            int limit = random.nextInt(10) + 1;

            list.add(new Route("Route #"+i, day + devDays, time, type, 5.5, limit));
        }

        list.sort(((o1, o2) -> {
            int dayDiff = o1.arriveDay - o2.arriveDay;

            if (dayDiff == 0) {
                return o1.arriveHour - o2.arriveHour;
            } else {
                return dayDiff;
            }
        }));
    }
 }
