import java.util.ArrayList;
import java.util.Stack;

public class AVLTreeImpl<T> implements IAVLTree {
	public static void main(String[] args) {
		AVLTreeImpl x= new AVLTreeImpl();
		x.insert(5);
		x.insert(7);
		//x.insert();
		NodeImp t =x.root;
		
	}
	
	NodeImp root;
	AVLTreeMethods m = new AVLTreeMethods();
	@Override
	public void insert(Comparable key) {
		// TODO Auto-generated method stub
		root = m.insert(key, root);
		
	}
	@Override
	public boolean delete(Comparable key) {
		// TODO Auto-generated method stub
		if(search(key)){
			root = m.deleteNode(root, key);
			return true;
		}
			
		return false;
	}

	@Override
	public boolean search(Comparable key) {
		// TODO Auto-generated method stub
		return m.search(root, key);
	}
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return root.height;
	}
	@Override
	public INode getTree() {
		// TODO Auto-generated method stub
		return root;
	}

}