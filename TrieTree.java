package trietree;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mojtaba
 */
public class TrieTree {

    static ArrayList<String> wordList;
    static ArrayList<String> paragraphWord;
    static URL url;

    public static void main(String[] args) throws IOException {
        Scanner scan;
        scan = new Scanner(System.in);
        Trie t = new Trie();
        System.out.println("Trie Test\n");
        char ch;
        int count = 0;
        do {
            System.out.println("Select your Operation:\n");
            System.out.println("1. insert a word");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. read from a txt file and insert into tree");
            System.out.println("5. read a txt file and checking the word");
//            System.out.println("6. print the tree\n");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter string element to insert");
                    t.insert(scan.next());
                    break;
                case 2:
                    System.out.println("Enter string element to delete");
                    try {
                        t.remove(scan.next());
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " not found ");
                    }
                    break;
                case 3:
                    System.out.println("Enter string element to search");
                    System.out.println("Search result : " + t.search(scan.next()));
                    break;
                case 4:
                    url = TrieTree.class.getResource("wordsEn.txt");
                    wordList = new TxtFileReader().readFile(url.getPath());
                    count = 0;
                    System.out.println("Wating for adding in tree ...\n");
                    for (String s : wordList) {
                        t.insert(s);
                        System.out.println(s);
                        count++;
                    }
                    System.out.println("Done. " + count + " word(s) added into the trie tree.");
                    break;
                case 5:
                    url = TrieTree.class.getResource("new.txt");
                    paragraphWord = new TxtFileReader().readFile(url.getPath());
                    SpellCheck spellCheck = new SpellCheck(wordList);
                    int wrongCount = 0;
                    for (String s : paragraphWord) {
                        if (t.search(s) == false) {
                            spellCheck.wordSuggester(s);
                            wrongCount++;
                        }
                        if (wrongCount == 0) {
                            System.out.println("All words mached by my dictionary.");
                        }
                    }
                    break;
                case 6:
//                    t.print();
                    System.out.println("Wrong Entry \n ");
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}