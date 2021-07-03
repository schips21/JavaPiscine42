import java.io.*;
import java.util.*;

public class Main {

    private static void makeDictionary(Set<String> words) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            for (String s : words) {
                writer.write(s + "\n");
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: you need to provide 2 files");
            System.exit(-1);
        }
        String file1 = args[0];
        String file2 = args[1];

        try{
            StringBuilder file1Buf = new StringBuilder();
            StringBuilder file2Buf = new StringBuilder();
            String buf = null;

            BufferedReader br = new BufferedReader(new FileReader(file1));
            while ((buf = br.readLine()) != null) {
                file1Buf.append(buf);
            }
            br.close();

            br = new BufferedReader(new FileReader(file2));
            while ((buf = br.readLine()) != null) {
                file2Buf.append(buf);
            }
            br.close();

            String[] allWords = (file1Buf + " " + file2Buf).split("\\s+");
            Set<String> words = new TreeSet<>(Arrays.asList(allWords));
            List<String> allWords1 = Arrays.asList(file1Buf.toString().split("\\s+"));
            List<String> allWords2 = Arrays.asList(file2Buf.toString().split("\\s+"));

            List<Integer> frequency1 = new ArrayList<>();
            List<Integer> frequency2 = new ArrayList<>();

            for (String s : words){
                frequency1.add(Collections.frequency(allWords1, s));
                frequency2.add(Collections.frequency(allWords2, s));
            }

            double num = 0.0;
            double denom = 0.0;
            for (int i = 0; i < frequency1.size(); i++){
                num += frequency1.get(i) * frequency2.get(i);
            }
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < frequency1.size(); i++){
                sum1 += frequency1.get(i) * frequency1.get(i);
                sum2 += frequency2.get(i) * frequency2.get(i);
            }
            denom = new Integer((int) (Math.sqrt(sum1) * Math.sqrt(sum2) * 10)).doubleValue() / 10;
            System.out.printf("Similarity = %.2f\n", Math.floor((int) num / denom * 100) / 100);
            makeDictionary(words);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
