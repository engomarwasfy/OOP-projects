package eg.edu.alexu.csd.oop.paint.model;

import java.awt.Color;
import java.awt.Point;

public class SquareImp extends RectangleImp {
	
	public SquareImp() {
	super();
	this.shape = "square";
	}
	public SquareImp(float x1, float y1, float x2, float y2,Color fillColor ,Color drawColor) {
		super(x1, y1, x2, y2,fillColor,drawColor);
		this.shape = "square";
		width = height;
	}
	/************************************************************/
	@Override
	public void reSize(float dx ,float dy,Point place){
		if (topLeft.contains(place)) {
			setBounds(Math.min(x1, x2) + dx , Math.min(y1, y2) + dx , Math.max(x1, x2) ,Math.max(y1, y2));
		} else if (topRight.contains(place)) {
			setBounds(Math.min(x1, x2)  , Math.min(y1, y2) + dx , Math.max(x1, x2) + dx ,Math.max(y1, y2));
		} else if (botomRight.contains(place)) {
			setBounds(Math.min(x1, x2)  , Math.min(y1, y2)  , Math.max(x1, x2) + dx ,Math.max(y1, y2) + dx);
		}  else if (botomLeft.contains(place)) {
			setBounds(Math.min(x1, x2) + dx , Math.min(y1, y2) , Math.max(x1, x2) ,Math.max(y1, y2) + dx);
		}
	}
	/**********************************************/
	
	
}
