package studentBackup.bst;

import studentBackup.bst.Node;
import studentBackup.util.Acceptor;
import studentBackup.util.Visitor;
public class BST implements Acceptor
{
	Node root;
	public BST()
	{
		root = null;
	}
	
	public void accept(Visitor v)
	{
		v.visit(this);
	}
	
	
	public void insert(int value)
	{
		Node n = new Node(value);
		if(this.root == null)
		{
			this.root = n;
			return;
		}
		else
		{
			insert(this.root, n);
		}
	}
	
	public void insert(Node current, Node n)
	{		
		if(current.getValue() > n.getValue())
		{
			if(current.getLeft() == null)
			{
				current.setLeft(n);
			}
			else
			{
				insert(current.getLeft(), n);
			}
		}
		else
		{
			if(current.getRight() == null)
			{
				current.setRight(n);
			}
			else
			{
				insert(current.getRight(), n);
			}
		}
	}
	public void printInOrder()
	{
		printInOrder(this.root);
		System.out.println("");
	}
	
	public void printInOrder(Node current)
	{
		if(current != null)
		{
			printInOrder(current.getLeft());
			System.out.print(current.getValue() + " ");
			printInOrder(current.getRight());
		}
	}
}
