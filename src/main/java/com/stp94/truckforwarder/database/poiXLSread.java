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
    private static final ArrayList<route> RouteDBinArray = new ArrayList<>(300);


    String DBrouteSource;
    String DBrouteDestinstion;
    String DBrouteLoadType;
    double DBrouteLenght;
    double DBrouteWidth;
    double DBrouteHeight;
    double DBrouteWeight;
    double DBrouteCapacity;
    double DBrouteCashReward;
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
            RouteDBinArray.add(new route("","","",0,0,0,0,
                    0,0
            ,""));


            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();



                while (cellIterator.hasNext())

                {



                    Cell currentCell = cellIterator.next();





                        if (currentCell.getColumnIndex() == 0) {
                            DBrouteSource = currentCell.getStringCellValue();
                        }

                        if (currentCell.getColumnIndex() == 1) {
                            DBrouteDestinstion = currentCell.getStringCellValue();

                        }


                        if (currentCell.getColumnIndex() == 2) {
                            DBrouteLoadType = currentCell.getStringCellValue();

                        }


                        if (currentCell.getColumnIndex() == 3) {
                            DBrouteLenght = currentCell.getNumericCellValue();

                        }


                        if (currentCell.getColumnIndex() == 4) {
                            DBrouteWidth = currentCell.getNumericCellValue();

                        }

                        if (currentCell.getColumnIndex() == 5) {
                            DBrouteHeight = currentCell.getNumericCellValue();

                        }

                        if (currentCell.getColumnIndex() == 6) {
                            DBrouteWeight = currentCell.getNumericCellValue();

                        }

                        if (currentCell.getColumnIndex() == 7) {
                            DBrouteCapacity = currentCell.getNumericCellValue();

                        }

                        if (currentCell.getColumnIndex() == 8) {
                            DBrouteCashReward = currentCell.getNumericCellValue();

                        }

                        if (currentCell.getColumnIndex() == 9) {
                            DBrouteCategory = currentCell.getStringCellValue();
                            RouteDBinArray.add(new route(DBrouteSource, DBrouteDestinstion, DBrouteLoadType,
                                    (long) DBrouteLenght, DBrouteWidth, DBrouteHeight, DBrouteWeight, DBrouteCapacity,
                                    DBrouteCashReward, DBrouteCategory));

                        }


                }


            }
        }   catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(RouteDBinArray.get(46).getRouteLenght()); // Pobieramy sobie RouteDestination z 50 pozycji :)



        return RouteDBinArray;
    }






}
