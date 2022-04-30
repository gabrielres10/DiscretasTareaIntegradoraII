package model;

public class RedBlackNode<T> {
	private T value;
	private Color color = Color.RED;
	private RedBlackNode<T> parent;
	private RedBlackNode<T> leftChild;
	private RedBlackNode<T> rightChild;
	
	public RedBlackNode(T value) {
		this.value = value;
	}
	
	public void flipColor() {
		setColor(this.color == Color.RED? Color.BLACK : Color.RED);
	}
	
	public boolean isLeftChild() {
		return this == parent.getLeftChild();
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @return the parent
	 */
	public RedBlackNode<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
	}

	/**
	 * @return the leftChild
	 */
	public RedBlackNode<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild the leftChild to set
	 */
	public void setLeftChild(RedBlackNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public RedBlackNode<T> getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild the rightChild to set
	 */
	public void setRightChild(RedBlackNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	
	
	

}
