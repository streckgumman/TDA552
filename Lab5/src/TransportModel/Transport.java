package TransportModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public abstract class Transport implements Movable {

    //instance variables
    private Point2D.Double position;
    private Point direction;
    private double speedFactor;
    private double enginePower; // Engine power of the transport
    private double currentSpeed; // The current speed of the transport
    private Color color; // Color of the transport
    private String modelName; // The transport model name
    private double transportSize;
    private double maxStorageSpace;
    private boolean isMovable;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * @return the current position.
     */
    public Point2D.Double getPosition() {
        return position;
    }

    /**
     * @return the size of the vehicle
     */
    public double getTransportSize() {
        return transportSize;
    }

    /**
     * @param transportSize size of object
     */
    public void setTransportSize(double transportSize) {
        this.transportSize = transportSize;
    }


    /**
     * @param position position to set, as a Point.
     */
    public void setPosition(Point position) {
        Point2D.Double p = new Point2D.Double(position.getX(), position.getY());
        this.position = p;
    }

    /**
     * @param position to set, as a Point2D.Double.
     */

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    /**
     * @return current direction as a vector in (x,y), where x and
     * y take on values between -1 and 1.
     */
    public Point getDirection() {
        return direction;
    }

    /**
     * @param direction direction to set.
     */
    public void setDirection(Point direction) {
        this.direction = direction;
    }

    /**
     * @return car speedfactor.
     */
    public double getSpeedFactor() {
        return speedFactor;
    }

    /**
     * @param speedFactor speedfactor to set.
     */
    public void setSpeedFactor(double speedFactor) {
        if (speedFactor < 0) {
            System.out.println("speedFactor must be a double equal to or larger than 0");
        } else {
            this.speedFactor = speedFactor;
        }
    }

    /**
     * @param enginePower Engine power to set.
     */
    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    /**
     * @return transport model name.
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName TransportModel.Transport's model name to set.
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return engine power.
     */
    public double getEnginePower() {
        return enginePower;
    }

    /**
     * @return current transport speed.
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * @param currentSpeed Current speed to set.
     */
    public void setCurrentSpeed(double currentSpeed) {
        if (currentSpeed < 0) {
            setCurrentSpeed(0);
        } else if (currentSpeed > getEnginePower()) {
            setCurrentSpeed(getEnginePower());
        } else {
            this.currentSpeed = currentSpeed;
        }
    }

    /**
     * @return transport color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param clr Color to set.
     */
    public void setColor(Color clr) {
        color = clr;
    }

    /**
     * Starts engine and sets current speed to 0.1.
     */
    public void startEngine() {
        setCurrentSpeed(0.1);
    }

    /**
     * Stops engine and sets current speed to 0.
     */
    public void stopEngine() {
        setCurrentSpeed(0);
    }

    /**
     * Increments speed with a variable amount.
     */
    public void incrementSpeed(double amount) {
        setCurrentSpeed(Math.min(getCurrentSpeed() + getSpeedFactor() * amount, getEnginePower()));
    }

    /**
     * Decrements speed with a variable amount.
     */
    public void decrementSpeed(double amount) {
        setCurrentSpeed(Math.max(getCurrentSpeed() - getSpeedFactor() * amount, 0));
    }

    public void gas(double amount) {
        if (amount < 0 || amount > 1) {
            //TODO: create exception instead???
            System.out.println("Method gas() must be called with parameter between 0 and 1, inclusive");
        } else {
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        decrementSpeed(amount);
    }

    /**
     * Method that moves the transport in its current direction, and updates
     * its position. The distance it's moved depends on its current speed.
     */
    public void move() {
        if (isMoveable()) {
            double x, y;
            x = getPosition().getX();
            y = getPosition().getY();

            x = x + getCurrentSpeed() * getDirection().getX();
            y = y + getCurrentSpeed() * getDirection().getY();

            Point2D.Double newPosition = new Point2D.Double(x, y);
            setPosition(newPosition);
        } else {
            System.out.println("Can't move transport when IsMoveable == false.");
        }
    }

    /**
     * Checks which direction is the transport's current direction:
     * (0, 1) = North
     * (1, 0) = East
     * (0, -1) = South
     * (-1, 0) = West
     * <p>
     * and then updates the direction to to one the transport would have after a right-turn.
     */
    public void turnRight() {
        if (getDirection().getX() == 0 && getDirection().getY() == 1) {            //Check North
            getDirection().setLocation(1, 0);
        } else if (getDirection().getX() == 1 && getDirection().getY() == 0) {     //Check East
            this.getDirection().setLocation(0, -1);
        } else if (getDirection().getX() == 0 && getDirection().getY() == -1) {    //Check South
            this.getDirection().setLocation(-1, 0);
        } else if (getDirection().getX() == -1 && getDirection().getY() == 0) {    //Check West
            this.getDirection().setLocation(0, 1);
        } else {
            //TODO: implement error if direction is any other direction than North, East, South, West
        }
    }

    /**
     * Checks which direction is the transport's current direction:
     * (0, 1) = North
     * (1, 0) = East
     * (0, -1) = South
     * (-1, 0) = West
     * <p>
     * and then updates the direction to to one the transport would have after a left-turn.
     */
    public void turnLeft() {
        if (getDirection().getX() == 0 && getDirection().getY() == 1) {            //Check North
            getDirection().setLocation(-1, 0);
        } else if (getDirection().getX() == 1 && getDirection().getY() == 0) {     //Check East
            getDirection().setLocation(0, 1);
        } else if (getDirection().getX() == 0 && getDirection().getY() == -1) {    //Check South
            getDirection().setLocation(1, 0);
        } else if (getDirection().getX() == -1 && getDirection().getY() == 0) {    //Check West
            getDirection().setLocation(0, -1);
        } else {
            //TODO: Fix for case of invalid direction
        }
    }

    /**
     * @return maxStorageSpace
     */
    public double getMaxStorageSpace() {
        return maxStorageSpace;
    }

    /**
     * @param maxStorageSpace the maxStorageSpace to be set
     */
    public void setMaxStorageSpace(double maxStorageSpace) {
        this.maxStorageSpace = maxStorageSpace;
    }

    public boolean isMoveable() {
        return isMovable;
    }

    public void setIsMoveable(boolean moveable) {
        isMovable = moveable;
    }

    /**
     * This method checks whether a vehicle is out of bounds in the
     * window.
     *
     * @return a boolean that is true if the vehicle is out of bounds
     * and false otherwise.
     */
    public boolean isOutOfBounds(Dimension size) {
        Point2D.Double position = getPosition();
        if (xIsOutOfBounds(position, size.width) || yIsOutOfBounds(position, size.height)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param p the point for which the x-coordinate will be checked
     * @return a boolean that is true if the x-coordinate is out of bounds
     */
    private boolean xIsOutOfBounds(Point2D.Double p, int width) {
        double x = p.getX();
        // getDrawPanel().getSize().getWidth()
        if (x < 0 || x > width - 100) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param p the point for which the y-coordinate will be checked
     * @return a boolean that is true if the y-coordinate is out of bounds
     */
    private boolean yIsOutOfBounds(Point2D.Double p, int height) {
        double y = p.getY();
        if (y < 0 || y > height - 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method inverts the direction of a vehicle.
     */
    public void invertDirection() {
        Point direction = getDirection();
        direction.x = -1 * direction.x;
        direction.y = -1 * direction.y;
        setDirection(direction);
    }

}


