import java.util.*;

public class Exercise7
{
	int[] possibilities;
	int removed;
	int count = 0;
	
	public Exercise7(int[] nums)
	{
		possibilities = new int[100];
		//int[] nums = {23, 27, 59, 87, 93};
		//System.out.println(getMod(nums));
		//Arrays.sort(nums);
		
		int[] four = get4Cards(nums);
		//for(int i: four)
		//	System.out.print(i+"|");
		testNum(four);
		//for(int i: possibilities)
		//	System.out.println(i);
		
		four = rotate(four);
		for(int i: four)
			System.out.print(i+"|");
	}

	public int getMod(int[] nums)
	{
		Arrays.sort(nums);

		int sum = 0;
		for(int i=0; i<nums.length; i++)
			sum = sum + nums[i];
		return sum % 5;
	}

	public int[] get4Cards(int[] nums)
	{
		int remove = getMod(nums);
		removed = nums[remove];
		int[] ret = new int[4];

		int j = 0;
		for(int i=0; i<5; i++)
		{
			if(nums[i] != nums[remove])
			{
				ret[j] = nums[i];
				j++;
			}
		}
		return ret;
	}
	public boolean contains(int[] nums4, int n)
	{
		for(int i: nums4)
			if(i == n)
				return true;
		
		return false;
	}
	
	public void testNum(int[] nums4)
	{
		int[] test = new int[5];

		for(int num=1; num<= 124; num++)
		{
			if(!contains(nums4, num))
			{
				for(int i = 0; i < 4; i++)
					test[i] = nums4[i];
				
				test[4] = num;
				Arrays.sort(test);
				
				int mod = getMod(test);
				
				if(test[mod] == num)
				{
					possibilities[count] = num;
					count++;
					//System.out.println(num);
				}
			}
		}
	}
	
	public int[] rotate(int[] nums4)
	{
		Exercise1 e = new Exercise1();
		int index = 0;
		for(int i=0; i<possibilities.length; i++)
		{
			if(possibilities[i] == removed)
				index = i + 1;
		}
		
		int permutate = e.encode(4, index);
		//System.out.println(permutate);
		int[] perms = new int[4];
		
		for(int p = 3; p>=0; p--)
		{
			int temp1 = permutate % 10;
			perms[p] = temp1;
			permutate = (int)permutate/10;
		}
		
		int[] perm = new int[4];
		for(int i=0; i<perm.length; i++)
			perm[i] = nums4[perms[i]-1];
		
		return perm;
	}
	
	public int findMissing(int[] nums4)
	{
		Exercise2 e = new Exercise2();
		for(int i = 0; i<possibilities.length; i++)
			possibilities[i] = 0;
		
		count = 0;
		testNum(nums4);
		
		int perm = e.decode(nums4);
		
		return possibilities[perm-1];
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 5 integers");
		int[] five = new int[5];
		for(int i=0; i<5; i++)
			five[i] = input.nextInt();
		
		Exercise7 e = new Exercise7(five);
		System.out.println();
		System.out.println("Enter 4 integers");
		int[] four = new int[4];
		for(int i=0; i<4; i++)
			four[i] = input.nextInt();
		
		System.out.println(e.findMissing(four));
		
		input.close();
	}
}
