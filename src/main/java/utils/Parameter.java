package utils;



import java.util.List;

public class Parameter {
    /**
     * 读写器部署区域的最大值
     */
    public static int maxPosition = 10;

    /**
     * 读写器部署区域的最小值
     */
    public static int minPosition = 0;

    /**
     * 标签数
     */
    public static int tagNum;

    /**
     * 读写器数量
     */
    public static int readerNum ;

    public static double ri ;

    public static double[]  readerPositionList;

    public static double[]  tagPositionList;

    public static int currentslot=0;

    public static int maxSlot;

    public static void setTagParameter(String path,int slot){
        DataUtils dataUtils=new DataUtils();
        String content=dataUtils.readJsonFile(path);
        tagNum=dataUtils.getTagNum(content);
        tagPositionList=dataUtils.getTagPosition(slot+1,content);
        maxSlot=dataUtils.getMaxSlot(content);
    }

    public static void setReaderParameter(String path){
        DataUtils dataUtils=new DataUtils();
        String content=dataUtils.readJsonFile(path);
        readerNum=dataUtils.getReaderNum(content);
        ri=dataUtils.getReaderRi(content);
        readerPositionList=dataUtils.getReaderPosition(content);
    }

    public static void nextSlot(){
        currentslot=(currentslot+1)%maxSlot;
    }
}
