
import bean.TestBean
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files


//===============生成项目相关内容 start ===============

const val gNewName = "ExamDetail"
var gRootDirName = "TalCode/${gNewName.toLowerCase()}"
// 通用的文件前缀
const val gFilePrefix = "TalFileName"
val defaultPackageName = "com.zkteam.livedata.bus".replace(".", "/")
val gAppPackageName = "com.zkteam.livedata.bus1".replace(".", "/")

//===============生成项目相关内容 end ===============

val proDir = System.getProperty("user.dir")!!
const val ENCODE = "UTF-8"

val templateOutDir = "$proDir/build"
val templateDestFtlDir = "$templateOutDir/ftl"

//修改包名
val gProRootPathAppJava = "$templateOutDir/$gRootDirName/app/src/main/java"


const val resourceZip = "/ZKLiveDataBus.zip"
val resourceZipList = mutableListOf(resourceZip, "/ftl.zip")
val ftl2FileMap = mutableMapOf(
        "/README.md.ftl" to "/$gRootDirName/README.md",
        "/bean/TalFileNameDataBean.java.ftl" to "/$gRootDirName/bean/TalFileNameDataBean.java",
//        "/app/build.gradle.ftl" to "/$gRootDirName/app/build.gradle",
//        "/app/values/strings.xml.ftl" to "/$gRootDirName/app/src/main/res/values/strings.xml",
//        "/package/MainActivity.kt.ftl" to "/$gRootDirName/app/src/main/java/$gAppPackageName/MainActivity.kt",
//        "/app/AndroidManifest.xml.ftl" to "/$gRootDirName/app/src/main/AndroidManifest.xml"
)


fun main(args: Array<String>) {
    logD("开始生成模板：")

    for (resourceZip in resourceZipList) {
        copyZip(resourceZip)
    }

    renameProDir()
    mvPackageDir()

    try {
        val cfg = Configuration(Configuration.VERSION_2_3_22)
        cfg.setDirectoryForTemplateLoading(File(templateDestFtlDir))
        cfg.defaultEncoding = ENCODE
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER

        logD("模板文件地址：$templateDestFtlDir")

        val testBean = TestBean(gNewName,
                "模板工具",
                "com.zkteam.livedata.bus1",
                gRootDirName)

        ftl2FileMap.entries.forEach {
            var newFileName = it.value
            newFileName = newFileName.replace(gFilePrefix, gNewName)
            genFileForTemplate(cfg, testBean, it.key, templateOutDir + newFileName)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    logD("生成模板完成！")
}

private fun genFileForTemplate(cfg: Configuration, testBean: TestBean, resourceFtl: String, destFile: String) {

    File(destFile).parentFile.mkdirs()

    val template = cfg.getTemplate(resourceFtl)
    val writer = FileWriter(destFile)
    template.process(testBean, writer)
    writer.close()

    logD("生成的文件：$destFile")
}

private fun mvPackageDir() {
    // 修改 app 里面的包名路径
    val defaultPackagePath = File("$gProRootPathAppJava/$defaultPackageName")
    if (defaultPackagePath.exists()) {
        FileUtils.copyDirectory(defaultPackagePath, File(gProRootPathAppJava, gAppPackageName))
        FileUtils.deleteDirectory(defaultPackagePath)
    }
}

private fun renameProDir() {
    // 修改项目的 dir 名字
    val fileSourceDir = File(templateOutDir, FilenameUtils.getBaseName(resourceZip))
    fileSourceDir.renameTo(File(templateOutDir, gRootDirName))
    if (fileSourceDir.exists())
        FileUtils.deleteDirectory(fileSourceDir)

    // 如果有临时文件夹，就删除
    val maxTempDir = File(templateOutDir, "__MACOSX")
    if (maxTempDir.exists())
        FileUtils.deleteDirectory(maxTempDir)
}

private fun copyZip(resourceZip: String) {
    logD("\n\n\n")
    logD("根目录是: $proDir")

    val fileName = FilenameUtils.getBaseName(resourceZip)

    try {
        val javaCls = Thread.currentThread().javaClass

        val inputZip = javaCls.getResourceAsStream(resourceZip)
        FileUtils.copyInputStreamToFile(inputZip, File(templateOutDir, resourceZip))
    } catch (e: Exception) {
        e.printStackTrace()
    }

    logD("开始解压 $fileName 中...")
    UnZip.unZip(File(templateOutDir, resourceZip), templateOutDir)
    logD("解压  $fileName  完成")
    logD("\n\n\n")
}

private fun logD(msg: String) {
    println(msg)
}