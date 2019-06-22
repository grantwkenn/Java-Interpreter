
package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;



public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
    	Program program = new Program();
    	String line = null;
    	// while loop reads line by line until end of source code
    	try {
    		line = byteSource.readLine();
    	} catch (IOException ex) {
    		line = null;
    	}

    	while(line != null)
    	{
    		//parse this line into its elements
    		// first token is the bytecode literal string
        	String lineString = line;
    		StringTokenizer lineCode = new StringTokenizer(line, " ");
    		String code = lineCode.nextToken();
    		String[] arguments = new String[4];  
    		arguments[0] = lineString;
    		int index = 1;
    		while(lineCode.hasMoreTokens())
    		{
    			arguments[index] = lineCode.nextToken();
    			index ++;
    		}
    		String className = CodeTable.getClassName(code);
			try {
				Class c = Class.forName("bytecode."+className);
				bytecode.ByteCode bc = (bytecode.ByteCode)c.getDeclaredConstructor().newInstance();
				bc.init(arguments);
				program.addCode(bc);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			
			try {
	    		line = byteSource.readLine();
	    	} catch (IOException ex) {
	    		line = null;
	    	} 
    	}
    	
    	program.resolveAddrs();
    	return program;
    }
}
