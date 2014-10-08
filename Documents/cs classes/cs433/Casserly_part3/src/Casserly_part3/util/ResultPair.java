package Casserly_part3.util;
import java.io.*;
import java.util.*;
public class ResultPair implements Comparable<ResultPair>{
//term frequency and posting list location 
	int docID;
	double similarity;
	
	public ResultPair(){}

	public ResultPair(int d, double s)
	{
		docID = d;
		similarity = s;

	}

	public void setSimilarity(double i)
	{
		similarity = i;
	}
	public double getSimilarity()
	{
		return similarity;
	}
	public void setDocID(int s)
	{
		docID = s;
	}
	public int getDocID()
	{
		return docID;
	}

	public String toString()
	{
		String s = docID + " " + similarity;
		return s;
	}
	public int compareTo(ResultPair pair) 
	{
  		if(this.similarity >= 0 && pair.similarity >= 0)
  		{
 			if(this.similarity > pair.similarity)
 			{ 
 				return -1;
 			}
 			else
 			{
 				return 1;
 			}
		}
		return 0;
	}
}
