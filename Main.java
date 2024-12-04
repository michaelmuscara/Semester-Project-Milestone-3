import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String directoryPath = "C:\\Users\\mpack\\Documents\\Programming Workshop Lab\\Milestone 3\\";
        Article stopWords = new Article(directoryPath + "stopwords.txt");

        ArrayList<Article> baseballArticles = loadArticles(directoryPath + "baseball");
        ArrayList<Article> footballArticles = loadArticles(directoryPath + "football");
        ArrayList<Article> basketballArticles = loadArticles(directoryPath + "basketball");


        textualInterface();
        Scanner scanner = new Scanner(System.in);


    }


    public static ArrayList<Article> loadArticles(String directoryPath) throws IOException {
        File folder = new File(directoryPath);
        ArrayList<Article> articles = new ArrayList<>();

        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    articles.add(new Article(file.getPath()));
                }
            }
        } else {
            System.out.println("No files found in directory: " + directoryPath);
        }
        return articles;
    }

    public static void textualInterface() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String directoryPath = "C:\\Users\\mpack\\Documents\\Programming Workshop Lab\\Milestone 3\\";
        Article positive = new Article(directoryPath + "positive-words.txt");
        Article negative = new Article(directoryPath + "negative-words.txt");
        int choice;

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Create a Folder");
            System.out.println("2. Add a File");
            System.out.println("3. Analyze Articles");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Enter the name of the folder you want to create: ");
                    String folderName = scanner.nextLine();
                    Folder.createFolder(directoryPath, folderName);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Enter the name of the file you want to add: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Which folder would you like to add it to: ");
                    String folder = directoryPath + scanner.nextLine();
                    Folder.addFile(folder, fileName);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Enter the topic of articles you'd like to analyze: ");
                    String topic = scanner.nextLine();
                    ArrayList<Article> topicArticles = loadArticles(directoryPath + topic);
                    System.out.println();
                    processArticles(topicArticles, positive, negative);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    public static void processArticles(ArrayList<Article> articles, Article positive, Article negative) {
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);

            System.out.println("Article " + (i + 1) + ":");
            article.getAttitude(positive, negative);
        }
    }
}


    class Article {

        private File file;
        private Scanner reader;
        private ArrayList<String> data;

        public Article(String filePath) throws IOException {
            this.file = new File(filePath);
            this.reader = new Scanner(this.file);
            this.data = new ArrayList<>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                line = line.replaceAll("[^a-zA-Z]", " ");
                line = line.toLowerCase();
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        this.data.add(word);
                    }
                }
            }
            reader.close();
        }

        public ArrayList<String> getData() {
            return data;
        }

        public int getWordCount() {
            return this.data.size();
        }

        public ArrayList<String> removeWord(Article article) {

            for (int i = this.data.size() - 1; i >= 0; i--) {
                if (article.getData().contains(this.data.get(i))) {
                    this.data.remove(i);
                }
            }
            return this.data;
        }

        public void getWordFrequency() {

            ArrayList<String> uniqueWord = new ArrayList<>();
            ArrayList<Integer> frequency = new ArrayList<>();
            ArrayList<String> result = new ArrayList<>();


            for (String word : data) {
                int index = uniqueWord.indexOf(word);
                if (index != -1) {
                    frequency.set(index, frequency.get(index) + 1);
                } else {
                    uniqueWord.add(word);
                    frequency.add(1);
                }
            }

            FrequencySort s1 = new FrequencySort(uniqueWord, frequency);
            s1.sort();
        }

        public int getUniqueWordCount() {
            ArrayList<String> uniqueWord = new ArrayList<>();
            for (String word : data) {
                int index = uniqueWord.indexOf(word);
                if (index == -1) {
                    uniqueWord.add(word);
                }
            }

            return uniqueWord.size();
        }

        public void getMostRepeated(int num) {
            ArrayList<String> uniqueWord = new ArrayList<>();
            ArrayList<Integer> frequency = new ArrayList<>();

            for (String word : data) {
                int index = uniqueWord.indexOf(word);
                if (index != -1) {
                    frequency.set(index, frequency.get(index) + 1);
                } else {
                    uniqueWord.add(word);
                    frequency.add(1);
                }
            }

            for(int i = frequency.size() - 1; i>=0; i--) {
                if (frequency.get(i) < num) {
                    uniqueWord.remove(i);
                    frequency.remove(i);
                }
            }
            FrequencySort s1 = new FrequencySort(uniqueWord, frequency);
            s1.sort();
        }

        public void getAttitude(Article positive, Article negative) {
            int numPos = 0;
            int numNeg = 0;

            //counting positive and negative
            for (String word : data) {
                if (positive.getData().contains(word)) {
                    numPos++;
                }
                else if(negative.getData().contains(word)) {
                    numNeg++;
                }
            }

            System.out.println("Positive Count: " + numPos);
            System.out.println("Negative Count: " + numNeg);
            if(numPos > numNeg) {
                System.out.println("Overall Sentiment: Positive");
            }
            else if(numNeg > numPos) {
                System.out.println("Overall Sentiment: Negative");
            }
            else{
                System.out.println("Overall Sentiment: Neutral");
            }
        }

    }



    class FrequencySort {

        private ArrayList<String> unique;
        private ArrayList<Integer> freq;

        public FrequencySort(ArrayList<String> uniqueWords, ArrayList<Integer> frequency) {
            this.unique = uniqueWords;
            this.freq = frequency;
        }

        public void sort() {
            for (int i = 0; i < freq.size() - 1; i++) {
                for (int j = 0; j < freq.size() - i-1; j++) {
                    if (freq.get(j) < freq.get(j + 1)) {
                        int tempFreq = freq.get(j);
                        freq.set(j, freq.get(j + 1));
                        freq.set(j + 1, tempFreq);

                        String tempWord = unique.get(j);
                        unique.set(j, unique.get(j + 1));
                        unique.set(j + 1, tempWord);
                    }
                }
            }

            if(freq.isEmpty()) {
                System.out.println("No frequency found!");
            }
            else {
                for (int i = 0; i < freq.size(); i++) {
                    System.out.println(unique.get(i) + ": " + freq.get(i));
                }
            }

        }

    }

    class Folder {
        public static void createFolder(String directory, String folderName) {
            File folder = new File(directory, folderName);
            if (folder.exists()) {
                System.out.println("Folder already exists!");
            }
            else {
                if (folder.mkdir()) {
                    System.out.println("Folder created successfully!");
                }
                else {
                    System.out.println("Failed to create folder!");
                }
            }
        }

        public static void addFile(String folder, String fileName) {
            File file = new File(folder, fileName);

            try {
                if (file.createNewFile()) {
                    System.out.println("File created successfully!");
                }
                else {
                    System.out.println("File already exists!");
                }
            } catch (IOException e) {
                System.out.println("Failed to create file!");
            }

        }
    }



