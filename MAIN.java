import java.io.*;
import java.util.*;

public class MAIN {
    public static void main(String[] args) throws IOException {
        /**
         * SafeCnc에서 gfn_으로 시작하는 함수 찾아서 마킹하기.
         * @return 함수명, 라인
         */
        String defaultFilePath = "C:/FFWMS/wordspace/ffwms/FlexFit/online-wms-web/src/main/FFWMS/biz/";
        String[] directoryPath = {"SM/","WM/","RS/","RM/","SMM/","NV/"};

        Arrays.stream(directoryPath).forEach( directoryName -> {
            try {
                directorySearch(defaultFilePath+directoryName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void directorySearch(String directoryPath) throws IOException {
        File files = new File(directoryPath);
        Map<String,Map<Integer, String>> listMap = new TreeMap<>();

        for (File file : files.listFiles()) {
            oneFileRun(listMap,file);
        }
//.xfdl
        String path = "C:/Users/JJH/Desktop/동인/마킹/"+files.getName()+".csv";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"euc-kr"));

        for(String key : listMap.keySet()) {
            Map<Integer, String> value = listMap.get(key);

            bw.write("파일명,"+key);
            bw.newLine();
            bw.write("함수,라인");
            bw.newLine();

            output(value,bw);

            bw.newLine();
//            System.out.println("파일명\t"+key);
//            System.out.println("함수명\t라인");
//            print(value);
//            System.out.println();
        }
        bw.flush();
        bw.close();
    }

    //file 객체를 통해 한개의 파일의 대한 정보를 담기 위한 로직
    public static void oneFileRun(Map<String,Map<Integer, String>> datas,File findFile) throws IOException {
        File file = findFile;
        int lineCnt = 0;
        boolean cntStatus = false;
        Map<Integer,String> map = new TreeMap<>();

        if(file.exists())
        {
            BufferedReader inFile = new BufferedReader(new FileReader(file));
            String sLine = null;
            while( (sLine = inFile.readLine()) != null ) {
                if(sLine.contains("<Script")) cntStatus = true;
                if(cntStatus) {
                    lineCnt++;
                    setFindData(map,sLine,lineCnt);
                }
            }
        }
        datas.put(file.getName(),map);
    }
    
    //gfn_ 으로 시작하는 데이터 담기
    public static void setFindData(Map data, String str, int line) {
        final String findName = "GFN_";
        if(str.toUpperCase().contains(findName)) {
            int start = str.toUpperCase().indexOf(findName);
            String newString = str.substring(start);
            int end = newString.indexOf("(");
            data.put(line,newString.substring(0,end));
        }
    }
    
    //데이터 출력    
    public static void print(Map<Integer, String> printData) {
        for (Integer key: printData.keySet()) {
            String data = printData.get(key);
//            System.out.println("line : "+key +", function : "+data);
            System.out.println(data + "\t" + key);
        }
    }

    //output Stream의 데이터를 쓴다.
    public static void output(Map<Integer, String> printData, BufferedWriter bw) throws IOException {
        for (Integer key: printData.keySet()) {
            String data = printData.get(key);
            bw.write(data + ',' + key);
            bw.newLine();
        }
    }

}
