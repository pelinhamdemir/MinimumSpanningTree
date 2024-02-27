import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> ListWithInnerList = new ArrayList<>();
        List<String> totalVisit = new LinkedList<>();
        Scanner keyboard = new Scanner(System.in);
        String fileName;
        fileName = keyboard.nextLine();
        String sourceCity = keyboard.next();
        String destCity = keyboard.next();
        totalVisit.add(sourceCity);
        int NumOfCity = keyboard.nextInt();
        for (int t = 0; t < NumOfCity; t++) {
            String CityToGo = keyboard.next();
            totalVisit.add(CityToGo);
        }
        totalVisit.add(destCity);
        String filePath = "C:\\Users\\asus\\IdeaProjects\\cmpe224hw3Q2\\src\\" + fileName;
        List<String> list = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] edge = line.split(",");
                String startCity = edge[0].trim();
                String destinationCity = edge[1].trim();
                int length = Integer.parseInt(edge[2]);
                list.add(startCity);
                list.add(destinationCity);
                list2.add(length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> citylist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!citylist.contains(list.get(i)))
                citylist.add(list.get(i));
        }

        EdgeWeightedGraph g = new EdgeWeightedGraph(citylist.size());
        int a=0;
        for (int i = 0; i < list.size(); i++) {
            Edge edge = new Edge(citylist.indexOf(list.get(i)), citylist.indexOf(list.get(i + 1)), list2.get(a));
            g.addEdge(edge);
            i++;
            a++;
        }

        List<Edge> resultPath = new ArrayList<>();
        EdgeWeightedGraph.dijkstra dijkstra;
        int shortestDistance = 0;

        for (int i = 0; i < NumOfCity+1; i++) {
            if (i + 1 < totalVisit.size()) {
                dijkstra = new EdgeWeightedGraph.dijkstra(g);
                dijkstra.dijkstra(g, citylist.indexOf(totalVisit.get(i)), citylist.indexOf(totalVisit.get(i + 1)));
                List<Edge> shortestPath = dijkstra.shortestPathTo(citylist.indexOf(totalVisit.get(i + 1)));
                shortestDistance += dijkstra.distanceTo(citylist.indexOf(totalVisit.get(i + 1)));
                resultPath.addAll(shortestPath);
            }
        }

        boolean[] written=new boolean[list.size()];
        System.out.print("Routes are: ");
        System.out.println();
        for (Edge edge : resultPath) {
            int either = edge.either();
            int other = edge.other(either);
            if (!written[either]==true) {
                System.out.print(citylist.get(either));
                written[either]=true;
            }

            System.out.print("-" + citylist.get(other));
            written[other]=true;
        }

        System.out.println("\nLength of route is: " + shortestDistance);
    }
}