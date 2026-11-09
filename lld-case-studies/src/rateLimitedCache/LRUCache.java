package rateLimitedCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K,V> {
    private final int capacity;

    private final Map<K,Node<K,V>> map;

    private final Node<K,V> head;
    private final Node<K,V> tail;

    private final ReentrantLock lock = new ReentrantLock();

    public LRUCache(int capacity){

        if(capacity<=0){
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        this.capacity= capacity;

        this.map= new HashMap<>();

        this.head= new Node<>(null,null);
        this.tail= new Node<>(null,null);
        head.next=tail;
        tail.prev=head;
    }

    private void addToTail(Node<K,V> node){
        Node<K,V> prev= tail.prev;

        prev.next=node;
        node.prev= prev;
        node.next=tail;
        tail.prev=node;
    }

    private void removeNode(Node<K, V> node) {

        Node<K, V> previous = node.prev;
        Node<K, V> next = node.next;

        previous.next = next;
        next.prev = previous;
    }

    private void moveToTail(Node<K,V> node){
        removeNode(node);

        addToTail(node);
    }

    private Node<K, V> removeHead() {

        Node<K, V> lru = head.next;

        if (lru == tail) {
            return null;
        }

        removeNode(lru);

        return lru;
    }

    public V get(K key) {
        if(key==null) return null;

        lock.lock();

        try{
            Node<K, V> node = map.get(key);

            // Cache miss
            if (node == null) {
                return null;
            }

            // Mark as most recently used
            moveToTail(node);

            return node.value;
        }finally{
            lock.unlock();
        }
    }

    public void put(K key, V value) {

        // We don't allow null keys or values
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                "Key and value cannot be null"
            );
        }

        lock.lock();

        try{
        Node<K, V> existingNode = map.get(key);

        // Scenario A: Key already exists
        if (existingNode != null) {

            existingNode.value = value;

            moveToTail(existingNode);

            return;
        }

        // Scenario B: New key
        Node<K, V> newNode = new Node<>(key, value);

        map.put(key, newNode);

        addToTail(newNode);

        // Evict LRU if capacity exceeded
        if (map.size() > capacity) {

            Node<K, V> lru = removeHead();

            map.remove(lru.key);
        }}finally{
            lock.unlock();
        }
    }

    public void remove(K key){
        if(key==null)return;

        lock.lock();

        try{
            Node<K,V> node= map.get(key);

            if(node==null)return;

            removeNode(node);

            map.remove(key);

            return;
        }finally{
            lock.unlock();
        }
    }

    public int size(){
        lock.lock(); // needs to lock to make sure when this method was called, no other thread made edits in between to the size

        try{
            return map.size();
        }finally{
            lock.unlock();
        }
    }
}
