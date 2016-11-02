package eg.edu.alexu.csd.oop.paint.model;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

public abstract class AllShape{
	protected Shape topRight;
	protected Shape topMid;
	protected Shape topLeft;
	protected Shape midLeft;
	protected Shape botomRight;
	protected Shape botomMid;
	protected Shape botomLeft;
	protected Shape midRight;
	protected Shape center;
	protected Shape selectionRectangle10;
	protected Graphics2D g2;
	protected float x1,x2,y1,y2;
	protected float width,height;
	protected Color fillColor =Color.WHITE  ;
	protected Color drawColor= Color.black ;
	protected String shape;
	public String getShapeName(){
		return shape;
	}
	/*************************************************/
	public AllShape() {
		
	}
	public AllShape(float x1 , float y1 , float x2 , float y2,Color fillColor ,Color drawColor ) {
		this.x1 = x1 ;
		this.y1 = y1 ;
		this.x2 = x2 ;
		this.y2 = y2 ;
		this.width = Math.abs(x2-x1);
		this.height = Math.abs(y1 - y2);
		this.fillColor = fillColor;
		if(drawColor!=null){
		this.drawColor = drawColor;
		}
	}
	/******************************************************/
	public Shape getTopMid() {
		return topMid;
	}
	public Shape getBotomMid() {
		return botomMid;
	}
	public Shape getCenter() {
		return center;
	}
	public float getX1() {
		return x1;
	}
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	public void setDrawColor(Color c) {
		this.drawColor = c;
	}
	public void setX1(float x1) {
		this.x1 = x1;
	}
	public float getX2() {
		return x2;
	}
	public void setX2(float x2) {
		this.x2 = x2;
	}
	public float getY1() {
		return y1;
	}
	public void setY1(float y1) {
		this.y1 = y1;
	}
	public float getY2() {
		return y2;
	}
	public void setY2(float y2) {
		this.y2 = y2;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	
	public Color getFillColor() {
		return fillColor;
	}
	public Color getDrawColor() {
		return drawColor;
	}
	/***************************/
	public void draw(Graphics g, boolean flag){
		
	}
	/***********************************/
	public void setSelectedPoints(Graphics g) {
		g2 = (Graphics2D) g;
		//g2.setStroke(new BasicStroke(3));
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,new float[]{9},0);
		g2.setStroke(dashed);
		g2.setColor(Color.CYAN);
		
		
		topLeft = new Rectangle2D.Float( Math.min(x1, x2) - 5 , Math.min(y1, y2) - 5, 10,10);
		g2.fill(topLeft);
		
		topMid = new Rectangle(  (int)(Math.min(x1, x2) + width / 2 - 5 ), (int)Math.min(y1, y2) - 5 , 10,10);
		g2.fill(topMid);
		
		topRight = new Rectangle((int) Math.max(x1, x2) - 5 , (int)Math.min(y1, y2) - 5 ,10 ,10);
		g2.fill(topRight);
		
		midRight = new Rectangle( (int)Math.max(x1, x2) - 5 ,(int)( Math.min(y1, y2) + height / 2 - 5) ,10, 10);
		g2.fill(midRight);
		
		botomRight = new Rectangle( (int)Math.max(x1, x2) - 5 ,(int)( Math.max(y1, y2) - 5) ,10, 10);
		g2.fill(botomRight);
		
		botomMid = new Rectangle( (int)(Math.min(x1, x2) + width / 2 - 5) , (int) Math.max(y1, y2) - 5 ,10, 10);
		g2.fill(botomMid);
		
		botomLeft = new Rectangle( (int)Math.min(x1, x2) - 5 ,(int)( Math.max(y1, y2) - 5) ,10, 10);
		g2.fill(botomLeft);
		
		midLeft = new Rectangle( (int)Math.min(x1, x2) - 5 ,(int)( Math.min(y1, y2) + height / 2 - 5) ,10, 10);
		g2.fill(midLeft);
		
		center = new Rectangle( (int)(Math.min(x1, x2) + width / 2 - 5 ) ,(int)( Math.min(y1, y2) + height / 2 - 5) ,10, 10);
		g2.fill(center);
		
		
		g2.drawRect((int)Math.min(x1, x2), (int)Math.min(y1, y2),(int)width ,(int)height);
	}
	/******************************************/
	public Shape getShape(){
		return topRight;
		
	}
	/**************************************/
	public void setBounds(float x1 , float y1 ,float x2 , float y2){
		setX1(Math.min(x1, x2));
		setX2(Math.max(x1, x2));
		setY1(Math.min(y1, y2));
		setY2(Math.max(y1, y2));
		setWidth(Math.abs(x2 - x1));
		setHeight(Math.abs(y2 - y1));
	}
	/*****************************************/
	public void move(float dx , float dy){
		
	}
	/*****************************************************/
	public void reSize(float dx ,float dy,Point place){
		if (topLeft.contains(place)) {
			setBounds(Math.min(x1, x2) + dx , Math.min(y1, y2) + dy , Math.max(x1, x2) ,Math.max(y1, y2));
		} else if (topMid.contains(place)) {
			setBounds(Math.min(x1, x2)  , Math.min(y1, y2) + dy , Math.max(x1, x2) ,Math.max(y1, y2));
		} else if (topRight.contains(place)) {
			setBounds(Math.min(x1, x2)  , Math.min(y1, y2) + dy , Math.max(x1, x2) + dx ,Math.max(y1, y2));
		} else if (midRight.contains(place)) {
			setBounds(Math.min(x1, x2)  , Math.min(y1, y2)  , Math.max(x1, x2) + dx ,Math.max(y1, y2));
		} else if (botomRight.contains(place)) {
			setBounds(Math.min(x1, x2)  , Math.min(y1, y2)  , Math.max(x1, x2) + dx ,Math.max(y1, y2) + dy);
		} else if (botomMid.contains(place)) {
			setBounds(Math.min(x1, x2) , Math.min(y1, y2) , Math.max(x1, x2) ,Math.max(y1, y2) + dy);
		} else if (botomLeft.contains(place)) {
			setBounds(Math.min(x1, x2) + dx , Math.min(y1, y2) , Math.max(x1, x2) ,Math.max(y1, y2) + dy);
		} else if (midLeft.contains(place)) {
			setBounds(Math.min(x1, x2) + dx , Math.min(y1, y2) , Math.max(x1, x2) ,Math.max(y1, y2));
		}
	}
	/****************************************/
	public boolean isLine(){
		return false;
	}
	/****************************************/
	public boolean contains(Point p){
		return false;
	}
	/****************************************/
	public void init(float x1 , float y1 , float x2 , float y2,Color fillColor ,Color drawColor) {
		this.x1 = x1 ;
		this.y1 = y1 ;
		this.x2 = x2 ;
		this.y2 = y2 ;
		this.width = Math.abs(x2-x1);
		this.height = Math.abs(y1 - y2);
		this.fillColor = fillColor;
		if(drawColor!=null){
		this.drawColor = drawColor;
		}
		
	}
}
