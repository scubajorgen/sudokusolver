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
public class Main
{
    public static void main(String[] args)
    {
        Sudoku sudoku=new SudokuExampleSimple();
        sudoku.dump();
        
        Solver solver=new Solver(sudoku);
        solver.solve();
       
    }
}
