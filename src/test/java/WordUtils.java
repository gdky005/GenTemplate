

/**
 * 单词工具类
 */
public class WordUtils {

    /**
     * 单词分割：ExamDetail -> exam_detail
     */
    public static String getLowerLineString(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append("_");
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 首字母小写：ExamDetail -> examDetail
     */
    public static String getFirstLetterLowerString(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
