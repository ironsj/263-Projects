import java.util.*;
import java.io.*;

public class Project1 {
    // Use a map to store the keys and the associated keys
    Map< String, LinkedList < String > > map = new HashMap < String, LinkedList < String > >();

    public void readData(String filename) {
        Scanner inFS = null;
        FileInputStream fileByteStream = null;

        try {
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            // continue while there is more data to read
            while(inFS.hasNext()) {
                String word = inFS.next();
                String key = convertToKey(word);
                if (!map.containsKey(key)) {
                    LinkedList < String > newList = new LinkedList < String > ();
                    newList.add(word);
                    map.put(key ,newList);
                }
                else {
                    LinkedList < String > existingList = map.get(key);
                    existingList.add(word);
                }

            }
            fileByteStream.close();

            // error while reading the file
        }catch(IOException error1) {
            System.out.println("Oops! Error related to: " + filename);
        }
    }
    // This method traverses the entire map and looks for the largest set
    // After traversing the entire map, it prints:
    //  - The key for the largest set
    //  - The size of the set
    //  - The elements in the set
    public void findMaxSize() {
        Set < String > keys = map.keySet();
        int listSize = 0;
        String largeKey = "";
        LinkedList < String > largest = new LinkedList< String >();

        for( String k : keys ) {
            LinkedList < String > linkedList = map.get(k);
            if(linkedList.size() > listSize){
                largest = linkedList;
                listSize = linkedList.size();
                largeKey = k;
            }
        }

        System.out.println("Key: " + largeKey);
        System.out.println("Size: " + listSize);
        System.out.println("Elements: ");
        for(String element : largest){
            System.out.println(element);
        }
    }
    // This method might be helpful.
    // It takes a string and converts it into an array of characters
    // It sorts the array of characters
    // It creates a new String with that array of characters
    // All the words that are anagrams will have the same key
    public String convertToKey(String word) {
        char [] chars = word.toCharArray();
        Arrays.sort(chars);
        String result = new String(chars);
        return result;
    }

    public static void main(String args[]) {
        Project1 p1 = new Project1();
        p1.readData("words.txt");
        p1.findMaxSize();
    }
}