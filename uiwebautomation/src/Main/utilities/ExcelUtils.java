/**
 * 
 */
package Main.utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelUtils {
	
	private static final String fileName = System.getProperty("user.dir") + "/src/com/testdata/TestData.xls";

	public static Map<String, Map<String, String>> readExcel(String sheetName) {
		Workbook book = null;
		Map<String, Map<String, String>> resMap = new HashMap<String, Map<String, String>>();		
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(sheetName);
			int rowNum = sheet.getRows();
			int colNum = sheet.getColumns();
			for (int i = 1; i < rowNum; i++) {
				Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < colNum; j++) {
					map.put(sheet.getCell(j, 0).getContents(),
							sheet.getCell(j, i).getContents());
				}
				resMap.put(sheet.getCell(0, i).getContents(), map);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (book != null) {
				book.close();
			}
		}
		return resMap;
	}

	public static Map<String, String> getTestDataByTCName(String sheetName, String TCName) {
		Workbook book = null;
		Map<String, String> resMap = new HashMap<String, String>();
		try {
			book = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = book.getSheet(sheetName);
			int rowNum = sheet.getRows();
			int colNum = sheet.getColumns();
			for (int i = 1; i < rowNum; i++) {
				if (sheet.getCell(0, i).getContents().equals(TCName)) {
					for (int j = 1; j < colNum; j++) {
						resMap.put(sheet.getCell(j, 0).getContents(), sheet
								.getCell(j, i).getContents());
					}
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (book != null) {
				book.close();
			}
		}
		return resMap;
	}

	public static void updateCellValue(String sheetName, int col, int row, String value) {
		Workbook book = null;
		WritableWorkbook wb = null;
		try {
			book = Workbook.getWorkbook(new File(fileName));
			wb = Workbook.createWorkbook(new File(fileName), book);
			WritableSheet wSheet = wb.getSheet(sheetName);
			Label lbl = new Label(col, row, value);
			wSheet.addCell(lbl);
			wb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (book != null) {
				book.close();
			}
		}

	}

	public static void updateTestResults(String sheetName, Map<String, String> dataMap) {
		Workbook book = null;
		WritableWorkbook wb = null;
		try {
			book = Workbook.getWorkbook(new File(fileName));
			wb = Workbook.createWorkbook(new File(fileName), book);
			WritableSheet wSheet = wb.getSheet(sheetName);
			for (Entry<String, String> entry : dataMap.entrySet()) {
				int row = wSheet.findCell(entry.getKey()).getRow();
				String[] results=entry.getValue().split("\\|");
				Label resLbl = new Label(1, row, results[0]);
				wSheet.addCell(resLbl);
			}
			wb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (book != null) {
				book.close();
			}
		}
	}

}
