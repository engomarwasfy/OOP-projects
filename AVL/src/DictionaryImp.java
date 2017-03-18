import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryImp implements IDictionary{
	AVLTreeImpl<String >tree;
	int size=0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(File file) {
		tree= new AVLTreeImpl<>();
		size=0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			    	
			    	tree.insert(line);
			    	size++;
			        line = br.readLine();
			    }
			    String everything = sb.toString();
			} finally {
			    br.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		// TODO Auto-generated method stub
		if(exists(word)){
			return false;
		}else{
		tree.insert(word);
		size++;
		return true;
		}
	}

	@Override
	public boolean exists(String word) {
		// TODO Auto-generated method stub
		return tree.search(word);
	}

	@Override
	public boolean delete(String word) {
		// TODO Auto-generated method stub
		
		if(exists(word)){
			tree.delete(word);
			size--;
			return true;
		}else{
		return false;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return tree.height();
	}

}
