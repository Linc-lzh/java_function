package ap;

/*
    大概意思
    实现 getHint 方法, 将 guess 字符串中的字符与 words 相比较, 返回一个提示字符串
    提示字符串由下面规则组成
    如果 i 处字符与 words i 处字符相同, 则使用该字符
    如果 i 处字符在 words 里存在, 但不是相同位置, 使用+字符
    如果 i 处字符在 words 里不存在, 使用*字符

    注意附录中未给出 String.chatAt方法, 因此改用 String.substring 来实现
 */
public class FreeResponse2015_2 {

    static class HiddenWord {
        private String words;

        public HiddenWord(String words) {
            this.words = words;
        }

        public String getHint(String guess) {
            String result = "";
            for (int i = 0; i < guess.length(); i++) {
                // 拿到 guess 串中每个字符
                String ch = guess.substring(i, i + 1);
                // 如果它与原始串中字符相同, 直接拼接
                if (ch.equals(words.substring(i, i + 1))) {
                    result += ch;
                }
                // 走到这，说明它与原始串中字符不同, 但若它存在于原始串中
                else if(words.indexOf(ch) != -1) {
                    result += "+";
                }
                // 走到这，说明它不存在于原始串中
                else {
                    result += "*";
                }
            }
            return result;
        }
    }
}
