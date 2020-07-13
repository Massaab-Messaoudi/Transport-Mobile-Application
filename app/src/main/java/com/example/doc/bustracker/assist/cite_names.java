package com.example.doc.bustracker.assist;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.doc.bustracker.MainActivity;

/**
 * Created by DOC on 27/02/2018.
 */

public class cite_names {

    public String [] getListNames()
    {
        String[]list=new String[32];
        list[0]="Cité 1200 Logment";
        list[1]="Hai 742 Log";//bou39al
        list[2]="Cité El Boustane";
        list[3]="Cité Tamachit";  //Aneme BATNA-
        list[4]="HAI CHOUHADA";
        list[5]="Sonatiba";
        list[6]="Bouzghaia";
        list[7]="Plais de Justice";
        list[8]="Hai Bouakal";
        list[9]="Alles Salah Nezzar";
        list[10]="Cité 1272 Logts"; //02,araar
        list[11]="5 Juillet";//01,02,bou39al
        list[12]="Town centre";//01,02,bou39al,araar,
        list[13]="Théatre regional-marché couvert";//02,araar
        list[14]="Bordj el ghoula";//02,araar
        list[15]="hotel chelia spa";//02,araar
        list[16]="Allées Ben Boulaïd ";//02,araar
        list[17]="La zone arar";
        list[18]="Hai 800 Logement ";// riyadh
        list[19]="Hai Mujahideen "; //01;bouzourane
        list[20]="Hai 46 Logement";// riadh , barkavourage-hamla
        list[21]="Université batna 1";//01,02,bouzourane
        list[22]="Quartier larbi ben mhidi";//01,bouzourane
        list[23]="Bouzourane";//01,bouzourane
        list[24]="Douar Eddis";
        ///////////////
        list[25]="Hai 500 Logment";// riadh , barkavourage-hamla
        list[26]="Hai Riyadh";// riyadh
        list[27]="Hai 1020 Logment";// riadh
        list[28]="Hai 345 Logment";// barkavourage-hamla
        list[29]="Hamla 1";// barkavourage-hamla,bouzour
        list[30]="Hamla 2";// barkavourage-hamla,bouzour
        list[31]="Hamla 3";  // barkavourage-hamla; bouz




        return list;
    }
    public StringBuilder getlistbus(int position){
        StringBuilder lis=new StringBuilder();
        String list_bus = " " ;
        switch (position)
        {
            case 0://1200
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;
            case 1://742
                //list_bus="\n 02 \n";
                lis.append(",07");
                MainActivity.data_downloaded=false;
                break;
            case 2://Cité El Boustane
                //list_bus="\n 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;

            case 3://  tamechit
                //list_bus="\n 02 \n";
                lis.append(",02");
                MainActivity.data_downloaded=false;
                break;
            case 4://hai shouhada
                //list_bus="\n 01 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;
            case 5: //sonatiba
                //list_bus="01 \n 02 \n";
                lis.append(",02");
                MainActivity.data_downloaded=false;
                break;
            case 6://bouzghaya
                //list_bus="01 \n 02 \n";
                lis.append(",02,01");
                MainActivity.data_downloaded=false;
                break;
            case 7://palais de justuce
                //list_bus="\n 01 \n";
                lis.append(",01,02");
                MainActivity.data_downloaded=false;
                break;
            case 8://bou39al
                //list_bus="\n 02 \n";
                lis.append(",07");
                MainActivity.data_downloaded=false;
                break;
            case 9://salh nezzar
               // list_bus="\n 02 \n";
                lis.append(",02,01,07,08,09,10,11");
                MainActivity.data_downloaded=false;
                break;
            case 10://1742
                //list_bus="\n 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;

            case 11:   //5 juill
                //list_bus="\n 02 \n";
                lis.append(",02,01,08,07,09,10,11");
                MainActivity.data_downloaded=false;
                break;
            case 12:    //town centre
                //list_bus="\n 02 \n";
                lis.append(",02,01,08,07,09,10,11");
                MainActivity.data_downloaded=false;
                break;
            case 13://theatre
                //list_bus="\n 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;
            case 14://bordj el ghoula
                //list_bus="\n 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;
            case 15://hotel chelia spa
                //list_bus="\n 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;
            case 16://Allées Ben Boulaïd
                //list_bus="\n 02 \n";
                lis.append(",02,08");
                MainActivity.data_downloaded=false;
                break;
            case 17://arar
                //list_bus="\n 02 \n";
                lis.append(",08");
                MainActivity.data_downloaded=false;
                break;
            case 18: //800
                //list_bus="\n 02 \n";
                lis.append(",09");
                MainActivity.data_downloaded=false;
                break;
            case 19://mujahdin
                //list_bus="\n 01 \n";
                lis.append(",11,01");
                MainActivity.data_downloaded=false;
                break;
            case 20://46
                //list_bus= "01 \n 02 \n";
                lis.append(",11,11");
                MainActivity.data_downloaded=false;
                break;
            case 21://univ
                //list_bus="\n 01 \n";
                lis.append(",01,02,11");
                MainActivity.data_downloaded=false;
                break;
            case 22://l3arbi ben mhidi
                //list_bus="\n 01 \n";
                lis.append(",01,11");
                MainActivity.data_downloaded=false;
                break;

            case 23://bouzourane
                lis.append(",01,11");
                MainActivity.data_downloaded=false;
                break;
            case 24://douar eddis
                //list_bus="\n 02 \n";
                lis.append(",07");
                MainActivity.data_downloaded=false;
                break;
            case 25://500
                list_bus="\n 02 \n";
                lis.append(",09,10");
                MainActivity.data_downloaded=false;
                break;
            case 26://riyadh
                list_bus="\n 02 \n";
                lis.append(",09");
                MainActivity.data_downloaded=false;
                break;
            case 27://1020
                list_bus="\n 02 \n";
                lis.append(",09");
                MainActivity.data_downloaded=false;
                break;
            case 28://345
                list_bus="\n 02 \n";
                lis.append(",10");
                MainActivity.data_downloaded=false;
                break;
            case 29://hamla1
                list_bus="\n 02 \n";
                lis.append(",10,11");
                MainActivity.data_downloaded=false;
                break;
            case 30://hamla2
                list_bus="\n 02 \n";
                lis.append(",10,11");
                MainActivity.data_downloaded=false;
                break;
            case 31://hamla3
                list_bus="\n 02 \n";
                lis.append(",10,11");
                MainActivity.data_downloaded=false;
                break;

        }
        return lis;
    }
    public int [] getnewlist(int position){
        int list[]=new int [7];
        switch (position)
        {
            case 0://1200
                list[0]=2;
                list[1]=8;
                MainActivity.data_downloaded=false;
                break;
            case 1://742
                list[0]=7;


                MainActivity.data_downloaded=false;
                break;
            case 2://Cité El Boustane
                list[0]=2;
                list[1]=8;

                MainActivity.data_downloaded=false;
                break;

            case 3://  tamechit
                list[0]=2;

                MainActivity.data_downloaded=false;
                break;
            case 4://hai shouhada
                list[0]=2;
                list[1]=8;

                MainActivity.data_downloaded=false;
                break;
            case 5: //sonatiba
                list[0]=2;

                MainActivity.data_downloaded=false;
                break;
            case 6://bouzghaya
                list[0]=2;
                list[1]=1;

                MainActivity.data_downloaded=false;
                break;
            case 7://palais de justuce
                list[0]=1;
                list[1]=2;

                MainActivity.data_downloaded=false;
                break;
            case 8://bou39al
                list[0]=7;
                MainActivity.data_downloaded=false;
                break;
            case 9://salh nezzar
                list[0]=1;
                list[1]=2;
                list[2]=7;
                list[3]=8;
                list[4]=9;
                list[5]=10;
                list[6]=11;
                MainActivity.data_downloaded=false;
                break;
            case 10://1742
                list[0]=2;
                list[1]=8;

                MainActivity.data_downloaded=false;
                break;

            case 11:   //5 juill
                //list_bus="\n 02 \n";
                list[0]=1;
                list[1]=2;
                list[2]=7;
                list[3]=8;
                list[4]=9;
                list[5]=10;
                list[6]=11;

                MainActivity.data_downloaded=false;
                break;
            case 12:    //town centre
                list[0]=1;
                list[1]=2;
                list[2]=7;
                list[3]=8;
                list[4]=9;
                list[5]=10;
                list[6]=11;
                MainActivity.data_downloaded=false;
                break;
            case 13://theatre
                list[0]=8;
                list[1]=2;
                MainActivity.data_downloaded=false;
                break;
            case 14://bordj el ghoula
                list[0]=2;
                list[1]=8;
                MainActivity.data_downloaded=false;
                break;
            case 15://hotel chelia spa
                list[0]=2;
                list[1]=8;
                MainActivity.data_downloaded=false;
                break;
            case 16://Allées Ben Boulaïd
                list[0]=2;
                list[1]=8;
                MainActivity.data_downloaded=false;
                break;
            case 17://arar
                //list_bus="\n 02 \n";
                list[0]=8;
                MainActivity.data_downloaded=false;
                break;
            case 18: //800
                list[0]=9;

                MainActivity.data_downloaded=false;
                break;
            case 19://mujahdin
                list[0]=1;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;
            case 20://46
                list[0]=1;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;
            case 21://univ
                list[0]=2;
                list[1]=1;
                list[2]=11;

                MainActivity.data_downloaded=false;
                break;
            case 22://l3arbi ben mhidi
                list[0]=1;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;

            case 23://bouzourane
                list[0]=1;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;
            case 24://douar eddis
                list[0]=7;

                MainActivity.data_downloaded=false;
                break;
            case 25://500
                list[0]=9;
                list[1]=10;
                MainActivity.data_downloaded=false;
                break;
            case 26://riyadh
                list[0]=9;
                MainActivity.data_downloaded=false;
                break;
            case 27://1020
                list[0]=9;

                MainActivity.data_downloaded=false;
                break;
            case 28://345
                list[0]=10;

                MainActivity.data_downloaded=false;
                break;
            case 29://hamla1
                list[0]=10;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;
            case 30://hamla2
                list[0]=10;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;
            case 31://hamla3
                list[0]=10;
                list[1]=11;
                MainActivity.data_downloaded=false;
                break;

        }
        return list;
    }
}
