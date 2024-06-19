package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() {
        StdIn.setFile(fileName);

	/* Your code goes here */
    sortedCharFreqList = new ArrayList<>();
    char holding = 'a';
    double probOccHOLDING = 0;
    double txtLength = 0;
    int occurrenceTracker [] = new int [128];
    while (StdIn.hasNextChar()){
        holding = StdIn.readChar();
        occurrenceTracker [holding] += 1;
        txtLength++;
    }
    int count = 0;
        for (int k = 0; k < 128; k++){
            if (occurrenceTracker[k] != 0){
                count++;
            }
        }

    if (count != 1){
    int valueatPoint = 0;
    if (txtLength != 1){
    for (int i = 0; i < 128; i++){
        if (occurrenceTracker[i] != 0){
            valueatPoint = occurrenceTracker [i];
            probOccHOLDING = valueatPoint / 1.0 / txtLength;
            CharFreq addtoList = new CharFreq((char)i , probOccHOLDING);
            sortedCharFreqList.add(addtoList);; //check here
        } 
    }
}
    }

    int var = 0;
    if (count == 1){
        for (int i = 0; i < 128; i++){
            if (occurrenceTracker[i] != 0){
            var = i;
        }
    }
        if (var != 127){
            int iplus = var +1; 
            CharFreq addOccToList = new CharFreq((char)var, 1);
            CharFreq singleOccAddToList = new CharFreq((char)iplus , 0);
            sortedCharFreqList.add(singleOccAddToList);
            sortedCharFreqList.add(addOccToList);
            }
        if(var == 127){
            int zero = 0;
            CharFreq addOccToList = new CharFreq((char)var, 1);
            CharFreq singleOccAddToList = new CharFreq((char)zero , 0);
            sortedCharFreqList.add(singleOccAddToList);
            sortedCharFreqList.add(addOccToList);
        }
        
        
        }

        Collections.sort(sortedCharFreqList);
    }



    
    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() {

	/* Your code goes here */
    Queue <TreeNode> source = new Queue<TreeNode>();
    Queue <TreeNode> target = new Queue<TreeNode>();

    for (int k = 0; k < sortedCharFreqList.size(); k++){
        TreeNode tempTN = new TreeNode();
        tempTN.setData(sortedCharFreqList.get(k));
        source.enqueue(tempTN);
    } 
    
    TreeNode res1 = null;
    TreeNode res2 = null;
   
    /* 
    while (!source.isEmpty() == true && target.size() != 1){
        
        if(target.isEmpty() == true ){
            TreeNode fit1 = source.dequeue();
            TreeNode fit2 = source.dequeue();
            CharFreq tempCF = new CharFreq(null,fit1.getData().getProbOcc() + fit2.getData().getProbOcc() );
            TreeNode tempTN = new TreeNode(tempCF, fit1, fit2);
            target.enqueue(tempTN);
        } 
        
      
        if (target.size() >= 1 && source.size() > 1){

            if (source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()){
                res1 = source.dequeue();
            } else if (target.peek().getData().getProbOcc() < source.peek().getData().getProbOcc()){
                res1 = target.dequeue();
            }

            
            
            if (source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc()){
                res2 = source.dequeue();
            } else if (target.peek().getData().getProbOcc() < source.peek().getData().getProbOcc()) {
                res2 = target.dequeue();
            }
     

            CharFreq tempCF2 = new CharFreq(null, res1.getData().getProbOcc() + res2.getData().getProbOcc());
            TreeNode tempTN2 = new TreeNode(tempCF2, res1, res2);
            target.enqueue(tempTN2);

            //StdOut.print(res1.getData().getProbOcc() + " ");
            //StdOut.print(res1.getData().getProbOcc() + " ");
        }

        
        if (source.size() == 0){
            TreeNode s1 = target.dequeue();
            TreeNode s2 = target.dequeue();
            CharFreq temp = new CharFreq(null, s1.getData().getProbOcc() + s2.getData().getProbOcc());
            TreeNode final1 = new TreeNode(temp, s1, s2);
            target.enqueue(final1);
        }
       
        
        
        /*  
        if(source.size() == 1 && target.size() >= 1 ){
           
    }
    */
    if(target.isEmpty() == true ){
        TreeNode fit1 = source.dequeue();
        TreeNode fit2 = source.dequeue();
        CharFreq tempCF = new CharFreq(null,fit1.getData().getProbOcc() + fit2.getData().getProbOcc() );
        TreeNode tempTN = new TreeNode(tempCF, fit1, fit2);
        target.enqueue(tempTN);
    } 
    /* 
    while (!source.isEmpty() || target.size() > 1){
            if(source.isEmpty() == false && (target.isEmpty() || source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc())){
                res1 = source.dequeue();
                if(source.isEmpty() == false && (target.isEmpty() || source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc())){
                    res2 = source.dequeue();
                } else  {//if (source.isEmpty() == true && (target.isEmpty() == false || target.peek().getData().getProbOcc() < source.peek().getData().getProbOcc())) {
                    res2 = target.dequeue();
                }
            } else  {
                res1 = target.dequeue();
                if (target.isEmpty() == false && (source.isEmpty()|| target.peek().getData().getProbOcc() < source.peek().getData().getProbOcc())){
                    res2 = target.dequeue();
                } else {//if (target.isEmpty() && (source.isEmpty() == false || source.peek().getData().getProbOcc() <= target.peek().getData().getProbOcc())){
                    res2 = source.dequeue();
                }
            }
            CharFreq tempCF2 = new CharFreq(null, res1.getData().getProbOcc() + res2.getData().getProbOcc());
            TreeNode tempTN2 = new TreeNode(tempCF2, res1, res2);
            target.enqueue(tempTN2);
            
        
        //StdOut.print("s:" + source.size());
        //StdOut.print("t:" + target.size());
            */

            while (source.size() + target.size() > 1) {
                if (source.isEmpty() || (!target.isEmpty() && source.peek().getData().getProbOcc() > target.peek().getData().getProbOcc())) {
                    res1 = target.dequeue();
                } else {
                    res1 = source.dequeue();
                }
                if (source.isEmpty() || (!target.isEmpty() && source.peek().getData().getProbOcc() > target.peek().getData().getProbOcc())) {
                    res2 = target.dequeue();
                } else {
                    res2 = source.dequeue();
                }
                CharFreq tempCF2 = new CharFreq(null, res1.getData().getProbOcc() + res2.getData().getProbOcc());
                TreeNode tempTN2 = new TreeNode(tempCF2, res1, res2);
                target.enqueue(tempTN2);
            }        
    
            huffmanRoot =target.peek();

    }
    

     
       
   
    /* 
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() {
        
	/* Your code goes here */
    
    encodings = new String [128];
    String tempString = ""; 
    helper(encodings, huffmanRoot, tempString);

    }

    private void helper( String [] encodings, TreeNode PTR, String tempString){
        if (PTR == null) {
            return;
        }
        helper(encodings, PTR.getLeft(), tempString + "0");
        if (PTR.getLeft() == null && PTR.getRight() == null){
            Character temp = PTR.getData().getCharacter();
            encodings[temp] = tempString;
        }
        helper(encodings, PTR.getRight(), tempString + "1");
      
    }
   

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) {
        StdIn.setFile(fileName);

        String stringtoAdd = "";
     while(StdIn.hasNextChar() == true){
        Character hold = StdIn.readChar();
        int temp = (int)hold;
        stringtoAdd += encodings[temp];
     }

     writeBitString(encodedFile, stringtoAdd);
    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) {
        StdOut.setFile(decodedFile);

	/* Your code goes here */
    String main = readBitString(encodedFile);
    //StdOut.print(main + " ");
    TreeNode PTR = huffmanRoot;
    int stringlength = main.length();

    for (int i = 0; i < stringlength; i++){
        if(main.charAt(i) == '0'){
            PTR = PTR.getLeft();
            //StdOut.print("first");
            if (PTR.getData().getCharacter() != null){
                StdOut.print(PTR.getData().getCharacter());
                PTR = huffmanRoot;
            }
        } else if(main.charAt(i) == '1') {
            PTR = PTR.getRight();
            //StdOut.print("second");
            if (PTR.getData().getCharacter() != null){
                StdOut.print(PTR.getData().getCharacter());
                PTR = huffmanRoot;
            }
        }
    } 
    

    
 }
    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
