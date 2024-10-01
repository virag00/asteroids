/**
 * Handles the creation of the Stars.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class Star extends BackgroundElement {

    /**
     * Default constructor.
     */
    public Star() {
        super(GameDriver.GENERATOR.nextDouble() * 480,
                GameDriver.GENERATOR.nextDouble() * 480);
    }

    /**
     * Draws the stars to the screen.
     */
    @Override
    public void draw() {
        StdDraw.filledCircle(location.getX(), location.getY(), 1);
    }

}
