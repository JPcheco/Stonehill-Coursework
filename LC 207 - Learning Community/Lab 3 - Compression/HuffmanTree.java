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

   private class Code
   {
      char data;
      String code;
      
      public Code(char d, String c)
      {
         data = d;
         code = c;
      }
   }
   
	int size; 
   String s1 = "";
	Node root, start, rear; 
	int[] frequencies; 
   ArrayList<Code> codes = new ArrayList<Code>(); 

	public HuffmanTree(String filename) throws IOException 
	{ 
		File infile = new File(filename);
		Scanner input = new Scanner(infile);
		root = null;
		
		frequencies = new int[128];
		
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
     PrintWriter pw = null;      
     File outfile = new File(outfilename);
     pw = new PrintWriter(outfile);
     
     String infile = infilename;
     
     
     
     
        
     pw.close();      
	} 

	public void unzip(String infilename, String outfilename) throws IOException 
	{ 
      
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
         makeCodewords(s + "0", r.left);
         if(r.data !=';')
         {
            System.out.println(r.data +" "+ s);
            codes.add(new Code(r.data, s));
         }
         makeCodewords(s + "1", r.right);
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
			
			Node r = new Node(';', low1.freq + low2.freq);
			
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
      System.out.print("Enter Inputfile: ");      
      String Infile = input.nextLine();
      String Outfile = "Outfile.txt";

      
      HuffmanTree t = new HuffmanTree(Infile);
      t.buildTree();      
      t.zip(Infile, Outfile);

	}
}