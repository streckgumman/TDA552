import java.util.Stack;

public class CarTransport extends Truck {

        LoadingPlatform loadingPlatform;
        int maxStorageSpace;
        int maxSizeOfOneVehicle;
        final int down = 0;
        final int up = 90;
        VehicleStorage v;

    public CarTransport() {
            maxStorageSpace = 250;
            maxSizeOfOneVehicle = 50;
            Stack<Vehicle> vehicles = new Stack<>();
            loadingPlatform = new LoadingPlatform(getCurrentSpeed());
            setLoadingPlatform(loadingPlatform, up);
            v = new VehicleStorage(maxStorageSpace, maxSizeOfOneVehicle, vehicles);
    }

    //TODO: Overload the setAngle method instead of creating a new one(this method)
    public void setLoadingPlatform(LoadingPlatform loadingPlatform, int angle) {
        if (angle == 0 && getCurrentSpeed() == 0) {
            loadingPlatform.setAngle(down);
        } else {
            loadingPlatform.setAngle(angle);

        }
    }

}
