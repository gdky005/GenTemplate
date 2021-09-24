/**
 * 单词工具类
 */
object WordUtils {

    /**
     * 单词分割：ExamDetail -> exam_detail
     */
    fun getLayoutName(text: String): String {
        return getLowerLineString(text)
    }

    /**
     * 单词分割：ExamDetail -> EXAM_DETAIL
     */
    fun getLayoutAllBigName(text: String): String {
        return getLowerLineString(text).toUpperCase()
    }

    /**
     * 单词分割：ExamDetail -> exam_detail
     */
    @JvmStatic
    fun getLowerLineString(text: String): String {
        val sb = StringBuilder()
        for (i in text.indices) {
            val c = text[i]
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append("_")
                }
                sb.append(Character.toLowerCase(c))
            } else {
                sb.append(c)
            }
        }
        return sb.toString()
    }

    /**
     * 首字母小写：ExamDetail -> examDetail
     */
    fun getVarName(text: String): String {
        return getFirstLetterLowerString(text)
    }

    /**
     * 首字母小写：ExamDetail -> examDetail
     */
    @JvmStatic
    fun getFirstLetterLowerString(text: String): String {
        val sb = StringBuilder()
        for (i in text.indices) {
            val c = text[i]
            if (i == 0) {
                sb.append(Character.toLowerCase(c))
            } else {
                sb.append(c)
            }
        }
        return sb.toString()
    }
}