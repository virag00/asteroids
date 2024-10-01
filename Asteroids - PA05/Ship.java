/**
 * Handles the creation and updating of the ship.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class Ship extends GameElement {

    /**
     * Default constructor.
     */
    public Ship() {
        super(new Pose(240, 240, Math.PI / 2), new Vector2D(Math.PI / 2, 20),
                10);
    }

    /**
     * Draws the ship to the screen.
     */
    @Override
    public void draw() {
        GameUtils.drawPoseAsTriangle(pose, 10, 20);
    }

    /**
     * Handles the controls and updating the location and state of the ship.
     */
    @Override
    public void update() {

        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_DOWN)) {
            velocity = velocity.newMagnitude(velocity.getMagnitude() + 0.1);
            velocity = velocity.newHeading(pose.getHeading());
            pose = pose.move(velocity);
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_RIGHT)) {
            pose = pose.newHeading(pose.getHeading() - 0.1);
        }
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_LEFT)) {
            pose = pose.newHeading(pose.getHeading() + 0.1);
        }
        if (!StdDraw.hasNextKeyTyped()) {
            velocity = velocity.newMagnitude(velocity.getMagnitude() * 0.99);
        }

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

}
