package model;

public class NodeAVL<T> {
	private T value; // this is the value that the node contains
	private int bf; // this is the balance factor of the node
	private NodeAVL<T> leftChild; // this is the left child of the node
	private NodeAVL<T> rightChild; // this is the right child of the node

	/**
	 * Constructor of the AVL node
	 * 
	 * @param value, this is the value of the node
	 */
	public NodeAVL(T value) {
		this.value = value;
		this.bf = 0;
		this.leftChild = null;
		this.rightChild = null;
	}

	/**
	 * Gets the value of the node
	 * 
	 * @return value, T, this is the value of the node
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value of the node
	 * 
	 * @param value, T, this is the value of the node
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Gets the balance factor of the node
	 * 
	 * @return bf, int, this is the balance factor of the node
	 */
	public int getBf() {
		return bf;
	}

	/**
	 * Sets the balance factor of the node
	 * 
	 * @param bf, int, this is the balance factor of the node
	 */
	public void setBf(int bf) {
		this.bf = bf;
	}

	/**
	 * Gets the left child of the node
	 * 
	 * @return leftChild, (T) NodeAVL, this is the left child of the node
	 */
	public NodeAVL<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets the left child of the node
	 * 
	 * @param leftChild, (T) NodeAVL, this is the left child of the node
	 */
	public void setLeftChild(NodeAVL<T> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * Gets the right child of the node
	 * 
	 * @return rightChild, (T) NodeAVL, this is the right child of the node
	 */
	public NodeAVL<T> getRightChild() {
		return rightChild;
	}

	/**
	 * Sets the right child of the node
	 * 
	 * @param rightChild, (T) NodeAVL, this is the right child of the node
	 */
	public void setRightChild(NodeAVL<T> rightChild) {
		this.rightChild = rightChild;
	}

}
