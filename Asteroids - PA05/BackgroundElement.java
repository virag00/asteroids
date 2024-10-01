/**
 * Abstracts class for all background elements such as Stars and Score and
 * Lives.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public abstract class BackgroundElement implements Drawable {

    protected Point location;

    /**
     * Default constructor.
     * 
     * @param xPosition - x position of the element
     * @param yPosition - y position of the element
     */
    public BackgroundElement(double xPosition, double yPosition) {
        location = new Point(xPosition, yPosition);
    }

    @Override
    public void draw() {

    }
}
