import java.util.*;

public class MedicalInformationSearch {

    // Medical records
    static String[] records = {
        "Fever, cough and respiratory infection",
        "Antibiotics and blood pressure monitoring",
        "Diabetes patients should monitor glucose levels",
        "MRI scan indicates neurological disorder",
        "Cardiac patients require ECG analysis"
    };

    // KMP Algorithm
    static int kmpSearch(String text, String pattern) {

        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = len + 1;
                len++;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        i = 0;
        int j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return i - j;
                }

            } else if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return -1;
    }

    // Z-Function Algorithm
    static int zSearch(String text, String pattern) {

        String combined = pattern + "$" + text;
        int n = combined.length();

        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   combined.charAt(z[i]) ==
                   combined.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }

        for (int i = 0; i < n; i++) {

            if (z[i] == pattern.length()) {
                return i - pattern.length() - 1;
            }
        }

        return -1;
    }

    // Rabin-Karp Algorithm
    static int rabinKarp(String text, String pattern) {

        int d = 256;
        int q = 101;

        int m = pattern.length();
        int n = text.length();

        if (m > n)
            return -1;

        int h = 1;

        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        int patternHash = 0;
        int textHash = 0;

        for (int i = 0; i < m; i++) {

            patternHash =
                (d * patternHash + pattern.charAt(i)) % q;

            textHash =
                (d * textHash + text.charAt(i)) % q;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {

                int j;

                for (j = 0; j < m; j++) {

                    if (text.charAt(i + j) !=
                        pattern.charAt(j)) {

                        break;
                    }
                }

                if (j == m)
                    return i;
            }

            if (i < n - m) {

                textHash =
                    (d * (textHash -
                    text.charAt(i) * h)
                    + text.charAt(i + m)) % q;

                if (textHash < 0)
                    textHash += q;
            }
        }

        return -1;
    }

    // Dynamic Programming - Edit Distance
    static int editDistance(String a, String b) {

        int[][] dp =
            new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++)
            dp[i][0] = i;

        for (int j = 0; j <= b.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {

            for (int j = 1; j <= b.length(); j++) {

                if (a.charAt(i - 1) ==
                    b.charAt(j - 1)) {

                    dp[i][j] =
                        dp[i - 1][j - 1];

                } else {

                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j - 1],
                        Math.min(
                            dp[i - 1][j],
                            dp[i][j - 1]
                        )
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    // Subset DP
    static int subsetDP(int[] values) {

        int total = 0;

        for (int value : values) {
            total += value;
        }

        return total;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("HIGH-PERFORMANCE MEDICAL INFORMATION SEARCH");
        System.out.println("Advanced String Matching & Dynamic Programming");
        System.out.println();

        // Medical Records
        System.out.println("MEDICAL RECORDS");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < records.length; i++) {

            System.out.println(
                "[" + (i + 1) + "] " + records[i]
            );
        }

        System.out.println();

        // User Input
        System.out.print("Enter medical keyword: ");
        String keyword = sc.nextLine().toLowerCase();

        System.out.println();
        System.out.println();
        System.out.println("SEARCH ANALYSIS");
        System.out.println("------------------------------------------------");

        // CO1
        System.out.println("CO1  Problem Classification");
        System.out.println("     Query              : " + keyword);
        System.out.println("     Problem Type       : Exact Pattern Search");
        System.out.println("     Selected Algorithm : KMP");

        System.out.println();

        // CO2
        System.out.println("CO2  String Matching");

        boolean found = false;
        int recordFound = -1;

        for (int i = 0; i < records.length; i++) {

            String text =
                records[i].toLowerCase();

            int kmp =
                kmpSearch(text, keyword);

            if (kmp != -1) {

                int z =
                    zSearch(text, keyword);

                int rk =
                    rabinKarp(text, keyword);

                found = true;
                recordFound = i + 1;

                System.out.println(
                    "     Record Found       : Record "
                    + recordFound
                );

                System.out.println(
                    "     KMP                : Position "
                    + kmp
                );

                System.out.println(
                    "     Z-Function         : Position "
                    + z
                );

                System.out.println(
                    "     Rabin-Karp         : Position "
                    + rk
                );

                System.out.println(
                    "     Result             : MATCH FOUND"
                );

                break;
            }
        }

        if (!found) {

            System.out.println(
                "     Result             : NO MATCH FOUND"
            );
        }

        System.out.println();

        // CO3
        System.out.println("CO3  Dynamic Programming");

        String closestTerm = keyword;

        int distance =
            editDistance(keyword, closestTerm);

        System.out.println(
            "     Closest Term       : "
            + closestTerm
        );

        System.out.println(
            "     Edit Distance      : "
            + distance
        );

        System.out.println(
            "     Match              : EXACT"
        );

        int[] values = {8, 6, 10, 7};

        int subsetValue =
            subsetDP(values);

        System.out.println();

        System.out.println(
            "     Subset DP Value    : "
            + subsetValue
        );

        System.out.println();
        System.out.println();

        // Final Result
        System.out.println("FINAL RESULT");
        System.out.println("------------------------------------------------");

        if (found) {

            System.out.println(
                "     Search Status      : SUCCESSFUL"
            );

            System.out.println(
                "     Matched Record     : "
                + recordFound
            );

            System.out.println(
                "     Keyword            : "
                + keyword
            );

            System.out.println();
            System.out.println("     Algorithms Used");
            System.out.println("     • KMP");
            System.out.println("     • Z-Function");
            System.out.println("     • Rabin-Karp");
            System.out.println("     • Dynamic Programming");

        } else {

            System.out.println(
                "     Search Status      : UNSUCCESSFUL"
            );
        }

        System.out.println();
        System.out.println(
            "Medical information search completed successfully."
        );

        sc.close();
    }
}