
package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.paint.model.AllShape;
import eg.edu.alexu.csd.oop.paint.model.CircleImp;
import eg.edu.alexu.csd.oop.paint.model.ElipseImp;
import eg.edu.alexu.csd.oop.paint.model.LineImp;
import eg.edu.alexu.csd.oop.paint.model.RectangleImp;
import eg.edu.alexu.csd.oop.paint.model.SquareImp;
import eg.edu.alexu.csd.oop.paint.view.Gui;

/**
 * @author wasfy
 *
 */
public class Surface extends JPanel {
    /**
     * manager of resizing multi shapes.
     *
     */
    private static MoreShape manger;
    /**
     * 
     * array of shapes.
     */
    private ArrayList<AllShape> shapes;
    /**
     * All moves of paint program. object of class undo redo.
     */
    public static AllMoves m;
    /**
     * 
     * points.
     */
    private Point startDrag, endDrag;
    /**
     * 
     * to check if shape was selected or not.
     */
    private boolean isSelected; // flag to check if there is selected shape
    /**
     * 
     * pointer for selected shape.
     */
    private AllShape selectedShape;
    /**
     * 
     *default color
     */
    private Color fillColor = Color.WHITE;
    /**
     * default color
     *
     */
    private Color color = Color.black; // drawer color
    /**
     * 
     *
     */
    private ArrayList<AllShape> selectedShapes;
    /**
     * getter
     * @retuurn selectedshape s
     */

    public ArrayList<AllShape> getSelectedShapes() {
	return selectedShapes;
    }

    /**
     * url for dynamic class loading
     *
     */
    public static String url;

    /********** getter and setter *********************/
    /**
     * 
     */
    public ArrayList<AllShape> getShapes() {
	return shapes;
    }

    public void setShapes(final ArrayList<AllShape> shapes) {
	this.shapes = shapes;
    }

    public Point getStartDrag() {
	return startDrag;
    }

    public void setStartDrag(final Point startDrag) {
	this.startDrag = startDrag;
    }

    public Point getEndDrag() {
	return endDrag;
    }

    public void setEndDrag(final Point endDrag) {
	this.endDrag = endDrag;
    }

    public boolean isSelected() {
	return isSelected;
    }

    public void setSelected(final boolean isSelected) {
	this.isSelected = isSelected;
    }

    public AllShape getSelectedShape() {
	return selectedShape;
    }

    public void setSelectedShape(final AllShape selectedShape) {
	this.selectedShape = selectedShape;
    }

    public Color getFillColor() {
	return fillColor;
    }

    public void setFillColor(final Color fillColor) {
	this.fillColor = fillColor;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(final Color color) {
	this.color = color;
    }

    /**********************************************/
    public Surface() {
	initUI();
	shapes = new ArrayList<AllShape>();
	m = new AllMoves();
	selectedShapes = new ArrayList<AllShape>();
	manger = new MoreShape();
    }

    private void initUI() {
	addMouseListener(new ShapeTestAdapter());
	addMouseMotionListener(new ShapeTestAdapter());
    }

    /******************** drawing function *****************/

    private void doDrawing(final Graphics g) {
	try {
	    for (AllShape s : shapes) {
		s.draw(g, false);
	    }

	    if (startDrag != null && endDrag != null) {
		if (Gui.activeButton == "rectangle") {
		    new RectangleImp(startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()).draw(g, false);
		} else if (Gui.activeButton == "square") {
		    new SquareImp(startDrag.x, startDrag.y, endDrag.x, startDrag.y + (endDrag.x - startDrag.x), null,
			    getColor()).draw(g, false);
		} else if (Gui.activeButton == "elipse") {
		    new ElipseImp(startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()).draw(g, false);
		} else if (Gui.activeButton == "circle") {

		    new CircleImp(startDrag.x, startDrag.y, endDrag.x, startDrag.y + (endDrag.x - startDrag.x), null,
			    getColor()).draw(g, false);
		} else if (Gui.activeButton == "triangle") {

		    try {
			DynamicLoader x = new DynamicLoader();
			x.getShape(url, startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()).draw(g,
				false);
		    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		    }
		} else if (Gui.activeButton == "line") {
		    new LineImp(startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()).draw(g, false);
		}
	    }

	    if (isSelected == true) {
		String name = selectedShape.getShapeName();
		float x1 = selectedShape.getX1();
		float x2 = selectedShape.getX2();
		float width = selectedShape.getWidth();
		float height = selectedShape.getHeight();
		Gui.getLabel().setText("omar wasfy  & shaban sheta" + "       " + name + "x1 = " + "     " + x1
			+ "     " + "x2 = " + x2 + "     " + "width = " + width + "     " + "height = " + height);
		selectedShape.setSelectedPoints(g);
	    }
	    if (Gui.activeButton == "select") {
		if (selectedShapes.size() != 0) {
		    manger.setShapes(selectedShapes);
		    manger.setSelectedPoints(g);
		    repaint();
		}
	    }
	} catch (Exception e5) {
	}
    }

    /************** function to create Graphic *********/
    @Override
    public void paintComponent(final Graphics g) {
	super.paintComponent(g);
	try {
	    doDrawing(g);
	} catch (Exception e6) {
	}
    }

    /*************** sub class for mouse event *******************/
    private class ShapeTestAdapter extends MouseAdapter {

	/********* function used to select shape ******************/
	// delete selected shape
	// fill selected shape
	// change drawer color
	@Override
	public void mouseClicked(final MouseEvent e) {
	    try {
		if (Gui.activeButtonEdit == "edit") {
		    if (Gui.activeButton == "select") {
			for (AllShape s : shapes) {
			    if ((e.getPoint() != null) && s.getShape().contains(e.getPoint())) {
				selectedShape = s;
			    }
			}
			selectedShapes.add(selectedShape);
			isSelected = false;
		    } else {
			for (AllShape s : shapes) {
			    if (!s.isLine()) {
				if ((e.getPoint() != null) && s.getShape().contains(e.getPoint())) {
				    isSelected = true;
				    selectedShape = s;
				}
			    } else {
				if ((e.getPoint() != null) && s.contains(e.getPoint())) {
				    isSelected = true;
				    selectedShape = s;
				}
			    }
			}
			if (Gui.activeButton == "delete") {
			    shapes.remove(selectedShape);
			    ArrayList<AllShape> s = new ArrayList<AllShape>();
			    for (AllShape s1 : shapes) {
				s.add(s1);
			    }
			    if (Gui.activeButton != "null") {
				m.takeMove(s);
			    }

			    isSelected = false;
			} else if ((selectedShape != null) && !selectedShape.isLine()
				&& (selectedShape.getShape().contains(e.getPoint()))
				&& (Gui.activeButton == "fillcolor")) {
			    selectedShape.setFillColor(fillColor);
			    ArrayList<AllShape> s = new ArrayList<AllShape>();
			    for (AllShape s1 : shapes) {
				s.add(s1);
			    }
			    m.takeMove(s);
			    isSelected = false;
			} else if ((selectedShape != null) && !selectedShape.isLine()
				&& (selectedShape.getShape().contains(e.getPoint()))
				&& (Gui.activeButton == "drawcolor")) {
			    selectedShape.setDrawColor(color);
			    ArrayList<AllShape> s = new ArrayList<AllShape>();
			    for (AllShape s1 : shapes) {
				s.add(s1);
			    }
			    m.takeMove(s);
			    selectedShapes.clear();
			    isSelected = false;
			}
		    }
		    repaint();
		}
	    } catch (Exception e3) {
	    }
	}

	/***** function to take first point when click ********/
	@Override
	public void mousePressed(final MouseEvent e) {
	    try {
		startDrag = new Point(e.getX(), e.getY());
		endDrag = startDrag;
		repaint();
	    } catch (Exception e4) {
	    }
	}

	/******* function to take second point when released ****/
	// save final shape in array list of shapes
	// save array list of shapes in another array list
	@Override
	public void mouseReleased(final MouseEvent e) {
	    try {
		if (Gui.activeButtonEdit != "edit") {
		    if (Gui.activeButton == "rectangle") {
			shapes.add(new RectangleImp(startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()));
		    } else if (Gui.activeButton == "square") {
			shapes.add(new SquareImp(startDrag.x, startDrag.y, endDrag.x,
				startDrag.y + (endDrag.x - startDrag.x), null, getColor()));
		    } else if (Gui.activeButton == "elipse") {
			shapes.add(new ElipseImp(startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()));
		    } else if (Gui.activeButton == "circle") {
			shapes.add(new CircleImp(startDrag.x, startDrag.y, endDrag.x,
				startDrag.y + (endDrag.x - startDrag.x), null, getColor()));
		    } else if (Gui.activeButton == "triangle") {
			DynamicLoader x = new DynamicLoader();
			try {
			    shapes.add(
				    x.getShape(url, startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			    e1.printStackTrace();
			}
		    } else if (Gui.activeButton == "line") {
			shapes.add(new LineImp(startDrag.x, startDrag.y, endDrag.x, endDrag.y, null, getColor()));
		    }
		    startDrag = null;
		    endDrag = null;

		}
		ArrayList<AllShape> s = new ArrayList<AllShape>();
		for (AllShape s1 : shapes) {
		    s.add(s1);
		}
		if (Gui.activeButton != "null") {
		    m.takeMove(s);
		}
		isSelected = false;
		repaint();
	    } catch (Exception e2) {
	    }
	}

	/********* function that paint the shape while drag with mouse *****/
	// without saving shape
	@Override
	public void mouseDragged(final MouseEvent e) {
	    try {
		isSelected = false;
		endDrag = new Point(e.getX(), e.getY());
		if (Gui.activeButtonEdit == "edit") {
		    /******************************* move ***********************/
		    if (selectedShape != null && !selectedShape.isLine()) {
			if (selectedShape != null && selectedShape.getShape().contains(e.getPoint())
				&& Gui.activeButton == "move") {
			    isSelected = true;
			    float dx = e.getX() - startDrag.x;
			    float dy = e.getY() - startDrag.y;
			    selectedShape.move(dx, dy);
			    startDrag.x += dx;
			    startDrag.y += dy;
			}
		    } else {

			if (selectedShape != null && Gui.activeButton == "move") {
			    if (selectedShape.getTopMid().contains(e.getPoint())) {
				isSelected = true;
				float dx = e.getX() - startDrag.x;
				float dy = e.getY() - startDrag.y;
				selectedShape.move(dx, dy);
				startDrag.x += dx;
				startDrag.y += dy;
			    }
			}
		    }

		    /******************************** resize ***************************/
		    if (selectedShape != null && (Gui.activeButton == "resize")) {
			isSelected = true;
			float dxEdit = e.getX() - startDrag.x;
			float dyEdit = e.getY() - startDrag.y;
			Point place = e.getPoint();
			selectedShape.reSize(dxEdit, dyEdit, place);
			startDrag.x += dxEdit;
			startDrag.y += dyEdit;
		    } else if (Gui.activeButton == "select") {
			isSelected = false;
			float dxEdit = e.getX() - startDrag.x;
			float dyEdit = e.getY() - startDrag.y;
			Point place = e.getPoint();
			manger.Edit(dxEdit, dyEdit, place);
			startDrag.x += dxEdit;
			startDrag.y += dyEdit;
		    }
		}
		repaint();
	    } catch (Exception e1) {
	    }
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	    repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
	    repaint();
	}
    }
}
