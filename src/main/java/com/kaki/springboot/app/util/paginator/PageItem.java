package com.kaki.springboot.app.util.paginator;

public class PageItem {

	private int numb;
	private boolean current;
	
	public PageItem(int numb, boolean current) {
		this.numb = numb;
		this.current = current;
	}
	public int getNumb() {
		return numb;
	}
	public boolean isCurrent() {
		return current;
	}
	
	
	
}
