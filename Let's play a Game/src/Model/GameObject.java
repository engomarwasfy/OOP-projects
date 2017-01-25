package Model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Controller.ID;
import Controller.ObjectsHandler;
public abstract class GameObject {
  public int x;
  public int y;
  public int collisionY;
  public float velX, velY;
	public ID id;
	public ID direction;
	public Color color;
	public boolean collision;
	public String playerStick;
  public String difficulty;

	protected GameObject(final int x, final int y, final ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.collision = false;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract GameObject getInstance(final int x, final int y, final ID id, final ID direction,
      final ObjectsHandler handler, final String difficulty);
	public abstract Rectangle getBound();

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(final int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(final int y) {
		this.y = y;
	}
	/**
	 * @return the velX
	 */
	public float getVelX() {
		return velX;
	}
	/**
	 * @param velX the velX to set
	 */
	public void setVelX(final float velX) {
		this.velX = velX;
	}
	/**
	 * @return the velY
	 */
	public float getVelY() {
		return velY;
	}
	/**
	 * @param velY the velY to set
	 */
	public void setVelY(final float velY) {
		this.velY = velY;
	}

}
