

/**
 * Created by root on 27/03/16.
 */
public class Stack {
    private int MAKS;
    private char[] data;
    private int top;

    public Stack(int N){
        MAKS = N;
        data = new char[N];
        top = -1;
    }

    public boolean isFull(){
        return top == MAKS - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public char getTop(){
        return data[top];
    }
//==========================================================
    public int getMaks()
    {
        return MAKS;
    }

    public int getTopIdx()
    {
        return top;
    }

    public char[] getData()
    {
        return data;
    }

    public void setMaks(int i)
    {
        MAKS = i;
    }

    public void setData(char[] other)
    {
        for(int i=0; i<MAKS; ++i)
        {
            data[i] = other[i];
        }
    }

    public void setTops(int i)
    {
        top = i;
    }


    public Stack clone()
    {
        Stack s = new Stack(getMaks());
        s.setData(getData());
        s.setTops(getTopIdx());
        return s;
    }

//==========================================================

    public int pop(){
        if(isEmpty())
            return -1;

        return data[top--];
    }

    public void push(char x){
        if(!isFull()){
            data[++top] = x;
        }
    }

    public String ambilStack(){
        String S = "";
        for(int i = 0; i <=  top; i++)
            S += data[i];

        return S;
    }


    public static void main(String[] args)
    {
        Stack s = new Stack(10);
        s.push('a');
        s.push('b');
        s.push('c');
        s.push('d');
        s.push('e');
        s.push('f');
        s.push('g');
        s.push('h');
        s.push('i');
        s.push('j');
        s.push('k');

        System.out.println(s.ambilStack());
    }
}
