package dong.demoboot.editor;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期转换
 *
 * @author: dongzhihua
 * @time: 2018/11/21 16:40:38
 */
public class MyDatePropertyEditor extends PropertyEditorSupport {
    public MyDatePropertyEditor(List<DateFormat> formats) {
        this.formats = formats;
    }

    public MyDatePropertyEditor(String[] patterns) {
        Assert.notNull(patterns, "patterns 不能为空！");
        this.formats = new ArrayList<>();
        for (String pattern : patterns) {
            formats.add(new SimpleDateFormat(pattern));
        }
    }

    public MyDatePropertyEditor() {
    }

    public void setFormats(List<DateFormat> formats) {
        this.formats = formats;
    }

    private List<DateFormat> formats;

    @Override
    public void setAsText(@Nullable String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            setValue(null);
        } else {
            setValue(this.parse(text));
        }
    }

    private Date parse(String text) {
        for (DateFormat format : formats) {
            try {
                return format.parse(text);
            } catch (ParseException e) {
            }
        }
        throw new IllegalArgumentException("Could not parse date: " + text);
    }
}
