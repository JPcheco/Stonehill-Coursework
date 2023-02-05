
public class Exercise6 
{
	private int[][] combo; 
	private String[] strat;

	public Exercise6()
	{
		combo = new int[56][3];
		strat = new String[56];
		for(int i = 0; i<strat.length; i++)
		{
			strat[i] = "";
		}
	}

	public void fillStrat(int i)
	{
		if(i < 56)
		{
			int pos = getPosition(combo[i]);

			String str = findStrat(pos, combo[i]);
			if(isUsed(str))
			{
				str = flipStrat(pos, combo[i]);
			}
			strat[i] = str;
			fillStrat(i+1);
		}
	}

	public int getPosition(int[] c)
	{
		return (c[0] + c[1] + c[2]) % 3;
	}

	public String findStrat(int p, int[] c)
	{
		switch(p)
		{
		case 0:
			return c[1] + "-" + c[2];
		case 1:
			return c[0] + "-" + c[2];
		case 2:
			return c[0] + "-" + c[1];
		}
		return "-1";
	}

	public String flipStrat(int p, int[] c)
	{
		switch(p)
		{
		case 0:
			return c[2] + "-" + c[1];
		case 1:
			return c[2] + "-" + c[0];
		case 2:
			return c[1] + "-" + c[0];
		}
		return "-1";
	}

	public boolean isUsed(String c)
	{
		for(String item: strat)
		{
			if(c.equals(item))
				return true;
		}
		return false;
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
		for(String item: strat)
			System.out.print(item+" ");
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
					count++;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		Exercise6 e = new Exercise6();
		e.fillArray();
		e.fillStrat(0);
		e.printStrat();
	}

}
