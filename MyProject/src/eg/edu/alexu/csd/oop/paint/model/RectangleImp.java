package eg.edu.alexu.csd.oop.paint.model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class RectangleImp extends AllShape{
	
	
	protected Rectangle rec ;
	public RectangleImp() {
		super();
		this.shape = "rectangle";
	}
	public RectangleImp(float x1 , float y1 , float x2 , float y2,Color fillColor ,Color drawColor ) {
		super(x1,y1,x2,y2,fillColor,drawColor);
		this.shape = "rectangle";
	}
	/******************************draw**********************************/
	@Override
	public void draw(Graphics g, boolean flag){
		rec= new Rectangle((int)Math.min(x1, x2), (int)Math.min(y1, y2),(int)width ,(int)height );
		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		AlphaComposite a = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5*.1f);
		g2.setComposite(a);
		if(fillColor!=null){
			g2.setColor(fillColor);
			g2.fill(rec);
		} else {
			g2.draw(rec);
		}
		g2.setColor(drawColor);
		g2.drawRect((int)Math.min(x1, x2), (int)Math.min(y1, y2),(int)width ,(int)height);
	}

	/******************************************/
	public Shape getShape(){
		return rec;
	}
	/**************************************/
	@Override
	public void move(float dx , float dy){
		setBounds( x1 + dx, y1 + dy, x2 +  dx, y2 + dy);
	}
	/*******************************************************/
	
}
