package com.raven.dream.job.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Slf4j
public class EntityCodeGenerateUtils {

    private String tablename;//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    public static final String URL = "jdbc:mysql://igaon.cn:3306";
    public static final String NAME = "dream_job";
    public static final String PASSWORD = "Y6EEBNyR7LKfpAz8";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String dbName = "dream_job";

    public static void main(String[] args) {

        String packageOutPath = "com.raven.dream.job.domain.entity";//指定实体生成所在包的路径
        String suffix = "";
        String serviceName = "";
        generateEntityCode("t_employees", packageOutPath, suffix, serviceName);
        // /Users/raven/Documents/devlop/coding-life/dream-job/src/main/java/com/raven/dream/job/domain/entity/A.java
        // /Users/raven/Documents/devlop/coding-life/dream-job/src/main/java/com/raven/dream/job/domain/entity/Employees.java
    }

    public static void generateEntityCode(String tableName, String packageOutPath, String suffix, String serviceName) {
        List<String> tableList = new ArrayList<String>();
        try {
            Connection con = getConnection();
            DatabaseMetaData dm = con.getMetaData();
            ResultSet rs = dm.getTables(con.getCatalog(), "root", null, new String[]{"TABLE"});
            while (rs.next()) {
                tableList.add(rs.getString("TABLE_NAME"));
            }
            if (StringUtils.isNotBlank(tableName)) {
                new EntityCodeGenerateUtils(con, tableName, packageOutPath, suffix, serviceName);
                return;
            }

            if (CollectionUtils.isNotEmpty(tableList)) {
                for (String table : tableList) {
                    new EntityCodeGenerateUtils(con, table, packageOutPath, suffix, serviceName);
                }
            }
        } catch (Exception e1) {
//			log.error("", e1);
            e1.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + "/" + dbName, NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    List<String> columnComments = new ArrayList<>();//列名注释集合

    public EntityCodeGenerateUtils(Connection con, String tableName, String packageOutPath, String suffix, String serviceName) {
        this.tablename = tableName;
        String sql = "select * from " + tablename;
        PreparedStatement pStemt;
        try {

            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();


            ResultSet rs = null;
            try {
                rs = pStemt.executeQuery("show full columns from " + tableName);
                while (rs.next()) {
                    columnComments.add(rs.getString("Comment"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int size = rsmd.getColumnCount();
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);


                if (StringUtils.equalsIgnoreCase("datetime", colTypes[i])) {
                    f_util = true;
                }
                if (StringUtils.equalsIgnoreCase("image", colTypes[i]) || StringUtils.equalsIgnoreCase("text", colTypes[i])) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(colnames, colTypes, colSizes, packageOutPath, suffix);

            try {
                File directory = new File("");
                String outputPath =
                        directory.getAbsolutePath() + "/" + serviceName + "/dream-job/src/main/java/" + packageOutPath.replace(".", "/") + "/"
                                + parseName(tablename, suffix) + ".java";
                if (directory.exists()) {
                    return;
                }

                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes, String packageOutPath, String suffix) {
        StringBuffer sb = new StringBuffer();

        sb.append("package " + packageOutPath + ";\r\n");
        sb.append("\r\n");
        //判断是否导入工具包
        if (f_util) {
            sb.append("import java.time.LocalDateTime;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("import javax.persistence.Column;\r\n");
        sb.append("import javax.persistence.Table;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("import javax.persistence.Id;\r\n");
        sb.append("import io.swagger.annotations.ApiModel;\r\n");
        sb.append("import io.swagger.annotations.ApiModelProperty;\r\n");
        sb.append("\r\n");
        //注释部分
        //sb.append("/**\r\n");
        //sb.append(" *@author: " +  this.authorName  + " \r\n");
        //sb.append(" *@date: " + DateUtils.format(new Date(),DateUtils.FORMAT_YYYY_MM_DD) +  "\r\n");
        //sb.append(" */ \r\n");
        //实体部分
        sb.append("@Data\r\n");
        sb.append("@ApiModel\r\n");
        sb.append("@Table(name = \"" + tablename + "\")\r\n");
        sb.append("public class " + parseName(tablename, suffix) + "{\r\n");
        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            String column = parseNameNotFirst(colnames[i]);
            if (StringUtils.equalsAny(colnames[i], "update_at", "create_at")) {
                System.out.println("创建时间 修改时间 跳过");
                continue;
            }
            if (StringUtils.equals("fileSize", column)) {
                System.out.println("here");
            }
            if (i == 0) {
                sb.append("\r\n");
            }
            if ("id".equals(column)) {
                sb.append("\t@Id\r\n");
                sb.append("\t@Column(name = \"" + colnames[i] + "\")\r\n");
                sb.append("\t@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n");
            } else {
                sb.append("\t@Column(name = \"" + colnames[i] + "\")\r\n");
                sb.append("\t@ApiModelProperty(value = \"" + columnComments.get(i) + "\")\r\n");
            }
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i], colnames[i]) + " " + column + ";\r\n");
            sb.append("\r\n");
        }
    }

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<>();//列名注释集合
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return columnComments;
    }

    /**
     * 功能：生成get、set方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

    }

    /**
     * 描述：第一个首字母不大写，后面大写
     *
     * @param name
     * @return String
     */
    public String parseNameNotFirst(String name) {
        StringBuffer sb = new StringBuffer();
        String[] array = name.split("_");
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (i > 0) {
                    sb.append(initcap(array[i]));
                } else {
                    sb.append(array[i]);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 描述：全部首字母大写
     *
     * @param name
     * @return String
     */
    public String parseName(String name, String suffix) {
        StringBuffer sb = new StringBuffer();
        String[] array = name.split("_");
        if (array.length > 0) {
            for (String string : array) {
                sb.append(initcap(string));
            }
        }

        return sb.toString().substring(1) + suffix;
    }

    /**
     * 功能：将输入字符串的首字母改成大写（修改成驼峰法）
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType, String colname) {
        sqlType = sqlType.toLowerCase();

        if (StringUtils.equalsIgnoreCase("bit", sqlType)) {
            return "Boolean";
        } else if (StringUtils.equalsIgnoreCase("bigint", sqlType) || StringUtils.equalsIgnoreCase("BIGINT UNSIGNED", sqlType)
                || StringUtils.endsWith(colname, "_id") || StringUtils.equals("id", colname)) {
            return "Long";
        } else if (StringUtils.equalsIgnoreCase("tinyint", sqlType) || StringUtils.equalsIgnoreCase("TINYINT UNSIGNED", sqlType)
                || StringUtils.equalsIgnoreCase("smallint", sqlType) || StringUtils.equalsIgnoreCase("SMALLINT UNSIGNED", sqlType)
                || StringUtils.equalsIgnoreCase("int", sqlType) || StringUtils.equalsIgnoreCase("INT UNSIGNED", sqlType)) {
            return "Integer";
        } else if (StringUtils.equalsIgnoreCase("float", sqlType)) {
            return "Float";
        } else if (StringUtils.equalsIgnoreCase("decimal", sqlType) || StringUtils.startsWithIgnoreCase(sqlType, "decimal")) {
            return "BigDecimal";
        } else if (StringUtils.startsWithIgnoreCase(sqlType, "numeric")
                || StringUtils.equalsIgnoreCase("numeric", sqlType) || StringUtils.equalsIgnoreCase("real", sqlType)
                || StringUtils.equalsIgnoreCase("money", sqlType) || StringUtils.equalsIgnoreCase("smallmoney", sqlType)) {
            return "Double";
        } else if (StringUtils.equalsIgnoreCase("varchar", sqlType) || StringUtils.equalsIgnoreCase("char", sqlType)
                || StringUtils.equalsIgnoreCase("nvarchar", sqlType) || StringUtils.equalsIgnoreCase("nchar", sqlType)
                || StringUtils.equalsIgnoreCase("text", sqlType)) {
            return "String";
        } else if (StringUtils.equalsIgnoreCase("datetime", sqlType)) {
            return "LocalDateTime";
        } else if (StringUtils.equalsIgnoreCase("date", sqlType)) {
            return "Date";
        } else if (StringUtils.equalsIgnoreCase("image", sqlType)) {
            return "Blod";
        }

        return null;
    }
}
