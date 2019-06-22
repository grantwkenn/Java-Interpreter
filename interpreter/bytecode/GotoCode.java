package bytecode;

public class GotoCode extends AddressCode {
	
	public void execute(interpreter.VirtualMachine vm)
	{
		vm.setPc(address);
	}
}