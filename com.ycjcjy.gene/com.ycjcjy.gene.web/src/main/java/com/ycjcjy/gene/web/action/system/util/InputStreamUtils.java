/*
 * 文件名：InputStreamUtils.java
 * 版权：Copyright by www.suyinchina.com
 * 描述：
 * 修改人：XLY
 * 修改时间：2014-10-28
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.ycjcjy.gene.web.action.system.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class InputStreamUtils
{
    final static int BUFFER_SIZE = 4096;

    /** 
     * 将InputStream转换成String 
     * @param in InputStream 
     * @return String 
     * @throws Exception 
     *  
     */
    public static String InputStreamTOString(InputStream in){
        String result = null;
        ByteArrayOutputStream outStream = null;
        if (in != null)
        {
            try
            {
                outStream = new ByteArrayOutputStream();
                byte[] data = new byte[BUFFER_SIZE];
                int count = -1;
                while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
                    outStream.write(data, 0, count);
                data = null;
                result = new String(outStream.toByteArray(), "utf-8");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    outStream.close();
                }
                catch (IOException e)
                {
                }
            }
        }
        return result;
    }
}
