package eval;
import java.util.ArrayList;

public class Set {

	public ArrayList<Integer> and (int[] New , int[] old) {
		ArrayList<Integer> s = new ArrayList<>();
		for (int i = 0 ; i < old.length ; i++) {
			for (int j = 0 ; j < New.length ; j++) {	
				if (old[i] == (New[j])) {
					s.add(old[i]);
					break ;
				}
			}
		}
		return s;
	}
	/**************************************************/
	public ArrayList<Integer> not (int[] old , int[] A) {
		ArrayList<Integer> s = new ArrayList<>();
		int found = 0 ;
		for (int i = 0 ; i < old.length ; i++) {
			found = 0 ;
			for (int j = 0 ; j < A.length ; j++) {
				if (old[i] == A[j]) {
					found = 1 ;
					break ;
				}
			}
			if (found == 0) {
				s.add(old[i]);
			}	
		}
		return s;
	}
	/******************************************************/
	public ArrayList<Integer> union (int[] A , int[] B) {
		ArrayList<Integer> s = new ArrayList<>();
		for (int i = 0 ; i < A.length ; i++) {
			s.add(A[i]);
		}
		int found = 0 ;
		for (int i = 0 ; i < B.length ; i++) {
			found = 0 ;
			for (int j = 0 ; j < A.length ; j++) {
				if (B[i] == A[j]) {
					found = 1 ;
					break ;
				}
			}
				if (found == 0) {
					s.add(B[i]);
				}
		}
		return s;
	}
	/******************************************************/
}
