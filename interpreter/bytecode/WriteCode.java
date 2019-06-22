package bytecode;

public class WriteCode extends NoArgumentCode {
	
	public void execute(interpreter.VirtualMachine vm)
	{
		int result = vm.pop();
		vm.push(result);
	    System.out.println(result);
	}
}