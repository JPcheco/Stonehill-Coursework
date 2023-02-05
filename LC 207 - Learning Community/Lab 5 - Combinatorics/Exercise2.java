import java.util.Arrays;
import java.util.Random;

public class Exercise2 
{
	private int[] deck, perm;
	private int numPerm;
	Exercise1 e1;
	
	public int[] getDeck()
	{
		Random gen = new Random();

		int[] deck = new int[5];

		for(int i = 0; i < 5; i++)
		{
			int rand = gen.nextInt(28)+1;
			for(int j=0; j< i; j++)
			{
				while(deck[j] == rand)
				{
					//System.out.println(rand);
					rand = gen.nextInt(28)+1;
					j = 0;
				}
			}
			deck[i] = rand;
		}
		Arrays.sort(deck);
		return deck;
	}
	
	public int[] permutate()
	{
		int[] perm = new int[deck.length-1];
		numPerm = deck[0];
		
		System.out.println(numPerm);
		String per = ((Integer)e1.encode(4, numPerm-1)).toString();
		System.out.println(per);
		
		int[] temp = new int[deck.length-1];
		for(int i = 1; i < deck.length; i++)
			temp[i-1] = deck[i];
		
		for(int i = 0; i<temp.length; i++)
			perm[i] = temp[Character.getNumericValue(per.charAt(i))-1];
		
		return perm;
	}
	
	public int decode(int[] nums)
	{
		
		int[] inOrder = new int[nums.length];
		int[] per = new int[nums.length];
		
		for(int i=0; i< nums.length; i++)
			inOrder[i] = nums[i];
		
		Arrays.sort(inOrder);
		
		for(int i=0; i<nums.length; i++)
		{
			for(int j = 0; j<nums.length; j++)
			{
				if(inOrder[i] == nums[j])
				{
					per[j] = i+1;
				}
			}
		}
		int ret = 0;
		
		for(int item : per)
			ret = ret*10 + item;
		
		System.out.println(ret);
		return e1.decode(4, ret)+1;
	}
	
	public Exercise2()
	{
		e1 = new Exercise1();
		deck = getDeck();
		for(int item : deck)
			System.out.print(item+"|");
		System.out.println();
		perm = permutate();
		for(int item : perm)
			System.out.print(item+"|");
		System.out.println();
		System.out.println(decode(perm));
	}
	
	public static void main(String[] args)
	{
		Exercise2 e2 = new Exercise2();
	}
}
