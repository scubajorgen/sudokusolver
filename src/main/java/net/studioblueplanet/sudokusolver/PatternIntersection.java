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
public class PatternIntersection  extends Pattern
{
    private static int executePointingRow(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int boxCol2;
        int cellRow;
        int cellCol;
        int value;
        int intersectionCount;
        int cellRowCount;
        int cellRowContainingValue;
        boolean found;
        
        intersectionCount=0;
        dimension=sudoku.getDimension();
        for (boxRow=0; boxRow<dimension; boxRow++)
        {
            for (boxCol=0; boxCol<dimension; boxCol++)
            {
                for (value=1; value<=dimension*dimension; value++)
                {
                    cellRowCount=0;
                    cellRowContainingValue=-1;
                    for (cellRow=0; cellRow<dimension && cellRowCount<=1; cellRow++)
                    {
                        found=false;
                        for (cellCol=0; cellCol<dimension && !found; cellCol++)
                        {
                            if (sudoku.isCandidate(boxRow, boxCol, cellRow, cellCol, value))
                            {
                                if (!found)
                                {
                                    cellRowContainingValue=cellRow;
                                    cellRowCount++;
                                    found=true;
                                }
                            }
                        }
                    }
                    if (cellRowCount==1)
                    {
                        found=false;
                        for (boxCol2=0; boxCol2<dimension; boxCol2++)
                        {
                            if (boxCol2!=boxCol)
                            {
                                for (cellCol=0; cellCol<dimension; cellCol++)
                                {
                                    if (sudoku.isCandidate(boxRow, boxCol2, cellRowContainingValue, cellCol, value))
                                    {
                                        sudoku.setCandidate(boxRow, boxCol2, cellRowContainingValue, cellCol, value, false);
                                        found=true;
                                    }
                                }                        
                            }
                        }
                        if (found)
                        {
                            intersectionCount++;
                        }
                    }
                }
            }
        }   
        return intersectionCount;
    }
    
    private static int executePointingColumn(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int boxRow2;
        int cellRow;
        int cellCol;
        int value;
        int intersectionCount;
        int cellColCount;
        int cellColContainingValue;
        boolean found;
        
        intersectionCount=0;
        dimension=sudoku.getDimension();
        for (boxRow=0; boxRow<dimension; boxRow++)
        {
            for (boxCol=0; boxCol<dimension; boxCol++)
            {
                for (value=1; value<=dimension*dimension; value++)
                {
                    cellColCount=0;
                    cellColContainingValue=-1;
                    for (cellCol=0; cellCol<dimension && cellColCount<=1; cellCol++)
                    {
                        found=false;
                        for (cellRow=0; cellRow<dimension && !found; cellRow++)
                        {
                            if (sudoku.isCandidate(boxRow, boxCol, cellRow, cellCol, value))
                            {
                                if (!found)
                                {
                                    cellColContainingValue=cellCol;
                                    cellColCount++;
                                    found=true;
                                }
                            }
                        }
                    }
                    if (cellColCount==1)
                    {
                        found=false;
                        for (boxRow2=0; boxRow2<dimension; boxRow2++)
                        {
                            if (boxRow2!=boxRow)
                            {
                                for (cellRow=0; cellRow<dimension; cellRow++)
                                {
                                    if (sudoku.isCandidate(boxRow2, boxCol, cellRow, cellColContainingValue, value))
                                    {
                                        sudoku.setCandidate(boxRow2, boxCol,cellRow, cellColContainingValue, value, false);
                                        found=true;
                                    }
                                }                        
                            }
                        }
                        if (found)
                        {
                            intersectionCount++;
                        }
                    }
                }
            }
        }   
        return intersectionCount;
    }

    private static int executeClaimingRow(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int boxCol2;
        int cellRow;
        int cellCol;
        int cellRow2;
        int cellCol2;
        int value;
        int intersectionCount;
        int cellColCount;
        int cellColContainingValue;
        boolean found;
        boolean exit;
        
        intersectionCount=0;
        dimension=sudoku.getDimension();
        for (boxRow=0; boxRow<dimension; boxRow++)
        {
            for (boxCol=0; boxCol<dimension; boxCol++)
            {
                for (cellRow=0; cellRow<dimension; cellRow++)
                {
                    for (value=1; value<=dimension*dimension; value++)
                    {
                        exit=false;
                        for (boxCol2=0; boxCol2<dimension && !exit; boxCol2++)
                        {
                            if (boxCol2!=boxCol)
                            {
                                for (cellRow2=0; cellRow2<dimension && !exit; cellRow2++)
                                {
                                    if (cellRow2!=cellRow)
                                    {
                                        exit=true;
                                        for (cellCol2=0; cellCol2<dimension && exit; cellCol2++)
                                        {
                                            if (sudoku.isCandidate(boxRow, boxCol2, cellRow2, cellCol2, value))
                                            {
                                                exit=false;
                                            }
                                        }   
                                    }
                                    else
                                    {
                                        for (cellCol2=0; cellCol2<dimension && !exit; cellCol2++)
                                        {
                                            if (sudoku.isCandidate(boxRow, boxCol2, cellRow2, cellCol2, value))
                                            {
                                                exit=true;
                                            }
                                        }                                        
                                    }
                                }                                
                            }
                        }
                        if (!exit)
                        {
                            intersectionCount++;
                            for (cellRow2=0; cellRow2<dimension && exit; cellRow2++)
                            {
                                if (cellRow2!=cellRow)
                                {
                                    for (cellCol2=0; cellCol2<dimension; cellCol2++)
                                    {
                                        sudoku.setCandidate(boxRow, boxCol, cellRow2, cellCol2, value, false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }   
        return intersectionCount;
    }

    private static int executeClaimingColumn(Sudoku sudoku)
    {
        int dimension;
        int row;
        int col;
        int boxRow;
        int boxCol;
        int boxRow2;
        int cellRow;
        int cellCol;
        int cellRow2;
        int cellCol2;
        int value;
        int intersectionCount;
        int cellColCount;
        int cellColContainingValue;
        boolean found;
        boolean exit;
        
        intersectionCount=0;
        dimension=sudoku.getDimension();
        for (boxRow=0; boxRow<dimension; boxRow++)
        {
            for (boxCol=0; boxCol<dimension; boxCol++)
            {
                for (cellCol=0; cellCol<dimension; cellCol++)
                {
                    for (value=1; value<=dimension*dimension; value++)
                    {
                        exit=false;
                        for (boxRow2=0; boxRow2<dimension && !exit; boxRow2++)
                        {
                            if (boxRow2!=boxRow)
                            {
                                for (cellCol2=0; cellCol2<dimension && !exit; cellCol2++)
                                {
                                    if (cellCol2!=cellCol)
                                    {
                                        exit=true;
                                        for (cellRow2=0; cellRow2<dimension && exit; cellRow2++)
                                        {
                                            if (sudoku.isCandidate(boxRow2, boxCol, cellRow2, cellCol2, value))
                                            {
                                                exit=false;
                                            }
                                        }   
                                    }
                                    else
                                    {
                                        for (cellRow2=0; cellRow2<dimension && !exit; cellRow2++)
                                        {
                                            if (sudoku.isCandidate(boxRow2, boxCol, cellRow2, cellCol2, value))
                                            {
                                                exit=true;
                                            }
                                        }                                        
                                    }
                                }                                
                            }
                        }
                        if (!exit)
                        {
                            intersectionCount++;
                            for (cellCol2=0; cellCol2<dimension && exit; cellCol2++)
                            {
                                if (cellCol2!=cellCol)
                                {
                                    for (cellRow2=0; cellRow2<dimension; cellRow2++)
                                    {
                                        sudoku.setCandidate(boxRow, boxCol, cellRow2, cellCol2, value, false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }   
        return intersectionCount;
    }


    /**
     * Execute the pattern
     * @param sudoku Sudoku to solve
     * @return Number of cells found
     */
    public static int execute(Sudoku sudoku)
    {
        int count;

        executePointingRow(sudoku);
        executePointingColumn(sudoku);
        executeClaimingRow(sudoku);
        executeClaimingColumn(sudoku);
        count=sudoku.collapseCandidates();
        return count;
    }
}
