/**
 * Abstract class for Enemy objects such as Saucers and Asteroids.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public abstract class Enemy extends GameElement {

    private int pointValue;
    
    /**
     * Default constructor.
     * 
     * @param speed - speed of the enemy
     * @param collisionRadius - collision radius of the enemy
     * @param pointValue - points associated with enemy
     */
    public Enemy(double speed, double collisionRadius, int pointValue) {
        super(speed, collisionRadius);
        
        this.pointValue = pointValue;
    }
    
    public int getPointValue() {
        
        return pointValue;
    }
}
