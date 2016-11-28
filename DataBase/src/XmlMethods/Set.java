package XmlMethods;

import java.util.ArrayList;

public class Set {

	public int[] and (int[] New , int[] old) {
		ArrayList<Integer> s = new ArrayList<>();
		for (int i = 0 ; i < old.length ; i++) {
			for (int j = 0 ; j < New.length ; j++) {	
				if (old[i] == (New[j])) {
					s.add(old[i]);
					break ;
				}
			}
		}
		int[] arr = new int[s.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.get(i);
		}
		return arr;
	}
	/**************************************************/
	public int[] not (int[] old , int[] A) {
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
		int[] arr = new int[s.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.get(i);
		}
		return arr;
	}
	/******************************************************/
	public int[] union (int[] A , int[] B) {
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
		int[] arr = new int[s.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.get(i);
		}
		return arr;
	}
	/******************************************************/
}
