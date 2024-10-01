/**
 * Handles the movements and creation of the saucers.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class Saucer extends Enemy {

    /**
     * Default constructor.
     */
    public Saucer() {
        super(2, 20, 400);
    }

    /**
     * updates the location and state of the saucer.
     */
    @Override
    public void update() {

        this.pose = pose.move(velocity);

        if (pose.getX() < 0 || pose.getX() > 480 || pose.getY() < 0
                || pose.getY() > 480) {
            destroyed = true;
        }

        double timer = GameDriver.GENERATOR.nextDouble();
        if (timer < 0.05) {
            this.velocity.newHeading(
                    GameDriver.GENERATOR.nextDouble() * 2 * Math.PI);
        }

    }

    /**
     * Draws the saucer to the screen.
     */
    @Override
    public void draw() {
        StdDraw.rectangle(pose.getX(), pose.getY(), 10, 5);
    }
}
