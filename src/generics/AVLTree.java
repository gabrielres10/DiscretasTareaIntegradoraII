package generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class AVLTree<T, V extends Comparator<T>> implements Serializable {

	public static int insertados = 0;
	private static final long serialVersionUID = 1L;

	private NodeAVL<T> root; // this is the root of the binary tree
	private V comparator;

	/**
	 * Constructor method of the AVL tree
	 */
	public AVLTree(V comparator) {
		root = null;
		this.comparator = comparator;
	}

	/**
	 * Gets the root of the AVL tree
	 * 
	 * @return root, (T) NodeAVL, this is the root of the tree
	 */
	public NodeAVL<T> getRoot() {
		return root;
	}

	/**
	 * Sets the root of the AVL tree
	 * 
	 * @param root, (T) NodeAVL, this is the root of the tree
	 */
	public void setRoot(NodeAVL<T> root) {
		this.root = root;
	}

	/**
	 * This method searches a node with a given value
	 * 
	 * @param value,   T, this is the value to be found
	 * @param current, (T) NodeAVL this is the node of the iteration, in the first
	 *                 time it will always be the root of the node
	 * @return (T) NodeAVL, this is the node that contains the searched value
	 */
	public NodeAVL<T> search(T value, NodeAVL<T> current) {
		// Stop conditions of the search method
		if (this.root == null) {
			return null;
		} else if (current.getValue().equals(value)) {
			return current;
			// Iteration of the search method
		} else if (comparator.compare(current.getValue(), value) < 0

		) {
			// if the value of the current node is smaller than the value that is being
			// searched
			return search(value, current.getRightChild());
		} else {
			// the value of the current node is bigger
			return search(value, current.getLeftChild());
		}
	}

	/**
	 * This method gets the balance factor of a given node
	 * 
	 * @param node, (T) NodeAVL, this is the given node
	 * @return bf, int, this is the balance factor of the given node
	 */
	public int getBf(NodeAVL<T> node) {
		if (node == null) {
			return -1;
		} else {
			return node.getBf();
		}
	}

	/**
	 * This method rotates the given node to the right
	 * 
	 * @param node, (T) NodeAVL, this is the given node
	 * @return aux, (T) NodeAVL, this is the resulting node rotated to the right
	 */
	public NodeAVL<T> rightRotation(NodeAVL<T> node) {
		NodeAVL<T> aux = node.getLeftChild();
		node.setLeftChild(aux.getRightChild());
		aux.setRightChild(node);
		node.setBf(Math.max(getBf(node.getLeftChild()), getBf(node.getRightChild())) + 1);
		aux.setBf(Math.max(getBf(aux.getLeftChild()), getBf(aux.getRightChild())) + 1);

		return aux;
	}

	/**
	 * This method rotates the given node to the left
	 * 
	 * @param node, (T) NodeAVL, this is the given node
	 * @return aux, (T) NodeAVL, this is the resulting node rotated to the left
	 */
	public NodeAVL<T> leftRotation(NodeAVL<T> node) {
		NodeAVL<T> aux = node.getRightChild();
		node.setRightChild(aux.getLeftChild());
		aux.setLeftChild(node);
		node.setBf(Math.max(getBf(node.getLeftChild()), getBf(node.getRightChild())) + 1);
		aux.setBf(Math.max(getBf(aux.getLeftChild()), getBf(aux.getRightChild())) + 1);

		return aux;

	}

	/**
	 * This method, firstly, rotates the left child of the given node to the left,
	 * and then it rotates the given node to the right. This process is known as
	 * left-right rotation
	 * 
	 * @param node, (T) NodeAVL, this is the given node
	 * @return (T) NodeAVL, aux, this is the resulting left-right rotated node
	 */
	public NodeAVL<T> leftRightRotation(NodeAVL<T> node) {
		NodeAVL<T> aux;
		node.setLeftChild(leftRotation(node.getLeftChild()));
		aux = rightRotation(node);

		return aux;
	}

	/**
	 * This method, firstly, rotates the right child of the given node to the right,
	 * and then it rotates the resulting node to the left. This process is known as
	 * right-left rotation
	 * 
	 * @param node, (T) NodeAVL, this is the given node
	 * @return (T) NodeAVL, this is the resulting right-left rotated node
	 */
	public NodeAVL<T> rightLeftRotation(NodeAVL<T> node) {
		NodeAVL<T> aux;
		node.setRightChild(rightRotation(node.getRightChild()));
		aux = leftRotation(node);
		return aux;
	}

	/**
	 * This method inserts a new node to the AVL tree. The logic of the insertion is
	 * the same as a binary tree. This method is recursive, and the variable of the
	 * recursion will be <b>subtree</b>
	 *
	 * @param newNode, (T) NodeAVL, this is the new node to insert into the tree
	 * @param subtree, (T) NodeAVL, this is the subtree of the iterated node. In the
	 *                 first time of the recursion, this variable will be the root,
	 *                 then, the variable will change according to the conditions
	 *                 given by the recursive method.
	 * @return (T) NodeAVL, this is the resulting parent node which will be the new
	 *         root of the tree. This node contains the inserted node.
	 */
	public NodeAVL<T> insertAVL(NodeAVL<T> newNode, NodeAVL<T> subtree) {

		NodeAVL<T> newParent = subtree;
		// If the value of the new node is smaller than the value of the subtree
		if (comparator.compare(newNode.getValue(), subtree.getValue()) < 0) {
			if (subtree.getLeftChild() == null) {
				subtree.setLeftChild(newNode);
				insertados++;
			} else {
				// Recursion when the new node already has a left child
				subtree.setLeftChild(insertAVL(newNode, subtree.getLeftChild()));
				/*
				 **** Balance cases ****
				 */
				if (getBf(subtree.getLeftChild()) - getBf(subtree.getRightChild()) == 2) {
					// When subtree (current parent) balance factor == -2
					if (comparator.compare(newNode.getValue(), subtree.getLeftChild().getValue()) < 0) {
						// When current child balance factor == -1
						newParent = rightRotation(subtree);
					} else {
						// When current child balance factor == 1
						newParent = leftRightRotation(subtree);
					}
				}
			}
			// If the value of the new node is greater than the value of the subtree
		} else if (comparator.compare(newNode.getValue(), subtree.getValue()) > 0) {
			if (subtree.getRightChild() == null) {
				insertados++;
				subtree.setRightChild(newNode);
			} else {
				// Recursion
				subtree.setRightChild(insertAVL(newNode, subtree.getRightChild()));
				/*
				 **** Balance cases ****
				 */
				if (getBf(subtree.getRightChild()) - getBf(subtree.getLeftChild()) == 2) {
					// When subtree (current parent) balance factor == 2
					if (comparator.compare(newNode.getValue(), subtree.getRightChild().getValue()) > 0) {
						// When current child balance factor == 1
						newParent = leftRotation(subtree);
					} else {
						// When current child balance factor == -1
						newParent = rightLeftRotation(subtree);
					}

				}
			}
			// If the value of the new node is equal to the value of the subtree
		} else {
			
		}

		// Uptade balance factor
		if (subtree.getLeftChild() == null && subtree.getRightChild() != null) {
			subtree.setBf(subtree.getRightChild().getBf() + 1);
		} else if (subtree.getRightChild() == null && subtree.getLeftChild() != null) {
			subtree.setBf(subtree.getLeftChild().getBf() + 1);
		} else {
			subtree.setBf(Math.max(getBf(subtree.getLeftChild()), getBf(subtree.getRightChild())) + 1);
		}
		return newParent;
	}
	
	/*
	public boolean add(T e) throws ClassCastException, NullPointerException, IllegalStateException{
    	NodeAVL<T> NodeAVL = new NodeAVL<T>(e);
    	boolean salir = false;
    	boolean der = false;
    	NodeAVL<T> raizTmp = this.getRoot();

    	int altIzq, altDer;

    	//no existía arbol
    	if(raizTmp == null){
    		this.raiz = NodeAVL;
    		return true;
    	}else
    	
    	//estaba ya en el arbol?
    	if(this.contains(NodeAVL.getValue())){
    		return false;
    	}
    	
    	//no estaba antes en el arbol
    	else{    	
    		while(!salir){

    			//es mayor el NodeAVL a insertar que la raiz?    				
		    	if(this.compararDato(NodeAVL.getValue(), raizTmp.getValue())>0){
		    		if(raizTmp.getRightChild()!=null){
		    			raizTmp = raizTmp.getRightChild();	
		    		}else{
		    			salir = true;
		    			der = true;
		    		}
		    			    		
		    	}
		    	//el NodeAVL es menor que la raiz
		    	else{
		    		if(raizTmp.getLeftChild()!=null){
		    			raizTmp = raizTmp.getLeftChild();
		    		}else{
		    			salir = true;
		    		}
		    	}
    		}
    		
    		//tengo que insertarlo a la derecha?
    		if(der){
    			raizTmp.setRightChild(NodeAVL);
    		}
    		
    		//lo inserto a la izquierda
    		else{
    			raizTmp.setLeftChild(NodeAVL);
    		}
	
    		//mientras no este equilibrado el arbol	miramos donde reestructurar
    		while(equilibrado(this.getRoot())<0){
				raizTmp = padre(raizTmp);
    		
    			if(raizTmp.getRightChild()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = raizTmp.getRightChild().getHeight();
	    		}
	    		
	    		if(raizTmp.getLeftChild()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = raizTmp.getLeftChild().getHeight();
	    		}
	    		
    			NodeAVL<T> cambiar = estructurar(raizTmp, altIzq, altDer);
    			NodeAVL<T> superior = padre(raizTmp);
	
    			//si los NodeAVLs modificados tenian un padre anteriormente
    			if(compararDato(superior.getValue(), raizTmp.getValue())!=0){
    				if(superior.getLeftChild()!=null && compararDato(superior.getLeftChild().getValue(), raizTmp.getValue())==0){
	    				superior.setLeftChild(cambiar);		
		    		}
		    		else if(superior.getRightChild()!=null && compararDato(superior.getRightChild().getValue(), raizTmp.getValue())==0){
	    				superior.setRightChild(cambiar);
	    			}
    			}else{
    				this.raiz = cambiar;
    			}
    		}
    		return true;
    	}
    }
*/
	/**
	 * In order to insert a new node, this method runs the <b>insertAVL</b> method
	 * if already exists a root. It assigns a new node with the <b>value</b> to the
	 * root otherwise.
	 * 
	 * @param value, T, this is the value to be added to the tree
	 */
	public void insert(T value) {
		NodeAVL<T> newNode = new NodeAVL<>(value);
		// In case this is the first insert in the tree
		if (root == null) {
			root = newNode;
		} else {
			root = insertAVL(newNode, root);
		}
	}

	/**
	 * This method gets the height of tree
	 * 
	 * @param node
	 * @return
	 */
	public int getHeight(NodeAVL<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
	}

	/**
	 * This method shows the AVL tree in a preOrden way
	 * 
	 * @param root, this is the root of the AVL tree
	 */
	public void preOrden(NodeAVL<T> root, int x) {
		if (root != null) {
			System.out.print(root.getValue() + " " + x);
			preOrden(root.getLeftChild(), x + 1);
			preOrden(root.getRightChild(), x + 1);
		}
	}

}