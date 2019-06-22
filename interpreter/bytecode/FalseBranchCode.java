package bytecode;

public class FalseBranchCode extends AddressCode {
		
	public void execute(interpreter.VirtualMachine vm)
	{
		if(vm.pop() == 0)
			vm.setPc(address);
	}
}