package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;

import eg.edu.alexu.csd.oop.paint.model.AllShape;

public class MoreShape {
    private ArrayList<AllShape> moreshape;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float width;
    private float height;
    private Shape topRight;
    private Shape topMid;
    private Shape topLeft;
    private Shape midLeft;
    private Shape botomRight;
    private Shape botomMid;
    private Shape botomLeft;
    private Shape midRight;
    private Shape center;
    private Graphics2D g2;

    public void setShapes(ArrayList<AllShape> shapes) {
	this.moreshape = shapes;
    }

    public Shape getShape() {
	return center;
    }

    public void setSelectedPoints(Graphics g) {
	set();
	g2 = (Graphics2D) g;

	g2.setColor(Color.blue);

	topLeft = new Rectangle2D.Float(Math.min(x1, x2) - 5, Math.min(y1, y2) - 5, 10, 10);
	g2.fill(topLeft);

	topMid = new Rectangle((int) (Math.min(x1, x2) + width / 2 - 5), (int) Math.min(y1, y2) - 5, 10, 10);
	g2.fill(topMid);

	topRight = new Rectangle((int) Math.max(x1, x2) - 5, (int) Math.min(y1, y2) - 5, 10, 10);
	g2.fill(topRight);

	midRight = new Rectangle((int) Math.max(x1, x2) - 5, (int) (Math.min(y1, y2) + height / 2 - 5), 10, 10);
	g2.fill(midRight);

	botomRight = new Rectangle((int) Math.max(x1, x2) - 5, (int) (Math.max(y1, y2) - 5), 10, 10);
	g2.fill(botomRight);

	botomMid = new Rectangle((int) (Math.min(x1, x2) + width / 2 - 5), (int) Math.max(y1, y2) - 5, 10, 10);
	g2.fill(botomMid);

	botomLeft = new Rectangle((int) Math.min(x1, x2) - 5, (int) (Math.max(y1, y2) - 5), 10, 10);
	g2.fill(botomLeft);

	midLeft = new Rectangle((int) Math.min(x1, x2) - 5, (int) (Math.min(y1, y2) + height / 2 - 5), 10, 10);
	g2.fill(midLeft);

	center = new Rectangle((int) (Math.min(x1, x2) + width / 2 - 5), (int) (Math.min(y1, y2) + height / 2 - 5), 10,
		10);
	g2.fill(center);

	g2.drawRect((int) x1, (int) y1, (int) width, (int) height);
    }

    ///////////////////////
    public void set() {
	float[] x1 = new float[moreshape.size()];
	float[] y1 = new float[moreshape.size()];
	float[] x2 = new float[moreshape.size()];
	float[] y2 = new float[moreshape.size()];
	int i = 0;
	for (AllShape s : moreshape) {
	    x1[i] = Math.min(s.getX1(), s.getX2());
	    y1[i] = Math.min(s.getY1(), s.getY2());
	    x2[i] = Math.max(s.getX1(), s.getX2());
	    y2[i] = Math.max(s.getY1(), s.getY2());
	    i++;
	}
	Arrays.sort(x1);
	this.x1 = x1[0];
	Arrays.sort(y1);
	this.y1 = y1[0];
	Arrays.sort(x2);
	this.x2 = x2[moreshape.size() - 1];
	Arrays.sort(y2);
	this.y2 = y2[moreshape.size() - 1];

	this.width = Math.abs(this.x2 - this.x1);
	this.height = Math.abs(this.y2 - this.y1);
    }

    public void Edit(float dx, float dy, Point place) {
	if (topMid.contains(place)) {
	    for (AllShape s : moreshape) {
		if ((s.getShapeName() == "circle") || (s.getShapeName() == "square")) {
		    s.setBounds(Math.min(s.getX1(), s.getX2()) + dy, Math.min(s.getY1(), s.getY2()) + dy,
			    Math.max(s.getX1(), s.getX2()), Math.max(s.getY1(), s.getY2()));
		} else {
		    s.setBounds(Math.min(s.getX1(), s.getX2()), Math.min(s.getY1(), s.getY2()) + dy,
			    Math.max(s.getX1(), s.getX2()), Math.max(s.getY1(), s.getY2()));
		}
	    }
	} else if (midRight.contains(place)) {
	    for (AllShape s : moreshape) {
		if ((s.getShapeName() == "circle") || (s.getShapeName() == "square")) {
		    s.setBounds(Math.min(s.getX1(), s.getX2()), Math.min(s.getY1(), s.getY2()),
			    Math.max(s.getX1(), s.getX2()) + dx, Math.max(s.getY1(), s.getY2()) + dx);
		} else {
		    s.setBounds(Math.min(s.getX1(), s.getX2()), Math.min(s.getY1(), s.getY2()),
			    Math.max(s.getX1(), s.getX2()) + dx, Math.max(s.getY1(), s.getY2()));
		}
	    }
	} else if (botomMid.contains(place)) {
	    for (AllShape s : moreshape) {
		if ((s.getShapeName() == "circle") || (s.getShapeName() == "square")) {
		    s.setBounds(Math.min(s.getX1(), s.getX2()), Math.min(s.getY1(), s.getY2()),
			    Math.max(s.getX1(), s.getX2()) + dy, Math.max(s.getY1(), s.getY2()) + dy);
		} else {
		    s.setBounds(Math.min(s.getX1(), s.getX2()), Math.min(s.getY1(), s.getY2()),
			    Math.max(s.getX1(), s.getX2()), Math.max(s.getY1(), s.getY2()) + dy);
		}
	    }
	} else if (midLeft.contains(place)) {
	    for (AllShape s : moreshape) {
		if ((s.getShapeName() == "circle") || (s.getShapeName() == "square")) {
		    s.setBounds(Math.min(s.getX1(), s.getX2()) + dx, Math.min(s.getY1(), s.getY2()) + dx,
			    Math.max(s.getX1(), s.getX2()), Math.max(s.getY1(), s.getY2()));
		} else {
		    s.setBounds(Math.min(s.getX1(), s.getX2()) + dx, Math.min(s.getY1(), s.getY2()),
			    Math.max(s.getX1(), s.getX2()), Math.max(s.getY1(), s.getY2()));
		}

	    }
	} else if (center.contains(place)) {
	    for (AllShape s : moreshape) {
		s.move(dx, dy);
	    }
	}
    }

}
