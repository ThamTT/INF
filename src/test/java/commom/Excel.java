package commom;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {
    private static String pathProject = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\";
    private List<String> idFields;
    private List<String> valueFields;
    private XSSFSheet sheetExel = null;

    public void setValueToExcel(int columnIndex, int rowIndex, String value, String fileName, int... sheetAt) {
        String path = pathProject + fileName;

        try {
            FileInputStream file;
            file = new FileInputStream(new File(path));
            XSSFSheet sheet;

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            if (sheetAt.length > 0) {
                sheet = workbook.getSheetAt(sheetAt[0]); // First sheet
            } else {
                sheet = workbook.getSheetAt(0); // First sheet
            }
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontName("Times New Roman"); // set font

            Cell cell;
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.createCell(columnIndex, CellType.STRING); // Set type

            style.setFont(font);
            cell.setCellStyle(style);
            cell.setCellValue(value);

            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(path));
            workbook.write(outFile); // Write excel file
            outFile.close();
        } catch (IOException e) {
            System.out.println("Error code");
        }
    }

    public static void updateDataIntoExcel(String value, Integer sheetId, Integer rowIndex, Integer cellIndex, String fileName) {
        try {
            String fileInput = pathProject + fileName;
            FileInputStream file = new FileInputStream(new File(fileInput));

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(sheetId);
            sheet.getRow(rowIndex).getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(value);
            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(fileInput));
            workbook.write(outFile);
            outFile.close();

        } catch (IOException e) {
            System.out.println("Error message");
        }
    }

    public static void updateDataIntoExcel(String value, String sheetName, Integer rowIndex, Integer cellIndex, String fileName) {
        try {
            String fileInput = pathProject + fileName;
            FileInputStream file = new FileInputStream(new File(fileInput));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            sheet.getRow(rowIndex).getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(value);
            file.close();
            FileOutputStream outFile = new FileOutputStream(new File(fileInput));
            workbook.write(outFile);
            outFile.close();
        } catch (IOException e) {
            System.out.println("Error message");
        }
    }

    public Iterator<Row> getSheetExcel(String fileName, int... sheetAt) {
        String path = pathProject + fileName;
        File file = new File(path);   //creating a new file instance
        FileInputStream fis;          //obtaining bytes from the file
        XSSFWorkbook wb;       //creating Workbook instance that refers to .xlsx file
        XSSFSheet sheet = null;

        try {
            fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);

            if (sheetAt.length > 0) {
                sheet = wb.getSheetAt(sheetAt[0]);   //creating a Sheet object to retrieve object
            } else {
                sheet = wb.getSheetAt(0);   //creating a Sheet object to retrieve object
            }
        } catch (IOException e) {

        }

        assert sheet != null;
        return sheet.iterator();
    }


    public void setIdFields(List<String> idFields) {
        this.idFields = idFields;
    }
    public void setValueFields(List<String> valueFields) {
        this.valueFields = valueFields;
    }
    public Iterator<Row> getSheetExcel(String fileName, String sheetName) throws IOException {
        String path = fileName;
        File file = new File(path);   //creating a new file instance
        FileInputStream fis;          //obtaining bytes from the fil
        XSSFWorkbook wb ;
        //creating a Sheet object to retrieve the object
        try {
            fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
            sheetExel = wb.getSheet(sheetName);
        } catch (IOException e) {

        }

        assert sheetExel != null;
        return sheetExel.iterator();
    }

    public void getValueExcel() throws IOException {
        String path = pathProject + "TestData1.xlsx";
        Iterator<Row> iterator = getSheetExcel(path, "MDDeal");
        List<String> idFields = new ArrayList<String>();
        List<String> valueFields = new ArrayList<String>();
        Row row2 = iterator.next();
        Iterator<Cell> cellIterator = row2.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            idFields.add(cell.getStringCellValue());
        }
        // Lấy data và mã TC
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (row.getCell(0).getStringCellValue().equals("TC02")) {
                for (int indexColumn = 0; indexColumn < idFields.size(); indexColumn++) {
                    try {
                        valueFields.add(row.getCell(indexColumn).getStringCellValue());
                    } catch (NullPointerException e) {
                        valueFields.add(null);
                    }
                }
                break;
            }
        }
        setIdFields(idFields); // set id fields
        setValueFields(valueFields); // set value fields
    }

    public static void main(String[] args) throws IOException {
        Excel excel = new Excel();
        excel.getValueExcel();
        System.out.println(excel.idFields.toString());
        System.out.println(excel.valueFields.toString());
    }

}
