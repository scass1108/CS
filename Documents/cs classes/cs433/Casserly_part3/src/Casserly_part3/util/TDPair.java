package Casserly_part3.util;
import java.io.*;
import java.util.*;
public class TDPair implements Comparable<TDPair>{

	int docID;
	String term;
	
	public TDPair(){}

	public TDPair(String t, int d)
	{
		term = t;
		docID = d;
	}

	public void setDocID(int i)
	{
		docID = i;
	}
	public int getDocID()
	{
		return docID;
	}
	public void setTerm(String s)
	{
		term = s;
	}
	public String getTerm()
	{
		return term;
	}
	public String toString()
	{
		return term + " " + docID;
	}
	public int compareTo(TDPair pair) 
	{
  		if(this.term != null && pair.term != null)
  		{
 			return this.term.compareToIgnoreCase(pair.term);
		}
		return 0;
	}
}

