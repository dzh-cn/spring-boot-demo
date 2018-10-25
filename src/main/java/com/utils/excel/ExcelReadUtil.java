package com.utils.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格导入工具类
 * 
 * @author dongzhihua
 * 
 */
public class ExcelReadUtil {

	/**
	 * 从excel中读取数据
	 * 
	 * @author dongzhihua
	 * @time 2017年11月9日 下午3:04:37
	 * @param file
	 * @param fieldNames
	 * @return List<Map<String,String>>
	 */
	public static List<Map<String, String>> readExcel(File file,
			String[] fieldNames) {
		Assert.notNull(file, "文件参数不能为空");
		Assert.notEmpty(fieldNames, "属性名数组不能为空");
		Assert.isTrue(file.exists(), "文件不存在");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fin);
			Sheet sheet = wb.getSheetAt(0); // 只取第一个sheet

			for (int i=1; i<=sheet.getLastRowNum(); i++) {
				// 读取行
				Map<String, String> rowData = readRowData(sheet.getRow(i), fieldNames);
				if (rowData != null) {
					list.add(rowData);
				} else {
					// 如果某行数据为空，则认为结束
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("读取excel文件异常：" + file.getAbsolutePath(), e);
		} finally {
			IOUtils.closeQuietly(fin);
		}
		return list;
	}

	/**
	 * 读取一行数据
	 * 
	 * @author dongzhihua
	 * @time 2017年11月9日 下午3:14:30
	 * @param row
	 * @param fieldNames
	 * @return Map<String,String>
	 */
	protected static Map<String, String> readRowData(Row row, String[] fieldNames) {

		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < fieldNames.length; i++) {
			String value = readStringCell(row.getCell(i));
			// 如果读取到空，不放入map
			if (value != null && value.trim().length() > 0) {
				map.put(fieldNames[i], value.trim());
			}
		}
		if (map.size() == 0) {
			return null;
		}
		return map;
	}

	/**
	 * 读取cell中字符串
	 * 
	 * @author dongzhihua
	 * @time 2017年11月9日 下午3:14:44
	 * @param cell
	 * @return String
	 */
	protected static String readStringCell(Cell cell) {
		if (cell == null) {
			return null;
		}
		return cell.toString();
	}

	public static void main(String[] args) {
		System.out.println(readExcel(new File("E:/export/xxo.xlsx"), new String[]{"name","age","gender"}));
	}
}