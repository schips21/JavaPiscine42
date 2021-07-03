import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SignatureReader signatureReader = new SignatureReader();
        try{
            signatureReader.makeSignatures();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try{
            FileOutputStream outputStream = new FileOutputStream("result.txt");
            Scanner scanner = new Scanner(System.in);
            String file;
            while (!(file = scanner.nextLine()).equals("42")){
                try{
                    FileProcessor fileProcessor = new FileProcessor(file);
                    String fileContent = fileProcessor.process();
                    String sig = null;
                    if ((sig = fileProcessor.matches(fileContent, signatureReader.getSignatures())) != null) {
                        System.out.println("PROCESSED");
                        sig = sig.substring(0, sig.length() - 1);
                        sig += "\n";
                        outputStream.write(sig.getBytes());
                    }
                    else{
                        System.out.println("UNDEFINED");
//                        outputStream.write("UNDEFINED\n".getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
