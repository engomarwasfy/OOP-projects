package eg.edu.alexu.csd.oop.paint.model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class ElipseImp extends AllShape {
	private Shape elipse4;
	public ElipseImp() {
		super();
		this.shape ="elipse";
	}
	public ElipseImp(float x1 ,float y1 ,float x2 ,float y2,Color fillColor ,Color drawColor ){
		super(x1,y1,x2,y2,fillColor,drawColor);
		this.shape="elipse";
	}
	
	@Override
	public void draw(Graphics g , boolean flag){
		
		elipse4= new Ellipse2D.Float((int)Math.min(x1, x2), (int)Math.min(y1, y2),(int)width ,(int)height );
		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		AlphaComposite a = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5*.1f);
		g2.setComposite(a);
		if(fillColor!=null){
			g2.setColor(fillColor);
			g2.fill(elipse4);
		}
		g2.setColor(drawColor);
		g2.draw(elipse4);
	}
	/***************************************************/
	
	@Override
	public Shape getShape(){
		return elipse4;
		
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
	/**************************************************/
	

}
