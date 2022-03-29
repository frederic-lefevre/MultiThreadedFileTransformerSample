package org.fl.xformerSample;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.fl.util.file.multiThreadedTransformer.ItemsExtractor;

public class SampleExtractor extends ItemsExtractor {

	private long lineNumber ;
	
	private final static String ELIMINATED_LINE = "WRONG LINE" ;
	
	public SampleExtractor(Path ip,
							Charset ics,
							Path	op,
							Charset ocs,
							Path	ep,
							Path	ap,
							Logger l) {
		super(ip, ics, op, ocs, ep, ap, l) ;
		lineNumber = 0 ;
	}

	@Override
	protected boolean isValidFirstLineEntry(String line) {
		
		// ligne paire et valide
		if (((lineNumber % 2) == 0) && (! line.startsWith(ELIMINATED_LINE))) {
			lineNumber++ ;
			return true ;
		} else {
			return false ;
		}
	}

	@Override
	protected boolean belongsToCurrentEntry(ArrayList<String> currentEntry, String line) {
		
		// ligne impaire et valide
		if (((lineNumber % 2) == 1) && (! line.startsWith(ELIMINATED_LINE))) {
			lineNumber++ ;
			return true ;
		} else {
			return false ;
		}
	}

}
