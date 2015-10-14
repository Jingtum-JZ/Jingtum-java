package com.jingtum.model;

import java.util.List;

public abstract class JingtumCollection<T> extends JingtumObject {
	
    Boolean hasMore;
    List<T> data;

	public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

}
