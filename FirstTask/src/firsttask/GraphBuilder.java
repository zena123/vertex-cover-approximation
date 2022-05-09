package firsttask;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Zainab
 */
public class GraphBuilder {

    class Edge {

        int dest;

        Edge(int dest) {

            this.dest = dest;
        }

        @Override
        public String toString() {
            return "(" + dest + ")";
        }
    }
    private int v;//Num of vertices
    private int edgeNum;
    LinkedList<Edge>[] adjListGraph;

    public GraphBuilder(int v) {
        this.v = v;
        adjListGraph = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjListGraph[i] = new LinkedList<>();
        }

    }

    public void setEdgeNum(int num) {
        this.edgeNum = num;
    }

    public int getEdgeNum() {
        return this.edgeNum;
    }

    public int getSize() {
        return v;
    }

    public boolean cheackEdge(int u, int v) {
        for (Edge e : adjListGraph[u]) {
            if (e.dest == v) {
                return true;
            }
        }
        return false;
    }

    public GraphBuilder(LinkedList<Edge>[] g) {
        adjListGraph = new LinkedList[g.length];
        for (int i = 0; i < g.length; i++) {
            adjListGraph[i] = new LinkedList<>();
        }
        for (int i = 0; i < g.length; i++) {
            for (Edge e : g[i]) {
                adjListGraph[i].add(new Edge(e.dest));
            }
        }
    }

    public GraphBuilder() {
    }

    public void addEdge(int u, int v) {

        Edge e2 = new Edge(v);
        if (!adjListGraph[u].contains(e2)) {
            adjListGraph[u].add(e2);
        }
        Edge e1 = new Edge(u);
        if (!adjListGraph[v].contains(e1)) {
            adjListGraph[v].add(e1);
        }
        edgeNum++;
    }

    public GraphBuilder grGraph() {
        GraphBuilder newGraph = new GraphBuilder(this.v);
        for (int i = 0; i < this.v; i++) {
            for (Edge e : adjListGraph[i]) {
                newGraph.adjListGraph[i].add(new Edge((e.dest)));
            }
        }
        return newGraph;
    }

    public void deleteEdge(int u, int v) {
        int index = 0;
        boolean flag = false;
        for (Edge node : adjListGraph[u]) {
            if (node.dest == v) {
                adjListGraph[u].remove(index);
                flag = true;
                break;
            }
            index++;
        }
        if (flag) {
            index = 0;
            for (Edge node : adjListGraph[v]) {
                if (node.dest == u) {
                    adjListGraph[v].remove(index);
                    break;
                }
                index++;
            }
        }
    }

    public void removeIncidentEdges(int v) {

        adjListGraph[v].clear();

    }

    @Override
    public String toString() {
        String result = "{ ";
        for (int i = 0; i < this.v; i++) {
            for (Edge e : adjListGraph[i]) {
                result += "( " + i + ", " + e.dest + " ), ";
            }
        }
        result += " }";
        return result;
    }

   

}
