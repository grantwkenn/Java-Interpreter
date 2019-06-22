package bytecode;

public abstract class SingleArgumentCode extends ByteCode{
	protected String argument;
	public void init(String...strings)
	{
		argument = strings[1];
	}
}
