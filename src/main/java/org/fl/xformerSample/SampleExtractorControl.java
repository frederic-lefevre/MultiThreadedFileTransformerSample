/*
 * MIT License

Copyright (c) 2017, 2024 Frederic Lefevre

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
	
	private static final Logger logger = Logger.getLogger(SampleExtractorControl.class.getName());
	
	// Source file
	private static Path inputFilePath;
	
	// target file
	private static Path outputFilePath;
	
	// Eliminated records file
	private static Path eliminatedRecordsFilePath;
	
	// Atypic records file
	private static Path atypicRecordsFilePath;
	
	// Input queue and output queue size
	private static int dispatchQueueSize;
	private static int outputQueueSize;
	
	// Number of threads that builds records
	private static int nbThreads;

	private static Charset fileCharset;
	
	private static boolean initialized = false;
	
	private SampleExtractorControl() {
	}

	// Application initialization
	public static void init() {

		// Get a logger and the properties
		RunningContext runningContext = new RunningContext("org.fl.xformerSample", "propFile", DEFAULT_PROP_FILE);
		AdvancedProperties properties = runningContext.getProps();

		inputFilePath = properties.getPathFromURI("xFormerSample.input.filePath");
		outputFilePath = properties.getPathFromURI("xFormerSample.output.filePath");
		eliminatedRecordsFilePath = properties.getPathFromURI("xFormerSample.eliminated.filePath");
		atypicRecordsFilePath = properties.getPathFromURI("xFormerSample.atypic.filePath");

		if (inputFilePath == null) {
			logger.severe("Input file is not properly defined is the property file");
		}

		dispatchQueueSize = properties.getInt("xFormerSample.dispatchQueue.size", 75);
		outputQueueSize = properties.getInt("xFormerSample.outputQueue.size", 75);
		nbThreads = properties.getInt("xFormerSample.getExpertise.nbThreads", 5);

		String cs2 = properties.getProperty("xFormerSample.charset");
		try {
			fileCharset = Charset.forName(cs2);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception when getting charset. Chartset default to ISO-8859-1", e);
			fileCharset = StandardCharsets.ISO_8859_1;
		}

		initialized = true;

	}

	public static Logger getLogger() {
		if (!initialized) {
			init();
		}
		return logger;
	}

	public static Path getInputFilePath() {
		if (!initialized) {
			init();
		}
		return inputFilePath;
	}

	public static int getDispatchQueueSize() {
		if (!initialized) {
			init();
		}
		return dispatchQueueSize;
	}

	public static int getOutputQueueSize() {
		return outputQueueSize;
	}

	public static Path getOutputFilePath() {
		if (!initialized) {
			init();
		}
		return outputFilePath;
	}

	public static Path getEliminatedRecordsFilePath() {
		if (!initialized) {
			init();
		}
		return eliminatedRecordsFilePath;
	}

	public static Path getAtypicRecordsFilePath() {
		if (!initialized) {
			init();
		}
		return atypicRecordsFilePath;
	}
	

	public static Charset getFileCharset() {
		if (!initialized) {
			init();
		}
		return fileCharset;
	}

	public static int getNbThreads() {
		if (!initialized) {
			init();
		}
		return nbThreads;
	}
}
