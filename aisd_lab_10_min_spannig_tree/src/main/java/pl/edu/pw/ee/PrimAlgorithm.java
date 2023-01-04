package pl.edu.pw.ee;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    public String findMST(String pathToFile){
        // TODO
        return null;
    }

    private List<Node> readGraph(String pathToFile) {
        try {
        FileInputStream in = new FileInputStream(pathToFile);
        Scanner scanner = new Scanner(in);
        
        List<Node> neighborhoodList = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            String firstNode = scanner.next();
            String secondNode = scanner.next();
            int cost = scanner.nextInt();
            
            if (neighborhoodList.contains())
        }
        } catch(IOException e) {
            
        }
    }
}
