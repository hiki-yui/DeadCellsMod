package deadCellsMod.cn.infinite.stsmod.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FileUtil {

    /**
     * the fileName
     * @param filePath should be relative
     * @return FileURL
     * @throws FileNotFoundException file not found
     */
    public static String loadResourcePath(String filePath) throws FileNotFoundException{
        URL resource = Thread.currentThread().getContextClassLoader().getResource(filePath);
        if (resource != null){
            return resource.getPath();
        }else{
            throw new FileNotFoundException("文件路径未找到错误");
        }
    }

    /**
     * the rootDir should be put under the module root directory
     * @param similarName fileName
     * @param rootDir from
     * @return Files those name contains similarName
     */
    public static ArrayList<File> searchFileBySimilarName(String similarName, String rootDir) throws IOException {
        String rootUrl = loadResourcePath(rootDir);
        File rootFile = new File(rootUrl);
        if (rootFile.isFile()){
            throw new IOException("the root dir is a file!! ");
        }
        return searchFileBySimilarName(similarName,rootFile);
    }

    private static ArrayList<File> searchFileBySimilarName(String similarName, File rootDir) throws IOException {
        ArrayList<File> fileList = new ArrayList<>(20);
        File[] files = rootDir.listFiles();
        if (files!=null){
            for(File file : files){
                if (file.isFile()){
                    //如果File是一个文件,查看该文件是否存在关键字similar
                    String fileName = file.getName();
                    if (fileName.contains(similarName)){
                        //存在的话将该file对象存入arrayList集合中
                        fileList.add(file);
                    }
                    //continue
                    continue;
                }
                fileList.addAll(searchFileBySimilarName(similarName,file));
            }
            return fileList;
        }else{
            //开始文件夹下没有文件,返回一个空的ArrayList
            return fileList;
        }

    }

}
