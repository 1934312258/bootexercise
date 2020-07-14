package com.kevin.firstUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 * @date 2020-7-10 15:03
 * @description todo
 **/
public class ScriptRunnerExecSql {
    private static String host = "localhost";
    private static String DbName = "test";
    private static String userName = "root";
    private static String password = "adminroot";
    private static String port = "3306";

    public static void main(String[] args) {
        try {
            Connection con = getMysqlConnection();
            final ScriptRunner runner = new ScriptRunner(con);
            Resources.setCharset(Charset.forName("UTF-8"));
            Reader reader = Resources.getResourceAsReader("test.sql");
            BufferedReader reader1 = new BufferedReader(reader);
            Map<Integer, String> map = new HashMap();
            getSqlMap(map, reader1);
            map.forEach((k, v) -> {
                System.out.println("正在执行sql:" + k + ":" + v);
                runner.runScript(new BufferedReader(new StringReader(v)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取数据库连接
    private static Connection getMysqlConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + DbName + "?characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&useSSL=false";
        return DriverManager.getConnection(url, userName, password);
    }

    //解析所有sql语句
    private static Map<Integer, String> getSqlMap(Map map, BufferedReader reader1) throws IOException {
        String stringLine = null;
        int line = 1;
        while ((stringLine = reader1.readLine()) != null) {
            if (stringLine.startsWith("##") || "".equals(stringLine)) continue;
            System.out.println("Line:" + line + stringLine);
            map.put(line, stringLine);
            line++;
        }

        return map;
    }

}
