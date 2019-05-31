package org.fl.xformerSample;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ibm.lge.fl.util.file.multiThreadedTransformer.ItemProcessor;

public class SampleItemProcessor extends ItemProcessor {

	private String linePrefix ;
	private Logger logger ;
	
	public SampleItemProcessor(String lp, Logger l) {
		linePrefix = lp ;
		logger	   = l ;
	}

	@Override
	public ItemProcessor getClone() {
		
		return new SampleItemProcessor(linePrefix, logger) ;
	}

	@Override
	public CharSequence processItem(ArrayList<String> currentEntry) {
		
		StringBuilder xFormLines = new StringBuilder() ;
		
		// Intervertit les lignes et ajoute un prefix
		for (int lineNum = currentEntry.size()-1; lineNum > -1; lineNum--) {
			String line = currentEntry.get(lineNum) ;
			if (line.startsWith("ATYPIC LINE")) {
				try {
					atypicEntries.put("Atypic line found=" + line);
				} catch (InterruptedException e) {
					logger.log(Level.WARNING, "Exception when processing atypic line" + line, e) ;
				}
			}
			xFormLines.append(linePrefix).append(lineNum).append(line).append("\n") ;
		}
		return xFormLines ;
	}

}
