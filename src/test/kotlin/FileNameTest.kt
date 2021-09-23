import org.apache.commons.io.FileUtils
import java.io.File

/**
 * 将目录 build 目录下的 flt 文件夹内容拷贝到 name 拆成包名的路径下，以便替换文件操作。
 */
fun main(args: Array<String>) {
    var name = "com.zkteam.livedata.bus1"

    name = name.replace(".", "/")

    val dirName = "/Users/wangqing/Desktop/Java/GenTemplate/build"

//    File(dirName, name)

    FileUtils.copyDirectory(File(dirName, "ftl"), File(dirName, name))

    println(name)
}