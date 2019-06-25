import bean.TestBean
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.io.*

fun main(args: Array<String>) {
    logD("开始生成模板：")

    val pro_dir = System.getProperty("user.dir")
    val SC_DIR = "$pro_dir/out/"
    val template_dir = "/src/main/resources/"
    val encode = "UTF-8"
    val resourceFtl = "/test.html.ftl"
    val templateDir = "$pro_dir/ftl"
    var testFtl = template_dir + resourceFtl
    var destFilePath = "$pro_dir/ftl$resourceFtl"

    val name = getFileNameNoFTL(testFtl)
    val distFile = File("$pro_dir/$name")
    logD("根目录是: $pro_dir")

    try {
        val input = Main::class.java.getResourceAsStream(resourceFtl)
        writeToLocal(destFilePath, input)

        testFtl = "$pro_dir/$name"
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        val cfg = Configuration(Configuration.VERSION_2_3_22)
        cfg.setDirectoryForTemplateLoading(File(templateDir))
        cfg.defaultEncoding = encode
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER

        val testBean = TestBean(
                "周杰伦",
                "我是你的老歌迷了")

        logD("模板文件地址：$testFtl")
        val template = cfg.getTemplate(resourceFtl)
        val writer = FileWriter(distFile)
        template.process(testBean, writer)
        writer.close()

        logD("生成的文件：" + distFile.path)

    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    logD("生成模板完成！")
}


/**
 * 根据文件名字，去除 ftl
 * @param testFtl   文件中字符串
 * @return  返回去除 ftl 的文件名
 */
private fun getFileNameNoFTL(testFtl: String): String {
    return File(testFtl).name.replace(".ftl", "")
}


private fun logD(msg: String) {
    println(msg)
}

/**
 * 将InputStream写入本地文件
 * @param destination 写入本地目录
 * @param input    输入流
 * @throws IOException
 */
private fun writeToLocal(destination: String, input: InputStream) {
    try {
        val file = File(destination)
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }

        if (!file.exists())
            file.createNewFile()

        val downloadFile = FileOutputStream(destination)

        someFunc(input, downloadFile)

        downloadFile.close()
        input.close()
    } catch (e: Exception) {
    }
}


/**
 * 把 InputStream 流 写入到 OutputStream 中。
 */
fun someFunc(input: InputStream, output: OutputStream) {
    try {
        var read: Int = -1
        input.use { inputStream ->
            output.use {
                while (inputStream.read().also { read = it } != -1) {
                    it.write(read)
                }
            }
        }
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}

