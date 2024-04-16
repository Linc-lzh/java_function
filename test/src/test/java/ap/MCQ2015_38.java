package ap;

import java.util.Arrays;

public class MCQ2015_38 {
    public static void main(String[] args) {
        String[] words = new String[]{"a", "b", "c", "d", "e"};
        String[] temp = new String[words.length];
        for (int k = 0; k <= words.length / 2; k++)
        {
            temp[k] = words[words.length - k - 1];
            temp[words.length - k - 1] = words[k];
        }
        System.out.println(Arrays.toString(temp));
    }

}
