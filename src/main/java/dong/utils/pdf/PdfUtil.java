package dong.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import dong.utils.FileUtil.FileUtil;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * pdf util
 *
 * @author: dongzhihua
 * @time: 2018/10/26 11:36:28
 */
public class PdfUtil {

    private static Logger logger = LoggerFactory.getLogger(PdfUtil.class);


    public static File htmlToPdfWithWater(File htmlFile, File out, File cssFile, String imgPath) {
        File temp = new File(out.getParent(), out.getName() + ".temp");
        temp = htmlToPdf(htmlFile, temp, cssFile);
        waterMark(temp, out, imgPath);//生成PDF并加水印
        return out;
    }

    public static File htmlToPdf(File inputFile, File outFile, File cssFile) {
        logger.info("解析html2pdf inputFile："+inputFile +" outFile:"+outFile);
        FileOutputStream fos = null;
        FileInputStream fileInputStream = null;
        try {
            FileUtil.initDir(outFile);
            fos = new FileOutputStream(outFile);
            Document document = new Document(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
            document.open();
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            logger.info("start print html2pdf fileInputStream:"+fileInputStream +" inputFile:"+inputFile);
            fileInputStream = new FileInputStream(inputFile);
            logger.info("end print html2pdf fileInputStream:"+fileInputStream +" inputFile:"+inputFile);
            worker.parseXHtml(pdfWriter, document, fileInputStream, null, new AsianFontProvider());
            document.close();
            logger.info("close print html2pdf fileInputStream:"+fileInputStream +" inputFile:"+inputFile);
            return outFile;
        } catch (Exception e) {
            String msg = "pdf文件生成失败";
            logger.error(msg);
            throw new RuntimeException(msg, e);
        } finally {
            logger.info("start关闭临时文件流");
            logger.info("fos close start");
            IOUtils.closeQuietly(fos);
            logger.info("fileInputStream close start");
            IOUtils.closeQuietly(fileInputStream);
        }
    }


    public static File waterMark(File inputFile, File outputFile, String imgPath) {
        FileOutputStream fos = null;
        PdfStamper stamper = null;
        PdfReader reader = null;
        try {
            logger.info("pdf加水印开始:" + inputFile.getAbsolutePath());
            reader = new PdfReader(inputFile.getAbsolutePath());
            File file = outputFile;
            FileUtil.initDir(file);
            fos = new FileOutputStream(outputFile);
            stamper = new PdfStamper(reader, fos);
            int total = reader.getNumberOfPages() + 1;

            PdfGState gsWater = new PdfGState();
            gsWater.setFillOpacity(0.1f);
            logger.info("water image path", imgPath);
            Image image = Image.getInstance(imgPath);
            image.scalePercent(50f);
            image.setRotationDegrees(45);//水印旋转角度
            Image title = Image.getInstance(imgPath);
            title.scalePercent(15f);//缩放比例

            for (int i = 1; i < total; i++) {
                PdfContentByte pdfContentWater = stamper.getOverContent(i);
                pdfContentWater.beginText();
                pdfContentWater.setGState(gsWater);

                image.setAbsolutePosition(30, 30);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(30, 230);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(30, 430);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(60, 600);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(250, 30);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(250, 200);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(280, 370);
                pdfContentWater.addImage(image);
                image.setAbsolutePosition(280, 570);
                pdfContentWater.addImage(image);
                pdfContentWater.endText();
            }
            logger.info("pdf加水印结束: " + outputFile.getAbsolutePath());
            return outputFile;
        } catch (Exception e) {
            String msg = "加pdf水印有异常";
            logger.error(msg, e);
            throw new RuntimeException(msg);
        } finally {
            close(stamper);
            close(reader);
            IOUtils.closeQuietly(fos);
        }
    }

    public static void close(PdfStamper stamper) {
        try {
            if (stamper != null) {
                stamper.close();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void close(PdfReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            logger.error("close error", reader);
        }
    }
    static class AsianFontProvider extends XMLWorkerFontProvider {

        public Font getFont(final String fontName, final String encoding,
                            final boolean embedded, final float size, final int style,
                            final BaseColor color) {
            try {
                File file = ResourceUtils.getFile("classpath:pdf/fonts/SIMFANG.TTF");
                BaseFont bf = BaseFont.createFont(file.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                Font font = new Font(bf, size, style, color);
                font.setColor(color);
                return font;
            } catch (Exception e) {
                throw new RuntimeException("pdf中文字体出错！",e);
            }
        }
    }
}
