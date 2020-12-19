package course.uploading;

import course.CargoType;
import course.table.Route;

import java.util.HashMap;

public class UnloadingPoint {
    HashMap<CargoType, Uploader> uploaders = new HashMap<>();

    public UnloadingPoint(int containers, int liquid, int loose) {
        uploaders.put(CargoType.Container, new Uploader(containers));
        uploaders.put(CargoType.Liquid, new Uploader(liquid));
        uploaders.put(CargoType.Loose, new Uploader(loose));
    }

    public void addRequestToUnloading(Route route, int toDay) {
        uploaders.get(route.getCargoType()).createRequest(route, toDay);
    }

    public void startUnloading(int day) {
        uploaders.forEach((cargoType, uploader) -> {
            uploader.executeRequests(day);
        });
    }
}
