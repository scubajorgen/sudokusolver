/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.studioblueplanet.sudokusolver;

/**
 * This class solves Sudoku
 * @author jorgen
 */
public class Solver
{
    private static final int    MAX_ITERATIONS=100;
    private Sudoku              sudoku;
    
    public Solver(Sudoku sudoku)
    {
        this.sudoku=sudoku;
    }
    
    public int solve()
    {
        int iteration;
        int count;
        
        iteration=0;
        while (iteration!=MAX_ITERATIONS && !sudoku.isSolved())
        {
            System.out.println("Iteration "+iteration);
            
            count=PatternNakedSingle.execute(sudoku);
            if (count>0)
            {
                System.out.println(count+" element values found by Naked Single");
            }
            sudoku.dump();

            count=PatternHiddenSingle.execute(sudoku);
            if (count>0)
            {
                System.out.println(count+" element values found by Hidden Single");
            }
            sudoku.dump();

            iteration++;
        }
        return iteration;
    }
}
