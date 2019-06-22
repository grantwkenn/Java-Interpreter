package bytecode;

public class LoadCode extends IntArgumentCode {
	
	public void execute(interpreter.VirtualMachine vm)
	{
		vm.load(argument);
	}
}