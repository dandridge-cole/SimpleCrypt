public class AltCipher extends ROT13 {

    static private Integer altShift;

    private AltCipher(Character cs, Character cf, Character cs2, Character cf2) {
        shift=checkCipherInputs(cf,cs);
        altShift = checkCipherInputs(cf2,cs2);
        super.inputFilename = "sonnet18.txt";
        super.outputFilename = "sonnet18alt.enc";
    }

    public static void main(String[] args) {
        AltCipher alt = new AltCipher('a','b','a','d');
        alt.run();
    }

    private void run(){
        String string = this.readFromFile();
        this.writeToFile(this.crypt(string));
        toggleDecrypt();
        super.inputFilename=super.outputFilename;
        super.outputFilename= "sonnet18alt.dcr";
        string = this.readFromFile();
        this.writeToFile(this.crypt(string));
    }

    @Override
    String crypt(String text) throws UnsupportedOperationException {
        StringBuilder crypted = new StringBuilder("");
        char[] theChars = text.toCharArray();
        for(char aChar:theChars) {
            if(aChar%2==0)crypted.append(shiftChar(aChar, shift));
            else crypted.append(shiftChar(aChar,altShift));
        }
        return crypted.toString();
    }

    @Override
    void toggleDecrypt(){
        Integer temp=altShift;
        altShift = 26-shift;
        shift=26-temp;
    }

    @Override
    Integer checkCipherInputs(char a, char b){
        if (!Character.isAlphabetic(a)||!Character.isAlphabetic(b))return 13;
        else {
            Integer x = Math.abs(Character.toLowerCase(a)-Character.toLowerCase(b));
            return x+(x+1)%2;
        }
    }

}
