package org.github.xhjcehust.dubbo.provider.model;

import java.io.Serializable;

/**
 * @author xiaohengjin<xiaohengjin@corp.netease.com>
 * @date 2019/8/10
 */
public class Pojo implements Serializable {

    private static final long serialVersionUID = 405880720364534136L;

    private Integer count;

    private String value;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "count=" + count +
                ", value='" + value + '\'' +
                '}';
    }
}
