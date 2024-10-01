/**
 * Handles anything related to bullets.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class Bullet extends GameElement {

    private int counter;

    /**
     * Default constructor.
     * 
     * @param pose - Pose of bullet
     * @param velocity - Velocity of bullet
     */
    public Bullet(Pose pose, Vector2D velocity) {
        super(pose, velocity, 1.5);
        counter = 20;
    }

    /**
     * updates the bullets location and state.
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

        if (counter == 0) {
            destroyed = true;
        }
        counter--;
    }

    /**
     * Draws the bullet.
     */
    @Override
    public void draw() {
        if (counter > 0) {
            StdDraw.filledCircle(pose.getX(), pose.getY(), 1.5);
        }

    }
}
