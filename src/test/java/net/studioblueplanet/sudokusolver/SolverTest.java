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
import org.junit.Ignore;

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
        assertEquals(4, iterations);

    }
    
}
