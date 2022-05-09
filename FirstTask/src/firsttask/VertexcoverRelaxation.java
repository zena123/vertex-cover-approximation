package firsttask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.problems.LinearProgram;

/**
 *
 * @author ZAinab
 */
public class VertexcoverRelaxation implements IVertexCoverSolver {

    @Override
    public ArrayList<Integer> solve(GraphBuilder graph) {
        Map<Integer, Double> solutionRelaxation = computeRelaxation(graph);
        ArrayList<Integer> cover = new ArrayList<>();
        for (int i = 0; i < graph.getSize(); i++) {
            double sol = solutionRelaxation.get(i);
            /*Vertex Cover via LP: 
            *Solve LP to obtain an optimal fractional solution x
            ∗ Let S ={v | x_v ≥ 0.5}
            Output S
             */
            if (sol >= 0.5) {
                cover.add(i);
            }
        }
        return cover;
    }

    private Map<Integer, Double> computeRelaxation(GraphBuilder graph) {
        double[] lpRelaxation = new double[graph.getSize()];

        Map<Integer, Integer> variableVertexMap = new HashMap<>();
        int i = 0;
        for (int v = 0; v < graph.getSize(); v++) {
            lpRelaxation[i] = 1;
            variableVertexMap.put(i, v);
            i++;
        }
        System.out.println("Step1 done!");

        LinearProgram lp = new LinearProgram(lpRelaxation);
        lp.setMinProblem(true);

        i = 0;

        for (int u = 0; u < graph.getSize(); u++) {
            for (GraphBuilder.Edge e : graph.adjListGraph[u]) {
                int v1 = MapHelper.getKey(variableVertexMap, u);
                int v2 = MapHelper.getKey(variableVertexMap, e.dest);
                System.out.println("( " + v1 + ", " + v2 + " )");
                //array of zeros but indices u and v are 1.0
                double[] constraint = new double[graph.getSize()];
                constraint[v1] = 1.0;
                constraint[v2] = 1.0;
                String cIdentifier = "c" + i;
                System.out.println(cIdentifier);
                //x_u + x_v ≥ 1 ∀e = (u,v)∈ E 
                lp.addConstraint(new LinearBiggerThanEqualsConstraint(constraint, 1.0, cIdentifier));
                i++;
            }
        }
       
        variableVertexMap.keySet().forEach((j) -> {

            double[] constraint = new double[graph.getSize()];

            constraint[j] = 1.0;

            String cIdentifier = "c" + (graph.getEdgeNum() + j);
            //we relax the constraint x_v ∈{0,1} to x_v ∈[0,1].It can be further simpliﬁed to x_v ≥0
            lp.addConstraint(new LinearBiggerThanEqualsConstraint(constraint, 0, cIdentifier));
            
        });

    
        LinearProgramSolver solver = SolverSingleton.getSolver();
        System.out.println("Solving!");
        double[] solution = solver.solve(lp);
        Map<Integer, Double> solutionMap = new HashMap<>();
        for (int j = 0; j < solution.length; j++) {
            solutionMap.put(variableVertexMap.get(j), solution[j]);
        }

       
        return solutionMap;
    }

}
