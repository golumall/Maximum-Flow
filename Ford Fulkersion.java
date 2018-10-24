import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
class Ford_Fulkersion
{
	int v;
	static int g[][];
	Ford_Fulkersion(int v)
	{
		this.v=v;
		g=new int[v][v];
	}
	boolean bfs(int tg[][],int s,int t,int p[])
	{
		boolean visited[]=new boolean[v];
		for(int i=0;i<v;i++)
		{
			visited[i]=false;
		}
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(s);
		visited[s]=true;
		p[s]=-1	;
		while(!q.isEmpty())
		{
			int u=q.poll();
			for(int i=0;i<v;i++)
			{
				if(!visited[i]&&tg[u][i]>0)
				{
					q.add(i);
					visited[i]=true;
                    p[i]=u;
				}
			}
		}
		return (visited[t]==true);
	}
	int fordFulkersion(int s,int t)
	{
		int tmp[][]=new int[v][v];
		for(int i=0;i<v;i++)
		{
			for(int j=0;j<v;j++)
			{
				tmp[i][j]=g[i][j];
			}
		}
		int u;
		int p[]=new int[v];
		int maxFlow=0;
		while(bfs(tmp,s,t,p))
		{
			int path=Integer.MAX_VALUE;
			for(int i=t;i!=s;i=p[i])
			{
				u=p[i];
				path=Math.min(path,tmp[u][i]);
			}
			for(int i=t;i!=s;i=p[i])
			{
				u=p[i];
				tmp[u][i]-=path;
				tmp[i][u]+=path;
			}
			maxFlow+=path;
		}
		return maxFlow;
	}

public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int v=sc.nextInt();
	Ford_Fulkersion graph=new Ford_Fulkersion(v);
	for(int i=0;i<v;i++)
	{
		for(int j=0;j<v;j++)
		{
			g[i][j]=sc.nextInt();
		}
	}
	int s=sc.nextInt();
	int t=sc.nextInt();
	System.out.println(graph.fordFulkersion(s,t));
}
}
