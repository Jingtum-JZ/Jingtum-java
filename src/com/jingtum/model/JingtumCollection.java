package com.jingtum.model;
import java.util.List;
/**
 * Jingtume object Collection base class
 * @author jzhao
 * @version 1.0
 * @param <T>
 */
public abstract class JingtumCollection<T> extends JingtumObject {
    List<T> data;
	public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
}
