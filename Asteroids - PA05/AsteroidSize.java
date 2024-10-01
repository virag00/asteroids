/**
 * Handles the different asteroid sizes.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public enum AsteroidSize {

    SMALL(10, 200, 0),
    MEDIUM(20, 100, 1),
    LARGE(30, 50, 2);

    private int radius;
    private int points;
    private double probability;

    /**
     * Default Constructor.
     * 
     * @param radius - radius of asteroid
     * @param points - points the asteroid is worth
     * @param probability - probability of spawning
     */
    AsteroidSize(int radius, int points, double probability) {
        this.radius = radius;
        this.points = points;
        this.probability = probability;
    }
    
    public int getRadius() {
        return radius;
    }
    
    public int getPoints() {
        return points;
    }
    
}
