package eg.edu.alexu.csd.oop.paint.controller;

import java.awt.Color;
import java.util.ArrayList;

import eg.edu.alexu.csd.oop.paint.model.AllShape;
import eg.edu.alexu.csd.oop.paint.model.CircleImp;
import eg.edu.alexu.csd.oop.paint.model.ElipseImp;
import eg.edu.alexu.csd.oop.paint.model.LineImp;
import eg.edu.alexu.csd.oop.paint.model.RectangleImp;
import eg.edu.alexu.csd.oop.paint.model.SquareImp;;

public class AllMoves {
	private ArrayList<ArrayList<AllShape>> moves ;
	private ArrayList<AllShape> empty ;
	private int pointer;

	public AllMoves() {
		empty = new ArrayList<AllShape>();
		moves = new ArrayList<ArrayList<AllShape>>();
		moves.add(empty);
		pointer = 0 ;
	}

	public void takeMove(ArrayList<AllShape> shapes) {
		ArrayList<AllShape> s = new ArrayList<AllShape>();
		for (AllShape s1 : shapes) {
			float x1 = s1.getX1();
			float x2 = s1.getX2();
			float y1 = s1.getY1();
			float y2 = s1.getY2();
			Color drawer = s1.getDrawColor();
			Color fill = s1.getFillColor();
			if(s1.getShapeName() == "rectangle") {
				s.add(new RectangleImp(x1,y1,x2,y2,fill,drawer));
			} else if(s1.getShapeName() == "square") {
				s.add(new SquareImp(x1,y1,x2,y2,fill,drawer));
			} else if(s1.getShapeName() == "elipse") {
				s.add(new ElipseImp(x1,y1,x2,y2,fill,drawer));
			} else if(s1.getShapeName() == "circle") {
				s.add(new CircleImp(x1,y1,x2,y2,fill,drawer));
			} else if(s1.getShapeName() == "line") {
				s.add(new LineImp(x1,y1,x2,y2,fill,drawer));
			}
		}
		for (int i = pointer + 1; i < moves.size(); i++) {
			moves.remove(i);
		}
		moves.add(s);

		pointer = moves.size() - 1;
	}

	public ArrayList<AllShape> redo() {
		if(pointer==moves.size()-1){
			throw new RuntimeException(); 
		}
		pointer++;
		ArrayList<AllShape> s = new ArrayList<AllShape>();
		for (AllShape s1 : moves.get(pointer)) {
			s.add(s1);
		}
		return s;

	}

	public ArrayList<AllShape> undo() {
		if(pointer==0){
			throw new RuntimeException(); 
		}
		pointer--;
		ArrayList<AllShape> s = new ArrayList<AllShape>();
		for (AllShape s1 : moves.get(pointer)) {
			s.add(s1);
		}
		return s;

	}

	public ArrayList<ArrayList<AllShape>> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<ArrayList<AllShape>> moves) {
		this.moves = moves;
	}

	public ArrayList<AllShape> getEmpty() {
		return empty;
	}

	public void setEmpty(ArrayList<AllShape> empty) {
		this.empty = empty;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
}
