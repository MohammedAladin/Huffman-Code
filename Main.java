package com.company;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;

public class Main {

    static HashMap<Character, String> dictionary = new HashMap<Character, String>();
    static HashMap<Character, Integer> charPriority = new HashMap<Character, Integer>();

    public static HashMap<Character, Integer> priority(String str){

        char[] strArray = str.toCharArray();
        for (char c : strArray) {
            if (charPriority.containsKey(c)) {
                charPriority.put(c, charPriority.get(c) + 1);
            } else {
                charPriority.put(c, 1);
            }
        }
        return charPriority;
    }
    public static void printDictionary(HuffmanNode root, String s) {

        if (root.left == null && root.right == null) {
            dictionary.put(root.c, s);
            return;
        }
        printDictionary(root.left, s + "0");
        printDictionary(root.right, s + "1");
    }

    public static void main(String[] args) throws IOException {

        String stringToRead = mainFiles.read("D:\\test.TXT");
        System.out.println(stringToRead);
        System.out.println(priority(mainFiles.read("D:\\test.TXT")));
        int len = stringToRead.length();

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(len, new MyComparator());

        for (Map.Entry<Character,Integer> entry : priority(stringToRead).entrySet()) {
            HuffmanNode hn = new HuffmanNode();

            hn.data = entry.getValue();
            hn.c= entry.getKey();
            hn.left = null;
            hn.right = null;
            q.add(hn);

        }
        HuffmanNode root = null;
        while (q.size() > 1) {

            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();

            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;

            q.add(f);
        }

        printDictionary(root, "");
        mainFiles.writeDictionary(stringToRead);
        mainFiles.writeCompCode(stringToRead);
        mainFiles.writeDeCompCode();

    }
}