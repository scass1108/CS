package studentBackup.util;
import studentBackup.bst.BST;
public class SumVisitor implements Visitor
{
	int sum;
	
	public SumVisitor()
	{
		sum = 0;
	}
	
	public int getSum()
	{
		return sum;
	}
	
	
	public void visit(BST b)
	{
		//sum += b.getData();
	}
}
