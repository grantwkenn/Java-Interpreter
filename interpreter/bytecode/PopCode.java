package bytecode;

public class PopCode extends IntArgumentCode {
	
	public void execute(interpreter.VirtualMachine vm)
	{
		for(int i=0; i<argument; i++)
			vm.pop();
	}
}