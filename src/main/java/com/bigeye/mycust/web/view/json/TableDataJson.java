package com.bigeye.mycust.web.view.json;

import java.util.List;

public final class TableDataJson <BaseEntityJson> {
	
	private List<BaseEntityJson> data;
	private int draw;
	private Long recordsTotal;
	private Long recordsFiltered = new Long(0);
	
	/**
	 * @return the data
	 */
	public List<BaseEntityJson> getData() {
		return data;
	}




	/**
	 * @param data the data to set
	 */
	public void setData(List<BaseEntityJson> data) {
		this.data = data;
	}




	/**
	 * @return the draw
	 */
	public int getDraw() {
		return draw;
	}


	/**
	 * @param draw the draw to set
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}


	/**
	 * @return the recordsTotal
	 */
	public Long getRecordsTotal() {
		return recordsTotal;
	}


	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}


	/**
	 * @return the recordsFiltered
	 */
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}


	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	
	
}
