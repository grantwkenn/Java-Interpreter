package bytecode;
import java.util.StringTokenizer;

public abstract class AddressCode extends ByteCode {
	private String addressLabel; ///NEED WHERE? CAN REMOVE?
	protected int address;
	
	public String getAddressLabel() { return addressLabel; }
	public int getAddress() { return address; }
	public void setAddress(int addr) { address = addr; }
	
	public void init(String... strings)
	{
		toString = strings[0];
		addressLabel = strings[1];

	}
}
