import java.util.*;



class Tree 
{
	private Stack current_stack;
	private String state;
	private char cc;
	private Aturan aturan;
	private Tree parent;
	private ArrayList<Tree> child = new ArrayList<Tree>();
	private int level;
	private int solved;

	public Tree(Stack s, char cc, Aturan aturan)
	{
		setStack(s);
		setCC(cc);
		setAturan(aturan);
		level = 0;
		solved = -1;
	}

	public Tree()
	{

	}

	public Tree(int i)
	{

	}

	public void setLevel(int i)
	{
		level = i;
	}

	public void setParent(Tree t)
	{
		parent = t;
	}

	public void setAturan(Aturan a)
	{
		aturan = a;
	}

	public void setCC(char c)
	{
		cc = c;
	}

	public void setStack(Stack s)
	{
		current_stack = s.clone();
	}

	public void setState(String s)
	{
		state = s;
	}

	public void setSolved(int i)
	{
		solved = i;
	}

	public Aturan getAturan()
	{
		return aturan;
	}

	public char getCC()
	{
		return cc;
	}

	public Stack getStack()
	{
		return current_stack;
	}

	public String getState()
	{
		return state;
	}

	public int getLevel()
	{
		return level;
	}

	public Tree getParent()
	{
		return parent;
	}

	public Tree getChild(int i)
	{
		return child.get(i);
	}

	public ArrayList<Tree> getChild()
	{
		return child;
	}

	public int solvedCon()
	{
		return solved;
	}

	public void addChild(Tree n)
	{
		n.setLevel(level+1);
		n.setParent(this);
		child.add(n);
	}

	public void addChild(char cc, Aturan aturan, int i)
	{
		Tree n = new Tree();
		n.setLevel(level+1);
		n.setParent(this);
		n.setAturan(aturan);
		n.setCC(cc);
		n.setState(state);
		n.setStack(current_stack);
		child.add(n);
	}

	public String toString()
	{
		return Character.toString(cc);
	} 

	public void print()
	{
		System.out.println(this);
		Iterator<Tree> iterator = child.iterator();
		while (iterator.hasNext()) 
		{
			System.out.print("-");
			iterator.next().print(1);
		}
	}

	public void print(int i)
	{
		System.out.println(this);
		Iterator<Tree> iterator = child.iterator();
		while (iterator.hasNext()) 
		{
			for(int in=0;in<i;++in)
				System.out.print("-");
			iterator.next().print(i+1);
		}
	}

	public static void main(String[] args)
	{
	
	}
}