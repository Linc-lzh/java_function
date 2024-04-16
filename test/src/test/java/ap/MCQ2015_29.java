package ap;

public class MCQ2015_29 {
    /**
     * Precondition: num >= 0
     */
    public static int what(int num) {
        if (num < 10) {
            return 1;
        } else {
            return 1 + what(num / 10);
        }
    }

    /*
        what(12345)
     */
}
