package Casserly_part1.util;
import java.io.*;
import java.util.*;
public class Triple{

	String term;
	int df;
	ArrayList<Integer> postingList = new ArrayList<Integer>();
	public Triple(){}
	
	public Triple(String a, int b, ArrayList<Integer> list)
	{
		term = a;
		df = b;
		postingList = list;
	}
	
	public void setTerm(String s)
	{
		term = s;
	}
	public void setDf(int i)
	{
		df = i;
	}
	public void setPostingList(ArrayList<Integer> list)
	{
		postingList = list;
	}
	public String getTerm()
	{
		return term;
	}
	public int getDf()
	{
		return df;
	}
	public ArrayList<Integer> getPostingList()
	{
		return postingList;
	}
	
	public String printTriple()
	{
		String s = "";
		s = term + " " + df + " " + printPostingList();
		return s;
	}	
	public String printPostingList()
	{
		String s = "";
		for(int i = 0; i < postingList.size(); i++)
		{
			s = s + postingList.get(i).toString() + ",";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}

}
