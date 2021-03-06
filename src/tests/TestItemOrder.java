package tests;


/*
 * TCSS 305 – Fall 2020
 * Assignment 1 – BookStore 
 */

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import model.Item;
import model.ItemOrder;

/**
 * Test ItemOrder class 
 * 
 * 
 * @author Jaehong Lee Student
 * @version Fall 2020 
 */



public class TestItemOrder {
    
    // Declare objects to use in the test fixture.
    
    /** An itemOrder to use in tests. */
    /** An item to use in tests. */
    private Item myItem;
    private ItemOrder myOrder;

    @Before
    public void setUp() throws Exception {
        
        /** An itemOrder to use in tests. */
        myItem = new Item("notebook", new BigDecimal("2.00"), 10, new BigDecimal("18.00"));   
        myOrder = new ItemOrder(myItem, 10);
    }

    /** Test when myQuantitiy is negative, throw IllegalArgumentExceptio */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMyItem() {
        new ItemOrder(myItem, -2); 
    }

    /** Test when myItem is null, throw NullPointerException */
    @Test(expected = NullPointerException.class)
    public void testNullMyItem() {
        new ItemOrder(null, 10);
    }


    /**
     * Test method for {@link ItemOrder#totalCost()}.
     */
    @Test
    public void testTotalCost() {
        assertEquals("return wrong totalCost for itemOrder", new BigDecimal("18.00"), myOrder.totalCost());
        assertNotEquals("return right totalCost for itemOrder name", new BigDecimal("20.00"), myOrder.totalCost());
    }
    
    /**
     * Test method for {@link ItemOrder#getItem()}.
     */
    @Test
    public void testGetItem() {
        assertEquals("", myItem, myOrder.getItem());
       
    }
    
    /**
     * Test method for {@link ItemOrder#getQuantity()}.
     */
    @Test
    public void testGetQuantity() {
        assertEquals("", 10, myOrder.getQuantity());
    }

    /**
     * Test method for {@link ItemOrder#toString()}.
     */
    @Test
    public void testItemOrderToString() {
        
        assertNotEquals("toString() method in ItemOrder class return right result1!", 
                "notebook, $2.00 (10 for $18)", myOrder.toString());
        assertNotEquals("toString() method in ItemOrder class return right result2!", 
                "notebook, $2.00 (10 for $18.00) , quantity : ", myOrder.toString());
 
        
        
        assertEquals("toString() method return wrong result1 !", 
                "notebook, $2.00 (10 for $18.00), quantity : 10", myOrder.toString());
        assertEquals("toString() method return wrong result2 !", 
                myItem.toString() + ", quantity : 10", myOrder.toString());
    }

}
