package firsttask;

import firsttask.GraphBuilder.Edge;


/**
 *
 * @author Zainab
 */
public class VertexcoverApproximation {

    private int edge;

    public String compute(GraphBuilder g) {
        edge = 0;
        boolean visited[] = new boolean[g.adjListGraph.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        String rename = "";
        for (int i = 0; i < g.adjListGraph.length; i++) {
            if (!visited[i]) {
                for (Edge e : g.adjListGraph[i]) {
                    if (!visited[e.dest]) {
                        visited[i] = true;
                        visited[e.dest] = true;
                        g.removeIncidentEdges(i);
                        g.removeIncidentEdges(e.dest);
                        edge++;
                        rename += "( " + i + ", " + e.dest + " )\n";
                        break;
                    }
                }
            }
        }
        return rename;
    }

     public int getNumofSelectedEdges() {
        return edge;
    }


}
