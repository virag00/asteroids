/**
 * Asteroid class creates and controls the movement of the asteroids.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class Asteroid extends Enemy {

    private AsteroidSize size;

    /**
     * Default constructor.
     * 
     * @param size - Asteroid Size (SMALL, MEDIUM, LARGE)
     */
    public Asteroid(AsteroidSize size) {
        super(1, size.getRadius(), size.getPoints());
        this.size = size;
    }

    /**
     * Updates the location of the Asteroid.
     */
    @Override
    public void update() {

        this.pose = pose.move(velocity);
        if (pose.getX() < 0) {
            this.pose = pose.newX(480);
        }
        if (pose.getX() > 480) {
            this.pose = pose.newX(0);
        }
        if (pose.getY() < 0) {
            this.pose = pose.newY(480);
        }
        if (pose.getY() > 480) {
            this.pose = pose.newY(0);
        }
    }

    /**
     * Draws the asteroid.
     */
    @Override
    public void draw() {
        StdDraw.circle(pose.getX(), pose.getY(), size.getRadius());
    }
}
