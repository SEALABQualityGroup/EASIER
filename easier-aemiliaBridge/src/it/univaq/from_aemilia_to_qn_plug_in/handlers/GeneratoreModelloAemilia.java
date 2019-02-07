/**
 * 
 */
package it.univaq.from_aemilia_to_qn_plug_in.handlers;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import aemiliaParser.AEMparser;
import aemiliaParser.ParseException;
import equivalenzaComportamentale.secondRelease.Equivalenza2;
import it.univaq.from_aemilia_to_qn_plug_in.serialize.MMAemiliaSerialize;
import restrizioniGenerali.RestrizioniGenException;
import restrizioniGenerali.secondRelease.GeneraliRules2;
import restrizioniIstanze.RestrizioniIstanzeException;
import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import restrizioniIstanze.restrizioniIstanze.RestrizioniIstanze;
import specificheAEmilia.ArchiType;
import utilities.ErrorMessage;
import utilities.MetodiVari;

/**
 * @author Mirko
 *
 */
public class GeneratoreModelloAemilia{

	public Object execute(FileInputStream aemiliaFileInputStream, Path aemiliaModelFilePath,
			Path destinationPath) {
		// ASE qui fa Text-to-Model
		AEMparser aeMparser = new AEMparser(aemiliaFileInputStream);
		try {
			ArchiType archiType = aeMparser.ArchiType();
			GeneraliRules2 generaliRules = new GeneraliRules2(archiType);
			boolean b;
			try {
				b = generaliRules.isCompliantGeneralRules(false);
			} catch (RestrizioniGenException e) {
				e.printStackTrace();
				return null;
			}
			if (b) {
				List<Equivalenza2> list = generaliRules.getEquivalenze();
				RestrizioniIstanze restrizioniIstanze;
				try {
					restrizioniIstanze = new RestrizioniIstanze(list, archiType, false);
				} catch (RestrizioniIstanzeException e) {
					e.printStackTrace();
					return null;
				}
				if (!restrizioniIstanze.isErrorOccurred()) {
					// String modelPath_noExtention = aemiliaModelFilePath +
					// aemiliaModelIPath.removeFileExtension().toString();
					// String mmAemiliaFile = modelPath_noExtention + ".mmaemilia";
					// IPath path_noExtention = aemiliaModelIPath.removeFileExtension();
					// String aemFileName = path_noExtention.lastSegment();

					// aemFileName = aemFileName + ".mmaemilia";

					ArchiType archiType2 = restrizioniIstanze.getArchiType();
					List<ElemTypeNorm> elemTypeNormList = restrizioniIstanze.getElemTypeNormList();
					// ASE in UpdateMMDialog esegue il salvataggio

					MMAemiliaSerialize mmAemiliaSerialize = new MMAemiliaSerialize();
					mmAemiliaSerialize.serialize(archiType2, elemTypeNormList, destinationPath);

					return null;
				} else {
					ErrorMessage errorMessage = restrizioniIstanze.getErrorMessage();
					System.out.println(errorMessage.toString());
					return null;
				}
			} else {
				ErrorMessage errorMessage = generaliRules.getErrorMessage();
				System.out.println(errorMessage.toString());
				return null;
			}
		} catch (ParseException e) {
			try {
				System.out.println("Scanning error: " + MetodiVari.fromStream(aemiliaFileInputStream)
						+ "is not a valid architectural type" + "\n");
			} catch (IOException e1) {
				e.printStackTrace();
				return null;
			}
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
