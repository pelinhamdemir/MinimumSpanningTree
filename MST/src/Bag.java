import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Bag<Item> implements Iterable<Item> {
    private LinkedList<Item> bag;

    public Bag() {
        bag = new LinkedList<>();
    }

    // Add an item to the bag
    public void add(Item item) {
        bag.add(item);
    }

    // Check if the bag is empty
    public boolean isEmpty() {
        return bag.isEmpty();
    }

    // Get the size of the bag
    public int size() {
        return bag.size();
    }

    // Iterator for iterating over the items in the bag
    @Override
    public Iterator<Item> iterator() {
        return bag.iterator();
    }

}

