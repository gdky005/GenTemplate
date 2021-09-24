/**
 * 测试单词的类
 */
public class TestWord {

    public static void main(String[] args) {
        String text = "ExamDetail";

        System.out.println("原始：" + text);

        System.out.println("转小写加下划线：" + WordUtils.getLowerLineString(text));
        System.out.println("首字母小写：" + WordUtils.getFirstLetterLowerString(text));
    }
}
