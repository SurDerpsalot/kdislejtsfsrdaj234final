import java.nio.ByteBuffer;

//import java.io.PrintWriter;

public class mainMemory {
	//private array of blocks to act as memory
//    private long lastExecTime;   
    int blockSize;
    int blockFillSize;
    protected byte[] buff;   
    protected ByteBuffer inBuff;
    /**
     * constructor
     */
    public mainMemory(int size) {
        blockSize = size; //size in bytes of a block, and the buffer
        blockFillSize = 0;
    	buff = new byte[size];
        inBuff = ByteBuffer.wrap(buff);
  //      lastExecTime = -1;
    }
    /**
     * increments the filled space based by a block
     * @param fill is the number of characters added to main memory
     * @return the size in byte of the number of characters in mainmemory
     */
    public int addToTheFill(int fill) {
        blockFillSize += fill;
        if (blockFillSize > blockSize) { 
            //if the number of bytes will exceed the main memory's size, it will grow
            byte[] newbuff = new byte[buff.length + blockSize];  
            ByteBuffer newInBuff = ByteBuffer.wrap(newbuff);
            inBuff.get(newInBuff.array(), 0, inBuff.capacity());
            buff = newbuff;
            inBuff.limit(buff.length + blockSize);
        }
        return blockFillSize; 
    }
//    /**
//     * pushes a block of data into this buffer space
//     * @param block the input data
//     * @return true if the block was pushed correctly
//     */
//    public boolean pushBlock(byte[] block) {
//        if(block.length != blockSize) {
//            return false;
//        }
//        blockFillSize += blockSize;
//        inBuff.clear();
//        inBuff.put(block);
//        return true;
//    }
//    /**
//     * pops an entire block of data
//     * @return
//     */
//    public byte[] popBlock() {
//        blockFillSize -= blockSize;
//        return inBuff.array();
//    }
    /**
     * given a position in the buffer, returns a record
     * @param record
     * @return
     */
    public long getRecord(int record) {
        return inBuff.asLongBuffer().get(record);
    }
    
    public byte getRecordFlag(int record) {
        if (record >= 0 && record < blockFillSize) {
            return inBuff.get(record);
        }
        return (byte)-1;
    }
    
    public int getRecordSize(int record) {
        if (record >= 0 && record < blockFillSize) {
            return (int)(inBuff.get(record + 1) << 4 | inBuff.get(record + 2));
        }        
        return (int)-1;        
    }
    
    public String getRecordValue(int record, int length) {
        byte[] b = new byte[getRecordSize(record)];
        if (record >= 0 && record < blockFillSize) {
            inBuff.get(b, record + 3, getRecordSize(record));
            return b.toString();
        }
        return "";
    }

}
