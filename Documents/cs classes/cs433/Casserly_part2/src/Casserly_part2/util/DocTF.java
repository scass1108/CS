package Casserly_part2.util;
import java.io.*;
import java.util.*;
public class DocTF{
//docID and term frequency container
	int docID;
	double tfw;
	
	public DocTF(){}

	public DocTF(int d, double t)
	{
		docID = d;
		tfw = t;

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
