package dong.utils.pdf;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class PdfUtilTest {

    @Test
    public void htmlToPdfWithWater() {
    }

    @Test
    public void htmlToPdf() throws FileNotFoundException {
        File in = ResourceUtils.getFile("classpath:pdf/template/tem.html");
        File out = new File("D:/export/pdf/tem.pdf");
        PdfUtil.htmlToPdf(in, out, null);
    }

    @Test
    public void waterMark() throws FileNotFoundException {
        File pdf = new File("D:/export/pdf/tem.pdf");
        File outPdf = new File("D:/export/pdf/tem-b.pdf");
        String imgPath = ResourceUtils.getFile("classpath:pdf/fonts/background.png").getAbsolutePath();
        PdfUtil.waterMark(pdf, outPdf, imgPath);
    }
}