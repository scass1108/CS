package studentBackup.bst;
public class Node extends BST
{
	int value;
	Node left;
	Node right;
	
	public Node(int n)
	{
		value = n;
		left = null;
		right = null;
	}
	
	public int getValue()
	{
		return value;
	}
	public Node getLeft()
	{
		return left;
	}
	public Node getRight()
	{
		return right;
	}
	public void setLeft(Node n)
	{
		left = n;
	}
	public void setRight(Node n)
	{
		right = n;
	}	
	public void setValue(int n)
	{
		value = n;
	}
}
