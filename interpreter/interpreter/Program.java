package interpreter;

import java.util.ArrayList;

import bytecode.AddressCode;

public class Program {

    private ArrayList<bytecode.ByteCode> program;

    public Program() {
        program = new ArrayList<bytecode.ByteCode>();
    }

    protected bytecode.ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public int getSize() {
        return this.program.size();
    }
    
    public void addCode(bytecode.ByteCode b)
    {
    	program.add(b);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param program Program object that holds a list of ByteCodes
     */
    ///////MUST SET Condition for CALL where addressLabel could be function<<7>> OR just function
    public void resolveAddrs() {
    	int size = program.size();
    	for (int i =0; i< size; i++) //for each byteCode in the ArrayList
    	{
    		//if this bytecode is an addressCode but not a label code
    		if(program.get(i) instanceof bytecode.AddressCode)
    		{
    	    	for (int j =0; j< size; j++) //for each byteCode in the ArrayList
    	    	{
    	    		if(program.get(j) instanceof bytecode.LabelCode)
    	    		{
    	    			bytecode.AddressCode ad = (bytecode.AddressCode) program.get(i);
    	    			bytecode.LabelCode lab = (bytecode.LabelCode) program.get(j);
  			
    	    			if(ad.getAddressLabel().equals(lab.getAddressLabel()))
    	    		    	ad.setAddress(j);
    	    		}	
    	    	}
    		}
    	}
    }
}
