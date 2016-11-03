package eg.edu.alexu.csd.oop.paint.model;

import java.awt.Color;
import java.awt.Point;

public class CircleImp extends ElipseImp {

    public CircleImp() {
	super();
	this.shape = "circle";
    }

    public CircleImp(float x1, float y1, float x2, float y2, Color fillColor, Color drawColor) {
	super(x1, y1, x2, y2, fillColor, drawColor);
	width = height;
	this.shape = "circle";
    }

    /************************************************/
    @Override
    public void move(float dx, float dy) {
	setBounds(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
	width = height;
    }

    /*************************************************/
    @Override
    public void reSize(float dx, float dy, Point place) {
	if (topMid.contains(place)) {
	    setBounds(x1 + dy, y1 + dy, x2 - dy, y2 - dy);
	} else if (midRight.contains(place)) {
	    setBounds(x1 - dx, y1 - dx, x2 + dx, y2 + dx);
	} else if (botomMid.contains(place)) {
	    setBounds(x1 - dy, y1 - dy, x2 + dy, y2 + dy);
	} else if (midLeft.contains(place)) {
	    setBounds(x1 + dx, y1 + dx, x2 - dx, y2 - dx);
	}
    }
    /**************************************************/
}
