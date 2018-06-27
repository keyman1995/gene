package com.ycjcjy.gene.web.action.system.util;


import java.io.IOException;
import java.util.Properties;


/**
 * 获取配置文件config.properties信息接口
 * @author kfcao
 *
 */
public class AppConfig
{
    private static Properties prop = new Properties();

    static
    {
        try
        {
            prop.load(AppConfig.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e)
        {}
    }

    public static String getValue(String name)
    {
        return prop.getProperty(name);
    }

}
