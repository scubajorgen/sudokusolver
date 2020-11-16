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
public class PatternHiddenSingleTest
{
    Sudoku sudoku;
    
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
       
        sudoku=new Sudoku(3);
        sudoku.fill(content);
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of execute method, of class PatternHiddenSingle.
     */
    @Test
    public void testExecuteSingle()
    {
        System.out.println("execute");
        sudoku.dump();
        PatternHiddenSingle.execute(sudoku);
        sudoku.collapseCandidates();
        sudoku.dump();
        assertEquals(5, sudoku.getElementValue(0, 1, 0, 0));
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(2, 1, 2, 1));
        assertEquals(false, sudoku.isSolved());
    }
    
    /**
     * Test of execute method, of class PatternHiddenSingle.
     */
    @Test
    public void testExecuteMultiple()
    {
        System.out.println("execute");
        sudoku.dump();
        PatternHiddenSingle.execute(sudoku);
        sudoku.collapseCandidates();
        PatternHiddenSingle.execute(sudoku);
        sudoku.collapseCandidates();
        PatternHiddenSingle.execute(sudoku);
        sudoku.collapseCandidates();
        PatternHiddenSingle.execute(sudoku);
        sudoku.collapseCandidates();
        sudoku.dump();
        assertEquals(5, sudoku.getElementValue(0, 1, 0, 0));
        assertEquals(6, sudoku.getElementValue(2, 1, 2, 1));
        assertEquals(true, sudoku.isSolved());
    }
    
}
