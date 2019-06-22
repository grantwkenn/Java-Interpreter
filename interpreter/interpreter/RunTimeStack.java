package interpreter;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }
    
    public void dump()
    {
    	Stack<Integer> tempFramePointer = new Stack<>();
    	String dumpString = "";
    	int frame1 = 0;
    	int frame2 = 0;
    	
    	while(!framePointer.isEmpty())
    	{
    		tempFramePointer.push(framePointer.pop());
    	}
    	
    	if(!tempFramePointer.isEmpty())
    	{
    		frame1 = tempFramePointer.pop();
    		if (tempFramePointer.isEmpty())
    		{
    			if(!runTimeStack.isEmpty())
    				dumpString += "[" + runTimeStack.get(frame1) + "]";
    			framePointer.push(frame1);
    		}
    	}
    		
    	
    	while(!tempFramePointer.isEmpty())
    	{
    		frame2 = tempFramePointer.pop();
    		dumpString += "[";
    		for(int i=frame1; i<frame2; i++)
    		{
    			dumpString += runTimeStack.get(i);
				if(i < ((frame2)-1))
					dumpString += ", ";
    		}
    		dumpString += "] ";
    		framePointer.push(frame1);
    		frame1 = frame2;
    		if(tempFramePointer.isEmpty())
    		{
    			dumpString += "[";
    			for(int i=frame2; i<runTimeStack.size(); i++)
    			{
    				dumpString += runTimeStack.get(i);
    				if(i < ((runTimeStack.size())-1))
    					dumpString += ", ";
    			}
    			dumpString += "]";
    			framePointer.push(frame2);
    		}
    	}
    	if(dumpString != "")
    		System.out.println(dumpString);
    }
    
    public int peek()
    {
    	//In case of error state, do not allow peek() to throw exception
    	if(!runTimeStack.isEmpty())
    		return runTimeStack.get(runTimeStack.size()-1);
    	return -1;
    }
    
    public int pop()
    {
    	//In case of error state, do not allow pop() to throw exception

    	if(!runTimeStack.isEmpty() && !framePointer.isEmpty())
        	//Ensure no values are popped below the current frame line
    		if(framePointer.peek() <= (runTimeStack.size()-1))
    			return runTimeStack.remove(runTimeStack.size()-1);
    	return -1;
    }
    
    public int push(int i)
    {
    	runTimeStack.add(i);
    	return i;
    }
    
    public void newFrameAt(int offset)
    {    	
    	framePointer.push(runTimeStack.size()-offset);
    }
    
    public void popFrame()
    {
    	//In case of error state, do not allow popFrame() to throw exception
    	if(!framePointer.empty())
    	{
        	int returnValue = pop();
    		int frame = framePointer.pop();

    		for(int top = runTimeStack.size()-1; top >= frame; top--)
    		{
    			runTimeStack.remove(top);
    		}
    		runTimeStack.add(returnValue);
    	}
    }
    
    public int store(int offset)
    {    	
    	//In case of error state, do not allow store() to throw exception
    	if(framePointer.isEmpty())
    	{
        	runTimeStack.set(framePointer.peek()+offset, peek());
        	return pop();
    	}
    	return -1;
    }
    
    public int load(int offset)
    {
    	//In case of error state, do not allow load() to throw exception
    	if(!framePointer.isEmpty() && !runTimeStack.isEmpty())
    		return push(runTimeStack.get(framePointer.peek() + offset));
    	return -1;
    }
    
    public Integer push(Integer val)
    {
    	int i = val;
    	return push(i);
    }
}
