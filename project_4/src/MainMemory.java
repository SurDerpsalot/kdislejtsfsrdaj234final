import java.nio.ByteBuffer;

//import java.io.PrintWriter;

public class MainMemory {
	//private array of blocks to act as memory
    //private long lastExecTime;   
    private int blockSize;
    private int blockFillSize;
    private byte[] buff;   
    private ByteBuffer inBuff;
    /**
     * constructor
     */
    public MainMemory(int size) {
        blockSize = size; //size in bytes of a block, and the buffer
        blockFillSize = 0;
    	buff = new byte[size];
        inBuff = ByteBuffer.wrap(buff);
  //      lastExecTime = -1;
    }
    /**
     * This retrieves the blockSize
     * @return the blockSize
     */
    public int getBlockSize() {
    	return blockSize;
    }
    /**
     * This sets the blockSize
     * @param i the integer that is replacing the current blockSize
     */
    public void setBlockSize(int i) {
    	blockSize = i;
    }
    /**
     * This retrieves the blockFillSize
     * @return the blockFillSize
     */
    public int getBlockFillSize() {
    	return blockFillSize;
    }
    /**
     * This sets the blockFillSize
     * @param i the integer that is replacing the current blockFillSize
     */
    public void setBlockFillSize(int i) {
    	blockFillSize = i;
    }
    /**
     * This retrieves the buff
     * @return the buff
     */
    public byte[] getBuff() {
    	return buff;
    }
    /**
     * This sets the buff
     * @param b the Byte Array that is replacing the current buff
     */
    public void setBuff(byte[] b) {
    	buff = b;
    }
    /**
     * This retrieves the inBuff
     * @return the inBuff
     */
    public ByteBuffer getInBuff() {
    	return inBuff;
    }
    /**
     * This sets the inBuff
     * @param b the ByteBuffer that is replacing the current inBuff
     */
    public void setInBuff(ByteBuffer b) {
    	inBuff = b;
    }
    
    
    /**
     * increments the filled space based by a block
     * @param fill is the number of characters added to main memory
     * @return the size in byte of the number of characters in mainmemory
     */
     public int addToTheFill(byte[] fillvalue) {
        if (blockFillSize + fillvalue.length > buff.length) { 
            //if the number of bytes will exceed the main memory's size, it will grow
            byte[] newbuff = new byte[buff.length + blockSize];  
            ByteBuffer newInBuff = ByteBuffer.wrap(newbuff);
            newInBuff.put(buff);
            
            //commented out lines beneath here killed.
            //inBuff.get(newInBuff.array(), 0, inBuff.capacity());
            buff = newbuff;
            inBuff = ByteBuffer.wrap(buff);
            //inBuff.limit(buff.length + blockSize);
            inBuff.position(newInBuff.position());
            inBuff.position(blockFillSize);
        }
      //inBuff.put(fillvalue, blockFillSize, fillvalue.length);
        inBuff.put(fillvalue);
        blockFillSize += fillvalue.length;
        return (blockFillSize - fillvalue.length); 
    }    
    /**
     * pushes a block of data into this buffer space
     * @param block the input data
     * @return true if the block was pushed correctly
     */
    /*public boolean pushBlock(byte[] block) {
        if(block.length != blockSize) {
            return false;
        }
        blockFillSize += blockSize;
        inBuff.clear();
        inBuff.put(block);
        return true;
    }
    */
    /**
     * pops an entire block of data
     * @return
     */
    /*public byte[] popBlock() {
        blockFillSize -= blockSize;
        return inBuff.array();
    }
    */
    /**
     * given a position in the buffer, returns a record
     * @param record
     * @return
     */
  /*  public long getRecord(int record) {
    	//records aren't always 8 bytes long
        return inBuff.asLongBuffer().get(record);
    }
    */
    public byte getRecordFlag(int recordIndex) {
        if (recordIndex >= 0 && recordIndex < blockFillSize) {
            return inBuff.get(recordIndex);
        }
        return (byte)-1;
    }
    
    public int getRecordSize(int record) {
        if (record >= 0 && record < blockFillSize) {
            return (int)(inBuff.get(record + 1) << 4 | inBuff.get(record + 2));
        }        
        return (int)-1;        
    }
    
    public String getRecordValue(int recordIndex) {
        int size = getRecordSize(recordIndex);
        if (recordIndex >= 0 && recordIndex < blockFillSize) {
        	byte[] b = new byte[size];
        	inBuff.position(recordIndex + 3);
            inBuff.get(b, 0, size);
            String out = new String(b);
            return out;
        }
        return "";
    }
    public String readEntry(int handle) {
        if(inBuff.get(handle) == 0) {
            return "";
        }
        else {
            int size = inBuff.get(handle + 1) << 4;
            size += inBuff.get(handle + 2);
            byte[] ret = new byte[size];
            inBuff.position(handle + 3);
            inBuff.get(ret, 0, ret.length);
            String out = new String(ret);
            return out;
        }
    }

}
