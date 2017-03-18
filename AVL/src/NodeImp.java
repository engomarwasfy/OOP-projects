
public class NodeImp<T>implements INode {
	NodeImp left;
	NodeImp right;
	Comparable<T> value;
	int height;
	public NodeImp(Comparable key) {
		// TODO Auto-generated constructor stub
		this.value=key;
	}
	@Override
	public INode getLeftChild() {
		// TODO Auto-generated method stub
		return left;
	}
	@Override
	public INode getRightChild() {
		// TODO Auto-generated method stub
		return right;
	}
	@Override
	public Comparable getValue() {
		// TODO Auto-generated method stub
		return (Comparable) value;
	}
	@Override
	public void setValue(Comparable value) {
		// TODO Auto-generated method stub
		this.value= value;
	}
	
	
	

}