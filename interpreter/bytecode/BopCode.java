package bytecode;

public class BopCode extends ByteCode {
	
	private String operator;
	public void init(String... strings)
	{
		toString = strings[0];
		operator = strings[1];
	}
	
	public void execute(interpreter.VirtualMachine vm)
	{
		int operand1, operand2;
		operand2 = vm.pop();
		operand1 = vm.pop();
		
		switch (operator)
		{
		    case "+": vm.push(operand1 + operand2);
		        break;
		    case "-": vm.push(operand1 - operand2);
		        break;
		    case "/": vm.push(operand1 / operand2);
		    	break;
		    case "*":  vm.push(operand1 * operand2);
		        break;
		    case "==":  
		    	if(operand1 == operand2)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case "!=":  
		    	if(operand1 != operand2)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case "<":  
		    	if(operand1 < operand2)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case "<=":  
		    	if(operand1 <= operand2)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case ">":  
		    	if(operand1 > operand2)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case ">=":  
		    	if(operand1 >= operand2)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case "|":  
		    	if(operand1 > 0 ||  operand2 > 0)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		        break;
		    case "&":  
		    	if(operand1 > 0 &&  operand2 > 0)
		    		vm.push(1);
		    	else 
		    		vm.push(0);
		    	break;
		}
	}
}