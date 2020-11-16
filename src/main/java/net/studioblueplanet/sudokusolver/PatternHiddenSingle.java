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
public class PatternHiddenSingle extends Pattern
{
    public static void execute(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        
        dimension=sudoku.getDimension();
        for (row=0; row<dimension*dimension; row++)
        {
            boxRow =sudoku.indexToBox(row);
            cellRow=sudoku.indexToCell(row);
            for (col=0; col<dimension*dimension; col++)
            {
                boxCol =sudoku.indexToBox(col);
                cellCol=sudoku.indexToCell(col);
                if (sudoku.getElementValue(boxRow, boxCol, cellRow, cellCol)==Sudoku.UNDEFINED)
                {
                    for(value=1; value<=dimension*dimension; value++)
                    {
                        if (sudoku.boxHasValue(boxRow, boxCol, value) || sudoku.rowHasValue(row, value) || sudoku.colHasValue(col, value))
                        {
                            sudoku.setCandidate(boxRow, boxCol, cellRow, cellCol, value, false);
                        }
                    }
                }
            }
        }
    }
}
