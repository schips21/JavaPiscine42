import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SignatureReader {
    private String signatureFile = "./signatures.txt";
    private HashMap<String, String> signatures;

    public SignatureReader() {
        signatures = new HashMap<>();
    }

    public String getSignatureFile() {
        return signatureFile;
    }

    public HashMap<String, String> getSignatures() {
        return signatures;
    }

    public ArrayList<String> signaturesArray() throws IOException {
        ArrayList<String> signArr = new ArrayList<String>();
        try (FileInputStream inputStream = new FileInputStream(this.signatureFile)){
            int readRes = 0;
            StringBuilder line = new StringBuilder();
            while((readRes = inputStream.read()) != -1){
                char symb = (char)readRes;
                if (symb == '\n'){
                    signArr.add(line.toString().toString());
                    line = new StringBuilder();
                    continue;
                }
                line.append(symb);
            }
            if (line.length() > 0){
                signArr.add(line.toString());
            }
        }
        return signArr;
    }

    private static void validateExt(String ext) throws IOException  {
        if (ext.charAt(ext.length() - 1) != ',') {
            throw new IOException("Incorrect signature");
        }
        char[] mainSig = ext.substring(ext.length() - 1).toCharArray();
        for (char ch : mainSig) {
            if ((ch == ' ') || (ch == '\t')) {
                throw new IOException("Incorrect signature");
            }
        }
    }

    private boolean validateSigElement(char[] sigElement) throws IOException {
        if (sigElement.length != 2) {
            throw new IOException("Incorrect signature");
        }
        char[] validSymbols = "0123456789ABCDEF".toCharArray();
        for (int i = 0; i < sigElement.length; i++) {
            boolean isIn = false;
            for (int j = 0; j < validSymbols.length; j++) {
                if (sigElement[i] == validSymbols[j]) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                return false;
            }
        }
        return true;
    }

    public void makeSignatures() throws IOException {
        ArrayList<String> signArr = signaturesArray();
        for (String s : signArr){
            String[] splitedLine = s.split(" ");
            validateExt(splitedLine[0]);
            ArrayList<String> signs = new ArrayList<String>();
            for (int i = 1; i < splitedLine.length; i++){
                char[] sigElement = splitedLine[i].toCharArray();
                if (sigElement.length != 2){
                    throw new IOException("Incorrect signature");
                }
                if (!validateSigElement(sigElement)) {
                    throw new IOException("Incorrect signature");
                }
                signs.add(String.valueOf(sigElement));
            }
            if (signatures.containsKey(splitedLine[0]) || signs.isEmpty()){
                throw new IOException("Incorrect signature");
            }
            //
            char a = (char) Integer.valueOf(signs.get(0), 16).intValue();
            String signsToPut = "";
            for (String s2 : signs){
                signsToPut += ((char) Integer.valueOf(s2, 16).intValue());
            }
            signatures.put(splitedLine[0], signsToPut);
        }
    }


}
