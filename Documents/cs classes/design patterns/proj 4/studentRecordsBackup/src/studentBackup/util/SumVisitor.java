package studentBackup.util;
import studentBackup.bst.BST;
import studentBackup.bst.Node;
import java.util.Stack;
public class SumVisitor implements Visitor
{
	int sum;
	
	public SumVisitor()
	{
	
	}
	
	public int getSum()
	{
		return sum;
	}
	
	
	public void visit(BST b)
	{
		sum = 0;
		Node node = b.getRoot();
		Stack<Node> stack = new Stack<Node>();
		int value;
		while(!stack.isEmpty() || node != null)
		{
			value = 0;
			if(node != null)
			{
				stack.push(node);
				node = node.getLeft();
			}
			else
			{
				node = stack.pop();
				value = node.getValue();
				sum += value;
				node = node.getRight();
			}
		}
		System.out.println("Sum from visitor is " + (sum * 3));
	}
}
