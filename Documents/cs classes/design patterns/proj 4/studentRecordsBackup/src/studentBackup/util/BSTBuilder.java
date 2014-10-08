package studentBackup.util;
import studentBackup.util.FileReaderHelper;
import studentBackup.bst.BST;
import studentBackup.bst.Node;
import studentBackup.util.SumVisitor;
import studentBackup.util.UpdateValVisitor;
public class BSTBuilder implements Observer
{
	int n;
	int sum = 0;
	BST tree = new BST();
	BST backup1 = new BST();
	BST backup2 = new BST();
	
	tree.registerObserver(backup1);
	tree.registerObserver(backup2);
	
	public BST update()
	{
		backup1 = updatedTree;
		backup2 = updatedTree;
	}
	
	public void buildTrees(String infile)
	{
		FileReaderHelper frh = new FileReaderHelper(infile);
		String s = "";
	
		while((s = frh.read()) != null)
    		{
			n = Integer.parseInt(s);
			sum += n;
			tree.insert(n);
			backup1.insert(n);
			backup2.insert(n);			
		}
	}
	public void printTrees()
	{
		System.out.println("printing orig tree");
		tree.printInOrder();
		System.out.println("printing backup1");
		backup1.printInOrder();
		System.out.println("printing backup2");
		backup2.printInOrder();
	}
	public void sumTrees()
	{
		Visitor sumVisitor = new SumVisitor();
		sumVisitor.visit(tree);
	}
	public void updateTrees(String updateValue)
	{
		
		Visitor updateVisitor = new UpdateValVisitor(Integer.parseInt(updateValue));
		updateVisitor.visit(tree);
	
		
	}
}
