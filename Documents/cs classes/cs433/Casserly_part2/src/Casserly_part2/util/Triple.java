package Casserly_part2.util;
import java.io.*;
import java.util.*;
public class Triple{

	String term;
	int df;
	int postingLocation;

	public Triple(){}
	
	public Triple(String a, int b, int c)
	{
		term = a;
		df = b;
		postingLocation = c;
	}
	
	public void setTerm(String s)
	{
		term = s;
	}
	public void setDf(int i)
	{
		df = i;
	}
	public void setPostingLoc(int location)
	{
		postingLocation = location;
	}
	public String getTerm()
	{
		return term;
	}
	public int getDf()
	{
		return df;
	}
	public int getPostingLocation()
	{
		return postingLocation;
	}
	
	public String printTriple()
	{
		String s = "";
		s = term + " " + df + " " + postingLocation;
		return s;
	}	

}
