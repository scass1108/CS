package Casserly_part1.util;
import java.io.*;
import java.util.*;
public class DTPair implements Comparable<DTPair>{

	int docID;
	int tf;
	
	public DTPair(){}

	public DTPair(int d, int t)
	{
		docID = d;
		tf = t;		
	}

	public void setDocID(int i)
	{
		docID = i;
	}
	public int getDocID()
	{
		return docID;
	}
	public void setTF(int t)
	{
		tf = t;
	}
	public int getTF()
	{
		return tf;
	}
	
	public int compareTo(DTPair pair) 
	{
  		if(this.docID >= 0 && pair.docID >= 0)
  		{
 			return this.docID - pair.docID;
		}
		return 0;
	}
}

