// Finish and comment me!

/*
 * TCSS 305 – Fall 2020
 * Assignment 1 – BookStore 
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * The item class have item's information like itemName itemPrice, bulkQuantity, bulkPrice
 * 
 * @author Jaehong Lee Student
 * @version Fall 2020 
 */


//bulkPrice is BigDecimal but it can be null? 

public final class Item implements Comparable<Item> {

    // instance fields
    
    /** item name */
    private final String myItemName;
    
    /** item price */
    private final BigDecimal myItemPrice;
    
    /** item's bulk quantity */
    private int myBulkQuantity;
    
    /** item's bulk price */
    private BigDecimal myBulkPrice;

    // constructors
    /**
     *  Constructs a item with the provided name and price
     *  
     *  @param theName The name to assign to this item.
     *  @param thePrice The price to assign to this item.
     *  @throws IllegalArgumentException if theName is empty or thePrice is smaller than 0.00
     *  @throws NullPointerException if theName is null or thePrice is null.
     */

    public Item(final String theName, final BigDecimal thePrice) {
               
        //check if item name is empty
        if(theName.isEmpty()) {
            throw new IllegalArgumentException("you need to set product Name");
        }
        
        // by using intValue() method change BigDecimal to Integer
        if(thePrice.doubleValue() < 0.00) {
            throw new IllegalArgumentException("Item price shouldn't smaller than 0");
        }
        
        this.myItemName = Objects.requireNonNull(theName, "you need to set product's name");
        this.myItemPrice = Objects.requireNonNull(thePrice, "you need to set product's price");
        
        // assign bulk quantity and bulk price 0 
        this.myBulkQuantity = 0;
        this.myBulkPrice = new BigDecimal("0.00");
    }

    /**
     *  Constructs a item with given theName, thePrice, theBulkQuantity and theBulkPrice
     *  
     *  @param theName           The name to assign to this item.
     *  @param thePrice          The price to assign to this item.
     *  @param theBulkQuantity   The bulk quantity to assign to this item.
     *  @param theBulkPrice      The bulk price to assign to this item.  
     *  @throws IllegalArgumentException if the theBulkQuantity or theBulkPrice is smaller than 0.00
     *  @throws NullPointerException if bulkPrice is null.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        
        this(theName, thePrice);
        
        if(theBulkQuantity < 0) {
            throw new IllegalArgumentException("theBulkQuantity shouldn't smaller than 0"); 
        }
        
        // by using intValue() method change BigDecimal to Integer
        if (theBulkPrice.doubleValue() < 0.00) {
            throw new IllegalArgumentException("theBulkPrice shouldn't smaller than 0");
        }
        
        this.myBulkQuantity = theBulkQuantity;
        this.myBulkPrice = Objects.requireNonNull(theBulkPrice, "you need to set product's bulk price");;   
    }

    /**
     * What is the item name?
     * 
     * @return the item name
     */
    public String getName() {
        return myItemName;
    }
    
    /**
     * What is the item price?
     * 
     * @return the item price
     */
    public BigDecimal getPrice() {
        return myItemPrice;
    }
 
    /**
     * What is the item's bulk quantity?
     * 
     * @return the item's bulk quantity
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }

    /**
     * What is the item's bulk price?
     * 
     * @return the item's bulk price
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Do item has bulk quantity and price?
     * 
     * @return true if bulk quantity and price is not 0
     *         false if bulk quantity and price is 0
     */
    public boolean isBulk() {
        if(getBulkQuantity() != 0 && getBulkPrice() != new BigDecimal("0.00")) {
            return true;
        }
        return false;
    }

    
    // toString using StringBuilder() - preferred
    /**
     * Returns a String containing the coordinate pair with a label : Point (1.23, 5.67).
     * The coordinates are formatted to show 2 decimal places.
     * 
     * {@inheritDoc}
     */
    
    // toString using StringBuilder() - preferred
    /**
     * @return a String containing item's name and price as String : “X, $10.00"  
     * if bulkQuantity is not zero and bulkPrice is not zero, ra string containing item's bulk quantity and bulk price
     * : “X, $10.00 (5 for $40.00)”  
     * 
     * {@inheritDoc} 
     */
    
    @Override
    public String toString() { 
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(", ");
        sb.append(nf.format(getPrice()));
        if(isBulk()) sb.append(" (" + getBulkQuantity() + " for " + nf.format(getBulkPrice()) + ")");
       
        return sb.toString();
    }
    
    
    /**
     * @return true if given object has equivalent to item's name, price, bulk quantity and bulk price
     * Otherwise, return false;
     * 
     * {@inheritDoc} 
     */
    
    @Override
    public boolean equals(final Object theOther) {
        boolean result = false;
        
        if ((theOther != null) && (theOther.getClass() == this.getClass())) {
            final Item otherItem = (Item) theOther;
            
            result = getName().equals(otherItem.getName()) &&
                     getPrice().equals(otherItem.getPrice());
            
            if(isBulk() || otherItem.isBulk()) {
                result = result &&
                         getBulkQuantity() == otherItem.getBulkQuantity() &&
                         getBulkPrice().equals(otherItem.getBulkPrice());
            }
         
        }
        return result;
    }

    
    /**
     * @return an item's integer hashcode
     * 
     * {@inheritDoc}
     */    
    @Override
    public int hashCode() {

        int code;
        code = Objects.hash(getName(), getPrice());

        if(isBulk()) {
            code = Objects.hash(getName(), getPrice(), getBulkQuantity(), getBulkPrice());
        }
        
        return code;
    }

    /**
     * @return an integer by comparing two item's alphabetical order
     *         if alphabetical order is equal, return an integer by comparing price
     * {@inheritDoc}
     */

    public int compareTo(final Item o) {
        // TODO Auto-generated method stub
        if(!getName().equals(o.getName())){
            return getName().compareTo(o.getName());
        }else {
            return getPrice().intValue() - o.getPrice().intValue();
        }
      
    }
    
    /**
     * @return an integer by comparing two item's comparing price order(low -> high)
     *         if alphabetical order is equal, return an integer by comparing price
     * {@inheritDoc}
     */  
    public int orderByPrice(Item item1) {
           
        double firstItem = getPrice().doubleValue();
        double secondItem = item1.getPrice().doubleValue();
        
        if(!getPrice().equals(item1.getPrice())) {
            if(firstItem < secondItem) return -1;
            else if(firstItem > secondItem) return 1;
            else return 0;
        } else {
            return item1.getName().compareTo(getName());
        }
    }
    

}
