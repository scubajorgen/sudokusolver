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
public class SudokuExampleSimple extends Sudoku
{
    public SudokuExampleSimple()
    {
        super(3);
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
        fill(content);
    }
}
