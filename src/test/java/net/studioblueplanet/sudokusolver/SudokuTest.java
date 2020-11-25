/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.studioblueplanet.sudokusolver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author jorgen
 */
public class SudokuTest
{
    private static Sudoku   theInstance;
    
    public SudokuTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
    {
    }
    
    @AfterAll
    public static void tearDownClass()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
        String content="981|  3| 4 "+
                       "   | 79|25 "+
                       " 7 |1 6| 83"+
                       "___________"+
                       " 9 |4 7|5 2"+
                       "  8| 1 |7  "+
                       "7 3|6 5| 1 "+
                       "___________"+
                       "31 |7 4| 9 "+
                       " 69|23 |   "+
                       " 5 |9  |324";
        theInstance=new Sudoku(3);
        theInstance.fill(content);
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of getDimension method, of class Sudoku.
     */
    @Test
    public void testGetDimension()
    {
        System.out.println("getDimension");
        assertEquals(3, theInstance.getDimension());
    }
    
    /**
     * Test of clear method, of class Sudoku.
     */
    @Test
    public void testClear()
    {
        System.out.println("clear");
        assertEquals(9, theInstance.getElementValue(0, 0, 0, 0));
        assertEquals(4, theInstance.getElementValue(2, 2, 2, 2));
        theInstance.clear();
        assertEquals(Sudoku.UNDEFINED, theInstance.getElementValue(0, 0, 0, 0));
        assertEquals(Sudoku.UNDEFINED, theInstance.getElementValue(2, 2, 2, 2));
    }

    /**
     * Test of fill method, of class Sudoku.
     */
    @Test
    public void testFill()
    {
        System.out.println("fill");
        String content = "-------"+
                         "|12|34|"+
                         "|23|41|"+
                         "|-----|"+
                         "|34|12|"+
                         "|41|23|"+
                         "-------";
        Sudoku instance = new Sudoku(2);
        instance.fill(content);
        assertEquals(1, instance.getElementValue(0, 0, 0, 0));
        assertEquals(4, instance.getElementValue(1, 0, 0, 1));
        assertEquals(1, instance.getElementValue(0, 1, 1, 1));
    }

    /**
     * Test of setField method, of class Sudoku.
     */
    @Test
    public void testSetElementValue()
    {
        System.out.println("setElementValue");
        int boxRow = 0;
        int boxCol = 1;
        int cellRow = 0;
        int cellCol = 1;
        int value = 5;
        assertEquals(Sudoku.UNDEFINED, theInstance.getElementValue(boxRow, boxCol, cellRow, cellCol));
        theInstance.setElementValue(boxRow, boxCol, cellRow, cellCol, value);
        assertEquals(value, theInstance.getElementValue(boxRow, boxCol, cellRow, cellCol));
    }

    /**
     * Test of setField method, of class Sudoku.
     */
    @Test
    public void testSetElementValue2()
    {
        System.out.println("setElementValue - clear possible values");
        Sudoku instance=new Sudoku(3);

        // element
        assertEquals(true, instance.isCandidate(1, 1, 1, 1, 5)); // same row
        assertEquals(true, instance.isCandidate(1, 1, 1, 1, 6)); // same row
        // row
        assertEquals(true, instance.isCandidate(1, 0, 1, 1, 5)); // same row
        assertEquals(true, instance.isCandidate(1, 2, 1, 1, 5)); // same row
        assertEquals(true, instance.isCandidate(1, 0, 0, 1, 5)); // row above
        assertEquals(true, instance.isCandidate(1, 2, 2, 1, 5)); // row below
        // col
        assertEquals(true, instance.isCandidate(0, 1, 1, 1, 5));    // same column
        assertEquals(true, instance.isCandidate(0, 1, 1, 0, 5));
        assertEquals(true, instance.isCandidate(2, 1, 1, 2, 5));
        // box
        assertEquals(true, instance.isCandidate(1, 1, 0, 0, 5));    // same box
        assertEquals(true, instance.isCandidate(0, 2, 0, 0, 5));    // other box
        
        instance.setElementValue(1, 1, 1, 1, 5);
        
        // element
        assertEquals(false, instance.isCandidate(1, 1, 1, 1, 5)); // same row
        assertEquals(false, instance.isCandidate(1, 1, 1, 1, 6)); // same row
        // row
        assertEquals(false, instance.isCandidate(1, 0, 1, 1, 5)); // same row
        assertEquals(true , instance.isCandidate(1, 0, 1, 1, 4)); // same row, other value
        assertEquals(false, instance.isCandidate(1, 2, 1, 1, 5)); // same row
        assertEquals(true , instance.isCandidate(1, 0, 0, 1, 5)); // row above
        assertEquals(true , instance.isCandidate(1, 2, 2, 1, 5)); // row below
        // col
        assertEquals(false, instance.isCandidate(0, 1, 1, 1, 5)); // same column
        assertEquals(true , instance.isCandidate(0, 1, 1, 0, 5));
        assertEquals(true , instance.isCandidate(2, 1, 1, 2, 5));
        // box
        assertEquals(false, instance.isCandidate(1, 1, 0, 0, 5));    // same box
        assertEquals(true , instance.isCandidate(0, 2, 0, 0, 5));    // other box
    }    
    
    /**
     * Test of getField method, of class Sudoku.
     */
    @Test
    public void testGetElementValue()
    {
        System.out.println("getElementValue");
        int boxRow = 0;
        int boxCol = 0;
        int cellRow = 0;
        int cellCol = 0;
        int expResult = 0;
        assertEquals(9, theInstance.getElementValue(boxRow, boxCol, cellRow, cellCol));
    }

    /**
     * Test of setCandidate method, of class Sudoku.
     */
    @Test
    public void testIsCandidate()
    {
        System.out.println("isCandidate");

        assertEquals(false, theInstance.isCandidate(0,0,0,0,1));
        assertEquals(true , theInstance.isCandidate(0,1,0,0,5));
    }

    /**
     * Test of setCandidate method, of class Sudoku.
     */
    @Test
    public void testSetCandidate()
    {
        System.out.println("setCandidate");
        int boxRow = 0;
        int boxCol = 1;
        int cellRow = 0;
        int cellCol = 0;
        int value = 5;
        boolean isCandidate = false;
        assertEquals(true, theInstance.isCandidate(boxRow, boxCol, cellRow, cellCol, value));
        theInstance.clearCandidates(boxRow, boxCol, cellRow, cellCol);
        assertEquals(false, theInstance.isCandidate(boxRow, boxCol, cellRow, cellCol, value));
        assertEquals(false, theInstance.isCandidate(boxRow, boxCol, cellRow, cellCol, value));
    }
    
    @Test
    public void testClearCandidates()
    {
        System.out.println("clearCandidates");

        int value = 5;
        boolean isCandidate = false;
        assertEquals(true, theInstance.isCandidate(0, 1, 0, 0, value));
        theInstance.setCandidate(0, 1, 0, 0, value, isCandidate);
        assertEquals(false, theInstance.isCandidate(0, 1, 0, 0, value));
        assertEquals(true, theInstance.isCandidate(0, 1, 0, 1, value));
    }

    /**
     * Test of isSolved method, of class Sudoku.
     */
    @Test
    public void testIsSolved()
    {
        System.out.println("isSolved");
        Sudoku instance = new Sudoku(2);
        String content = "-------"+
                         "|1 |34|"+
                         "|23|41|"+
                         "|-----|"+
                         "|34|12|"+
                         "|41|23|"+
                         "-------";
        instance.fill(content);
        assertEquals(false, instance.isSolved());
        content        = "-------"+
                         "|12|34|"+
                         "|23|41|"+
                         "|-----|"+
                         "|34|12|"+
                         "|41|23|"+
                         "-------";
        instance.fill(content);
        assertEquals(true, instance.isSolved());
    }

    /**
     * Test of countCellValues method, of class Sudoku.
     */
    @Test
    public void testCountCellValues()
    {
        System.out.println("isSolved");
        assertEquals(41, theInstance.countCellValues());
    }
    
    /**
     * Test of dump method, of class Sudoku.
     */
    @Test
    @Disabled
    public void testDump()
    {
        System.out.println("dump");
        theInstance.dump();
    }

    /**
     * Test of boxHasValue method, of class Sudoku.
     */
    @Test
    public void testBoxHasValue()
    {
        System.out.println("boxHasValue");
        assertEquals(true , theInstance.boxHasValue(0, 0, 9));
        assertEquals(false, theInstance.boxHasValue(0, 1, 5));
        assertEquals(true , theInstance.boxHasValue(1, 1, 4));
        assertEquals(false, theInstance.boxHasValue(1, 1, 8));
    }

    /**
     * Test of rowHasValue method, of class Sudoku.
     */
    @Test
    public void testRowHasValue()
    {
        System.out.println("rowHasValue");
        assertEquals(false, theInstance.rowHasValue(1, 1));
        assertEquals(true , theInstance.rowHasValue(1, 7));
    }

    /**
     * Test of boxCellToIndex method, of class Sudoku.
     */
    @Test
    public void testBoxCellToIndex()
    {
        System.out.println("boxCellToIndex");
        assertEquals(4 , theInstance.boxCellToIndex(1, 1));
        assertEquals(-1, theInstance.boxCellToIndex(3, 1));
        assertEquals(-1, theInstance.boxCellToIndex(2, 3));
        assertEquals(-1, theInstance.boxCellToIndex(-1, 2));
        assertEquals(-1, theInstance.boxCellToIndex(2, -1));
    }
    
    /**
     * Test of indexToBox method, of class Sudoku
     */
    @Test
    public void testIndexToBox()
    {
        System.out.println("indexToBox");
        assertEquals( 1 , theInstance.indexToBox( 4));
        assertEquals( 1 , theInstance.indexToBox( 5));
        assertEquals(-1 , theInstance.indexToBox(-1));
        assertEquals( 2 , theInstance.indexToBox( 8));
        assertEquals(-1 , theInstance.indexToBox( 9));
    }
    
    /**
     * Test of indexToCell method, of class Sudoku
     */
    @Test
    public void testIndexToCell()
    {
        System.out.println("indexToCell");
        assertEquals( 1 , theInstance.indexToCell( 4));
        assertEquals( 2 , theInstance.indexToCell( 5));
        assertEquals(-1 , theInstance.indexToCell(-1));
        assertEquals( 2 , theInstance.indexToCell( 8));
        assertEquals(-1 , theInstance.indexToCell( 9));        
    }
    
    /**
     * Test of collapseCandidates method, of class Sudoku
     */
    @Test
    public void testCollapseCandidates()
    {
        int value;
        int count;
        Sudoku instance=new Sudoku(3);
        for (value=1; value<=9; value++)
        {
            instance.setCandidate(0, 1, 1, 1, value, false);
            instance.setCandidate(0, 0, 0, 0, value, false);
            instance.setCandidate(0, 2, 2, 2, value, false);
        }
        instance.setCandidate(0, 1, 1, 1, 5, true);
        instance.setCandidate(0, 0, 0, 0, 5, true);
        instance.setCandidate(0, 2, 2, 2, 5, true);
        count=instance.collapseCandidates();
        assertEquals(5, instance.getElementValue(0, 0, 0, 0));
        assertEquals(3, count);
        assertEquals(Sudoku.UNDEFINED, instance.getElementValue(1, 2, 1, 2));
    }

    /**
     * Test of collapseCandidates method, of class Sudoku
     */
    @Test
    public void testCollapseCandidates2()
    {
        int value;
        int count;
        for (value=1; value<=9; value++)
        {
            theInstance.setCandidate(0, 0, 1, 0, value, false);
        }
        theInstance.setCandidate(0, 0, 1, 0, 5, true);
        count=theInstance.collapseCandidates(0, 0, 1, 0);
        assertEquals(5, theInstance.getElementValue(0, 0, 1, 0));
        assertEquals(1, count);
        assertEquals(Sudoku.UNDEFINED, theInstance.getElementValue(0, 0, 1, 1));
    }

}
