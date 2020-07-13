package com.example.doc.bustracker;

/**
 * Created by DOC on 27/02/2018.
 */

public class BusStopsLocation {

    /*  public BusStopsLocation(int Busid)
        {
            this.id=Busid;
        }*/
    public double[]getlat(int bus_id){

        double[] table=new double[38];
        switch (bus_id) {
            case 2:
                table[0] = 35.531612;
                table[1] = 35.530896;
                table[2] = 35.532179;
                table[3] = 35.533347;
                table[4] = 35.534761;
                table[5] = 35.537607;
                table[6] = 35.540471;
                table[7] = 35.542521;
                table[8] = 35.543070;
                table[9] = 35.542916;
                table[10] = 35.544067;
                table[11] = 35.545934;
                table[12] = 35.547937;
                table[13] = 35.550854;
                table[14] = 35.554166;
                table[15] = 35.556231;
                table[16] = 35.558673;
                table[17] = 35.557530;
                table[18] = 35.555078;
                table[19] = 35.554322;
                table[20] = 35.552837;
                table[21]=35.550474;
                table[22]=35.547583;
                table[23]=35.545717;
                table[24]=35.543052;
                table[25]=35.542516;
                table[26]=35.540587;
                table[27]=35.537935;
                table[28]=35.536013;
                table[29]=35.534834;
                table[30]=35.532922;
                table[31]=35.531659;
                table[32]=35.530284;
                table[33]=35.528860;
                table[34]=35.527775;
                table[35]=35.527906;
                table[36]=35.528756;
                table[37]=35.529513;
                break;
            case 1:

                table[0] = 35.537622;
                table[1] = 35.535245;
                table[2] = 35.537352;
                table[3] = 35.540477;
                table[4] = 35.542589;
                table[5] = 35.543058;
                table[6] = 35.542658 ;
                table[7] = 35.545471 ;
                table[8] = 35.547271 ;
                table[9] = 35.549074;
                table[10] = 35.550089 ;
                table[11] = 35.552821;
                table[12] = 35.554823;
                table[13] = 35.555867;
                table[14] = 35.556667;
                table[15] = 35.559031;
                table[16] = 35.561035;
                table[17] =35.562978;
                table[18] = 35.562491;
                table[19] = 35.559058;
                table[20] = 35.557328;
                table[21]=35.556718;
                table[22]=35.552838;
                table[23]=35.550481;
                table[24]=35.537355;
                table[25]=35.538731;
                table[26]=35.544170;
                table[27]=35.561167;
                table[28]=35.547688;
                table[29]=35.546064;
                table[30]=35.543059;
                table[31]=35.542589;
                table[32]=35.540582;
                table[33]=35.537886;
                table[34]=35.536048;
                table[35]=35.535276;
                table[36]=35.536975;
                table[37]=35.554186;

                break;
        }

        return table;
    }
    public double [] getlong(int bus_id){
        double[]table=new double[38];
        switch (bus_id) {
            case 2:
                table[0] = 6.172241;
                table[1] = 6.169323;
                table[2] = 6.165053;
                table[3] = 6.162567;
                table[4] = 6.159388;
                table[5] = 6.156452;
                table[6] = 6.155807;
                table[7] = 6.158880;
                table[8] = 6.161755;
                table[9] = 6.166641;
                table[10] = 6.168676;
                table[11] = 6.170956;
                table[12] = 6.173322;
                table[13] = 6.176939;
                table[14] = 6.176652;
                table[15] = 6.174037;
                table[16] = 6.174202;
                table[17] = 6.175371;
                table[18] = 6.178485;
                table[19] = 6.178537;
                table[20] = 6.176955;
                table[21]=6.174169;
                table[22]=6.170727;
                table[23]=6.168511;
                table[24]=6.161093;
                table[25]=6.158318;
                ///////
                table[26]=6.155689;
                table[27]=6.152630;
                table[28]=6.151281;
                table[29]=6.152571;
                table[30]=6.154784;
                table[31]=6.157471;
                table[32]=6.160547;
                table[33]=6.163659;
                table[34]=6.166187;
                table[35]=6.169390;
                table[36]=6.172366;
                table[37]=6.173830;

                break;
            case 1:
                table[0] = 6.137846;
                table[1] = 6.146094;
                table[2] = 6.152160;
                table[3] = 6.155798;
                table[4] = 6.158740;
                table[5] = 6.161497;
                table[6] =6.167216;
                table[7] =6.172721;
                table[8] = 6.175711;
                table[9] = 6.177924;
                table[10] = 6.179172;
                table[11] = 6.182361;
                table[12] =6.184702;
                table[13] = 6.184085;
                table[14] = 6.183832;
                table[15] = 6.186072;
                table[16] = 6.187875;
                table[17] = 6.189105;
                table[18] = 6.188600;
                table[19] =6.185799;
                table[20] = 6.183836;
                table[21]=6.181365;
                table[22]= 6.176956;
                table[23]=6.174156;
                table[24]=6.140661;
                table[25]=6.153503;
                table[26]=6.166449;
                table[27]=6.187699;
                table[28]=6.170854;
                table[29]=6.168894;
                table[30]=6.161017;
                table[31]=6.158541;
                table[32]=6.155689;
                table[33]=6.152574;
                table[34]=6.150458;
                table[35]=6.146327;
                table[36]= 6.143292;
                table[37]=6.178435;

                break;
        }
        return table;
    }
}
