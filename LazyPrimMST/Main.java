import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> charWithInnerList = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        String fileName = keyboard.nextLine();
        String filePath = "C:\\Users\\asus\\IdeaProjects\\CMPE224HW3Q1\\src\\" + fileName;

        int a = 0;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String alf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            while ((line = reader.readLine()) != null) {


                String[] parts = line.split(",");
                if (parts.length == 3) {
                    char character = parts[0].charAt(0);
                    int s = alf.indexOf(character);
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);

                    List<Integer> innerList = new ArrayList<>();
                    innerList.add(x);
                    innerList.add(y);
                    charWithInnerList.add(innerList);
                    a++;


                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EdgeWeightedGraph graph = new EdgeWeightedGraph(a);
        for (int j = 0; j < a; j++) {
            for (int i = j; i < a; i++) {
                Edge edge = null;
                if (!(i == j)) {
                    edge = new Edge(j, i, distance(charWithInnerList, j, i));
                    graph.addEdge(edge);
                }


            }

        }
        String alfabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        LazyPrim prim = new LazyPrim(graph);

        // Minimum Spanning Tree'yi weight değerine göre sırala
        List<Edge> sortedMST = (List<Edge>) prim.mst();
        Collections.sort(sortedMST, Comparator.comparingDouble(Edge::getWeight));

        // Sıralanmış MST'yi yazdır
        System.out.println("Paths are:");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.0", symbols);
        for (Edge e : sortedMST) {
            char h = alfabe.charAt(e.either());
            char p = alfabe.charAt(e.other(e.either()));
            String formattedWeight = df.format(e.getWeight());
            System.out.println(h + "-" + p + ": " + formattedWeight);
        }
    }

    public static double distance(List<List<Integer>> charWithInnerList, int v1, int v2) {
        try {
            int x1 = charWithInnerList.get(v1).get(0);
            int y1 = charWithInnerList.get(v1).get(1);
            int x2 = charWithInnerList.get(v2).get(0);
            int y2 = charWithInnerList.get(v2).get(1);
            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        } catch (IndexOutOfBoundsException e) {
            throw e; // rethrow the exception to terminate the program
        }
    }
}




