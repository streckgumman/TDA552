import java.awt.*;
import static java.awt.Color.*;

/**
 * This class represents a Scania truck, which extends the abstract class truck,
 * which extends vehicle, which extends Transport which implements moveable.
 * When a Scania object is created, all instance variables are initialized according
 * to Scania's unique values (see constructor).
 *
 * @author SaraLandfors
 * @version 1.0
 */
public class Scania extends Truck {

	public Scania() {
		setTransportSize(21.2);
		setPosition(new Point(50,50)); 	//starting position of a new Scania Truck
		setDirection(new Point(1,0)); 	//starting direction set to East
		setSpeedFactor(2);
		setEnginePower(0.8);
		setCurrentSpeed(0.0);
		setNrDoors(2);
		setColor(Color.BLUE);
		setModelName("Tekno Scania 142H");
		setLoadingPlatform(new LoadingPlatform(getCurrentSpeed(), 0, 70));
		setIsMoveable(true);
	}




}

