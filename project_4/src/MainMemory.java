import java.nio.ByteBuffer;
/**
 * 
 * @author maden
 * @version 1
 */
public class MainMemory {
    //private array of blocks to act as memory
//    private long lastExecTime;   
    private int blockSize;
    private int blockFillSize;
    private byte[] buff;   
    private ByteBuffer inBuff;
    /**
     * constructor
     * @param size is the initial size of the main memory block
     */
    public MainMemory(int size) {
        blockSize = size; //size in bytes of a block, and the buffer
        blockFillSize = 0;
        buff = new byte[size];
        inBuff = ByteBuffer.wrap(buff);
    }
    /**
     * adds an entry to the main memory and returns the corresponding handle
     * @param text the string to add to memory
     * @return the handle in memory
     */
    public int addEntryGetHandle(String text) {
        byte[] recordlength = new byte[3  +  text.length()];
        recordlength[0] = 1;
        recordlength[1] = (byte)((3  +  text.length()) >> 4);
        recordlength[2] = (byte)((3  +  text.length()) & 0xF);
        for (int i = 0; i < text.length(); i++ ) {
            recordlength[i  +  3] = (byte)text.charAt(i);
        }
        return addToTheFill(recordlength);
    }
    /**
     * increments the filled space based by a block
     * @param fillvalue is the number of characters added to main memory
     * @return the size in byte of the number of characters in mainmemory
     */
    public int addToTheFill(byte[] fillvalue) {
        if (blockFillSize + fillvalue.length > inBuff.capacity()) { 
            //if the number of bytes will exceed the main memory's size,
            //it will grow
            byte[] newbuff = new byte[buff.length + blockSize];  
            ByteBuffer newInBuff = ByteBuffer.wrap(newbuff);
            newInBuff.limit(newInBuff.capacity());
            newInBuff.position(0);
            inBuff.rewind();
            newInBuff.put(inBuff);
            buff = newbuff;
            inBuff = newInBuff;
        }
        inBuff.position(blockFillSize);
        inBuff.put(fillvalue);
        blockFillSize += fillvalue.length;
        return (blockFillSize - fillvalue.length); 
    }
    /**
     * given a position in the buffer, returns a record
     * @param record is the record to read
     * @return is the record value
     */
    public long getRecord(int record) {
        return inBuff.asLongBuffer().get(record);
    }
    /**
     * returns the record flag
     * @param record is the record number
     * @return the status bit
     */
    public byte getRecordFlag(int record) {
        if (record >= 0 && record < blockFillSize) {
            return inBuff.get(record);
        }
        return (byte)-1;
    }
    /**
     * reads the size of a record at a given index
     * @param record is the index for the start of the record
     * @return the size of the record
     */
    public int getRecordSize(int record) {
        if (record >= 0 && record < blockFillSize) {
            return (int)(inBuff.get(record + 1) << 4 
                    | inBuff.get(record + 2));
        }        
        return (int)-1;        
    }
    /**
     * gets the record's value
     * @param record the record's starting index
     * @return the string name of this record
     */
    public String getRecordValue(int record) {
        int size = getRecordSize(record);
        if (record >= 0 && record < blockFillSize) {
            byte[] b = new byte[size];
            inBuff.position(record + 3);
            inBuff.get(b, 0, size);
            String out = new String(b);
            return out;
        }
        return "";
    }
    /**
     * gets the record's value from a given handle
     * @param handle the index to read
     * @return the string for the record
     */
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
    /**
     * returns the byte array
     * @return is the byte[]
     */
    public byte[] getBuff() {
        // TODO Auto-generated method stub
        return buff;
    }
    /**
     * gets the bytebuffer
     * @return the bytebuffer
     */
    public ByteBuffer getInBuff() {
        // TODO Auto-generated method stub
        return inBuff;
    }
    /**
     * sets the size of the memory block
     * @param i is the size to assert
     */
    public void setBlockSize(int i) {
        // TODO Auto-generated method stub
        blockSize = i;
    }
    /**
     * sets the initial size of the fill
     * @param i is the value to input
     */
    public void setBlockFillSize(int i) {
        // TODO Auto-generated method stub
        blockFillSize = i;
    }
    /**
     *  resets the byte array to a new value
     * @param b the byte array to input
     */
    public void setBuff(byte[] b) {
        // TODO Auto-generated method stub
        buff = b;
    }
    /**
     * resets the inBuff bytebuffer
     * @param c the input
     */
    public void setInBuff(ByteBuffer c) {
        // TODO Auto-generated method stub
        inBuff = c;
    }
    /**
     * gets the current block size
     * @return the integer for the block size
     */
    public int getBlockSize() {
        // TODO Auto-generated method stub
        return blockSize;
    }
    /**
     * gets the size of the memory's filled area
     * @return the size of the block
     */
    public int getBlockFillSize() {
        // TODO Auto-generated method stub
        return blockFillSize;
    }
    /**
     * turns the record's flag to false
     * @param record is the index/handle
     */
    public void killRecord(int record) {
        buff[record] = 0;
    }

}
