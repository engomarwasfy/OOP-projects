package eg.edu.alexu.csd.oop.paint.model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class TriangleImp extends AllShape {
	private Shape tri;
	//private String shapeName ;
	public TriangleImp() {
		super();
		shape = "triangle" ;
	}
	public TriangleImp(float x1 , float y1 ,float x2 , float y2,Color fillColor ,Color drawColor){
		super(x1,y1,x2,y2,fillColor,drawColor);
		this.shape = "triangle";
	}
	
	@Override
	public void draw(Graphics g , boolean flag){
		
		tri= new Polygon(new int[] {(int)x1, (int)x2, (int)(x1 - (x2-x1))}, new int[] {(int)y1, (int)y2, (int)y2}, 3);
		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		AlphaComposite a = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5*.1f);
		g2.setComposite(a);
		if (fillColor != null) {
			g2.setPaint(fillColor);
			g2.fill(tri);
		}
		g2.setPaint(drawColor);
		g2.draw(tri);
		
		g2.drawPolygon(new Polygon(new int[] {(int)x1, (int)x2, (int)(x1 - (x2-x1))}, new int[] {(int)y1, (int)y2, (int)y2}, 3));
	}
	/***************************************************/
	
	@Override
	public Shape getShape(){
		return tri;
		
	}
	/**************************************/
	@Override
	public void move(float dx , float dy){
		setBounds( x1 + dx, y1 + dy, x2 +  dx, y2 + dy);
	}
	/************************************/
	public void reSize(float dx ,float dy){
		setBounds( x1 + dx, y1 + dy, x2 , y2 );
		}
	/*******************************************/
	@Override
	public void setBounds(float x1 , float y1 ,float x2 , float y2){
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
		setWidth(Math.abs(x2 - x1));
		setHeight(Math.abs(y2 - y1));
	}
	/**************************************************/
	@Override
	public void setSelectedPoints(Graphics g) {
		g2 = (Graphics2D) g;
		
		g2.setColor(Color.blue);
		
		topLeft = new Rectangle2D.Float( (x1 - width) - 5 , Math.min(y1, y2) - 5, 10,10);
		g2.fill(topLeft);
		
		///////
		topMid = new Rectangle(  (int)(Math.min(x1, x2) - 5 ), (int)Math.min(y1, y2) - 5 , 10,10);
		g2.fill(topMid);
		
		topRight = new Rectangle((int) (x1 + width) - 5 , (int)Math.min(y1, y2) - 5 ,10 ,10);
		g2.fill(topRight);
		
		midRight = new Rectangle( (int)(x1 + width) - 5 ,(int)( Math.min(y1, y2) + height / 2 - 5) ,10, 10);
		g2.fill(midRight);
		
		botomRight = new Rectangle( (int)(x1 + width) - 5 ,(int)( Math.max(y1, y2) - 5) ,10, 10);
		g2.fill(botomRight);
		
		//////
		botomMid = new Rectangle( (int)(Math.min(x1, x2) - 5) , (int) Math.max(y1, y2) - 5 ,10, 10);
		g2.fill(botomMid);
		
		botomLeft = new Rectangle( (int)((x1 - width) - 5 ) ,(int)( Math.max(y1, y2) - 5) ,10, 10);
		g2.fill(botomLeft);
		
		midLeft = new Rectangle( (int)(((x1 - width)) - 5 ) ,(int)( Math.min(y1, y2) + height / 2 - 5) ,10, 10);
		g2.fill(midLeft);
		
		center = new Rectangle( (int)(x1 - 5 ) ,(int)( Math.min(y1, y2) + height / 2 - 5) ,10, 10);
		g2.fill(center);
		
		
		g2.drawRect((int)(x1 - width), (int)Math.min(y1, y2),(int)( 2 * width) ,(int)height);
	}
	/******************************************/
}
