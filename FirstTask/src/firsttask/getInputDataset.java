package firsttask;




import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author Zainab
 */
public class getInputDataset {
     

    public GraphBuilder getGraph(File file) throws IOException {
       String path=file.getAbsolutePath();
       //System.out.println( path);
      
      GraphBuilder graph1 = new GraphBuilder();
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        // there is a small difference between the two datasets so here we handeled both of them 
        
        if
                ("C:\\Users\\Zainab\\Documents\\NetBeansProjects\\FirstTask\\Choose Datasets\\facebook_combined(the smallest please ^_^).txt".equals(path))
        {
            try {
            while (it.hasNext()) {
                String line = it.nextLine();

                int num, u, v;
                if (line.toLowerCase().contains("nodes:")) {
                    int index = line.indexOf(":");
                    int next_index = line.indexOf(" ", index + 2);
                    String node_num = line.substring(index + 2, next_index);

                    line = line.toLowerCase().replaceAll("\\s+", "  ");

                    num = Integer.parseInt(node_num);
                   // System.out.println(node_num);
                    graph1 = new GraphBuilder(num);

                   // System.out.println(line);
                }
                if (line.contains("#")) {
                    continue;
                }
                    
                String nodes[] = line.split(" ");
               // System.out.println(line);
               
                  
              //   System.out.println(nodes[1]);
                u = Integer.parseInt(nodes[0]);
                v = Integer.parseInt(nodes[1]);
         
                graph1.addEdge(u, v);}
            

        } finally {
            LineIterator.closeQuietly(it);
        }}
        else if("C:\\Users\\Zainab\\Documents\\NetBeansProjects\\FirstTask\\Choose Datasets\\com-amazon.ungraph.txt".equals(path)){
        
        try {
            while (it.hasNext()) {
                String line = it.nextLine();

                int num, u, v;
                if (line.toLowerCase().contains("nodes:")) {
                    int index = line.indexOf(":");
                    int next_index = line.indexOf(" ", index + 2);
                    String node_num = line.substring(index + 2, next_index);
                    // this for making data from file standard shaped
                    line = line.toLowerCase().replaceAll("\\s+", "");

                    num = Integer.parseInt(node_num);
                   // System.out.println(node_num);
                    graph1 = new GraphBuilder(num);

                   // System.out.println(line);
                }
                if (line.contains("#")) {
                    continue;
                }
                    
                String nodes[] = line.split("	");
               
               
                  
                u = Integer.parseInt(nodes[0]);
                v = Integer.parseInt(nodes[1]);
               
                graph1.addEdge(u, v);}
            

        }finally {
            LineIterator.closeQuietly(it);
        }
        
        
        }
       
        return graph1;

    }}
    
   


