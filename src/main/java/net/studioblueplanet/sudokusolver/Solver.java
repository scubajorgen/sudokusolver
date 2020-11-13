/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.studioblueplanet.sudokusolver;

/**
 *
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
    
    public void solve()
    {
        int iterations;
        
        iterations=0;
        while (iterations!=MAX_ITERATIONS)
        {
            iterations++;
        }
    }
}
