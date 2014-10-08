package Casserly_part3.util;
import java.io.*;
import java.util.*;
import Casserly_part3.util.*;
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
	
	public void constructDictionaryAndPostings(String filename)
	{
		FileReaderHelper frh = new FileReaderHelper(filename);
		String s = "";
		FileWriterHelper fwh = new FileWriterHelper("dictionary.txt");
		FileWriterHelper fwh2 = new FileWriterHelper("postings.txt");
		
		List<TDPair> arr = new ArrayList<TDPair>();
		for(int i = 0; ((s = frh.read()) != null); i++)
	    	{
	    		ArrayList<TDPair> temp = new ArrayList<TDPair>();
	    		//System.out.println(i + " " + s);
	    		//fwh.write(s);
	    		temp = process(s,i);
	    		for(int k = 0; k < temp.size(); k++)
	    		{
	    			TDPair pairTemp = new TDPair();
	    			pairTemp = temp.get(k);
	    			arr.add(pairTemp);
	    		}
	    	}
	    	Collections.sort(arr);
	    	ArrayList<Triple> list1 = new ArrayList<Triple>();
	    	
	    	ArrayList<Triple> dict = new ArrayList<Triple>();
	    	for(int a = 0; a < arr.size(); a++)
	    	{	
	    		String termA = arr.get(a).getTerm();
	    		int docIDA = arr.get(a).getDocID();
	  		int contains = 0;
	  		
	    		for(int c = 0; c < dict.size(); c++)
	    		{
	    			String dictTerm = dict.get(c).getTerm();
	    			if(dictTerm.equals(termA))
	    			{
	    				
	    				contains = c;
	    			}
	    		}	

	    		if(contains == 0)
	    		{
	    			ArrayList<Integer> l = new ArrayList<Integer>();
	    			l.add(docIDA);
	    			dict.add(new Triple(termA, 1, l));
	    		}
	    		else
	    		{
	    			ArrayList<Integer> tempPosting = dict.get(contains).getPostingList();
	    			String tempTerm = dict.get(contains).getTerm();
	    			int tempDF = dict.get(contains).getDf();
	    			tempDF++;
	    			tempPosting.add(docIDA);
	    			dict.set(contains,new Triple(tempTerm, tempDF, tempPosting));

	    		}
	    		
	    	}
		int offsetAccumulator = 0;			
	    	for(int z = 0; z < dict.size(); z++)
	    	{
	    		fwh.write(dict.get(z).getTerm() + " " + dict.get(z).getDf() + " " + offsetAccumulator);
	    		offsetAccumulator += dict.get(z).getDf();
	    		
	    	}
	    	
	    	for(int z1 = 0; z1 < dict.size(); z1++)
	    	{
	    		ArrayList<Integer> tp = dict.get(z1).getPostingList();
	    		for(int z2 = 0; z2 < tp.size(); z2++)
	    		{
	    			fwh2.write(tp.get(z2) + " " + "1");
	    		}
	    	}
	    	
	    	fwh.close();
	    	fwh2.close();
	}
	
	public ArrayList<TDPair> process(String s, int n)
	{

		String[] arr = s.split("\\s+");
		ArrayList<TDPair> list = new ArrayList<TDPair>();
		
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = arr[i].replaceAll("[^a-zA-Z0-9]","").toLowerCase();
		
			Stemmer stemmer = new Stemmer();
			char[] c = arr[i].toCharArray();
			stemmer.add(c, arr[i].length());
			stemmer.stem(); 
			String u;
			u = stemmer.toString();
			//System.out.println(u);
			TDPair p = new TDPair(u,n);
			list.add(p);
		}
			
		return list;
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
			DocTF dt = new DocTF(docID, tf);
			
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
	
	public double getAvgDocLength()
	{
		double sum = 0;
		for(int i = 0; i < docLengths.size(); i++)
		{
			sum += (double)docLengths.get(i);
		}
		
		return (sum / (double)docLengths.size());
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
	
	public ArrayList<ResultPair> doCosine(HashStruct h, String[] query)
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
	
	public ArrayList<ResultPair> doOkapi(HashStruct h, String[] query)
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
				
				
				double wi = Math.log10((N * tfw + .5));
				double dti = ((1.2 + 1) * tfw) / ((1.0 - .75) + .75 * ((double) docLengths.get(docID) / getAvgDocLength()) + tfw);
				double qti = ((1.2 + 1)*1) / (1.2 + 1); 
				double result = wi * dti * qti;
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
		ArrayList<ResultPair> q1CosResults = doCosine(h, queries.get(0));
		ArrayList<ResultPair> q2CosResults = doCosine(h, queries.get(1));	
		ArrayList<ResultPair> q3CosResults = doCosine(h, queries.get(2));
		ArrayList<ResultPair> q4CosResults = doCosine(h, queries.get(3));

		ArrayList<ResultPair> q1OkapiResults = doOkapi(h, queries.get(0));
		ArrayList<ResultPair> q2OkapiResults = doOkapi(h, queries.get(1));	
		ArrayList<ResultPair> q3OkapiResults = doOkapi(h, queries.get(2));
		ArrayList<ResultPair> q4OkapiResults = doOkapi(h, queries.get(3));

		Collections.sort(q1CosResults);
		Collections.sort(q2CosResults);
		Collections.sort(q3CosResults);
		Collections.sort(q4CosResults);
		
		Collections.sort(q1OkapiResults);
		Collections.sort(q2OkapiResults);
		Collections.sort(q3OkapiResults);
		Collections.sort(q4OkapiResults);
		
		writeResults(q1CosResults, "cosquery1result.txt");
		writeResults(q2CosResults, "cosquery2result.txt");		
		writeResults(q3CosResults, "cosquery3result.txt");		
		writeResults(q4CosResults, "cosquery4result.txt");	
		
		writeResults(q1OkapiResults, "okaquery1result.txt");
		writeResults(q2OkapiResults, "okaquery2result.txt");		
		writeResults(q3OkapiResults, "okaquery3result.txt");		
		writeResults(q4OkapiResults, "okaquery4result.txt");		
			
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
