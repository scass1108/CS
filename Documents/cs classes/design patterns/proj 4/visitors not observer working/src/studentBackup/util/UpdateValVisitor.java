package studentBackup.util;
import studentBackup.bst.BST;
import studentBackup.bst.Node;
import java.util.Stack;
public class UpdateValVisitor implements Visitor
{
	int update_val;
	public UpdateValVisitor(int update_value)
	{
		update_val = update_value;
	}

	public void visit(BST b)
	{
		Node node = b.getRoot();
		Stack<Node> stack = new Stack<Node>();
		while(!stack.isEmpty() || node != null)
		{
			if(node != null)
			{
				stack.push(node);
				node = node.getLeft();
			}
			else
			{
				node = stack.pop();
				node.setValue(node.getValue() + update_val);
				node = node.getRight();
			}
		}
	}
	
}
