package tests;


/*
 * TCSS 305 – Fall 2020
 * Assignment 1 – BookStore 
 */

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Cart;
import model.Item;
import model.ItemOrder;

/**
 * Test Item class, ItemOrder class and Cart class
 * 
 * 
 * @author Jaehong Lee Student
 * @version Fall 2020 
 */


public class TestItem {

    
    // Declare objects to use in the test fixture.
    
    /** An item to use in tests. */
    private Item myItem;
    
    
    /**
     * Initialize the test fixture before each test.
     */
    @Before
    public void setUp() throws Exception {
        myItem = new Item("notebook", new BigDecimal("2.00"), 10, new BigDecimal("18.00"));   
    }
    
    // -----------------------  Test first constructor  -----------------------
    /** Test when itemName is null, throw IllegalArgumentExceptio */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyItemName() {
        new Item("", new BigDecimal("18.00")); 
    }

    /** Test when itemPrice is negative, throw IllegalArgumentException */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeItemPrice() {
        new Item("notebook", new BigDecimal("-1.00"));
    }

    /** Test when itemName is null, throw NullPointerException */
    @Test(expected = NullPointerException.class)
    public void testNullItemName() {
        new Item(null, new BigDecimal("18.00")); 
    }
    
    /** Test when itemPrice is null, throw NullPointerException */
    @Test(expected = NullPointerException.class)
    public void testNullItemPrice() {
        new Item("notebook", null); 
    }
    // -----------------------  finish Test first constructor  -----------------------
    
    // -----------------------  Test second constructor  -----------------------
    
    /** Test when bulkQuantity is negative, throw IllegalArgumentException */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBulkQuantity() {
       new Item("notebook", new BigDecimal("2.00"), -2, new BigDecimal("18.00"));
    }
    
    /** Test when bulkPrice is negative, throw IllegalArgumentException */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBulkPrice() {
       new Item("notebook", new BigDecimal("2.00"), 10, new BigDecimal("-2.00"));
    }
    
    /** Test when bulkPrice is null, throw NullPointerException */
    @Test(expected = NullPointerException.class)
    public void testNullBulkPrice() {
        new Item("notebook", new BigDecimal("2.00"), 10, null);
    }
    
    // -----------------------  finish Test second constructor  -----------------------

    
    /**
     * Test method for {@link Item#getName()}.
     */

    @Test
    public void testGetName() {
        assertEquals("return wrong name", "notebook", myItem.getName());

    
    }
    
    /**
     * Test method for {@link Item#getPrice()}.
     */
    
    @Test
    public void testGetPrice() {

        assertEquals("return wrong Item price", new BigDecimal("2.00"), myItem.getPrice());

    }
    /**
     * Test method for {@link Item#getBulkQuantity()}.
     */
    
    @Test
    public void testGetBulkQuantity() {

        assertEquals("return wrong BulkQuantity", 10, myItem.getBulkQuantity());
    }
    
    /**
     * Test method for {@link Item#getBulkPrice()}.
     */
    @Test
    public void testGetBulkPrice() {
        // This should pass. return the name price bulk quantity, bulk price.

        assertEquals("return wrong BulkPrice", new BigDecimal("18.00"), myItem.getBulkPrice());
    }
    
    
    /**
     * Test method for {@link Item#isBulk()}.
     */
   
    @Test
    public void testIsBultMethod() {
        // create new Item for testing isBulk method.
        // myItem return true because it has bulk quantity and bulk price, 
        // myItem1 return false because it doesn't have bulk quantity and bulk price;
        final Item myItem1 = new Item("airplane", new BigDecimal("12.00"));
        assertTrue("Item has bulk quantity and price value", myItem.isBulk());
        assertFalse("item doesn't have bulk quantity and Price", myItem1.isBulk());
    }
    
    
    /**
     * Test method for {@link Item#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("toString() method return wrong result !", 
                "notebook, $2.00 (10 for $18.00)", myItem.toString());
        assertNotEquals("toString() method return wrong result !", 
                "notebook, $2.00 (10 for $18)", myItem.toString());
    }
    
    
    /**
     * Test method for {@link Item#equals(java.lang.Objects)}.
     */
    @Test
    public void testEquals() {
        final Item item2 = new Item("notebook", new BigDecimal("2.00"), 10, new BigDecimal("18.00"));
        
        // This should also pass. because these two items has same 
        assertEquals("given two item is equal!", myItem, myItem);
        assertEquals("given two item is equal!", myItem, item2);
        
        assertNotEquals("given two item is not equal!", myItem, new Item("notebook", new BigDecimal("2.00")));
        
        
        assertTrue("equals return false, but it need to return true",
                myItem.equals(item2));
        
        // one item has bulk price and quantity but other doesn't     
        assertFalse("equals return true, but it should return false1",
                myItem.equals(new Item("pencil", new BigDecimal("3.00"))));
        
        // two item has different name
        assertFalse("equals return true, but it should return false2",
                myItem.equals(new Item("flexnotebook", new BigDecimal("2.00"), 10, new BigDecimal("18.00"))));
        
        // two item has different price
        assertFalse("equals return true, but it should return false3",
                myItem.equals(new Item("notebook", new BigDecimal("2.20"), 10, new BigDecimal("18.00"))));
        
        // two item has different bulk quantity
        assertFalse("equals return true, but it should return false4",
                myItem.equals(new Item("flexnotebook", new BigDecimal("2.00"), 8, new BigDecimal("18.00"))));
        
        // two item has different bulk price
        assertFalse("equals return true, but it should return false5",
                myItem.equals(new Item("flexnotebook", new BigDecimal("2.00"), 8, new BigDecimal("16.00"))));
        
    }
    
    /**
     * Test method for {@link Item#hashCode()}.
     */
    @Test
    public void testHashCode() {
        final Item item3 = new Item("notebook", new BigDecimal("2.00"), 10, new BigDecimal("18.00"));
            
        assertEquals(item3.hashCode(), myItem.hashCode());
    }
    
    /**
     * Test method for {@link Item#compareTo()}.
     */
    
    @Test
    public void testOrder() {
      Item item1 = new Item("pen", new BigDecimal("2.00"));
      Item item2 = new Item("airplane", new BigDecimal("1.00"));
      Item item3 = new Item("pen", new BigDecimal("6.00"));
      
      List<Item> itemList = new ArrayList<>();
      itemList.add(item1);
      itemList.add(item2);
      itemList.add(item3);
      
      
      //compareTo
      Collections.sort(itemList);
      
      assertEquals("testOrder - 1", item2, itemList.remove(0));
      assertEquals("testOrder - 2", item1, itemList.remove(0));
      assertEquals("testOrder - 3", item3, itemList.remove(0));
    }
    
    /**
     * Test method for {@link Item#orderByPrice()}.
     */
//    (XYZ:0.01) (XYZ:0.99) (AAA:0.99) (ABC:9:99) (ABC:10.99)
    @Test
    public void orderByPrice() {
        // item1 (pen:1) item3 (pen:2) item2(airplane:2) (notebooke 4) (pencil : 6) ( airplane :8)
        Item item1 = new Item("pen", new BigDecimal("1.00"));
        Item item2 = new Item("airplane", new BigDecimal("2.00"));
        Item item3 = new Item("pen", new BigDecimal("2.00"));
        Item item4 = new Item("pencil", new BigDecimal("6.00"));
        Item item5 = new Item("notebook", new BigDecimal("4.00"));
        Item item6 = new Item("airplane", new BigDecimal("8.00"));
        
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);
        
        
        //orderByPrice
        Collections.sort(itemList, (Item a, Item b) -> {
            return a.orderByPrice(b);
         });
        
        assertEquals("1", item1, itemList.remove(0));
        assertEquals("2", item3, itemList.remove(0));
        assertEquals("3", item2, itemList.remove(0));
        assertEquals("4", item5, itemList.remove(0));
        assertEquals("5", item4, itemList.remove(0));
        assertEquals("6", item6, itemList.remove(0));
    }
}
