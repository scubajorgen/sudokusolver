/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.studioblueplanet.sudokusolver;

/**
 * This class represents the naked single pattern. In this pattern all but one
 * possible field is left
 * 
 * @author jorgen
 */
public class PatternNakedSingle extends Pattern
{
    public static int execute(Sudoku sudoku)
    {
        int count;
        
        // simply collapse the elements that contain one candidate (the naked
        // single). By setting an element value the candidates are updated 
        // automatically.
        count=sudoku.collapseCandidates();
        return count;
    }
}
