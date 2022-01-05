import java.io.File
import kotlin.Throws
import java.lang.RuntimeException
import java.util.zip.ZipFile
import java.util.Enumeration
import java.util.zip.ZipEntry
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

/**
 * 参考地址是：https://www.cnblogs.com/zeng1994/p/8142862.html
 */
object UnZip {
    private const val BUFFER_SIZE = 4096

    /**
     * zip解压
     * @param srcFile        zip源文件
     * @param destDirPath      解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */
    @Throws(RuntimeException::class)
    fun unZip(srcFile: File, destDirPath: String) {
        val start = System.currentTimeMillis()
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw RuntimeException(srcFile.path + "所指文件不存在")
        }
        // 开始解压
        var zipFile: ZipFile? = null
        try {
            zipFile = ZipFile(srcFile)
            val entries: Enumeration<*> = zipFile.entries()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement() as ZipEntry
                println("解压" + entry.name)
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory) {
                    val dirPath = destDirPath + "/" + entry.name
                    val dir = File(dirPath)
                    dir.mkdirs()
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    val targetFile = File(destDirPath + "/" + entry.name)
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.parentFile.exists()) {
                        targetFile.parentFile.mkdirs()
                    }
                    targetFile.createNewFile()
                    // 将压缩文件内容写入到这个文件中
                    val `is` = zipFile.getInputStream(entry)
                    val fos = FileOutputStream(targetFile)
                    var len: Int
                    val buf = ByteArray(BUFFER_SIZE)
                    while (`is`.read(buf).also { len = it } != -1) {
                        fos.write(buf, 0, len)
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close()
                    `is`.close()
                }
            }
            val end = System.currentTimeMillis()
            println("解压完成，耗时：" + (end - start) + " ms")
        } catch (e: Exception) {
            throw RuntimeException("unzip error from ZipUtils", e)
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}