package dong.utils.string;

import org.apache.commons.lang3.StringUtils;

/**
 * 掩码工具类
 */
public class CoverUtil {

    /**
     * 11位手机号码掩码
     *
     * @param phoneNum
     * @return
     */
    public static String coverPhoneNum(String phoneNum) {
        return cover(phoneNum, 3, 4, 11);
    }

    /**
     * 银行卡号掩码
     * @author: dongzhihua
     * @time: 2018/7/12 18:20:13
     */
    public static String coverBankCardNo(String bankCardNo) {
        return cover(bankCardNo, 6, 4, 16);
    }

    /**
     * 姓名掩码
     * @param name
     * @return
     */
    public static String coverName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (name.length() < 3) {
            return name.substring(0, 1) + "*";
        }
        return cover(name, 1, 1, 3);
    }

    /**
     * 获取掩码，用‘*’掩饰
     * @author: dongzhihua
     * @time: 2018/7/13 9:30:29
     * @param str
     * @param before 留前几位
     * @param after 留后几位
     * @param expectation 字符串预期长度，如果少于这个长度，按照比例做掩码
     * @return
     */
    public static String cover(String str, int before, int after, int expectation) {
        return cover(str, before, after, expectation, '*');
    }

    /**
     * 获取掩码
     * @author: dongzhihua
     * @time: 2018/7/13 9:30:29
     * @param str
     * @param before 留前几位
     * @param after 留后几位
     * @param expectation 字符串预期长度，如果少于这个长度，按照比例做掩码
     * @param pad 掩码，例如[*]
     * @return
     */
    public static String cover(String str, int before, int after, int expectation, Character pad) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        String beforeStr = getBeforeSize(str, before, expectation);
        String afterStr = getAfterSize(str, after, expectation);
        return StringUtils.rightPad(beforeStr, str.length() - afterStr.length(), pad) + afterStr;
    }

    /**
     * 象征性的获取字符串前size位，如果少于length个字符，则按照size/expectation的比例截取
     * @param str
     * @param size 需要获取的长度
     * @param expectation str预期长度
     * @return
     */
    public static String getBeforeSize(String str, int size, int expectation) {
        if (StringUtils.isBlank(str) || size < 1 || expectation < 1 || size > expectation) {
            return null;
        }
        int len = str.length();
        int end = len < expectation ? len * size / expectation : size;

        return StringUtils.substring(str, 0, end);
    }

    /**
     * 象征性的获取字符串后size位，如果少于length个字符，则按照size/expectation的比例截取
     * @param str
     * @param size 需要获取的长度
     * @param expectation str预期长度
     * @return
     */
    public static String getAfterSize(String str, int size, int expectation) {
        if (StringUtils.isBlank(str) || size < 1 || expectation < 1 || size > expectation) {
            return null;
        }
        int len = str.length();
        int start = len < expectation ? len * size / expectation : size;

        return StringUtils.substring(str, len - start, len);
    }

    public static void main(String[] args) {
        String bankCardNo = null;
        System.out.println(coverName(bankCardNo));
    }
}
