package com.printnode.api;

/**
 * Object to be serailized into JSON in createPrintJob.
 * */
public class Options {

    /**
     * Which bin you want to use....
     * */
    private String bin;
    /**
     * Whether you want to have collate enabled.
     * */
    private Boolean collate;
    /**
     * the amount of copies you want to have.
     * */
    private int copies = -1;
    /**
     * The DPI you want to use.
     * */
    private String dpi;
    /**
     * The duplex you want to use.
     * */
    private String duplex;
    /**
     * Whether you want to fit the printjob to page.
     * */
    private Boolean fitToPage;
    /**
     * Which type of media you want to use.
     * */
    private String media;
    /**
     * The NUP you want to use.
     * */
    private int nup = -1;
    /**
     * Which pages of the printjob you want to print.
     * */
    private String pages;
    /**
     * What type of paper you are using.
     * */
    private String paper;
    /**
     * Rotation of the printjob.
     * */
    private int rotate = -1;

    /**
     * Default constructor.
     * */
    public Options() {
    }

    /**
     * @param newBin set the bin we want to use.
     * */
    public final void setBin(final String newBin) {
        bin = newBin;
    }

    /**
     * @param newCollate Set whether we want to collate.
     * */
    public final void setCollate(final boolean newCollate) {
        collate = newCollate;
    }

    /**
     * @param newCopies Set the amount of copies we want to print.
     * */
    public final void setCopies(final int newCopies) {
        copies = newCopies;
    }

    /**
     * @param newDpi Set the DPI.
     * */
    public final void setDpi(final String newDpi) {
        dpi = newDpi;
    }

    /**
     * @param newDuplex Set the duplex.
     * */
    public final void setDuplex(final String newDuplex) {
        duplex = newDuplex;
    }

    /**
     * @param newFitToPage set whether we want to fit the content to the page.
     * */
    public final void setFitToPage(final boolean newFitToPage) {
        fitToPage = newFitToPage;
    }

    /**
     * @param newMedia Set the media for the printer.
     * */
    public final void setMedia(final String newMedia) {
        media = newMedia;
    }

    /**
     * @param newNup Set number of pages to print per page for a compact layout printing.
     * */
    public final void setNup(final int newNup) {
        nup = newNup;
    }

    /**
     * @param newPages set the pages to print.
     * */
    public final void setPages(final String newPages) {
        pages = newPages;
    }

    /**
     * @param newPaper set paper type.
     * */
    public final void setPaper(final String newPaper) {
        paper = newPaper;
    }

    /**
     * @param newRotate set rotation. Can only be 90,180 or 270.
     * */
    public final void setRotate(final int newRotate) {
        rotate = newRotate;
    }


}
