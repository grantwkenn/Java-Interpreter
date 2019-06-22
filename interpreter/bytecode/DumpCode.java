package bytecode;

public class DumpCode extends ByteCode {
	
	private boolean dumpOn;
	
	public void init(String... strings)
	{
		toString = strings[0];
		dumpOn = (strings[1] == "ON");
	}
	
	public void execute(interpreter.VirtualMachine vm)
	{
		vm.setDump(dumpOn);
	}
}