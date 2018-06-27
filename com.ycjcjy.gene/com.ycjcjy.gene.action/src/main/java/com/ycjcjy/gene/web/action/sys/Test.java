package com.ycjcjy.gene.web.action.sys;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        try {
            String[] arr = {"#new-4-6","#new-4-5","#new-4-4","#new-4-3","#new","#web-application","#file-type",
                    "#spinner","#form-control","#payment","#chart","#currency","#text-editor","#directional","#video-player","#brand","#medical"};
            Document doc =Jsoup.connect("http://amazeui.org/css/icon").get();
            Elements elements;
            Elements ilist;
            List<String> lsit = new ArrayList<>();

            for (int j = 0; j <arr.length ; j++) {
                elements = doc.select(arr[j]);
                ilist = elements.select("i");
                for (int i = 0; i < ilist.size(); i++) {
                    lsit.add(ilist.get(i).className());
                    System.out.print("'"+ilist.get(i).className()+"',");
                }
            }
            System.out.println("lsit.size() = "+lsit.size());



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
