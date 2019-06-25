import bean.TestBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        logD("开始生成模板：");


        String PRO_DIR = System.getProperty("user.dir");
        String SC_DIR =  PRO_DIR + "/out/";
        String TEMPLATE_DIR = "/src/main/resources/";
        String ENCODE = "UTF-8";
        String resourceFtl = "/test.html.ftl";
        String testFtl = TEMPLATE_DIR + resourceFtl;

        String name = getFileNameNoFTL(testFtl);
        File distFile = new File(PRO_DIR + "/" + name);
        logD("根目录是: " + PRO_DIR);

        try {
            InputStream is = Main.class.getResourceAsStream(resourceFtl);
            writeToLocal(PRO_DIR + "/ftl" + resourceFtl, is);

            testFtl = PRO_DIR + "/" + name;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            cfg.setDirectoryForTemplateLoading(new File(PRO_DIR + "/ftl"));
            cfg.setDefaultEncoding(ENCODE);
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


//            HashMap map = new HashMap();
//            map.put("name", "周杰伦");
//            map.put("message", "我是你的老歌迷了");

            TestBean testBean = new TestBean();
            testBean.name = "周杰伦";
            testBean.message = "我是你的老歌迷了";


            Thread.sleep(500);
            logD("模板文件地址：" + testFtl);
            File newFile = new File(PRO_DIR + "/ftl" + resourceFtl);
            Template template = cfg.getTemplate(resourceFtl);
            FileWriter writer = new FileWriter(distFile);
            template.process(testBean, writer);
            writer.close();

            logD("生成的文件：" + distFile.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logD("生成模板完成！");
    }

    /**
     * 根据文件名字，去除 ftl
     * @param testFtl   文件中字符串
     * @return  返回去除 ftl 的文件名
     */
    private static String getFileNameNoFTL(String testFtl) {
        return new File(testFtl).getName().replace(".ftl", "");
    }


    private static void logD(String msg) {
        System.out.println(msg);
    }

    /**
     * 将InputStream写入本地文件
     * @param destination 写入本地目录
     * @param input	输入流
     * @throws IOException
     */
    private static void writeToLocal(String destination, InputStream input)
            throws IOException {

        File file = new File(destination);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists())
            file.createNewFile();

        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}
