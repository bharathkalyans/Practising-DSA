import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

class LRUCache {

    HashMap<Integer, Integer> map;
    Deque<Integer> queue;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        queue = new LinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        else {
            queue.remove(key);
            queue.addLast(key);
            return map.get(key);
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            queue.remove(key);
            queue.addLast(key);
        } else {
            if (queue.size() == this.capacity) {
                int remove = queue.removeFirst();
                map.remove(remove);
            }
            queue.addLast(key);
        }

        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */