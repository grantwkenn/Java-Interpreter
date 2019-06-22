package bytecode;

public class LitCode extends IntArgumentCode {
	
	public void execute(interpreter.VirtualMachine vm)
	{
		vm.push(argument);
	}
}