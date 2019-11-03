package com.bilibili.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Map;

/**
 * JSON文件操作工具类
 *
 */
public class JSONFileUtils {

    /**
     * 读取JSON文件
     * @param fileName 文件名字
     * @return json对象
     */
    public static JSONObject readFile(String fileName) throws IOException {
        String str = "";
        File jsonFile = new File(fileName);
        FileReader fileReader = new FileReader(jsonFile);

        Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        str = sb.toString();

        JSONObject jsonData = JSONObject.parseObject(str);

        return jsonData;
    }


}
