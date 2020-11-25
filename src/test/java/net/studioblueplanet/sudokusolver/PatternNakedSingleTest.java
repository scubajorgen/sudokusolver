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
public class PatternNakedSingleTest
{
    Sudoku sudoku;
    
    public PatternNakedSingleTest()
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
     * Test of execute method, of class PatternNakedSingle.
     */
    @Test
    public void testExecuteSingle()
    {
        System.out.println("execute - 1");
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
        sudoku.dump();
        PatternNakedSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(5, sudoku.getElementValue(0, 1, 0, 0));
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(1, 0, 1, 0));
        assertEquals(false, sudoku.isSolved());
    }


    /**
     * Test of execute method, of class PatternNakedSingle.
     */
    @Test
    public void testExecuteSingle2()
    {
        System.out.println("execute - 2");
        String content="   |   |   "+
                       "12 |456|789"+
                       "   |   |   "+
                       "___________"+
                       "   |   |   "+
                       "   |   |   "+
                       "   |   |   "+
                       "___________"+
                       "   |   |   "+
                       "   |   |   "+
                       "   |   |   ";     
       
        sudoku=new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(0, 0, 1, 2));
        PatternNakedSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(3               , sudoku.getElementValue(0, 0, 1, 2));
    }

    /**
     * Test of execute method, of class PatternNakedSingle.
     */
    @Test
    public void testExecuteSingle3()
    {
        System.out.println("execute - 3");
        String content="  1|   |   "+
                       "   |   |   "+
                       "  3|   |   "+
                       "___________"+
                       "  4|   |   "+
                       "  5|   |   "+
                       "  6|   |   "+
                       "___________"+
                       "  7|   |   "+
                       "  8|   |   "+
                       "  9|   |   ";     
       
        sudoku=new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(0, 0, 1, 2));
        PatternNakedSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(2               , sudoku.getElementValue(0, 0, 1, 2));
    }

    
    /**
     * Test of execute method, of class PatternNakedSingle.
     */
    @Test
    public void testExecuteSingle4()
    {
        System.out.println("execute - 4");
        String content="   |   |   "+
                       "   |   |   "+
                       "   |   |   "+
                       "___________"+
                       "   |1 3|   "+
                       "   |456|   "+
                       "   |789|   "+
                       "___________"+
                       "   |   |   "+
                       "   |   |   "+
                       "   |   |   ";     
       
        sudoku=new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        assertEquals(Sudoku.UNDEFINED, sudoku.getElementValue(1, 1, 0, 1));
        PatternNakedSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(2               , sudoku.getElementValue(1, 1, 0, 1));
    }

    
  
    /**
     * Test of execute method, of class PatternNakedSingle.
     */
    @Test
    public void testExecuteMultiple()
    {
        System.out.println("execute");
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
        sudoku.dump();
        PatternNakedSingle.execute(sudoku);
        PatternNakedSingle.execute(sudoku);
        PatternNakedSingle.execute(sudoku);
        PatternNakedSingle.execute(sudoku);
        sudoku.dump();
        assertEquals(5, sudoku.getElementValue(0, 1, 0, 0));
        assertEquals(6, sudoku.getElementValue(2, 1, 2, 1));
        assertEquals(true, sudoku.isSolved());
    }
    
}
