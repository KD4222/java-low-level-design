package models;

import java.util.HashMap;
import java.util.List;

public class StockInventory {
    static HashMap<Item,Integer> itemInventory = new HashMap<>();

    public synchronized boolean checkInventory(List<Item> items,List<Integer> quantities){
        int itemNum=0;
        for(;itemNum<items.size();itemNum++){
            if(!itemInventory.containsKey(items.get(itemNum)) || itemInventory.get(items.get(itemNum))<quantities.get(itemNum)){
                System.out.println("Item : "+items.get(itemNum).itemName+" does not exist or is currently out of stock");
                return false;
            }
        }   
        System.out.println("All items are present in the inventory");
        return true;
    }

    public synchronized void updateInventoryAfterOrderCreated(List<Item> items, List<Integer> quantities) {
        if (items.size() != quantities.size()) {
            throw new IllegalArgumentException("Items and quantities must have the same size");
        }
        if (!checkInventory(items, quantities)) {
            throw new IllegalStateException("Insufficient inventory for order");
        }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            itemInventory.put(item, itemInventory.get(item) - quantities.get(i));
            System.out.println("Inventory updated for order placed.");
        }
    }

    public synchronized void restoreInventoryAfterCancellation(List<Item> items, List<Integer> quantities) {
        if (items.size() != quantities.size()) {
            throw new IllegalArgumentException("Items and quantities must have the same size");
        }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            itemInventory.put(item, itemInventory.getOrDefault(item, 0) + quantities.get(i));
        }
    }

    public synchronized void addItems(List<Item> items, List<Integer> quantities) {
        if (items.size() != quantities.size()) {
            throw new IllegalArgumentException("Items and quantities must have the same size");
        }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            itemInventory.put(item, itemInventory.getOrDefault(item, 0) + quantities.get(i));
        }
    }
}
