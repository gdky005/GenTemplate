
import bean.TestBean
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.io.FileWriter
import java.io.IOException

fun main(args: Array<String>) {
    logD("开始生成模板：")

    //===============生成项目相关内容 start ===============

    val gRootDirName = "ZKLiveDataBus1"
    val defaultPackageName = "com.zkteam.livedata.bus".replace(".", "/")
    val gAppPackageName = "com.zkteam.livedata.bus1".replace(".", "/")

    //===============生成项目相关内容 end ===============





    val proDir = System.getProperty("user.dir")
    val templateSourceDir = "/src/main/resources/"
    val encode = "UTF-8"
    val resourceFtl = "/test.html.ftl"
//    val resourceZip = "/templateZip.zip"
    val resourceZip = "/ZKLiveDataBus.zip"

//    val templateOutDir = "$proDir/out"
    val templateOutDir = "$proDir/build"
    val templateDestFtlDir = "$templateOutDir/ftl"
    val testFtl = templateSourceDir + resourceFtl //test.ftl 文件目录

    val name = FilenameUtils.getBaseName(testFtl)
    val distTestFile = File("$templateDestFtlDir/$resourceFtl")
    val destTestFile = File("$templateOutDir/$name")

    logD("根目录是: $proDir")
    try {
        val javaCls = Thread.currentThread().javaClass

        val testInput = javaCls.getResourceAsStream(resourceFtl)
        FileUtils.copyInputStreamToFile(testInput, distTestFile)

        val inputZip = javaCls.getResourceAsStream(resourceZip)
        FileUtils.copyInputStreamToFile(inputZip, File(templateOutDir, resourceZip))
    } catch (e: Exception) {
        e.printStackTrace()
    }

    try {
        val cfg = Configuration(Configuration.VERSION_2_3_22)
        cfg.setDirectoryForTemplateLoading(File(templateDestFtlDir))
        cfg.defaultEncoding = encode
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER

        val testBean = TestBean(
                "周杰伦",
                "我是你的老歌迷了")

        logD("模板文件地址：$templateDestFtlDir")
        val template = cfg.getTemplate(resourceFtl)
        val writer = FileWriter(destTestFile)
        template.process(testBean, writer)
        writer.close()

        logD("生成的文件：" + distTestFile.path)
        logD("开始解压文件中...")
        UnZip.unZip(File(templateOutDir, resourceZip), templateOutDir)
        File(templateOutDir, FilenameUtils.getBaseName(resourceZip)).renameTo(File(templateOutDir, gRootDirName))

        logD("解压文件 完成")

    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    //修改包名
    val gProRootPath = "$templateOutDir/$gRootDirName"
    val gProRootPathAppJava = "$gProRootPath/app/src/main/java"


    val defaultPackagePath = File("$gProRootPathAppJava/$defaultPackageName")
    FileUtils.copyDirectory(defaultPackagePath, File(gProRootPathAppJava, gAppPackageName))
    FileUtils.deleteDirectory(defaultPackagePath)



    logD("生成模板完成！")
}

private fun logD(msg: String) {
    println(msg)
}