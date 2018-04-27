package com.homevip.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.homevip.util.system.Const;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * CSV工具类
 * Created by 周枫 on 2018/4/4.
 */
public class CsvUtil {
    /**
     * 普通方法
     */
    public static void createCsv(List<Map<String, Object>> list, String keys[], String columnNames[], String fileName, String textFix) throws IOException {
        // 定义一个CSV路径
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(fileName, ',', Charset.forName(Const.UTF8));
            // 写表头
            csvWriter.writeRecord(columnNames);
            // 写内容
            for (int i = 1; i < list.size(); i++) {
                String[] csvContent = new String[keys.length];
                for (int j = 0; j < keys.length; j++) {
                    csvContent[j] = list.get(i).get(keys[j]) == null ? " " : textFix + list
                            .get(i).get(keys[j]).toString();
//                    strings.add(temp);
//                    csvContent = Arrays.copyOf(csvContent, strLen1 + strLen2);
//                    System.arraycopy(csvContent,0,temp, strLen1,strLen2 );
                }
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 包含UTF-8bom的方式
     */
    public static void createCsvByFlux(List<Map<String, Object>> list, String keys[], String columnNames[], String fileName, String textFix) throws IOException {
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(out,"utf-8");
            //添加utf8bom
            byte[] uft8bom={(byte)0xef,(byte)0xbb,(byte)0xbf};
            out.write(uft8bom);
            bw = new BufferedWriter(osw);
            if (!CollectionUtil.isEmpty(list)){
                //添加表头，最后一个加换行
                int headEnd = columnNames.length-1;
                for (int h = 0; h < columnNames.length; h++){
                    bw.append(columnNames[h]).append(",");
                    if (headEnd == h){
                        bw.append("\r");
                    }
                }
                //添加数据
                for (int i = 1; i < list.size(); i++) {
                    int end = keys.length-1;
                    //根据每行的列数遍历数据，最后一列加换行
                    for (int j = 0; j < keys.length; j++) {
                        bw.append(list.get(i).get(keys[j]) == null ? " " : textFix + list
                                .get(i).get(keys[j]).toString()).append(",");
                        if (end == j){
                            bw.append("\r");
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null){
                bw.close();
            }
            if (osw != null){
                osw.close();
            }
            if (out != null){
                out.close();
            }
        }
    }


    /**
     * 数组标题与值的对应关系
     * @param dataList
     * @return
     */
    private static <T> List<Map<String, String>> getTitles(List<String[]> dataList) {
        List<Map<String, String>> list = new ArrayList<>();
        String[] titles = dataList.get(0);
        dataList.remove(0);
        for (String[] values : dataList) {
            Map<String, String> titleMap = new HashMap<>();
            for (int i = 0; i < values.length; i++) {
                titleMap.put(titles[i], values[i]);
            }
            list.add(titleMap);
        }
        return list;
    }

    public static void readCSV() {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = "D://StemQ.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();

            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                // 取得第row行第0列的数据
                String cell = csvFileList.get(row)[0];
                System.out.println("------------>"+cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String biao="用户名,姓名,排序,专家类型,业务专长,错误信息\n";
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("0","无服务站");
        map.put("1","admin");
        map.put("2","0.0");
        map.put("3","1");
        list.add(map);
        createCsvByFlux(list,new String[]{"0","1","2","3","4"},new String[]{"服务站","客户经理","充值金额","排名","业务专长"},"D:/A.csv", "");



    }


}
