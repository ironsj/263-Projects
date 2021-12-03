import java.util.*;


public class DominatingSet {

    private static int n;
    private static int adjacencyMatrix[][];

    /* Return true if the SetOfLabels x is
     * a subset that is a dominating set.
     * Your code goes here.
     */
    private static boolean isDominatingSet(SetOfLabels x) {
        /*int[] coveredNodes = new int[n];
        for(int i = 0; i < n; i++){
            if(x.contains(NodeLabel.values()[i])) {
                for(int j = 0; j < n; j++){
                    if(adjacencyMatrix[i][j] == 1){
                        coveredNodes[j] = 1;
                    }
                }
            }
        }

        for (int element : coveredNodes) {
            if (element != 1) {
                return false;
            }
        }
        return true;*/
        int[] coveredSet = new int[n];
        int[] result = new int[n];
        for(int i = 0;i < n;i++) {
            if (x.contains(NodeLabel.values()[i])) {
                coveredSet[i] = 1;
            }
            else {
                coveredSet[i] = 0;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j ++){
                result[i] += adjacencyMatrix[i][j] * coveredSet[j];
            }
        }

        for(int i = 0; i < n; i++){
            if(result[i] == 0){

                return false;
            }
        }


        return true;

    }

    public static void main(String args[]) {
        /* Read the size of the graph - number of vertices
        and then read the adjacency matrix for the graph
         */
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        adjacencyMatrix = new int [n][n];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n;j++) {
                adjacencyMatrix[i][j] = scanner.nextInt();
            }
        }

        // Calculate the size of the power set: 2^n
        int twoToN = 1;
        for(int i = 0;i < n;i++) {
            twoToN = twoToN * 2;
        }
        //System.out.println("2^n is: "+twoToN);

        SetOfLabels smallestDominatingSet = new SetOfLabels(twoToN-1,n);
        int sizeOfSmallestDominatingSet = n;
        // Iterate over all the elements in the PowerSet
        // Those elements are encoded by the integers 1..2^n -1
        // For each posssible subset, check if it is a dominating set
        // Report the smallest dominating set
        for(int i = 1;i < twoToN;i++) {
            SetOfLabels candidate = new SetOfLabels(i,n);
            if (isDominatingSet(candidate)) {
                if (candidate.getNumberOfElements()<sizeOfSmallestDominatingSet) {
                    smallestDominatingSet = candidate;
                    sizeOfSmallestDominatingSet = candidate.getNumberOfElements();
                }
            }
        }

        System.out.println("The smallest dominating set is: "+
                smallestDominatingSet.toString());
        System.out.println("The size of the smallest dominating set is: "+
                smallestDominatingSet.getNumberOfElements());

    }
}