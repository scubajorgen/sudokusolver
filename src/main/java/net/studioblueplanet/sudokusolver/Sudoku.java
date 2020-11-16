/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.studioblueplanet.sudokusolver;

/**
 * This class represents a Sudoku. It contains the field containing the values.
 * It contains for each field element a list of possible values.
 * Terms (from https://en.wikipedia.org/wiki/Glossary_of_Sudoku): 
 * dimension N  : the size of a box. A common sudoku has N=3
 * grid         : the sudoku grid N*N x N*N cells, N x N boxes
 * box          : part of the grid containing NxN cells
 * cell         : one single value
 * row          :
 * column       :
 * @author jorgen
 */
public class Sudoku
{

    public static final int     UNDEFINED = -1;
    protected int               dimension;
    protected int[][][][]       grid;
    protected boolean[][][][][] possibleValues;

    /**
     * Constructor
     *
     * @param dimension Dimension of the sudoku, usually 3
     */
    public Sudoku(int dimension)
    {
        this.dimension      = dimension;
        this.grid           = new int[dimension][dimension][dimension][dimension];
        this.possibleValues = new boolean[dimension][dimension][dimension][dimension][dimension * dimension];
        clear();
    }

    /**
     * Returns the dimension of the sudoku
     * @return The dimension
     */
    public int getDimension()
    {
        return dimension;
    }
    
    /**
     * Clears the grid
     */
    public void clear()
    {
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;

        for (boxRow = 0; boxRow < dimension; boxRow++)
        {
            for (boxCol = 0; boxCol < dimension; boxCol++)
            {
                for (cellRow = 0; cellRow < dimension; cellRow++)
                {
                    for (cellCol = 0; cellCol < dimension; cellCol++)
                    {
                        grid[boxRow][boxCol][cellRow][cellCol] = UNDEFINED;
                        for (value = 0; value < dimension * dimension; value++)
                        {
                            possibleValues[boxRow][boxCol][cellRow][cellCol][value] = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Fill sudoku based on the content passed.
     *
     * @param content Content as string. Use digits '1'-'9', use ' ' or '0' for
     * empty place. Other chars don't matter.
     */
    public void fill(String content)
    {
        int count;
        int i;
        int chr;
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int r;
        int c;

        clear();
        i = 0;
        count = 0;
        while (i < content.length())
        {
            chr = content.charAt(i);
            if (chr >= '1' && chr <= '9')
            {
                r = count / (dimension * dimension);
                c = count % (dimension * dimension);
                boxRow = r / dimension;
                cellRow = r % dimension;
                boxCol = c / dimension;
                cellCol = c % dimension;
                grid[boxRow][boxCol][cellRow][cellCol] = chr-'0';
                cellCol = count % dimension;
                count++;
            } else if (chr == '0' || chr == ' ')
            {
                count++;
            }
            i++;
        }
        fillCandidates();
        if (count != dimension * dimension * dimension * dimension)
        {
            System.err.println("Error in sudoku format");
        }
    }

    private void fillCandidates()
    {
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;

        for (boxRow = 0; boxRow < dimension; boxRow++)
        {
            for (boxCol = 0; boxCol < dimension; boxCol++)
            {
                for (cellRow = 0; cellRow < dimension; cellRow++)
                {
                    for (cellCol = 0; cellCol < dimension; cellCol++)
                    {
                        if (grid[boxRow][boxCol][cellRow][cellCol] != UNDEFINED)
                        {
                            for (value = 0; value < dimension * dimension; value++)
                            {
                                possibleValues[boxRow][boxCol][cellRow][cellCol][value] = false;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Fill in a digit in the sudoku
     *
     * @param boxRow  Box row number
     * @param boxCol  Box column number
     * @param cellRow Cell row in Cell
     * @param cellCol Cell column in cell
     * @param value   Value to enter 1-9
     */
    public void setField(int boxRow, int boxCol, int cellRow, int cellCol, int value)
    {
        grid[boxRow][boxCol][cellRow][cellCol] = value;
    }

    /**
     * Return the value in the sudoku at given location.
     *
     * @param boxRow  Cell row number
     * @param boxCol  Cell col number
     * @param cellRow Row in Cell
     * @param cellCol Column in cell
     * @return 1-9 or 0 if UNDEFINED
     */
    public int getElementValue(int boxRow, int boxCol, int cellRow, int cellCol)
    {
        return grid[boxRow][boxCol][cellRow][cellCol];
    }

    /**
     * Pencil in or out candidate
     *
     * @param boxRow  Box row number
     * @param boxCol  Box column number
     * @param cellRow Cell row in Cell
     * @param cellCol Cell column in cell
     * @param value   Value 1-9
     * @param isCandidate True to pencil in, false to pencil out
     */
    public void setCandidate(int boxRow, int boxCol, int cellRow, int cellCol, int value, boolean isCandidate)
    {
        possibleValues[boxRow][boxCol][cellRow][cellCol][value - 1] = isCandidate;
    }

    /**
     * Returns whether the indicated value is a possible value
     *
     * @param boxRow  Box row number
     * @param boxCol  Box column number
     * @param cellRow Cell row in Cell
     * @param cellCol Cell column in cell
     * @param value   Value 1-9
     * @return True if the indicated value is a possible value
     */
    public boolean isCandidate(int boxRow, int boxCol, int cellRow, int cellCol, int value)
    {
        return possibleValues[boxRow][boxCol][cellRow][cellCol][value - 1];
    }    
    
    /**
     * Returns whether the soduko has been solved
     *
     * @return True if solved, false if not
     */
    public boolean isSolved()
    {
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        boolean found;

        found = false;
        for (boxRow = 0; boxRow < dimension && !found; boxRow++)
        {
            for (boxCol = 0; boxCol < dimension && !found; boxCol++)
            {
                for (cellRow = 0; cellRow < dimension && !found; cellRow++)
                {
                    for (cellCol = 0; cellCol < dimension && !found; cellCol++)
                    {
                        if (grid[boxRow][boxCol][cellRow][cellCol] == UNDEFINED)
                        {
                            found = true;
                        }
                    }
                }
            }
        }
        return !found;
    }

    /**
     * Counts the filled in cells
     *
     * @return The number of filled in cells
     */
    public int countCellValues()
    {
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        int count;

        count=0;
        for (boxRow = 0; boxRow < dimension; boxRow++)
        {
            for (boxCol = 0; boxCol < dimension; boxCol++)
            {
                for (cellRow = 0; cellRow < dimension; cellRow++)
                {
                    for (cellCol = 0; cellCol < dimension; cellCol++)
                    {
                        if (grid[boxRow][boxCol][cellRow][cellCol] != UNDEFINED)
                        {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * Print sudoku to screen
     */
    public void dump()
    {
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;

        System.out.println(" -----------------------");
        for (boxRow = 0; boxRow < dimension; boxRow++)
        {
            for (cellRow = 0; cellRow < dimension; cellRow++)
            {
                System.out.print("| ");
                for (boxCol = 0; boxCol < dimension; boxCol++)
                {
                    for (cellCol = 0; cellCol < dimension; cellCol++)
                    {
                        if (grid[boxRow][boxCol][cellRow][cellCol] == UNDEFINED)
                        {
                            System.out.print("  ");
                        } else
                        {
                            System.out.print(String.format("%c ", grid[boxRow][boxCol][cellRow][cellCol] + '0'));
                        }
                    }
                    System.out.print("| ");
                }
                System.out.println();
            }
            System.out.println(" -----------------------");

        }
    }

    /**
     * Tests whether the box indicated by cellRow and cellCol contains
     * the indicated value
     * @param boxRow   Cell row
     * @param boxCol   Cell column
     * @param value     Value to look for
     * @return True if the cell contains the value
     */
    public boolean boxHasValue(int boxRow, int boxCol, int value)
    {
        boolean found;
        int cellRow;
        int cellCol;

        found = false;
        for (cellRow = 0; cellRow < dimension && !found; cellRow++)
        {
            for (cellCol = 0; cellCol < dimension && !found; cellCol++)
            {
                if (grid[boxRow][boxCol][cellRow][cellCol] == value)
                {
                    found=true;
                }
            }
        }
        return found;
    }
    
    /**
     * Indicates whether a row contains the value mentioned.
     * @param row    Row number
     * @param value  Value
     * @return 
     */
    public boolean rowHasValue(int row, int value)
    {
        boolean found;
        int     col;
        
        found=false;
        
        for (col=0;col<dimension*dimension && !found;col++)
        {
            if (grid[row/dimension][col/dimension][row%dimension][col%dimension]==value)
            {
                found=true;
            }
        }
        
        return found;
    }
    
    /**
     * Indicates whether a column contains the value mentioned.
     * @param col    Column number
     * @param value  Value
     * @return True if the column contains the indcated value
     */
    public boolean colHasValue(int col, int value)
    {
        boolean found;
        int     row;
        
        found=false;
        
        for (row=0; row<dimension*dimension && !found; row++)
        {
            if (grid[row/dimension][col/dimension][row%dimension][col%dimension]==value)
            {
                found=true;
            }
        }
        
        return found;
    }

    /**
     * Converts box and cell row/col to row/col
     * @param box  Box row/col number
     * @param cell Cell row/col number
     * @return The row/col number
     */
    public int boxCellToIndex(int box, int cell)
    {
        int returnValue;
        
        returnValue=-1;
        if (box>=0 && box<dimension && cell>=0 && cell<dimension)
        {
            returnValue=box*dimension+cell;
        }
        return returnValue;
    }
    
    /**
     * Converts row or column index to box row resp. column
     * @param index Row or column index
     * @return The box row or column value
     */
    public int indexToBox(int index)
    {
        int returnValue;
        
        returnValue=-1;
        if (index>=0 && index<dimension*dimension)
        {
            returnValue=index/dimension;
        }
        return returnValue;        
    }

    /**
     * Convers row or column index to cell row resp. column
     * @param index Row or column index
     * @return The cell row or column value
     */
    public int indexToCell(int index)
    {
        int returnValue;
        
        returnValue=-1;
        if (index>=0 && index<dimension*dimension)
        {
            returnValue=index%dimension;
        }
        return returnValue;        
    }    
    
    /**
     * If there is only one candidate, move it to the grid
     * The number of element values created
     */
    public int collapseCandidates()
    {
        int boxRow;
        int boxCol;
        int cellRow;
        int cellCol;
        int value;
        int theValue;
        int count;
        int found;

        found=0;
        for (boxRow = 0; boxRow < dimension; boxRow++)
        {
            for (cellRow = 0; cellRow < dimension; cellRow++)
            {
                for (boxCol = 0; boxCol < dimension; boxCol++)
                {
                    for (cellCol = 0; cellCol < dimension; cellCol++)
                    {
                        if (grid[boxRow][boxCol][cellRow][cellCol]==UNDEFINED)
                        {
                            count=0;
                            theValue=UNDEFINED;
                            for (value=1; value<=dimension*dimension; value++)
                            {
                                if(possibleValues[boxRow][boxCol][cellRow][cellCol][value-1])
                                {
                                    theValue=value;
                                    count++;
                                }
                            }
                            if (count==1)
                            {
                                possibleValues[boxRow][boxCol][cellRow][cellCol][theValue-1]=false;
                                grid[boxRow][boxCol][cellRow][cellCol]=theValue;
                                found++;
                            }
                        }
                    }
                }
            }
        }
        return found;
    }
}
