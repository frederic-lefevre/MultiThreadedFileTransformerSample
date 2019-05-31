package org.fl.xformerSample;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.logging.Logger;

public class SampleExtractorMain {

	public static void main(String[] args) {

		SampleExtractorControl sampleExtractorControl = new SampleExtractorControl() ;
		Logger logger = sampleExtractorControl.getLogger() ;

		Path inputFile 			   	   = sampleExtractorControl.getInputFilePath() ;
		Path outputFilePath 	   	   = sampleExtractorControl.getOutputFilePath() ;
		Path eliminatedRecordsFilePath = sampleExtractorControl.getEliminatedRecordsFilePath() ;
		Path atypicRecordsFilePath	   = sampleExtractorControl.getAtypicRecordsFilePath() ;
		Charset allCharset 			   = sampleExtractorControl.getFileCharset() ;
		SampleExtractor sampleExtractor = new SampleExtractor(inputFile,
															  allCharset,
															  outputFilePath,
															  allCharset,																   
															  eliminatedRecordsFilePath,
															  atypicRecordsFilePath,
															  logger) ;
		
		

		SampleItemProcessor sampleProcessor = new SampleItemProcessor("Line prefix: ", logger) ;
		
		sampleExtractor.extract(sampleProcessor);
		
	}
	
}
