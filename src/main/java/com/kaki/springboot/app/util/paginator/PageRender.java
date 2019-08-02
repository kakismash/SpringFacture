package com.kaki.springboot.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;


public class PageRender<T> {

	private String url;
	private Page<T> page;
	
	private List<PageItem> pages;
	
	private int currentPage;
	
	private int totalPages;
	private int numElemtPerPage;
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.pages = new ArrayList<PageItem>();
		
		numElemtPerPage = page.getSize();
		totalPages = page.getTotalPages();
		
		currentPage = page.getNumber() + 1;
		
		int from, to;
		if(totalPages <= numElemtPerPage) {
			from = 1;
			to = totalPages;
		}else {
			if(currentPage <= numElemtPerPage/2) {
				from = 1;
				to = numElemtPerPage;
			}else if(currentPage >= totalPages - numElemtPerPage/2) {
				from = totalPages - numElemtPerPage + 1;
				to = numElemtPerPage;
			}else {
				from = currentPage - numElemtPerPage/2;
				to = numElemtPerPage;
			}
		}
		
		for(int i = 0; i < to; i++) {
			pages.add(new PageItem(from + i, currentPage == from + i));
		}
	}
	public String getUrl() {
		return url;
	}
	public List<PageItem> getPages() {
		return pages;
	}
	public int getTotalPages() {
		return totalPages;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	public boolean isHasNext() {
		return page.hasNext();
	}
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
	
}
