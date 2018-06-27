package com.ycjcjy.gene.VO;

import net.onebean.core.BaseTree;
import com.ycjcjy.gene.model.SysPermission;

import java.util.List;

/**
 * 菜单树模型
 */
public class MenuTree extends BaseTree {

    public final static String TYPE_ITEM = "item";
    public final static String TYPE_FOLDER = "folder";

    /**
     * ID
     */
    private Long id;
    private String url;
    private String menu_type;
    private String name;
    private Integer sort;
    private List<MenuTree> childList;
    private List<SysPermission> dataList;


    public List<SysPermission> getDataList() {
        return dataList;
    }

    public void setDataList(List<SysPermission> dataList) {
        this.dataList = dataList;
    }

    public List<MenuTree> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuTree> childList) {
        this.childList = childList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
