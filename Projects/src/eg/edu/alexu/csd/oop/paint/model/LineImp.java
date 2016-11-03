package eg.edu.alexu.csd.oop.paint.model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class LineImp extends AllShape {
    public LineImp() {
	super();
	this.shape = "line";
    }

    public LineImp(float x1, float y1, float x2, float y2, Color fillColor, Color drawColor) {
	super(x1, y1, x2, y2, fillColor, drawColor);
	this.shape = "line";
    }

    @Override
    public void draw(Graphics g, boolean flag) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setStroke(new BasicStroke(5));
	AlphaComposite a = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5 * .1f);
	g2.setComposite(a);
	g2.setPaint(fillColor);
	if ((topRight != null) && (flag == true)) {
	    g2.fill(topRight);
	    g2.fill(topMid);
	}
	g2.setColor(drawColor);
	g2.draw(new Line2D.Float(x1, y1, x2, y2));
    }

    public boolean isLine() {
	return true;
    }

    public boolean contains(Point p) {
	double d1 = Math.sqrt(Math.pow(x1 - p.getX(), 2) + Math.pow(y1 - p.getY(), 2));
	double d2 = Math.sqrt(Math.pow(x2 - p.getX(), 2) + Math.pow(y2 - p.getY(), 2));
	double d = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	double eps = 5e-1;
	return (d1 + d2 - d) < eps;

    }

    @Override
    public void setSelectedPoints(Graphics g) {

	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.CYAN);
	topMid = new Rectangle.Float(x1 - 5, y1 - 5, 10, 10);
	g2.fill(topMid);

	botomMid = new Rectangle.Float(x2 - 5, y2 - 5, 10, 10);
	g2.fill(botomMid);

	// center = new Rectangle.Float( Math.min(x1, x2) + width / 2 - 5 ,
	// Math.min(y1, y2) + height / 2 - 5 , 10 , 10);
	// g2.fill(center);
	draw(g2, true);
    }

    public void move(float dx, float dy) {
	x1 += dx;
	x2 += dx;
	y1 += dy;
	y2 += dy;
    }

    public void reSize(float dx, float dy, Point place) {
	if (topMid.contains(place)) {
	    // setBounds( Math.min(a, b)+dx, y1 + dy, x2 , y2 );
	    x1 += dx;
	    y1 += dy;
	} else if (botomMid.contains(place)) {
	    // setBounds( x1, y1 , x2 + dx , y2+dy );
	    x2 += dx;
	    y2 += dy;
	}
    }
    /*****************************************************/

}
