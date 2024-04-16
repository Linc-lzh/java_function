package ap;

public class MCQ2015_21 {
    public static void whatsItDo(String str) {
        int len = str.length();
        if (len > 1) {
            String temp = str.substring(0, len - 1);
            System.out.println(temp);
            whatsItDo(temp);
        }
    }

    public static void main(String[] args) {
        whatsItDo("WATCH");
    }
}
