package assn02;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class JavaWarmUp {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numTrans = s.nextInt();
//        System.out.println("Number of Transactions: " + numTrans);
        String[] dates = new String[numTrans];
        String[] times = new String[numTrans];
        String[] categories = new String[numTrans];
        double[] prices = new double[numTrans];
        int[] quantity = new int[numTrans];
        double[] rating = new double[numTrans];
        int[] duration = new int[numTrans];
        s.nextLine();
        for (int i = 0; i < numTrans; i++) {
            String entry = s.nextLine();
            String[] entryList = entry.split(" ");
            dates[i] = entryList[0];
            times[i] = entryList[1];
            categories[i] = entryList[2];
            prices[i] = Double.valueOf(entryList[3]);
            quantity[i] = Integer.valueOf(entryList[4]);
            rating[i] = Double.valueOf(entryList[5]);
            duration[i] = Integer.valueOf(entryList[6]);
        }
        int maxIndex = getMaxPriceIndex(prices);
        int minIndex = getMinPriceIndex(prices);
        hpus(maxIndex, dates, times, categories, prices, rating);
        lpus(minIndex, dates, times, categories, prices, rating);
        avgCalc(categories, prices, rating, duration, quantity);
    }
    public static int maximum(double[] list) {
        int max = 0;
        for (int i = 1; i < list.length; i++)
            if (list[i] >= list[max])
                max = i;
        return max;
    }

    public static int minimum(double[] list) {
        int min = 0;
        for (int i = 1; i < list.length; i++)
            if (list[i] <= list[min])
                min = i;
        return min;
    }
    public static int getMaxPriceIndex(double[] prices) {
        int ind;
        ind = maximum(prices);
        return ind;
    }

    public static int getMinPriceIndex(double[] prices) {
        int ind;
        ind = minimum(prices);
        return ind;
    }
    public static void hpus(int index, String[] dates, String[] times, String[] categories, double[] prices, double[] rating) {
        System.out.println("Highest per unit sale:");
        System.out.println("\tWhen: " + dates[index] + " " + times[index]);
        System.out.println("\tCategory: " + categories[index]);
        System.out.println("\tPrice: " + String.format("%.2f", prices[index]));
        System.out.println("\tRating: " +  rating[index]);
    }

    public static void lpus(int index, String[] dates, String[] times, String[] categories, double[] prices, double[] rating) {
        System.out.println("Lowest per unit sale:");
        System.out.println("\tWhen: " + dates[index] + " " + times[index]);
        System.out.println("\tCategory: " + categories[index]);
        System.out.println("\tPrice: " + String.format("%.2f", prices[index]));
        System.out.println("\tRating: " +  rating[index]);
    }

    public static ArrayList<Integer> listIndicies(String[] categories, String category) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < categories.length; i++) {
            if (categories[i].equals(category)) {
                indices.add(i);
            }
        }
        return indices;
    }

    public static void avgCalc(String[] categories, double[] prices, double[] rating, int[] duration, int[] quantity) {
        String[] category = {"phone", "book", "jewelry"};
        ArrayList<Integer> books = listIndicies(categories, category[1]);
        double bookPriceSum = 0;
        int bookNums = 0;
        double bookRatings = 0;
        double bookDurations = 0;
        for (int i = 0; i < books.size(); i++) {
            bookPriceSum += (prices[books.get(i)] * quantity[books.get(i)]);
            bookNums += quantity[books.get(i)];
            bookRatings += rating[books.get(i)];
            bookDurations += duration[books.get(i)];
        }
        double bookAvg = bookPriceSum / bookNums;

        System.out.println("Averages by book");
        System.out.println("\tQuantity: " + bookNums);
        System.out.println("\tPrice: " + String.format("%.2f", bookAvg));
        System.out.println("\tRating: " + String.format("%.2f", (bookRatings / books.size())));
        System.out.println("\tDuration: " + String.format("%.2f", (bookDurations / books.size())));

        ArrayList<Integer> jewels = listIndicies(categories, category[2]);
        double jewelPriceSum = 0;
        int jewelNums = 0;
        double jewelRatings = 0;
        double jewelDurations = 0;
        for (int i = 0; i < jewels.size(); i++) {
            jewelPriceSum += (prices[jewels.get(i)] * quantity[jewels.get(i)]);
            jewelNums += quantity[jewels.get(i)];
            jewelRatings += rating[jewels.get(i)];
            jewelDurations += duration[jewels.get(i)];
        }
        double jewelAvg = jewelPriceSum / jewelNums;

        System.out.println("Averages by jewelry");
        System.out.println("\tQuantity: " + jewelNums);
        System.out.println("\tPrice: " + String.format("%.2f", jewelAvg));
        System.out.println("\tRating: " + String.format("%.2f", (jewelRatings / jewels.size())));
        System.out.println("\tDuration: " + String.format("%.2f", (jewelDurations / jewels.size())));

        ArrayList<Integer> phones = listIndicies(categories, category[0]);
        double phonePriceSum = 0;
        int phoneNums = 0;
        double phoneRatings = 0;
        double phoneDurations = 0;
        for (int i = 0; i < phones.size(); i++) {
            phonePriceSum += (prices[phones.get(i)] * quantity[phones.get(i)]);
            phoneNums += quantity[phones.get(i)];
            phoneRatings += rating[phones.get(i)];
            phoneDurations += duration[phones.get(i)];
        }
        double phoneAvg = phonePriceSum / phoneNums;

        System.out.println("Averages by phone");
        System.out.println("\tQuantity: " + phoneNums);
        System.out.println("\tPrice: " + String.format("%.2f", phoneAvg));
        System.out.println("\tRating: " + String.format("%.2f", (phoneRatings / phones.size())));
        System.out.println("\tDuration: " + String.format("%.2f", (phoneDurations / phones.size())));
    }
}
