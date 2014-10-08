package studentBackup.util;
import studentBackup.util.FileReaderHelper;
import studentBackup.bst.BST;
import studentBackup.bst.Node;
public class BSTBuilder
{
	int n;
	
	BST tree = new BST();
	BST backup1 = new BST();
	BST backup2 = new BST();
	
	public void buildTrees(String infile)
	{
		FileReaderHelper frh = new FileReaderHelper(infile);
		String s = "";
	
		while((s = frh.read()) != null)
    		{
    			System.out.println("adding " + s);
			n = Integer.parseInt(s);
			tree.insert(n);
			backup1.insert(n);
			backup2.insert(n);
			System.out.println(s + " added");			
		}
		System.out.println("done making trees");
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
}
