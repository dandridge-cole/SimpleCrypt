
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.BiConsumer;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13  {
    static Integer shift;
    //   Integer unShift;
    String inputFilename = "sonnet18.txt";
    String outputFilename = "sonnet18.enc";
    /*
    static InputStream input;
    {
        try {
            input = Files.newInputStream(Paths.get("sonet18.txt"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    File outputFile = new File("sonnet18.enc");
    static OutputStream output;
    {
        try {
            output = Files.newOutputStream(Paths.get("sonnet18.enc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        ROT13 rot13 = new ROT13();
        rot13.run();
        /*try {
            output.write(rot13.crypt(string).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void run(){
        String string = readFromFile();
        writeToFile(this.crypt(string));
    }

    ROT13(Character cs, Character cf) {
        shift=checkCipherInputs(cf,cs);
   //     if (!Character.isAlphabetic(cf)||!Character.isAlphabetic(cs))shift=13;
   //     else this.shift = Math.abs(cf-cs);
   //     this.unShift=26-this.shift;
    }

    ROT13() {
        this('a','n');
    }


    String crypt(String text) throws UnsupportedOperationException {
        StringBuilder crypted = new StringBuilder("");
        char[] theChars = text.toCharArray();
        for(char aChar:theChars)crypted.append(shiftChar(aChar, shift));
        return crypted.toString();
    }

    String encrypt(String text) {
        return crypt(text);
    }

    String decrypt(String text) {
        toggleDecrypt();
        text=crypt(text);
        toggleDecrypt();
        return text;
    }

    static String rotate(String s, Character c) {
        Integer rot = c-'A';
        rot=rot%s.length();
        if(rot==0)return s;
        String s1=s.substring(rot);
        String s2=s.substring(0,rot);
        return s1+s2;
    }


    char shiftChar(char toShift, Integer thisShift){
        if(!Character.isAlphabetic(toShift))return toShift;
        char reference='A';
        if(Character.isLowerCase(toShift)) reference='a';
        return (char)((toShift-reference+thisShift)%26+reference);
    }

    void toggleDecrypt(){
        shift=26-shift;
    }

    void writeToFile(String outputString){
        try{
            PrintWriter printWriter = new PrintWriter(this.outputFilename);
            printWriter.print(outputString);
            printWriter.close();
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
    }

    String readFromFile(){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.inputFilename));
            String line = br.readLine();
            while(line!=null){
                builder.append(line).append("\n");
                line=br.readLine();
            }
            br.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return builder.toString();
    }

    Integer checkCipherInputs(char a, char b){
        if (!Character.isAlphabetic(a)||!Character.isAlphabetic(b))return 13;
        else return Math.abs(a-b);
    }
}
