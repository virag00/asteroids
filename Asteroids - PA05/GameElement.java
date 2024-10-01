/**
 * Game Element handles any element that will be drawn or updated.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public abstract class GameElement implements Drawable, Updatable {

    protected Pose pose;
    protected Vector2D velocity;
    protected boolean destroyed;
    protected double collisionRadius;

    /**
     * Three parameter constructor.
     * 
     * @param pose - Pose of this element
     * @param velocity - velocity of this element
     * @param collisionRadius - collision radius of this element
     */
    public GameElement(Pose pose, Vector2D velocity, double collisionRadius) {
        this.pose = pose;
        this.velocity = velocity;
        this.collisionRadius = collisionRadius;
        destroyed = false;
    }

    /**
     * Two parameter constructor.
     * 
     * @param speed - speed of the element
     * @param collisionRadius - collision radius of the element
     */
    public GameElement(double speed, double collisionRadius) {
        pose = new Pose(GameDriver.GENERATOR.nextDouble() * 420,
                GameDriver.GENERATOR.nextDouble() * 420, collisionRadius);
        velocity = new Vector2D(GameDriver.GENERATOR.nextDouble() * 2 * Math.PI,
                speed);
        destroyed = false;
    }

    /**
     * Does nothing in this class.
     */
    public void update() {

    }

    /**
     * Does nothing in this class.
     */
    public void draw() {

    }

    /**
     * Formula for checking to see if two elements have collided.
     * 
     * @param other - the other element
     * @return true if collided, false otherwise
     */
    public boolean checkCollision(GameElement other) {

        boolean result = false;

        double hypotenuse = Math.hypot(this.pose.getX() - other.pose.getX(),
                this.pose.getY() - other.pose.getY());

        if (hypotenuse < this.collisionRadius + other.collisionRadius) {
            result = true;
        }

        return result;
    }
}
