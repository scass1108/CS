package Casserly_part2.util;
import java.io.*;
import java.util.*;
public class DictPair{
//term frequency and posting list location 
	int tf;
	int posting;
	
	public DictPair(){}

	public DictPair(int t, int p)
	{
		tf = t;
		posting = p;
	}

	public void setTf(int i)
	{
		tf = i;
	}
	public int getTf()
	{
		return tf;
	}
	public void setPosting(int s)
	{
		posting = s;
	}
	public int getPosting()
	{
		return posting;
	}

	public String toString()
	{
		String s = tf + " " + posting;
		return s;
	}
}
