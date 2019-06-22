package bytecode;

import java.util.Scanner;

public class ReadCode extends NoArgumentCode {
	
	public void execute(interpreter.VirtualMachine vm)
	{
		System.out.println("Enter Valid Input: ");
    	Scanner sc = new Scanner(System.in);
    		try {
                int i = sc.nextInt();
            	vm.push(i);
        	} catch(java.util.InputMismatchException ex) {
        		System.out.println("Invalid Input!");
        		vm.halt();
        	}
    	sc.close();
	}
}