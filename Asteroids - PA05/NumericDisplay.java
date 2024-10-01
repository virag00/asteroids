/**
 * Handles the Score and Lives counters.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class NumericDisplay extends BackgroundElement {

    private int value;
    private String prefix;

    /**
     * Default constructor.
     * 
     * @param xPosition - x position on screen
     * @param yPosition - y position on screen
     * @param value - lives or score
     * @param prefix - "Lives: " or "Score: "
     */
    public NumericDisplay(double xPosition, double yPosition, int value,
            String prefix) {
        
        super(xPosition, yPosition);
        this.value = value;
        this.prefix = prefix;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Draws the lives and score to the screen.
     */
    @Override
    public void draw() {
        StdDraw.text(location.getX(), location.getY(), prefix + value);
    }
}
