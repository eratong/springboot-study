package com.ontg.demo.Utils;

import java.io.File;

public class FilesRenameUtil {

    /**
     *  ms003000101_000057480t_2018-05-07T20-39-30.dat.tar.gz批量替换 ms233333333_000057480t_2018-05-07T20-39-30.dat.tar.gz
     * @param path
     * @param msid
     */
    public static void fileRename(String path,String msid){

        File[] files = new File(path).listFiles();
        for (File file : files) {
            String fileName = file.getName();
            String name = msid+fileName.substring(11,fileName.length());
            file.renameTo(new File(path+File.separator+name));
        }
    }
}
