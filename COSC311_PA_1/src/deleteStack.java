public class deleteStack {
    private int [ ] data;
    private int next;

    public deleteStack ()
    {     data=new int[10];
        next=0;
    }

    public deleteStack(int sz)
    {    data=new int[sz];
        next =0;
    }

    public boolean isEmpty( )
    {    return next==0;       }


    public boolean isFull( )
    { return next==data.length;     }

    public void push(int pushVal)
    {   data[ next++]=pushVal; }

    public int pop( )
    {   return( data[--next]);  }
}
