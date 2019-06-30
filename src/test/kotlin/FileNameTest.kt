import org.apache.commons.io.FileUtils
import java.io.File

fun main(args: Array<String>) {
    var name = "com.zkteam.livedata.bus1"

    name = name.replace(".", "/")

    val dirName = "/Users/WangQing/HaoWeiLai/GenTemplate/build"

//    File(dirName, name)

    FileUtils.copyDirectory(File(dirName, "ftl"), File(dirName, name))

    println(name)
}