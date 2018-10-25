package dong.utils.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * freemarker 工具类
 * 模板默认在src/main/resources/template目录下，如果不在可以通过template转换
 *
 * @author: dongzhihua
 * @time: 2018/9/12 16:50:05
 */
public abstract class FreemarkerUtil {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);

    static Configuration cfg = null;
    static void init() {
        if (cfg != null) {
            return;
        }
        synchronized (new Object()) {
        }
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_0);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/template"));
        } catch (IOException e) {
            logger.error("static initializer 出错", e);
        }
    }

    /**
     * 根据src/main/resources/template下的模板输出
     * @author: dongzhihua
     * @time: 2018/9/17 17:41:26
     */
    public static void process(String ftlName, Writer out, Object dataModel) {
        try {
            if (cfg == null) {
                init();
            }
            Template template = cfg.getTemplate(ftlName);
            process(template,out, dataModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据给定模板输出
     * @author: dongzhihua
     * @time: 2018/9/17 17:42:13
     */
    public static void process(Template template, Writer out, Object dataModel) {
        try {
            template.process(dataModel, out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据模板或者名称输出
     * @Param templateInfo Template或者src/main/resources/template目录下的文件名称
     * @author: dongzhihua
     * @time: 2018/9/12 16:52:53
     */
    public static void process(Object templateInfo, File targetFile, Object dataModel) {
        FileWriter c = null;
        try {
            initDir(targetFile);
            c =  new FileWriter(targetFile);
            if(templateInfo instanceof Template) {
                process((Template) templateInfo, c, dataModel);
            } else {
                process(templateInfo.toString(), c, dataModel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(c);
        }
    }

    /**
     * 初始化文件
     * @author: dongzhihua
     * @time: 2018/9/28 10:57:33
     */
    public static void initDir(File file) {
        File dir = file;
        if (!file.isDirectory()) {
            dir = new File(file.getParent());
        }
        if (!dir.exists()) {
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }
}
