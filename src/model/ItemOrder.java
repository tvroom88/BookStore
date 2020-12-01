// Finish and comment me!

/*
 * TCSS 305 – Fall 2020
 * Assignment 1 – BookStore 
 */

package model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The itemOrder class have information about a item and the item's quantity
 * In addition, user calculate item's total cost totalCost method
 * 
 * @author Jaehong Lee Student
 * @version Fall 2020 
 */

public final class ItemOrder {

    // instance fields
    
    /** item */
    private final Item myItem;
    
    /** item's quantity */
    private final int myQuantity;
    
    
    // Constructor
    /**
     *   Constructs a itemOrder with the provided item and quantity
     *  
     *  @param theName The item to assign to this item.
     *  @param thePrice The item's quantity to assign to this quantity.
     *  @throws IllegalArgumentException if theQuantity is smaller than 0.
     *  @throws NullPointerException if theName is null.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        
        if(theQuantity < 0) {
            throw new IllegalArgumentException("theQuantity shouldn't smaller than 0");
        }
        
        this.myItem = Objects.requireNonNull(theItem, "myItem should set Item");
        this.myQuantity = theQuantity;

    }
    
    
    /**
     * Calculate the cost for a given quantity of the Item.
     * 
     * @return item's total cost as a BigDecimal.
     */
    public BigDecimal totalCost() {
        
        BigDecimal itemTotalCost = BigDecimal.ZERO;
 
        if(getItem().isBulk()) {
            if(getQuantity() >= getItem().getBulkQuantity()) {
                
                final int numberOfBulks = (int)(getQuantity() / getItem().getBulkQuantity());
                final int extraItem = getQuantity() % getItem().getBulkQuantity();
                
                // cost = bulkCost * numberOfBulks
                // if there is extra -> Item cost = bulkCost * numberOfBulks + ItemCost * numberOfItem
                itemTotalCost = itemTotalCost.add(getItem().getBulkPrice().multiply(new BigDecimal(numberOfBulks)));
                
                if(extraItem > 0) {
                    itemTotalCost = itemTotalCost.add(getItem().getPrice().multiply(new BigDecimal(extraItem)));
                }       
                
            }else {
                itemTotalCost = itemTotalCost.add(getItem().getPrice().multiply(new BigDecimal(getQuantity())));
            }
        }else {
            itemTotalCost = itemTotalCost.add(getItem().getPrice().multiply(new BigDecimal(getQuantity())));
        }
        
        return itemTotalCost;
    }

    
    /**
     * What is the item?
     * 
     * @return the item
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * What is the item's quantity?
     * 
     * @return the item's quantity
     */
    public int getQuantity() {
        return myQuantity;
    }

    
    /**
     * @return a String containing item's name, price as String and quantity : “X, $10.00", quantity : 2"  
     * if bulkQuantity is not zero and bulkPrice is not zero, ra string containing item's bulk quantity and bulk price
     * : “X, $19.99 (5 for $89.99), quantity : 2”  
     * 
     * {@inheritDoc} 
     */
    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        sb.append(getItem());
        sb.append(", quantity : ");
        sb.append(getQuantity());

        return sb.toString();
    }

}
