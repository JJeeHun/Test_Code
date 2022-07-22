import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConvertFileData {
    public static void main(String[] args) throws IOException {
        String defaultFilePath = "C:/FFWMS/wordspace/ffwms/FlexFit/online-wms-web/src/main/FFWMS/biz/";
        String[] directoryNames = {"SM/","WM/","RS/","RM/","SMM/","NV/"};

        for (String directoryName: directoryNames) {
            String directoryPath = defaultFilePath + directoryName;
            xfdlFileRun(directoryPath);
        }
    }

    public static String getCheck(String textLine) {
        String newTextLine = textLine;

        Map<String, String> checkList = new HashMap<>();
        checkList.put("BizLib::Common.xjs","lib::lib_script_common.xjs");
        checkList.put("gfn_alert","gfm_message");
        checkList.put("gfnGrdFindRowPos","");
        checkList.put("gfn_excelExport","gfn_ToExcel");
        checkList.put("gfn_isNull","sfw_isEmpty");
        checkList.put("_getGetText","gfn_autoMapping");
        checkList.put("gfn_convertGridValue","gfn_nvl2");
        checkList.put("gfn_today","sfw_today");
        checkList.put("gfn_Length","sfn_length");
        checkList.put("gfn_AddMonth","sfw_addMonth");
        checkList.put("gfn_trim","trim");
        checkList.put("gfn_DsRowToJSON","gfn_dsRowToJSON");
        checkList.put("gfn_formOnLoad","gfn_FormLoadSetting");

        for (String replaceStr : checkList.keySet()) {
            newTextLine = newTextLine.replaceAll(replaceStr,checkList.get(replaceStr));
        }

        return newTextLine;
    }

    /**
     * javascript file로 만들어줌.(newLine이없어서 생성이 안되었음, 테스트후 다른 메소드로 변경)
     * @param directoryPath
     */
    public static void fileRun(String directoryPath) {
        File[] files = new File(directoryPath).listFiles();

        for (File file : files) {
            String fileName = "TEMP_"+file.getName().replace(".xfdl",".js");
            try(
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directoryPath+fileName),"utf-8"));
                    BufferedReader br = new BufferedReader(new FileReader(file))
            ) {
                String sLine = null;
                boolean isStart = false;
                while( (sLine = br.readLine()) != null ) {
                    if(sLine.contains("<![CDATA[")) {
                        if(sLine.contains("<Script type=\"xscript5.1\">")) {
                            sLine = sLine.replace("    <Script type=\"xscript5.1\">","");
                        }
                        isStart = true;
                        sLine = sLine.replace("<![CDATA[","");
                    }
                    if(sLine.contains("]]></Script>")) { break; }
                    if(!isStart) continue;

                    bw.write(getCheck(sLine));
                    bw.newLine();
                }
            }catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    /**
     * 변경해야 할 값으로 변경후 넥사크로 파일로 떨궈줌.
     * @param directoryPath
     */
    public static void xfdlFileRun(String directoryPath) {
        File[] files = new File(directoryPath).listFiles();

        for (File file : files) {
            String fileName = "new_"+file.getName();
            try(
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directoryPath+fileName),"utf-8"));
                    BufferedReader br = new BufferedReader(new FileReader(file))
            ) {
                String sLine = null;
                boolean isStart = false;
                while( (sLine = br.readLine()) != null ) {
                    bw.write(getCheck(sLine));
                    bw.newLine();
                }
            }catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

}
