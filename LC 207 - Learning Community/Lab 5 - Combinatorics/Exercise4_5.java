
public class Exercise4_5 
{
	private String[] order = {"cb", "ba", "ca", "ab", "bc", "ac"}; //{"ba", "ab", "ca", "ac", "bc", "cb"}
	private int[][] combo; 
	private int[] strat;
	private final int MAX = 1000000;
	private int count=0;

	public boolean findStrategy(int i)
	{
		boolean flag = false;
		count++;

		if(count == MAX)
			printStrat();
		if(i == 56)
			return true;
		else
		{
			for(int j = 0; j < 6; j++)
			{
				int oSet = findStrat(j, combo[i]);
				for(int s = 0; s < i; s++) 
				{
					if(oSet == strat[s])
						flag = true;
				}
				if(!flag)
				{
					strat[i] = oSet;
					findStrategy(i+1);
				}
				flag = false;
			}
		}
		return false;
	}

	public int findStrat(int i, int[] fixed)
	{
		switch(i)
		{
		case 0:
			return fixed[0]* 10 + fixed [1];
		case 1:
			return fixed[0]* 10 + fixed [2];
		case 2:
			return fixed[1]* 10 + fixed [2];
		case 3:
			return fixed[1]* 10 + fixed [0];
		case 4:
			return fixed[2]* 10 + fixed [0];
		case 5:
			return fixed[2]* 10 + fixed [1];
		}
		return -1;
	}

	public void printStrat()
	{
		for(int i=0; i < 56; i++)
		{
			for(int j=0; j<3; j++)
			{
				System.out.print(combo[i][j]);
			}
			System.out.print(" ");
		}
		System.out.println();
		for(int item: strat)
			System.out.print(item+"| ");
	}

	public void fillArray()
	{
		combo = new int[56][3];
		int count = 0;
		for(int i = 1; i <= 6; i++)
		{
			for(int j = i+1; j<= 7; j++)
			{
				for(int k = j+1; k <= 8; k++)
				{
					combo[count][0] = i;
					combo[count][1] = j;
					combo[count][2] = k;
					//System.out.println(combo[count][0] + "" + combo[count][1] + "" + combo[count][2]);
					count++;
				}
			}
		}
	}
	public Exercise4_5()
	{
		strat = new int[56];
	}

	public static void main(String[] args)
	{
		Exercise4_5 e = new Exercise4_5();
		e.fillArray();
		e.findStrategy(0);
	}
}
