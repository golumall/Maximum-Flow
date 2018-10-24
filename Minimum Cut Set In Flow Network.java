import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
class Minimum_Cut_ST
{
	int v;
	static int g[][];
	Minimum_Cut_ST(int v)
	{
	this.v=v;
	g=new int[v][v];
	}
	boolean bfs(int tmp[][],int s,int t,int p[])
	{
		boolean visited[]=new boolean[v];
		for(int i=0;i<v;i++)
		{
			visited[i]=false;
		}
		Queue<Integer> q=new LinkedList<>();
		q.add(s);
		visited[s]=true;
		p[s]=0;
		while(!q.isEmpty())
		{
			int u=q.poll();
			for(int i=0;i<v;i++)
			{
				if(!visited[i]&&tmp[u][i]>0)
				{
					visited[i]=true;
					p[i]=u;
					q.add(i);
				}
			}
		}
		return (visited[t]==true);
	}
	void dfs(int tmp[][],int u,boolean isVisited[])
	{
		isVisited[u]=true;
		for(int i=0;i<v;i++)
		{
			if(!isVisited[i]&&tmp[u][i]>0)
				dfs(tmp,i,isVisited);
		}

	}
	void cutSet(int s,int t)
	{
		int tmp[][]=new int[v][v];
		for(int i=0;i<v;i++)
		{
			for(int j=0;j<v;j++)
			{
				tmp[i][j]=g[i][j];
			}
		}
		int maxFlow=0;
		int p[]=new int[v];
		int u;
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

		boolean isVisited[]=new boolean[v];
		for(int i=0;i<v;i++)
		{
			isVisited[i]=false;
		}
		dfs(tmp,s,isVisited);
		for(int i=0;i<v;i++)
		{
			for(int j=0;j<v;j++)
			{
				if(g[i][j]>0&&isVisited[i]&&!isVisited[j])
					System.out.println(i+" "+j);
			}
		}

	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
	int v=sc.nextInt();
	Minimum_Cut_ST graph=new Minimum_Cut_ST(v);
	for(int i=0;i<v;i++)
	{
		for(int j=0;j<v;j++)
		{
			g[i][j]=sc.nextInt();
		}
	}
	int s=sc.nextInt();
	int t=sc.nextInt();
	graph.cutSet(s,t);
	}
}
