import java.util.*;


public class InSet {

    private static int n;
    private static int adjacencyMatrix[][];

    /* Return true if the SetOfLabels x is
     * a subset that is a dominating set.
     * Your code goes here.
     */
    private static boolean isIndependent(SetOfLabels x) {
        int[] covered = new int[n];
        int size = 0;

        for(int i=0; i < n; i++){
            if(x.contains(NodeLabel.values()[i])){
                size++;
                for(int j = 0; j < n; j++){
                    if(adjacencyMatrix[i][j] == 0){
                        covered[j] = 1;
                    }

                }
            }
        }

        for(int i = 0; i < n; i++){
            System.out.print(covered[i]);
        }
        System.out.print(" " + size);
        System.out.print(x.toString());
        System.out.println();

        int count = 0;
        for(int i = 0; i < n; i++){
            if(x.contains(NodeLabel.values()[i])){
                if(covered[i] == 1){
                    count++;
                }
            }

        }
        System.out.println(count);

        if(size != count){
            return false;
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

        SetOfLabels bigIn = new SetOfLabels(twoToN-1,n);
        int sizeBigIn = 0;
        // Iterate over all the elements in the PowerSet
        // Those elements are encoded by the integers 1..2^n -1
        // For each posssible subset, check if it is a dominating set
        // Report the smallest dominating set
        for(int i = 1;i < twoToN - 1;i++) {
            SetOfLabels candidate = new SetOfLabels(i,n);
            if (isIndependent(candidate)) {
                if (candidate.getNumberOfElements()>=sizeBigIn) {
                    bigIn = candidate;
                    sizeBigIn = candidate.getNumberOfElements();
                    System.out.println(bigIn.toString());
                }
            }
        }

        System.out.println("The largest independent set is: "+
                bigIn.toString());
        System.out.println("The size largest independent set is: "+
                bigIn.getNumberOfElements());

    }
}