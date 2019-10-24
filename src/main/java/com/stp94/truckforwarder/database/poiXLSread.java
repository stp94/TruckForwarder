package com.stp94.truckforwarder.database;

import com.stp94.truckforwarder.route;
import org.apache.commons.collections4.Get;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class poiXLSread {

    private static final String fileDB = "C:\\javaDev\\TruckForwarder2\\src\\main\\resources\\view\\routesdb.xls";
    private static final ArrayList<route> RouteDBinArray = new ArrayList<>();

    String DBrouteSource;
    String DBrouteDestinstion;
    String DBrouteLoadType;
    long   DBrouteLenght;
    double DBrouteWidth;
    double DBrouteHeight;
    double DBrouteWeight;
    double DBrouteCapacity;
    double DbrouteCashReward;
    String DBrouteCategory;



    public poiXLSread ()
    {
        InitializeDatabase();
        GetRoutefromFile();

    }

    public static void InitializeDatabase()
    {



    }

    public ArrayList<route> GetRoutefromFile()
    {

        try
        {

            FileInputStream excelFile = new FileInputStream(new File(fileDB));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0




                   if (currentCell.getColumnIndex()==0)
                   {
                       DBrouteSource=currentCell.getStringCellValue();
                       System.out.println("To jest moj" + DBrouteSource);
                   }


                    if (currentCell.getColumnIndex()==1)
                    {
                        DBrouteDestinstion=currentCell.getStringCellValue();
                        System.out.println(" oraz " + DBrouteDestinstion);
                    }


                    if (currentCell.getColumnIndex()==2)
                    {
                        DBrouteLoadType=currentCell.getStringCellValue();
                        System.out.println("To jest moj" + DBrouteLoadType);
                    }



                    /*
                    if (currentCell.getColumnIndex()==3)
                    {
                        DBrouteLenght=(long)currentCell.getNumericCellValue();
                        System.out.println("To jest moj" + DBrouteLenght);
                    }





                    if (currentCell.getCellType() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }
                    */


                }
                //System.out.println();

            }
        }   catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return RouteDBinArray;
    };






}
