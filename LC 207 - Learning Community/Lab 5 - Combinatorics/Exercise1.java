import java.util.*;

public class Exercise1 
{


	public int encode(int n, int m)
	{
		m = m-1;
		int[] a = new int[n];
		int index = 0, h = 0, ret = 0;
		boolean found = false;

		for(int i=0; i<n; i++)
		{
			a[i] = i+1;
		}

		for(int perm = 0; perm < m; perm++)
		{
			for(int q = n - 2; q >= 0 ; q--)
			{
				if(a[q+1] > a[q])
				{
					index = q;
					//System.out.println("found: " + a[q+1] + " > " + a[q]);
					found = true;
					break;
				}
			}

			if(found)
			{
				for(int i = n-1; i >= 0; i--)
				{
					if(a[i] > a[index])
					{
						h = i;

						int temp = a[h];
						a[h] = a[index];
						a[index] = temp;
						break;
					}
				}
				
				//flip last digits
				if(perm != 0)
				{
					int temp = 0;
					for(int in = index+1; in<n; in++)
						temp = temp*10 + a[in];
					//System.out.println(temp);
					for(int in = index+1; in<n; in++)
					{
						int mod = temp%10;
						temp = temp/10;

						a[in] = mod;
					}
				}
			}
			else
				break;
		}

		for(int i = 0; i < n; i++)
			ret = ret*10 + a[i];
		return ret;

	}

	public int getFactorial(int n)
	{
		int ans = 1;
		for(int i = n; i > 1; i--)
			ans = ans*i;
		return ans;
	}

	public int decode(int n, int p)
	{
		for(int i=1; i < getFactorial(n); i++)
		{
			int e = encode(n, i);
			if(e == p)
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Exercise1 e1 = new Exercise1();
		System.out.print("Enter number of digits: ");
		int n = input.nextInt();
		System.out.print("Enter number of permutations: (1 to 24) ");
		int m = input.nextInt();
		System.out.println(e1.encode(n, m));
		
		//for(int i=0; i <  24; i++)
		{
		//	System.out.println(e1.encode(n, i));
		}
		
		System.out.print("Enter a permutation to find its index: ");
		int p = input.nextInt();
		System.out.println(e1.decode(n, p));
		
		input.close();
	}
}
