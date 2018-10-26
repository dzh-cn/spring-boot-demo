package dong.utils.FileUtil;

import java.io.File;

/**
 * file util
 *
 * @author: dongzhihua
 * @time: 2018/10/26 11:45:13
 */
public class FileUtil {

    /**
     * 初始化目录
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
