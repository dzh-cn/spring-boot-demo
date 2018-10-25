package dong.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * excel导出工具类
 * 
 * @author dongzhihua
 * @time 2017年11月8日 上午11:49:39
 */
public abstract class ExportExcelUtil {

	static Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);

	/**
	 * 将数据导出至excel
	 * @author dongzhihua
	 * @time 2017年11月8日 下午2:45:20
	 * @param filePathName
	 * @param data 数据集合
	 * @param fieldsTile 二位数组，分别是表格title和字段key，例如 String[][]{{"姓名", "年龄"},{"name", "age"}}
	 */
	public static void exportExcelToXlsx(String filePathName,
			Collection<Map<String, String>> data, String[][] fieldsTile) {

		Assert.notEmpty(fieldsTile, "fieldsTile can not empty!");
		Assert.notEmpty(fieldsTile[0], "表格title不能为空");
		Assert.notEmpty(fieldsTile[1], "字段key不能为空");
		Assert.isTrue(fieldsTile[0].length == fieldsTile[1].length, "表格title与字段key数目不一致");
		Assert.notEmpty(data, "导出数据为空");
		Assert.hasText(filePathName, "文件路径不能为空");
		Assert.isTrue(filePathName.endsWith(".xlsx"), "文件后缀应为[.xlsx]");

		OutputStream outputStream = null;
		try {
			File file = new File(filePathName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			SXSSFWorkbook wb = new SXSSFWorkbook(500);
			Sheet sheet = wb.createSheet("sheet0");
			int rowCount = 0;
			Row rowData = sheet.createRow(rowCount++);
			// 写表头
			writeRow(rowData, fieldsTile[0]);
			// 写主数据
			String[] filedsName = fieldsTile[1];
			String[] valus = new String[filedsName.length]; // 值集合
			for (Map<String, String> mapData : data) {
				rowData = sheet.createRow(rowCount++);
				for (int i = 0; i < filedsName.length; i++) {
					valus[i] = mapData.get(filedsName[i]);
				}
				writeRow(rowData, valus);
			}
			outputStream = new FileOutputStream(file);
			wb.write(outputStream);
			wb.dispose();
			log.info("已导出文件至：", file.getAbsolutePath());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

	/**
	 * 写入行
	 * @author dongzhihua
	 * @time 2017年11月8日 下午12:21:26
	 * @param rowData
	 * @param valueArray
	 * void
	 */
	static void writeRow(Row rowData, String[] valueArray) {
		int i = 0;
		for(String value : valueArray) {
			Cell cell = rowData.createCell(i++);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(value);
		}
	}

	public static void main(String[] args) throws IOException {
		List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
		Map<String, String> map;
		for(int i=0; i<10; i++) {
			map = new HashMap<String, String>();
			datas.add(map);
			for(int j=0; j<3; j++) {
				map.put("B" + j, i + "xxxo" + j);
			}
		}
		String[][] fieldsTile = new String[][]{{"a1","a2","a3"},{"B0","B1","B2"}};
		System.out.println(datas);
		exportExcelToXlsx("E://export/xxo.xlsx", datas, fieldsTile);
	}
}
