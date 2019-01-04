public class Ship
{
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int row=UNSET;
    private int col=UNSET;
    private int length=UNSET;
    private int direction=UNSET;
    
    public Ship(int length)
    {
        this.length=length;
    }
    
    public boolean isLocationSet()
    {
        return row!=UNSET;
    }
    
    public boolean isDirectionSet()
    {
        return direction!=UNSET;
    }
    
    public void setLocation(int row, int col)
    {
        this.row=row;
        this.col=col;
    }
    
    public void setDirection(int direction)
    {
        this.direction=direction;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getDirection()
    {
        return direction;
    }
    
    private String directionToString()
    {
        if (direction==UNSET)
            return "unset direction";
        else if(direction==HORIZONTAL)
            return "horizontal";
        else if(direction==VERTICAL)
            return "vertical";
        else
            return "error";
    }
    
    private String locationToString()
    {
        if (row==UNSET)
            return "(unset location)";
        return "("+row+", "+col+")";
    }
    
    public String toString()
    {
        return directionToString() + " ship of length "+length+" at "+ locationToString();
    }
}
