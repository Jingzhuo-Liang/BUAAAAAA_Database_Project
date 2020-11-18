package com.evan.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin_menu")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    private String path;
    private String name;
    private String nameZh; //中文名称，用于渲染导航栏（菜单）界面
    private String iconCls;
    private String component;
    private int parentId;

    // 用于储存子节点
    @Transient
    List<AdminMenu> children;

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public String getIconCls() {
        return iconCls;
    }

    public String getComponent() {
        return component;
    }

    public int getParentId() {
        return parentId;
    }

    public List<AdminMenu> getChildren() {
        return children;
    }

}
