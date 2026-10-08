import java.io.*;
import java.util.*;

class Article {
    String title, author, date, content;

    public Article(String title, String author, String date, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public void displayInfo() {
        System.out.println("Title   : " + title);
        System.out.println("Author  : " + author);
        System.out.println("Date    : " + date);
        System.out.println("Length  : " + content.split("\\s+").length + " words");
    }
}

class NaiveSearch {

    public static int countOccurrences(String text, String keyword) {

        int n = text.length();
        int m = keyword.length();
        int count = 0;

        for (int i = 0; i <= n - m; i++) {

            int j;

            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != keyword.charAt(j)) {
                    break;
                }
            }

            if (j == m) {
                count++;
            }
        }

        return count;
    }
}

public class TextHackCorpus {

    private ArrayList<Article> corpus = new ArrayList<Article>();

    public void loadCorpus(String folderPath) throws IOException {

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Articles folder not found.");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null) {
            System.out.println("No text files found.");
            return;
        }

        for (File file : files) {

            if (file.isFile() && file.getName().endsWith(".txt")) {

                BufferedReader fileReader = new BufferedReader(new FileReader(file));

                String title = fileReader.readLine();
                String author = fileReader.readLine();
                String date = fileReader.readLine();

                StringBuilder content = new StringBuilder();
                String line;

                while ((line = fileReader.readLine()) != null) {
                    content.append(line).append(" ");
                }

                fileReader.close();

                corpus.add(new Article(title, author, date, content.toString()));
            }
        }
    }

    public void displayCorpus() {

        if (corpus.isEmpty()) {
            System.out.println("No articles loaded.");
            return;
        }

        for (int i = 0; i < corpus.size(); i++) {

            System.out.println("\nArticle " + (i + 1));
            corpus.get(i).displayInfo();
        }
    }

    public void keywordAnalytics(String keyword) {

        if (corpus.isEmpty()) {
            System.out.println("No articles loaded.");
            return;
        }

        System.out.println("\nKeyword Analysis for: " + keyword);

        for (int i = 0; i < corpus.size(); i++) {

            Article article = corpus.get(i);

            int count = NaiveSearch.countOccurrences(
                    article.content.toLowerCase(),
                    keyword.toLowerCase());

            System.out.println("Article " + (i + 1) + " (" +
                    article.title + ") : " + count + " occurrence(s)");
        }
    }

    public static void main(String[] args) {

        TextHackCorpus loader = new TextHackCorpus();

        try {

            loader.loadCorpus("articles");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.println("\n====== TextHack Menu ======");
                System.out.println("1. Display Corpus");
                System.out.println("2. Keyword Analytics");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                int choice = Integer.parseInt(br.readLine());

                switch (choice) {

                    case 1:
                        loader.displayCorpus();
                        break;

                    case 2:
                        System.out.print("Enter keyword: ");
                        String keyword = br.readLine();
                        loader.keywordAnalytics(keyword);
                        break;

                    case 3:
                        System.out.println("Exiting TextHack...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}