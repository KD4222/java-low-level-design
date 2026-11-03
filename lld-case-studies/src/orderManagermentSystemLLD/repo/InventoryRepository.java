package repo;

import java.util.concurrent.ConcurrentHashMap;

import models.ProductInventory;

public class InventoryRepository {
    ConcurrentHashMap<String,ProductInventory> inventoryMap;

    public InventoryRepository(){
        inventoryMap=new ConcurrentHashMap<>();
    }

    public void save(ProductInventory product){
        inventoryMap.put(product.getProductId(), product);
    }

    public ProductInventory get(String productId){
        return inventoryMap.get(productId);
    }
}
