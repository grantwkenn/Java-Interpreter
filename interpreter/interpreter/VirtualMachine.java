package interpreter;

import java.util.Stack;
import java.util.Scanner;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning;
    private boolean dump;

    protected VirtualMachine(Program program) {
        this.program = program;
    }
    
    public void executeProgram()
    {
    	pc = 0;
    	
    	runStack = new RunTimeStack();
    	returnAddrs = new Stack<Integer>();
    	isRunning = true;
    	
    	int args = 0;
    	while(isRunning)
    	{
    		bytecode.ByteCode code = program.getCode(pc);
    		code.execute(this);
    		runStack.dump();
    		if (!dump)
    		{
    			
    			if(code instanceof bytecode.ArgsCode)
    			{
    				args = ((bytecode.ArgsCode) code).getArgument();
    			}
    			
    			if(code instanceof bytecode.CallCode)
    			{
    				Stack<Integer> tempStack = new Stack<>();
    				String argumentString = "";
    				for(int i = 0; i<args; i++)
    				{
    					tempStack.push(runStack.pop());
    				}
    				while(!tempStack.isEmpty())
    				{		
    					argumentString += runStack.push(tempStack.pop());
    					if(!tempStack.isEmpty())
    						argumentString += ",";
    				}
    					
    				bytecode.CallCode cc = (bytecode.CallCode) code;
    				System.out.println(cc.toString() + "      "
    						+ cc.getBaseId() + "(" + argumentString + ")");
    			}
    			
    			else if(code instanceof bytecode.LitCode)
    			{
    				bytecode.LitCode lc = (bytecode.LitCode) code;
    				System.out.println(lc.toString() + "      int " + lc.getId());
    			}
    			
    			else if(code instanceof bytecode.LoadCode)
    			{
    				bytecode.LoadCode lc = (bytecode.LoadCode) code;
    				System.out.println(lc.toString() + "      <load " + lc.getId() + ">");
    			}
    			
    			else if(code instanceof bytecode.ReturnCode)
    				System.out.println(code.toString() + ": " + runStack.peek());
    			
    			else if(code instanceof bytecode.StoreCode)
    			{
    				bytecode.StoreCode sc = (bytecode.StoreCode) code;
    				System.out.println(sc.toString() + "      " + sc.getId() + "=" + runStack.peek());

    			}
    			runStack.dump();
    		}

    		pc++;
    	}
    }
    
    public void pushReturnAddress(int address)
    {
    	returnAddrs.push(address);
    }
    
    public int popReturnAddress()
    {
    	return returnAddrs.pop();
    }
    
    public void setDump(boolean dump)
    {
    	this.dump = dump;
    }
    
    public void setPc(int pc)
    {
    	int p = pc-1;
    	this.pc = p;
    }
    
    public void halt()
    {
    	isRunning = false;
    }
    
    public void push(int val)
    {
    	runStack.push(val);
    }
    
    public int pop()
    {
    	return runStack.pop();
    }
    
    public void returnCall()
    {
    	runStack.popFrame();
    }
    
    public void newFrameAt(int offset)
    {
    	runStack.newFrameAt(offset);
    }
    
    public void store(int offset)
    {
    	runStack.store(offset);
    }
    
    public void load(int offset)
    {
    	runStack.load(offset);
    }
    
    public void storeReturn()
    {
    	returnAddrs.push(pc+1);
    }
    

}
