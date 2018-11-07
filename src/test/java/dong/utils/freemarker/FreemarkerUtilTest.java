package dong.utils.freemarker;

import freemarker.template.Configuration;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FreemarkerUtilTest {

    @Test
    public void process() throws IOException {
        String path = "d://export/card/test";
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(path));
        Map map = new HashMap();
        map.put("a", "java");
        map.put("list", CollectionUtils.arrayToList(new String[]{"c"}));
        FreemarkerUtil.process(cfg.getTemplate("abc.ftl"), new OutputStreamWriter(System.out), map);
    }

    @Test
    public void process1() {
    }

    @Test
    public void process2() {
    }
}