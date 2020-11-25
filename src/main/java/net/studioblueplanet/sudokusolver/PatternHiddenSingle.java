/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.studioblueplanet.sudokusolver;

/**
 * This class implements the hidden single pattern. Hidden singles may 
 * occur in a box, in a row or in a column.
 * @author jorgen
 */
public class PatternHiddenSingle extends Pattern
{
    /**
     * Execute the pattern in a box. Indicate a hidden single by 
     * reducing the possible candidates to one value. The value is not
     * yet assigned to the cell in the grid.
     * @param sudoku Sodoku to process
     * @return Number of hidden singles found
     */
    private static int executeBox(Sudoku sudoku)
    {
        int dimension;
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        int count;
        int i;
        int cellRowIndex;
        int cellColIndex;
        
        count=0;
        dimension=sudoku.getDimension();
        for (boxRow=0; boxRow<dimension; boxRow++)
        {
            for (boxCol=0; boxCol<dimension; boxCol++)
            {
                for(value=1; value<=dimension*dimension; value++)
                {
                    i=0;
                    cellRowIndex=-1;
                    cellColIndex=-1;
                    for (cellRow=0; cellRow<dimension; cellRow++)
                    {
                        for (cellCol=0; cellCol<dimension; cellCol++)
                        {
                            if (sudoku.isCandidate(boxRow, boxCol, cellRow, cellCol, value))
                            {
                                cellRowIndex =cellRow;
                                cellColIndex =cellCol;
                                i++;
                            }
                        }
                    }
                    if (i==1)
                    {
                        sudoku.clearCandidatesButOne(boxRow, boxCol, cellRowIndex, cellColIndex, value);
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    /**
     * Execute the pattern in a row. Indicate a hidden single by 
     * reducing the possible candidates to one value
     * @param sudoku Sodoku to process
     * @return Number of hidden singles found
     */
    private static int executeRow(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        int count;
        int i;
        int boxIndex;
        int cellIndex;
        
        count=0;
        dimension=sudoku.getDimension();
        for (row=0; row<dimension*dimension; row++)
        {
            boxRow =sudoku.indexToBox(row);
            cellRow=sudoku.indexToCell(row);
            for(value=1; value<=dimension*dimension; value++)
            {
                i=0;
                boxIndex=-1;
                cellIndex=-1;
                for (col=0; col<dimension*dimension; col++)
                {
                    boxCol =sudoku.indexToBox(col);
                    cellCol=sudoku.indexToCell(col);
                    if (sudoku.isCandidate(boxRow, boxCol, cellRow, cellCol, value))
                    {
                        boxIndex =boxCol;
                        cellIndex=cellCol;
                        i++;
                    }
                }
                if (i==1)
                {
                    sudoku.clearCandidatesButOne(boxRow, boxIndex, cellRow, cellIndex, value);
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Execute the pattern in a column. Indicate a hidden single by 
     * reducing the possible candidates to one value
     * @param sudoku Sodoku to process
     * @return Number of hidden singles found
     */
    private static int executeColumn(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        int count;
        int i;
        int boxIndex;
        int cellIndex;
        
        count=0;
        dimension=sudoku.getDimension();
        for (col=0; col<dimension*dimension; col++)
        {
            boxCol =sudoku.indexToBox(col);
            cellCol=sudoku.indexToCell(col);            
            for(value=1; value<=dimension*dimension; value++)
            {
                i=0;
                boxIndex=-1;
                cellIndex=-1;
                for (row=0; row<dimension*dimension; row++)
                {
                    boxRow =sudoku.indexToBox(row);
                    cellRow=sudoku.indexToCell(row);

                    if (sudoku.isCandidate(boxRow, boxCol, cellRow, cellCol, value))
                    {
                        boxIndex =boxRow;
                        cellIndex=cellRow;
                        i++;
                    }
                }
                if (i==1)
                {
                    sudoku.clearCandidatesButOne(boxIndex, boxCol, cellIndex, cellCol, value);
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Execute the pattern
     * @param sudoku Sudoku to process
     * @return Number of hidden singles founed
     */
    public static int execute(Sudoku sudoku)
    {
        int count;
        executeRow(sudoku);
        executeColumn(sudoku);
        executeBox(sudoku);
      
        // assing the found hidden singles to the cells
        count=sudoku.collapseCandidates();
        return count;
    }    
    
}
