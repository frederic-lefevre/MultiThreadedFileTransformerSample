/*
 * MIT License

Copyright (c) 2017, 2023 Frederic Lefevre

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package org.fl.xformerSample;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fl.util.AdvancedProperties;
import org.fl.util.RunningContext;

public class SampleExtractorControl {

	// Property file name
	private static final String DEFAULT_PROP_FILE = "xFormerSample.properties";
	
	private Logger logger ;
	
	// Source file
	private Path inputFilePath ;
	
	// target file
	private Path outputFilePath ;
	
	// Eliminated records file
	private Path eliminatedRecordsFilePath ;
	
	// Atypic records file
	private Path atypicRecordsFilePath ;
	
	// Input queue and output queue size
	private int dispatchQueueSize ;
	private int outputQueueSize ;
	
	// Number of threads that builds records
	private int nbThreads ;

	private Charset fileCharset ;
	
	public SampleExtractorControl() {
		init() ;
	}

	// Application initialization
	public void init() {
	
		// Get a logger and the properties		
		RunningContext runningContext = new RunningContext("Sample xFormer", "propFile", DEFAULT_PROP_FILE);
		logger 		   		  		  = runningContext.getpLog() ;
		AdvancedProperties properties = runningContext.getProps() ;
		
		inputFilePath	   		  = properties.getPathFromURI("xFormerSample.input.filePath") ;
		outputFilePath 		  	  = properties.getPathFromURI("xFormerSample.output.filePath") ;
		eliminatedRecordsFilePath = properties.getPathFromURI("xFormerSample.eliminated.filePath") ;
		atypicRecordsFilePath 	  = properties.getPathFromURI("xFormerSample.atypic.filePath") ;
		
		if (inputFilePath == null) {
			logger.severe("Input file is not properly defined is the property file") ;
		} 
		
		dispatchQueueSize 	  = properties.getInt("xFormerSample.dispatchQueue.size", 	   75) ;
		outputQueueSize		  = properties.getInt("xFormerSample.outputQueue.size", 	   75) ;
		nbThreads = properties.getInt("xFormerSample.getExpertise.nbThreads",  5) ;
		
		String cs2 = properties.getProperty("xFormerSample.charset") ;
		try {
			fileCharset = Charset.forName(cs2) ;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception when getting charset. Chartset default to ISO-8859-1", e);
			fileCharset			= StandardCharsets.ISO_8859_1 ;
		}

	}

	public Logger getLogger() {
		return logger;
	}

	public Path getInputFilePath() {
		return inputFilePath;
	}

	public int getDispatchQueueSize() {
		return dispatchQueueSize;
	}

	public int getOutputQueueSize() {
		return outputQueueSize;
	}

	public Path getOutputFilePath() {
		return outputFilePath;
	}

	public Path getEliminatedRecordsFilePath() {
		return eliminatedRecordsFilePath;
	}

	public Path getAtypicRecordsFilePath() {
		return atypicRecordsFilePath;
	}
	

	public Charset getFileCharset() {
		return fileCharset;
	}

	public int getNbThreads() {
		return nbThreads;
	}
}
