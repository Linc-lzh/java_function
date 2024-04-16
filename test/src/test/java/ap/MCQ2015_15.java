package ap;

public class MCQ2015_15 {

    public static void showMe(int arg) {
        if (arg < 10) {
            showMe(arg + 1);
        } else {
            System.out.print(arg + " ");
        }
    }

    public static void main(String[] args) {
        showMe(0);
    }
}
