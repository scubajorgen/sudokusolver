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
public class PatternHiddenSingleTest
{
    
    public PatternHiddenSingleTest()
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
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of execute method, of class PatternHiddenSingle.
     */
    @Test
    public void testExecuteCell()
    {
        System.out.println("execute - cell");
        String content="   |   |   "+
                       "   |   |   "+
                       "   |   |   "+
                       "___________"+
                       "3  |   |46 "+
                       "   |   | 78"+
                       "   |   |  1"+
                       "___________"+
                       "   |   |   "+
                       "   |   |3  "+
                       "   |   |   ";        

        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        int expResult = 1;
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(1, 2, 2, 1));
        
        int result = PatternHiddenSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        assertEquals(3, sudoku.getElementValue(1, 2, 2, 1));
    }
    
    /**
     * Test of execute method, of class PatternHiddenSingle.
     */
    @Test
    public void testExecuteRow()
    {
        System.out.println("execute - row");
        String content="   |   |   "+
                       " 3 |   |   "+
                       "   |   |   "+
                       "___________"+
                       "1  |4 6|7 9"+
                       "   |   |   "+
                       "   |   |   "+
                       "___________"+
                       "   | 3 |   "+
                       "   |   | 3 "+
                       "   |   |   ";        

        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        int expResult = 1;
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(1, 0, 0, 2));
        
        int result = PatternHiddenSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        assertEquals(3, sudoku.getElementValue(1, 0, 0, 2));

    }

    /**
     * Test of execute method, of class PatternHiddenSingle.
     */
    @Test
    public void testExecuteColumn()
    {
        System.out.println("execute - column");
        String content="   | 1 |   "+
                       "   | 2 |   "+
                       "   |   |   "+
                       "___________"+
                       "   |   | 3 "+
                       "   | 5 |   "+
                       "   | 6 |   "+
                       "___________"+
                       "3  |   |   "+
                       "   |   |  3"+
                       "   | 9 |   ";        

        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        int expResult = 1;
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(0, 1, 2, 1));
        
        int result = PatternHiddenSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        assertEquals(3, sudoku.getElementValue(0, 1, 2, 1));

    }    

    /**
     * Test of execute method, of class PatternHiddenSingle.
     */
    @Test
    public void testExecuteAll()
    {
        int cellsBefore;
        int cellsAfter;
        System.out.println("execute - all");
        String content=" 9 |8  |   "+
                       "  2|   |1 5"+
                       "   |   | 97"+
                       "___________"+
                       "3  |  8|46 "+
                       "61 | 2 | 78"+
                       " 29|6  |  1"+
                       "___________"+
                       "26 |   |   "+
                       "9 7|   |3  "+
                       "   |  7| 1 ";

        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        int expResult = 11;
        cellsBefore=sudoku.countCellValues();
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(1, 2, 2, 1));
        
        int result = PatternHiddenSingle.execute(sudoku);
        cellsAfter=sudoku.countCellValues();
        sudoku.dump();
        assertEquals(expResult, result);
        assertEquals(3, sudoku.getElementValue(1, 2, 2, 1));
        assertEquals(expResult, cellsAfter-cellsBefore);
    }    
    
}
