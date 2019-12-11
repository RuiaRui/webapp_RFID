package utils;

import vo.Location;
import vo.Reader;
import vo.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Method {



    /**
     * 确定某个位置是否位于读写器的范围内
     *
     * @param loc_x    位置的X坐标
     * @param loc_y    位置的Y坐标
     * @param reader_x 读写器的X坐标
     * @param reader_y 读写器的Y坐标
     * @param r        读写器半径
     * @return true false
     */
    public static boolean locWithinReader(double loc_x, double loc_y,
                                          double reader_x, double reader_y,
                                          double r) {
        return Math.pow(loc_x - reader_x, 2) + Math.pow(loc_y - reader_y, 2) < r * r;
    }
    /**
     * 将double数组的位置转化为读写器集合
     * @param position 读写器位置
     * @return
     */


    public static List<Reader> Position2ReaderList(double[] position) {
        List<Reader> readerList = new ArrayList<>();
        for (int i = 0; i < Parameter.readerNum; i++) {
            Reader r = new Reader(i, new Location(position[2 * i], position[2 * i + 1]));
            readerList.add(r);
        }

        return readerList;
    }

    public static List<Tag> Position2TagList(List<String> id,double[] position) {
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < Parameter.tagNum; i++) {
            Tag t = new Tag(id.get(i), new Location(position[2 * i], position[2 * i + 1]));
            tagList.add(t);
        }

        return tagList;
    }

    private static int random(){
        int i=new Random().nextInt(Parameter.maxPosition+1);
        return i;
    }


    public static double[] GerenateTagPosition(int tagNum){
         double[] tagPosition= new double[tagNum*2];
         for(int i=0;i<tagNum;i++){
             tagPosition[2*i]=random();
             tagPosition[2*i+1]=random();
         }

         return tagPosition;
    }
}
