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

/**
 *
 * @author jorgen
 */
public class PatternIntersectionTest
{
    
    public PatternIntersectionTest()
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
     * Test of execute method, of class PatternIntersection.
     */
    @Test
    public void testExecuteRow()
    {
        System.out.println("execute - pointing - row");
        String content="   |   |   "+
               "   |   |   "+
               "   |   |   "+
               "___________"+
               "4 6|   |  3"+
               "789|   |456"+
               "  3|   |789"+
               "___________"+
               "   |   |   "+
               "   |   |   "+
               "   |   |   ";  
        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(true, sudoku.isCandidate(1, 0, 0, 1, 2));
        assertEquals(true, sudoku.isCandidate(1, 0, 0, 1, 5));
        assertEquals(true, sudoku.isCandidate(1, 0, 2, 1, 2));
        assertEquals(true, sudoku.isCandidate(1, 0, 2, 1, 5));
        
        int expResult = 1;
        int result = PatternIntersection.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        
        assertEquals(5, sudoku.getElementValue(1, 0, 0, 1));
    }

    /**
     * Test of execute method, of class PatternIntersection.
     */
    @Test
    public void testExecuteColumn()
    {
        System.out.println("execute - pointing - column");
        String content=
               "   |12 |   "+
               "   |45 |   "+
               "   |789|   "+
               "___________"+
               "   |   |   "+
               "   |   |   "+
               "   |   |   "+
               "___________"+
               "   |261|   "+
               "   | 4 |   "+
               "   |978|   ";  
        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(true, sudoku.isCandidate(2, 1, 1, 0, 3));
        assertEquals(true, sudoku.isCandidate(2, 1, 1, 0, 5));
        assertEquals(true, sudoku.isCandidate(2, 1, 1, 2, 3));
        assertEquals(true, sudoku.isCandidate(2, 1, 1, 2, 5));
        
        int expResult = 1;
        int result = PatternIntersection.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        
        assertEquals(5, sudoku.getElementValue(2, 1, 1, 2));
    }

    /**
     * Test of execute method, of class PatternIntersection.
     */
    @Test
    public void testExecuteRow2()
    {
        System.out.println("execute - claiming - column");
        String content=
               "   |   |8 9"+
               "246|167|   "+
               "   |   |25 "+
               "___________"+
               "   |   |   "+
               "   |   |   "+
               "   |   |  3"+
               "___________"+
               "   |   |   "+
               "   |   |3  "+
               "   |   |   ";  
        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(true , sudoku.isCandidate(0, 2, 0, 1, 3));
        assertEquals(true , sudoku.isCandidate(0, 2, 1, 1, 3));
        assertEquals(false, sudoku.isCandidate(0, 2, 1, 2, 3));
        assertEquals(false, sudoku.isCandidate(0, 2, 2, 2, 3));
        assertEquals(true , sudoku.isCandidate(0, 2, 2, 2, 1));
        assertEquals(true , sudoku.isCandidate(0, 2, 2, 2, 4));
        assertEquals(true , sudoku.isCandidate(0, 2, 2, 2, 7));
        
        int expResult = 1;
        int result = PatternIntersection.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        
        assertEquals(3, sudoku.getElementValue(0, 2, 1, 1));
        assertEquals(true , sudoku.isCandidate(0, 2, 2, 2, 1));
        assertEquals(true , sudoku.isCandidate(0, 2, 2, 2, 4));
        assertEquals(true , sudoku.isCandidate(0, 2, 2, 2, 7));

    }


    /**
     * Test of execute method, of class PatternIntersection.
     */
    @Test
    public void testExecuteColumn2()
    {
        System.out.println("execute - claiming - column");
        String content=
               " 2 |   |   "+
               " 4 |   |   "+
               " 5 |   |   "+
               "___________"+
               " 1 |   |   "+
               " 6 |   |   "+
               " 7 |   |   "+
               "___________"+
               "8 2| 3 |   "+
               "  5|   |   "+
               "9  |   | 3 ";  
        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(true , sudoku.isCandidate(2, 0, 1, 0, 3));
        assertEquals(true , sudoku.isCandidate(2, 0, 1, 1, 3));
        assertEquals(false, sudoku.isCandidate(2, 0, 2, 2, 3));
        assertEquals(false, sudoku.isCandidate(2, 0, 2, 1, 3));
        assertEquals(false, sudoku.isCandidate(2, 0, 2, 2, 3));
        assertEquals(true , sudoku.isCandidate(2, 0, 2, 2, 1));
        assertEquals(true , sudoku.isCandidate(2, 0, 2, 2, 4));
        assertEquals(true , sudoku.isCandidate(2, 0, 2, 2, 7));
        
        int expResult = 1;
        int result = PatternIntersection.execute(sudoku);
        sudoku.dump();
        assertEquals(expResult, result);
        
        assertEquals(3, sudoku.getElementValue(2, 0, 1, 1));
        assertEquals(true , sudoku.isCandidate(2, 0, 2, 2, 1));
        assertEquals(true , sudoku.isCandidate(2, 0, 2, 2, 4));
        assertEquals(true , sudoku.isCandidate(2, 0, 2, 2, 7));

    }

}
