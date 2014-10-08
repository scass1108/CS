package Casserly_part3.util;
import java.io.*;
import java.util.*;
public class DocTF{
//docID and term frequency container
	int docID;
	int tf;
	double tfw;
	
	public DocTF(){}

	public DocTF(int d, int t)
	{
		docID = d;
		tf = t;
		tfw = Math.log10(t);
	}
	public void setTf(int i)
	{
		tf = i;
	}
	public double getTf()
	{
		return tf;
	}
	public void setTfw(double i)
	{
		tfw = i;
	}
	public double getTfw()
	{
		return tfw;
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
		String s = docID + " " + tfw;
		return s;
	}
}
