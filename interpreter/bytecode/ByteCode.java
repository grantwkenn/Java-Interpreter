package bytecode;

public abstract class ByteCode {
	

	public abstract void init(String... strings);
	public abstract void execute(interpreter.VirtualMachine vm);
	
	//save the command string for Dumping
	protected String toString; 
	public String toString() 
	{
		return toString;
	}
}