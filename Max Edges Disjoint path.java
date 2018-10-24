import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
class Disjoint_Edges_FordFulKersion
{
	int v;
	static int g[][];
	Disjoint_Edges_FordFulKersion(int v)
	{
	this.v=v;
	g=new int[v][v];
	}
	boolean bfs(int tmp[][],int s,int t,int p[])
	{
		boolean visited[]=new boolean[v];
		for(int i=0;i<v;i++)
			visited[i]=false;
		Queue<Integer> q=new LinkedList<Integer>();
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
         int p[]=new int[v];
         int maxEdge=0;
         int tmp[][]=new int[v][v];
         for(int i=0;i<v;i++)
         {
         	for(int j=0;j<v;j++)
         	{
         		tmp[i][j]=g[i][j];
         	}
         }
           int u=0;
         while(bfs(tmp,s,t,p))
         {
             int path=Integer.MAX_VALUE;

             for(int i=t;i!=s;i=p[i])
             {
                 u=p[i];
                 path=Math.min(path,g[u][i]);
             }
             for(int i=t;i!=s;i=p[i])
             {
             	u=p[u];
             	tmp[u][i]-=path;
             	tmp[i][u]+=path;
             }
              maxEdge+=path;
         }
         return maxEdge;
	}
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	int v=sc.nextInt();
	Disjoint_Edges_FordFulKersion graph=new Disjoint_Edges_FordFulKersion(v);
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
