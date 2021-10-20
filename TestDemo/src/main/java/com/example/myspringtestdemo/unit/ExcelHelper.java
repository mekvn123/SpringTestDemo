package com.example.myspringtestdemo.unit;

import com.example.myspringtestdemo.application.dto.UserDto;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ExcelHelper {

    static String[] HEADERs = {"UserId", "UserName", "UserPassword", "UserEmail"};
    static String SHEET = "UserInfo";

    @SneakyThrows
    public static List<UserDto> excelToUser(InputStream inputStream) {
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(SHEET);
        Iterator<Row> rows = sheet.iterator();
        List<UserDto> userDtos = newArrayList();

        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }
            Iterator<Cell> cellsInRow = currentRow.iterator();
            UserDto userDto = new UserDto();
            int cellIdx = 0;
            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                switch (cellIdx) {
                    case 0:
                        userDto.setUserId(currentCell.getStringCellValue());
                        break;
                    case 1:
                        userDto.setUserName(currentCell.getStringCellValue());
                        break;
                    case 2:
                        userDto.setUserPassword(currentCell.getStringCellValue());
                        break;
                    case 3:
                        userDto.setUserEmail(currentCell.getStringCellValue());
                        break;
                    default:
                        break;
                }
                cellIdx++;
            }
            userDtos.add(userDto);
        }
        workbook.close();
        return userDtos;
    }

    public static ByteArrayInputStream UserToExcel(List<UserDto> userDtos) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (UserDto userDto : userDtos) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(userDto.getUserId());
                row.createCell(1).setCellValue(userDto.getUserName());
                row.createCell(2).setCellValue(userDto.getUserPassword());
                row.createCell(3).setCellValue(userDto.getUserEmail());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}

