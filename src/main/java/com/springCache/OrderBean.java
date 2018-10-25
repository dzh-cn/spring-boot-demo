package com.springCache;

/**
 * bean
 *
 * @author: dongzhihua
 * @time: 2018/10/25 12:06:13
 */
public class OrderBean {
    private Integer id;
    private String name;

    public OrderBean() {
    }

    public OrderBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
