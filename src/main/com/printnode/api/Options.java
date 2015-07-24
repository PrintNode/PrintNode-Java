package com.printnode.api;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Object to be serailized into JSON in createPrintJob.
 * */
public class Options{

	private String bin;
	private Boolean collate;
	private int copies = -1;
	private String dpi;
	private String duplex;
	private Boolean fitToPage;
	private String media;
	private int nup = -1;
	private String pages;
	private String paper;
	private int rotate = -1;

	/**
	 * Default constructor.
	 * */
	public Options(){
	}

	/**
	 * @param bin set the bin we want to use.
	 * */
	public void setBin(String bin){
		this.bin = bin;
	}

	/**
	 * @param collate Set whether we want to collate.
	 * */
	public void setCollate(boolean collate){
		this.collate = collate;
	}

	/**
	 * @param copies Set the amount of copies we want to print.
	 * */
	public void setCopies(int copies){
		this.copies = copies;
	}

	/**
	 * @param dpi Set the DPI.
	 * */
	public void setDpi(String dpi){
		this.dpi = dpi;
	}

	/**
	 * @param duplex Set the duplex.
	 * */
	public void setDuplex(String duplex){
		this.duplex = duplex;
	}

	/**
	 * @param fitToPage set whether we want to fit the content to the page.
	 * */
	public void setFitToPage(boolean fitToPage){
		this.fitToPage = fitToPage;
	}

	/**
	 * @param media Set the media for the printer.
	 * */
	public void setMedia(String media){
		this.media = media;
	}

	/**
	 * @param nup Set number of pages to print per page for a compact layout printing.
	 * */
	public void setNup(int nup){
		this.nup = nup;
	}

	/**
	 * @param pages set the pages to print.
	 * */
	public void setPages(String pages){
		this.pages = pages;
	}

	/**
	 * @param paper set paper type.
	 * */
	public void setPaper(String paper){
		this.paper = paper;
	}

	/**
	 * @param rotate set rotation. Can only be 90,180 or 270.
	 * */
	public void setRotate(int rotate){
		this.rotate = rotate;
	}


}
