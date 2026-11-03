package models;

/**
 * InsufficientInventoryException
 */
public class InsufficientInventoryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientInventoryException(String productId) {
        super("Insufficient inventory for the product: " + productId);
    }
}
