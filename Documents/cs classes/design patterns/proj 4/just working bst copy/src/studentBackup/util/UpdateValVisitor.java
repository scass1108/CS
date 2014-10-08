package studentBackup.util;
import studentBackup.bst.BST;
public class UpdateValVisitor implements Visitor
{
	int update_val;
	public UpdateValVisitor(int update_value)
	{
		update_val = update_value;
	}

	public void visit(BST b)
	{
		//Node.data += update_val;
	}
	
}
