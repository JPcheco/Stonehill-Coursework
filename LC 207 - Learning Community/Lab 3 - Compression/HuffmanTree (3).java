import java.io.*;
import java.util.*;

public class HuffmanTree 
{
	private class Node 
	{ 
		int freq; 
		char data; 
		Node next; 
		Node left; 
		Node right; 

		public Node(char data, int freq)
		{
			this.data = data;
			this.freq = freq;
			next = right = left = null;
		}		
	} 
	
	int size; 
	String s1 = "";
	Node root, start, rear; 
	int[] frequencies;
	String[] codes; 

	public HuffmanTree(String filename) throws IOException 
	{ 
		File infile = new File(filename+".txt");
		Scanner input = new Scanner(infile);
		root = null;

		frequencies = new int[128];
		codes = new String[128];

		for(int i = 0; i<frequencies.length; i++)
			frequencies[i] = 0;

		s1 = "";

		while(input.hasNext())
		{
			s1 = input.nextLine();
			for(int i=0; i<s1.length(); i++)
			{
				char c1 = s1.charAt(i);
				frequencies[c1]++;
			}
		}
	} 

	public void zip(String infilename, String outfilename) throws IOException 
	{ 
		File outfile = new File(outfilename+".zip");
		PrintWriter pw = new PrintWriter(outfile);

		File infile = new File(infilename+".txt");
		Scanner input = new Scanner(infile);

		File feq = new File("output.feq");
		PrintWriter pr = new PrintWriter(feq);
		for(int i = 0; i<codes.length; i++)
			pr.println(codes[i] +" "+ codes[i]);

		while(input.hasNext())
		{
			String line = input.nextLine();
			char[] letters = line.toCharArray();

			for(int l=0; l<letters.length; l++)
				for(int c = 0; c<codes.length; c++)
					if(letters[l] == c)
						pw.print(codes[c]);
		}
		pw.close();  
		pr.close();
	}

	public void unzip(String infilename, String outfilename) throws IOException 
	{ 
		File of = new File(outfilename+".txt");
		PrintWriter printW = new PrintWriter(of);

		File in = new File(infilename+".zip");
		Scanner inp = new Scanner(in);

		while(inp.hasNext())
		{
			String line = inp.nextLine();
			char[] let = line.toCharArray();

			int l = 0;
			while(l<let.length)
			{
				Node n = root;
				while(n.left != null && n.right != null)
				{	
					if(let[l] == '0')
						n = n.left;
					else if(let[l] == '1')
						n = n.right;
					l++;
				}
				printW.print(n.data);
				//System.out.print(n.data);
			}
			printW.println();
		}
		printW.close();
	} 

	public void compress(String infilename) throws FileNotFoundException
	{
		File infile = new File(infilename+".zip");
		Scanner input = new Scanner(infile);

		File ofile = new File("compress.txt");
		PrintWriter pWrite = new PrintWriter(ofile);

		byte[] byteArray = new byte[1000000];
		for(int i=0; i<byteArray.length; i++)
			byteArray[i] = 0;
		int count = 0;
		
		while(input.hasNext())
		{
			byte temp = 0;
			String binString = input.nextLine()+" ";
			char[] binary = new char[binString.length()];
			binary = binString.toCharArray();

			for(int loc = 0; loc < binary.length; loc+=8)
			{
				byte b = 0;
				for(int bit = 0; bit < 8; bit++)
				{
					if(binary[bit+loc] == '0')
						b =(byte) 0;
					else if(binary[bit+loc] == '1')
						b = (byte)1;
					else
						break;
					temp = (byte)(temp * 2 + b);
				}
				pWrite.print(temp);
			}
		}
		System.out.println(count);

		pWrite.close();
		input.close();
	}

	private void insert(Node p) 
	{ 
		Node q = start;
		Node r = null; // r follows q 
		while (q != null && (q.freq <= p.freq)) 
			// stops when a q priority is larger or exhausts the list
		{
			r = q; // behind q
			q = q.next;
		}
		if (r == null) // insert at front
		{
			p.next = start;
			start = p;
		}
		else // insert Node r into its proper place
		{
			r.next = p;
			p.next = q;
		}
	} 

	public void makeCodewords(String s, Node r) 
	{ 
		if( r != null)
		{
			makeCodewords(s + '0', r.left);
			if(r.data !='ð')
			{
				System.out.println(r.data +" "+ s);
				codes[(int)(r.data)] = s;
			}
			makeCodewords(s + '1', r.right);
		}
	} 

	public void buildTree() 
	{ 
		int i;
		size = 1;

		for(i=0; i<frequencies.length; i++)
		{
			if(frequencies[i] > 0)
			{
				start = new Node((char) i, frequencies[i]);
				break;
			}
		}
		rear = start;

		for(int j = i+1; j<frequencies.length; j++)
		{
			if(frequencies[j] > 0)
			{
				Node n = new Node((char) j, frequencies[j]);
				insert(n);
				size++;
			}
		}
		Node p = start;

		while(p != null)
		{
			System.out.println(p.data+" "+p.freq);
			p = p.next;
		}
		System.out.println();

		while(size >=1)
		{
			Node low1 = start;
			start = start.next;
			size--;

			Node low2 = start;
			if(size > 2)
				start = start.next;

			Node r = new Node('ð', low1.freq + low2.freq);

			r.left = low1;
			r.right = low2;

			root = r;

			insert(root);
		}
		makeCodewords("", root);
	} 

	public static void main(String [] args) throws IOException 
	{ 
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Input file: ");
		String Infile = input.nextLine();

		System.out.print("Enter Output file: ");
		String Outfile = input.nextLine();

		System.out.print("Enter Decode file: ");  
		String Decode = input.nextLine();

		HuffmanTree t = new HuffmanTree(Infile);
		t.buildTree();      
		t.zip(Infile, Outfile);
		t.unzip(Outfile, Decode);
		t.compress(Outfile);
	}
}