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
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jorgen
 */
public class SolverTest
{
    private static Sudoku sudoku;
    
    public SolverTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
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
     * Test of solve method, of class Solver.
     */
    @Test
    public void testSolve()
    {
        System.out.println("solve");
        Solver instance = new Solver(sudoku);
        int iterations=instance.solve();
        assertEquals(1, iterations);
    }

    
    /**
     * Test of solve method, of class Solver.
     */
    @Test
    public void testSolveModerate1()
    {
        System.out.println("solve Moderate #8");
        String content=
               " 27|   |36 "+
               "6  | 1 |  2"+
               "4  |7 2|  5"+
               "___________"+
               "  6| 9 |2  "+
               " 1 |6 7| 5 "+
               "  4| 5 |6  "+
               "___________"+
               "1  |4 3|  6"+
               "7  | 8 |  4"+
               " 45|   |97 ";  
        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();
        
        Solver instance = new Solver(sudoku);
        int iterations=instance.solve();
        assertEquals(2, iterations);
        assertEquals(5, sudoku.getElementValue(0, 0, 0, 0));
        assertEquals(3, sudoku.getElementValue(1, 1, 1, 1));
        assertEquals(3, sudoku.getElementValue(2, 2, 2, 2));
    }
    
    
    /**
     * Test of solve method, of class Solver.
     */
    @Test
    @Disabled
    public void testSolveChallenging1()
    {
        System.out.println("solve Chalenging #7");
        String content=
               "5  |37 |84 "+
               "   |   | 25"+
               "  6|   |7 3"+
               "___________"+
               "  9| 53|1  "+
               "8  |   |  9"+
               "  4|96 |5  "+
               "___________"+
               "7 3|   |2  "+
               "28 |   |   "+
               " 65| 82|  7";  
        Sudoku sudoku = new Sudoku(3);
        sudoku.fill(content);
        sudoku.dump();

        
        
        Solver instance = new Solver(sudoku);
        int iterations=instance.solve();
        assertEquals(1, iterations);
    }

    
}
