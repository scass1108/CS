package Casserly_part2.util;
import java.io.*;
import java.util.*;
import Casserly_part2.util.*;
public class Processor
{
	ArrayList<String[]> queries;
	ArrayList<DocTF> postings;
	ArrayList<Integer> docLengths;
	public Processor()
	{
		queries = new ArrayList<String[]>();
		postings = new ArrayList<DocTF>();	
		docLengths = new ArrayList<Integer>();
	}
	
	public void populatePostings(String filename)
	{
		FileReaderHelper frh = new FileReaderHelper(filename);
		
		String s = "";
		for(int i = 0; ((s = frh.read()) != null); i++)
		{
			int docID;
			int tf;
			
			String[] arr = s.split("\\s+");
			docID = (int) Integer.parseInt(arr[0]);
			tf = (int) Integer.parseInt(arr[1]);
			double tfw = 1 + Math.log10((double) tf);
			DocTF dt = new DocTF(docID, tfw);
			
			postings.add(dt);
			
			
		}
		
	}
	
	public void readQueries(String filename)
	{
		FileReaderHelper frh = new FileReaderHelper(filename);
		ArrayList<String> aL = new ArrayList<String>();
		String s = "";
		for(int i = 0; ((s = frh.read()) != null); i++)
		{
			aL.add(s);

			
		}
		String[] q0 = aL.get(0).split("\\s+");
		String[] q1 = aL.get(1).split("\\s+");
		String[] q2 = aL.get(2).split("\\s+");
		String[] q3 = aL.get(3).split("\\s+");
	
		String[] stemmedQ0 = stem(q0);
		String[] stemmedQ1 = stem(q1);
		String[] stemmedQ2 = stem(q2);
		String[] stemmedQ3 = stem(q3);

//		System.out.println(Arrays.toString(stemmedQ0));
//		System.out.println(Arrays.toString(stemmedQ1));
//		System.out.println(Arrays.toString(stemmedQ2));
//		System.out.println(Arrays.toString(stemmedQ3));

		queries.add(stemmedQ0);
		queries.add(stemmedQ1);
		queries.add(stemmedQ2);
		queries.add(stemmedQ3);		
	}
	
	public void populateDocLengths(String filename)
	{
		FileReaderHelper frh = new FileReaderHelper(filename);
		
		String s = "";
		for(int i = 0; ((s = frh.read()) != null); i++)
		{
			String[] temp = s.split("\\s+");
			docLengths.add(temp.length);
		}
	}
	
	public ArrayList<Integer> getDocLengths()
	{
		return docLengths;
	}
	
	public String[] stem(String[] arr)
	{
		String[] temp = new String[arr.length];
		for(int i = 0; i < arr.length; i++)
		{
			Stemmer stemmer = new Stemmer();
			char[] c = arr[i].toCharArray();
			stemmer.add(c, arr[i].length());
			stemmer.stem(); 
			String u;
			u = stemmer.toString();
			//System.out.println(u);
			temp[i] = u;
		}
		return temp;
	}	
	
	public ArrayList<DocTF> getPostings()	
	{
		return postings;
	}
	
	public void printPostings()
	{
		for(int i = 0; i < postings.size(); i++)
		{
			System.out.println(postings.get(i).getDocID() + " " + postings.get(i).getTfw());	
		}
	}
	
	public ArrayList<String[]> getQueries()
	{
		return queries;
	}
	
	public ArrayList<ResultPair> doCalculations(HashStruct h, String[] query)
	{
		double N = 200;
		Hashtable <String, DictPair> dictionary = h.getDictionary();
		
		ArrayList<ResultPair> results = new ArrayList<ResultPair>(200);
		for(int i = 0; i < 200; i++)
		{
			results.add(null);
		}

		for(int j = 0; j < query.length; j++)
		{
			String s = query[j];
			
			DictPair d = dictionary.get(s);
			for(int i = d.getPosting(); i < d.getPosting() + d.getTf(); i++)
			{
				int docID = postings.get(i).getDocID();
				double tfw = postings.get(i).getTfw();
				
				double qval = (1 + Math.log10(tfw)) * (Math.log10(N/d.getTf()));
				double dval = (1 + Math.log10(tfw))/ (double)docLengths.get(docID);
				double result = qval * dval;
				ResultPair rp = new ResultPair(docID, result);
				
				if(results.get(docID) != null)
				{
					ResultPair temp = results.get(docID);
					ResultPair sum = new ResultPair(docID, result + temp.getSimilarity());
					results.set(docID, sum);
				}
				else
				{
					results.set(docID, rp);
				}
			}
		}
		results.removeAll(Collections.singleton(null));
		return results;
	}
	
	public void getTopRankings(HashStruct h)
	{
		ArrayList<ResultPair> q1Results = doCalculations(h, queries.get(0));
		ArrayList<ResultPair> q2Results = doCalculations(h, queries.get(1));	
		ArrayList<ResultPair> q3Results = doCalculations(h, queries.get(2));
		ArrayList<ResultPair> q4Results = doCalculations(h, queries.get(3));

		Collections.sort(q1Results);
		Collections.sort(q2Results);
		Collections.sort(q3Results);
		Collections.sort(q4Results);
		
		writeResults(q1Results, "query1result.txt");
		writeResults(q2Results, "query2result.txt");		
		writeResults(q3Results, "query3result.txt");		
		writeResults(q4Results, "query4result.txt");		
			
	}
	
	public void writeResults(ArrayList<ResultPair> r, String filename)
	{
		FileWriterHelper fr = new FileWriterHelper(filename);
		if(r.size() >= 10)
		{
			for(int i = 0; i < 10; i++)
			{	
			
				fr.write(r.get(i).toString());
			
			}
		}
		
		else
		{
			for(int j = 0; j < r.size(); j++)
			{
				fr.write(r.get(j).toString());
			}
		}	
	
	}

	
}
