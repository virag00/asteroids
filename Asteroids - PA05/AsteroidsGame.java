import java.util.ArrayList;

/**
 * Handles all of the game elements created and used in the Asteroid game.
 * 
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 * 
 * @author charlie virag
 * @version 04/12/2021
 */
public class AsteroidsGame implements Playable {

    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;
    private ArrayList<Enemy> enemies;
    private NumericDisplay score;
    private NumericDisplay lives;

    private final Ship ship;

    /**
     * Adds all of the game elements to an array to be drawn and updated.
     */
    public AsteroidsGame() {

        drawElements = new ArrayList<Drawable>();
        enemies = new ArrayList<Enemy>();
        updateElements = new ArrayList<Updatable>();
        shipAndBullets = new ArrayList<GameElement>();

        score = new NumericDisplay(60, 460, 0, "Score: ");
        lives = new NumericDisplay(60, 420, 3, "Lives: ");

        // Add 100 stars to the background
        // --------------------------------
        for (int i = 0; i < 100; i++) {
            drawElements.add(new Star());
        }

        // Add Score and Lives to to be displayed
        // ---------------------------------------
        drawElements.add(score);
        drawElements.add(lives);

        // Add the player's Ship
        // ----------------------
        ship = new Ship();
        drawElements.add(ship);
        updateElements.add(ship);
        shipAndBullets.add(ship);

        // Add 10 randomly placed asteroids
        // ---------------------------------
        for (int i = 0; i < 10; i++) {
            int randomNum = GameDriver.GENERATOR.nextInt(4);
            Asteroid asteroid;
            if (randomNum == 0) {
                asteroid = new Asteroid(AsteroidSize.SMALL);
                drawElements.add(asteroid);
                updateElements.add(asteroid);
                enemies.add(asteroid);
            } else if (randomNum == 1) {
                asteroid = new Asteroid(AsteroidSize.MEDIUM);
                drawElements.add(asteroid);
                updateElements.add(asteroid);
                enemies.add(asteroid);
            } else if (randomNum == 2 || randomNum == 3) {
                asteroid = new Asteroid(AsteroidSize.LARGE);
                drawElements.add(asteroid);
                updateElements.add(asteroid);
                enemies.add(asteroid);
            }
        }

    }

    /**
     * Updates all game elements.
     */
    @Override
    public void update() {

        // When the space bar is pressed, shoot a bullet
        // ----------------------------------------------
        if (StdDraw.hasNextKeyTyped()) {
            Bullet bullet = new Bullet(ship.pose,
                    new Vector2D(ship.velocity.getHeading(), 20));
            drawElements.add(bullet);
            updateElements.add(bullet);
            shipAndBullets.add(bullet);
            StdDraw.nextKeyTyped();
        }

        // There is a 0.2% chance a saucer is going to spawn
        // -------------------------------------------------
        if (GameDriver.GENERATOR.nextDouble() <= 0.002) {
            Saucer saucer = new Saucer();
            drawElements.add(saucer);
            updateElements.add(saucer);
            enemies.add(saucer);
        }

        // Update the updatable elements, and remove destroyed ones
        // ---------------------------------------------------------
        for (int i = updateElements.size() - 1; i >= 0; i--) {
            updateElements.get(i).update();

            if (updateElements.get(i) instanceof Bullet) {
                Bullet bullet = (Bullet) updateElements.get(i);

                if (bullet.destroyed) {
                    drawElements.remove(bullet);
                    updateElements.remove(bullet);
                    shipAndBullets.remove(bullet);
                }
            } else if (updateElements.get(i) instanceof Saucer) {
                Saucer saucer = (Saucer) updateElements.get(i);

                if (saucer.destroyed) {
                    drawElements.remove(saucer);
                    updateElements.remove(saucer);
                    enemies.remove(saucer);
                }
            } else if (updateElements.get(i) instanceof Asteroid) {
                Asteroid asteroid = (Asteroid) updateElements.get(i);

                if (asteroid.destroyed) {
                    drawElements.remove(asteroid);
                    updateElements.remove(asteroid);
                    enemies.remove(asteroid);
                }
            } else if (updateElements.get(i) instanceof Ship) {
                Ship ship = (Ship) updateElements.get(i);

                if (ship.destroyed) {
                    drawElements.remove(ship);
                    updateElements.remove(ship);
                    shipAndBullets.remove(ship);

                    Ship newShip = new Ship();
                    drawElements.add(newShip);
                    updateElements.add(newShip);
                    shipAndBullets.add(newShip);
                }
            }
        }

        // If there are no more asteroids, spawn 10 more
        // ----------------------------------------------
        if (enemies.size() == 0) {
            for (int i = 0; i < 10; i++) {
                int randomNum = GameDriver.GENERATOR.nextInt(4);
                Asteroid asteroid;
                if (randomNum == 0) {
                    asteroid = new Asteroid(AsteroidSize.SMALL);
                    drawElements.add(asteroid);
                    updateElements.add(asteroid);
                    enemies.add(asteroid);
                } else if (randomNum == 1) {
                    asteroid = new Asteroid(AsteroidSize.MEDIUM);
                    drawElements.add(asteroid);
                    updateElements.add(asteroid);
                    enemies.add(asteroid);
                } else if (randomNum == 2 || randomNum == 3) {
                    asteroid = new Asteroid(AsteroidSize.LARGE);
                    drawElements.add(asteroid);
                    updateElements.add(asteroid);
                    enemies.add(asteroid);
                }
            }
        }
        // Stops game if lives run out
        // ----------------------------
        if (lives.getValue() == 0) {
            StdDraw.pause(Integer.MAX_VALUE);
        }
        // Checks for collision
        // ---------------------
        collision();
    }

    /**
     * Draws everything into the game.
     */
    @Override
    public void draw() {

        StdDraw.setPenColor(StdDraw.WHITE);

        for (int i = drawElements.size() - 1; i >= 0; i--) {
            drawElements.get(i).draw();
        }

    }

    /**
     * Checks for collisions between objects.
     */
    public void collision() {

        for (int i = shipAndBullets.size() - 1; i >= 0; i--) {
            for (int j = enemies.size() - 1; j >= 0; j--) {

                // Check to see if the bullets hit anything
                // -----------------------------------------
                if (shipAndBullets.get(i).checkCollision(enemies.get(j))) {
                    if (shipAndBullets.get(i) instanceof Bullet) {
                        Bullet bullet = (Bullet) shipAndBullets.get(i);

                        if (enemies.get(j) instanceof Saucer) {
                            Saucer saucer = (Saucer) enemies.get(j);

                            saucer.destroyed = true;
                            bullet.destroyed = true;
                            score.setValue(
                                    score.getValue() + saucer.getPointValue());

                        } else if (enemies.get(j) instanceof Asteroid) {
                            Asteroid asteroid = (Asteroid) enemies.get(j);

                            asteroid.destroyed = true;
                            bullet.destroyed = true;
                            score.setValue(score.getValue()
                                    + asteroid.getPointValue());
                        }
                    }
                    // Check to see if the Ship hits anything
                    // ---------------------------------------
                    if (shipAndBullets.get(i) instanceof Ship) {
                        Ship ship = (Ship) shipAndBullets.get(i);

                        if (enemies.get(j) instanceof Saucer) {
                            Saucer saucer = (Saucer) enemies.get(j);

                            saucer.destroyed = true;
                            ship.destroyed = true;
                            score.setValue(
                                    score.getValue() - saucer.getPointValue());
                            lives.setValue(lives.getValue() - 1);

                        } else if (enemies.get(j) instanceof Asteroid) {
                            Asteroid asteroid = (Asteroid) enemies.get(j);

                            asteroid.destroyed = true;
                            ship.destroyed = true;
                            score.setValue(score.getValue()
                                    - asteroid.getPointValue());
                            lives.setValue(lives.getValue() - 1);
                        }
                    }
                }
            }
        }
    }

}
