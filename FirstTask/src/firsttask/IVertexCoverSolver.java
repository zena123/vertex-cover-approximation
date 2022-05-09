package firsttask;

import java.util.ArrayList;
/**
 * Generic interface for Minimum Vertex Cover solvers
 */
public interface IVertexCoverSolver {
	ArrayList<Integer> solve(GraphBuilder graph);
       // ArrayList<Integer> solve(Graph graph);
}
