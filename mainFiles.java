package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class mainFiles {
    static String compCode="";
    static String deCompCode="";

    public static String read(String path) throws IOException {
        String str="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
               str+=line+"\n";
            }

            br.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return str;
    }
    public static void writeDictionary(String s) throws IOException {
        FileWriter writer = new FileWriter("D:\\dictionary.TXT");
        writer.write(String.valueOf(Main.dictionary));
        writer.close();
    }
    public static void writeCompCode(String str) throws IOException {

        for(int i = 0; i<str.length(); i++){
            compCode+=Main.dictionary.get(str.charAt(i));
        }
        FileWriter writer = new FileWriter("D:\\compCode.TXT");
        writer.write(compCode);
        writer.close();
    }
    public static void writeDeCompCode()throws IOException{ //deCompression Code

        for (int i = 0; i<compCode.length(); i++){
            for (int j = i+1; j<=compCode.length(); j++) {
                String subI = compCode.substring(i, j);
                if (Main.dictionary.containsValue(subI)) {
                    for (Character key : Main.dictionary.keySet()) {
                        if (Main.dictionary.get(key).equals(subI)) {
                            deCompCode += key;
                            i += subI.length();
                            break;
                        }
                    }
                }
            }
        }
        FileWriter writer = new FileWriter("D:\\deCompCode.TXT");
        writer.write(deCompCode);
        writer.close();

    }
}
