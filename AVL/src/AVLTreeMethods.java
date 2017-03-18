
public class AVLTreeMethods {
	
	 int height(NodeImp N)
	    {
	        if (N == null)
	             return 0;
	         return N.height;
	    }
	 
	    // A utility function to get maximum of two integers
	    int max(int a, int b)
	    {
	        return (a > b) ? a : b;
	    }
	 
	    // A utility function to right rotate subtree rooted with y
	    // See the diagram given above.
	    NodeImp rightRotate(NodeImp y)
	    {
	    	NodeImp x = y.left;
	    	NodeImp T2 = x.right;
	 
	        // Perform rotation
	        x.right = y;
	        y.left = T2;
	 
	        // Update heights
	        y.height = max(height(y.left), height(y.right)) + 1;
	        x.height = max(height(x.left), height(x.right)) + 1;
	 
	        // Return new root
	        return x;
	    }
	 
	    // A utility function to left rotate subtree rooted with x
	    // See the diagram given above.
	    NodeImp leftRotate(NodeImp x)
	    {
	    	NodeImp y = x.right;
	    	NodeImp T2 = y.left;
	 
	        // Perform rotation
	        y.left = x;
	        x.right = T2;
	 
	        //  Update heights
	        x.height = max(height(x.left), height(x.right)) + 1;
	        y.height = max(height(y.left), height(y.right)) + 1;
	 
	        // Return new root
	        return y;
	    }
	 
	    // Get Balance factor of node N
	    int getBalance(NodeImp N)
	    {
	        if (N == null)
	            return 0;
	        return height(N.left) - height(N.right);
	    }
//	 
//	    NodeImp insert(NodeImp node, int key)
//	    {
//	        /* 1.  Perform the normal BST rotation */
//	        if (node == null)
//	            return (new NodeImp(key));
//	 
//	        if (key < node.value)
//	            node.left = insert(node.left, key);
//	        else if (key > node.value)
//	            node.right = insert(node.right, key);
//	        else // Equal keys not allowed
//	            return node;
//	 
//	        /* 2. Update height of this ancestor node */
//	        node.height = 1 + max(height(node.left),
//	                              height(node.right));
//	 
//	        /* 3. Get the balance factor of this ancestor
//	           node to check whether this node became
//	           Wunbalanced */
//	        int balance = getBalance(node);
//	 
//	        // If this node becomes unbalanced, then
//	        // there are 4 cases Left Left Case
//	        if (balance > 1 && key < node.left.value)
//	            return rightRotate(node);
//	 
//	        // Right Right Case
//	        if (balance < -1 && key > node.right.value)
//	            return leftRotate(node);
//	 
//	        // Left Right Case
//	        if (balance > 1 && key > node.left.value)
//	        {
//	            node.left = leftRotate(node.left);
//	            return rightRotate(node);
//	        }
//	 
//	        // Right Left Case
//	        if (balance < -1 && key < node.right.value)
//	        {
//	            node.right = rightRotate(node.right);
//	            return leftRotate(node);
//	        }
//	 
//	        /* return the (unchanged) node pointer */
//	        return node;
//	    }
	    
	    NodeImp insert(Comparable key, NodeImp t)
	    {
	        if (t == null)
	            t = new NodeImp(key);
	        else if (key .compareTo( t.value)<0)
	        {
	            t.left = insert( key, t.left );
	            if( height( t.left ) - height( t.right ) == 2 )
	                if( key.compareTo(t.left.value)<0 )
	                    t = rotateWithLeftChild( t );
	                else
	                    t = doubleWithLeftChild( t );
	        }
	        else if( key .compareTo( t.value)>0 )
	        {
	            t.right = insert( key, t.right );
	            if( height( t.right ) - height( t.left ) == 2 )
	                if( key .compareTo(t.right.value)>0)
	                    t = rotateWithRightChild( t );
	                else
	                    t = doubleWithRightChild( t );
	        }
	        else
	          ;  // Duplicate; do nothing
	        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
	        return t;
	    }
	 
	    /* Given a non-empty binary search tree, return the
	       node with minimum key value found in that tree.
	       Note that the entire tree does not need to be
	       searched. */
	    NodeImp minValueNode(NodeImp node)
	    {
	    	NodeImp current = node;
	 
	        /* loop down to find the leftmost leaf */
	        while (current.left != null)
	           current = current.left;
	 
	        return current;
	    }
	 
	    NodeImp deleteNode(NodeImp root, Comparable key)
	    {
	        // STEP 1: PERFORM STANDARD BST DELETE
	        if (root == null)
	            return root;
	 
	        // If the key to be deleted is smaller than
	        // the root's key, then it lies in left subtree
	        if (key .compareTo(root.value)<0)
	            root.left = deleteNode(root.left, key);
	 
	        // If the key to be deleted is greater than the
	        // root's key, then it lies in right subtree
	        else if (key .compareTo(root.value)>0)
	            root.right = deleteNode(root.right, key);
	 
	        // if key is same as root's key, then this is the node
	        // to be deleted
	        else
	        {
	 
	            // node with only one child or no child
	            if ((root.left == null) || (root.right == null))
	            {
	            	NodeImp temp = null;
	                if (temp == root.left)
	                    temp = root.right;
	                else
	                    temp = root.left;
	 
	                // No child case
	                if (temp == null)
	                {
	                    temp = root;
	                    root = null;
	                }
	                else   // One child case
	                    root = temp; // Copy the contents of
	                                 // the non-empty child
	            }
	            else
	            {
	 
	                // node with two children: Get the inorder
	                // successor (smallest in the right subtree)
	            	NodeImp temp = minValueNode(root.right);
	 
	                // Copy the inorder successor's data to this node
	                root.value = temp.value;
	 
	                // Delete the inorder successor
	                root.right = deleteNode(root.right, temp.value);
	            }
	        }
	 
	        // If the tree had only one node then return
	        if (root == null)
	            return root;
	 
	        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
	        root.height = max(height(root.left), height(root.right)) + 1;
	 
	        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
	        //  this node became unbalanced)
	        int balance = getBalance(root);
	 
	        // If this node becomes unbalanced, then there are 4 cases
	        // Left Left Case
	        if (balance > 1 && getBalance(root.left) >= 0)
	            return rightRotate(root);
	 
	        // Left Right Case
	        if (balance > 1 && getBalance(root.left) < 0)
	        {
	            root.left = leftRotate(root.left);
	            return rightRotate(root);
	        }
	 
	        // Right Right Case
	        if (balance < -1 && getBalance(root.right) <= 0)
	            return leftRotate(root);
	 
	        // Right Left Case
	        if (balance < -1 && getBalance(root.right) > 0)
	        {
	            root.right = rightRotate(root.right);
	            return leftRotate(root);
	        }
	 
	        return root;
	    }
	    public boolean search(NodeImp r, Comparable val)
	    {
	        boolean found = false;
	        while ((r != null) && !found)
	        {
	            Comparable  rval = r.value;
	            if (val.compareTo(rval)<0)
	                r = r.left;
	            else if (val .compareTo(rval)>0)
	                r = r.right;
	            else
	            {
	                found = true;
	                break;
	            }
	            found = search(r, val);
	        }
	        return found;
	    }
	    
	    private NodeImp rotateWithLeftChild(NodeImp k2)
	     {
	         NodeImp k1 = k2.left;
	         k2.left = k1.right;
	         k1.right = k2;
	         k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
	         k1.height = Math.max( height( k1.left ), k2.height ) + 1;
	         return k1;
	     }
	 
	     /* Rotate binary tree node with right child */
	     private NodeImp rotateWithRightChild(NodeImp k1)
	     {
	         NodeImp k2 = k1.right;
	         k1.right = k2.left;
	         k2.left = k1;
	         k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
	         k2.height = Math.max( height( k2.right ), k1.height ) + 1;
	         return k2;
	     }
	     /**
	      * Double rotate binary tree node: first left child
	      * with its right child; then node k3 with new left child */
	     private NodeImp doubleWithLeftChild(NodeImp k3)
	     {
	         k3.left = rotateWithRightChild( k3.left );
	         return rotateWithLeftChild( k3 );
	     }
	     /**
	      * Double rotate binary tree node: first right child
	      * with its left child; then node k1 with new right child */      
	     private NodeImp doubleWithRightChild(NodeImp k1)
	     {
	         k1.right = rotateWithLeftChild( k1.right );
	         return rotateWithRightChild( k1 );
	     }    
		

}
