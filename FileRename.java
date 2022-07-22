import java.io.*;

public class FileRename {
    public static void main(String[] args) {
        String defaultFilePath = "C:/FFWMS/wordspace/ffwms/FlexFit/online-wms-web/src/main/FFWMS/biz/";
        String[] directoryNames = {"SM/","WM/","RS/","RM/","SMM/","NV/"};

        for (String directoryName: directoryNames) {
            String directoryPath = defaultFilePath + directoryName;
            fileRename(directoryPath);
        }
    }

    public static void fileRename(String directoryPath) {
        File[] files = new File(directoryPath).listFiles();

        for (File file : files) {
            String fileName = file.getName();
            String path = file.getPath().replace(fileName,"");
            if(fileName.contains("new_")) {
                file.renameTo(new File(path+fileName.replace("new_", "")));
            }
        }
    }


}
