package BOJ_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697
{
	public static int a;
	public static int b;
	
	public static int visited[] = new int[1000000];
	
	// X-1, X+1	// X*2
	public static void main(String[] args) throws IOException
	{
		BufferedReader builder = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(builder.readLine());
		
		a = Integer.valueOf(tokenizer.nextToken());
		b = Integer.valueOf(tokenizer.nextToken());
		
		int result = bfs(a);
		
		System.out.println(result);
	}

	private static int bfs(int num)
	{
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(num);
		int firstIndex = num;
		int n = 0;
		visited[firstIndex] = 1;
		while (!queue.isEmpty())
		{
			n = queue.remove();
			
			if (n == b)
			{
				return visited[n]-1;
			}
			
			if (n - 1 >= 0 && visited[n-1] == 0)
			{
				visited[n - 1] = visited[n] + 1;
				queue.add(n - 1);
			}
			if (n + 1 <= 100000 && visited[n + 1] == 0)
			{
				visited[n + 1] = visited[n] + 1;
				queue.add(n+1);
			}
			if (n * 2 <= 100000 && visited[n * 2] == 0)
			{
				visited[n * 2] = visited[n] + 1;
				queue.add(n * 2);
			}
		}
		return -1;
	}
}