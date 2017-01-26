package View;

import Controller.ID;
import Model.BlueClown;
import Model.GreenClown;

public class Originator {
	private int bScore;
	private int gScore;
	
	   public void setbScore(int bScore){
	      this.bScore = bScore;
	   }
	   public void setgScore(int gScore){
		      this.gScore = gScore;
		   }

	   public int getbScore(){
	      return bScore;
	   }
	   public int getgScore(){
		      return gScore;
		   }


	   public Memento saveStateToMemento(){
	      return new Memento(bScore,gScore);
	   }

	   public int getbScoreFromMemento(Memento memento){
	      return memento.getbScore();
	   }
	   public int getgScoreFromMemento(Memento memento){
		      return memento.getgScore();
		   }

}
