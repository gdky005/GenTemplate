
import bean.TestBean
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import java.io.File
import java.io.FileWriter
import java.io.IOException


//===============生成项目相关内容 start ===============

var libPackageName = "com.zkteam.live.event"

var gRootDirName = "ZKLiveEventBus"
const val defaultLibDirName = "ZKLiveData"
val defaultPackageName = "com.zkteam.livedata.bus".replace(".", "/")
val gLibPackageName = libPackageName.replace(".", "/")
val gAppPackageName = "$gLibPackageName/demo"

//===============生成项目相关内容 end ===============

var proDir = System.getProperty("user.dir")!!
const val ENCODE = "UTF-8"

val templateOutDir = "$proDir/build"
val templateDestFtlDir = "$templateOutDir/ftl"

//修改包名
val gProRootPathAppJava = "$templateOutDir/$gRootDirName/app/src/main/java"


const val resourceZip = "/ZKLiveDataBus.zip"
val resourceZipList = mutableListOf(resourceZip, "/ftl.zip")
val ftl2FileMap = mutableMapOf(
        "/README.md.ftl" to "/$gRootDirName/README.md",
        "/settings.gradle.ftl" to "/$gRootDirName/settings.gradle",
        "/app/build.gradle.ftl" to "/$gRootDirName/app/build.gradle",
        "/app/values/strings.xml.ftl" to "/$gRootDirName/app/src/main/res/values/strings.xml",
        "/package/MainActivity.kt.ftl" to "/$gRootDirName/app/src/main/java/$gAppPackageName/MainActivity.kt",
        "/app/AndroidManifest.xml.ftl" to "/$gRootDirName/app/src/main/AndroidManifest.xml",
        "/lib/AndroidManifest.xml.ftl" to "/$gRootDirName/$gRootDirName/src/main/AndroidManifest.xml"
)


fun main(args: Array<String>) {

    getAppArgs(args)

    logD("开始生成模板：")

    for (resourceZip in resourceZipList) {
        copyZip(resourceZip)
    }

    renameProDir()
    mvPackageDir()

    gLibPackageDir()

    try {
        val cfg = Configuration(Configuration.VERSION_2_3_22)
        cfg.setDirectoryForTemplateLoading(File(templateDestFtlDir))
        cfg.defaultEncoding = ENCODE
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER

        logD("模板文件地址：$templateDestFtlDir")


        val testBean = TestBean(
                "WQ",
                "模板工具",
                "$libPackageName.demo",
                libPackageName,
                gRootDirName)

        ftl2FileMap.entries.forEach {
            genFileForTemplate(cfg, testBean, it.key, templateOutDir + it.value)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    logD("生成模板完成！")

    logD("清理其他无用资源【暂未让本地设置，后续如果需要，可以修改保留】")
    clearOther()
}

// 这是清理资源的，但是目前好像不需要。因为有了更好的办法
fun clearOther() {
    logD("\n清理资源中...\n")
    try {
        for (item in resourceZipList) {
            val tempFile = File(templateOutDir, item)
            logD("\n清理文件 ${tempFile.path}中...\n")
            FileUtils.forceDelete(tempFile)
            val tempDir = File(templateOutDir, FilenameUtils.getBaseName(tempFile.path))
            logD("\n清理文件 ${tempDir.path}中...\n")
            FileUtils.deleteDirectory(tempDir)
        }
    } catch (e: Exception) {
        print(e)
    }
    logD("\n清理资源 已完成\n")
}

private fun getAppArgs(args: Array<String>) {
    val dirNameForArgs = System.getProperty("n")
    val packageNameForArgs = System.getProperty("p")
    val rootDirNameForArgs = System.getProperty("d")
    logD("-Dn --> 文件夹的名字  【n】是:$dirNameForArgs")
    logD("-Dp --> App 包名的名字【p】是:$packageNameForArgs")
    logD("-Dd --> 生成的目录地址【d】是:$rootDirNameForArgs")

    if (!dirNameForArgs.isNullOrEmpty())
        gRootDirName = dirNameForArgs

    if (!packageNameForArgs.isNullOrEmpty())
        libPackageName = packageNameForArgs

    if (!packageNameForArgs.isNullOrEmpty())
        proDir = packageNameForArgs

    for ((index, value) in args.withIndex()) {
        logD("$index --> $value")

        if (index == 0) {
            logD("jar 后直接参数 文件夹的名字是: $value")
            if (!value.isNullOrEmpty())
                gRootDirName = value
        }

        if (index == 1) {
            logD("jar 后直接参数 App 包名的名字是: $value")
            if (!value.isNullOrEmpty()) {
                libPackageName = value
            }
        }

        if (index == 2) {
            logD("jar 后直接参数 生成目录的 dir 是: $value")
            if (!value.isNullOrEmpty()) {
                proDir = value
            }
        }
    }

    logD("app 中 包名是:$libPackageName")
    logD("app 中 文件夹的路径是:$proDir")
    logD("app 中 文件夹的名字是::$gRootDirName")
}

fun gLibPackageDir() {
    val libJavaDir = "$templateOutDir/$gRootDirName/$gRootDirName/src/main/java/"
    FileUtils.deleteDirectory(File(libJavaDir))

    FileUtils.forceMkdir(File(libJavaDir, libPackageName))
}

private fun genFileForTemplate(cfg: Configuration, testBean: TestBean, resourceFtl: String, destFile: String) {
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

    val name = FilenameUtils.getBaseName(resourceZip)
    val fileSourceDir = File(templateOutDir, name)
    if (!name.equals(gRootDirName)) {
        fileSourceDir.renameTo(File(templateOutDir, gRootDirName))
        if (fileSourceDir.exists())
            FileUtils.deleteDirectory(fileSourceDir)
    }

    // 如果有临时文件夹，就删除
    val maxTempDir = File(templateOutDir, "__MACOSX")
    if (maxTempDir.exists())
        FileUtils.deleteDirectory(maxTempDir)

    if (!defaultLibDirName.equals(gRootDirName)) {
        // 修改 lib 的名字为 项目名
        val libFileSourceDir = File(templateOutDir, "$gRootDirName/$defaultLibDirName")
        libFileSourceDir.renameTo(File(templateOutDir, "$gRootDirName/$gRootDirName"))
        if (libFileSourceDir.exists())
            FileUtils.deleteDirectory(libFileSourceDir)
    }
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