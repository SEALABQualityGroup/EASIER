package it.univaq.disim.sealab.metaheuristic.twoeagles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.sealab.ttep.val.classes.ValSpec;
import it.univaq.disim.sealab.ttep.val.classes.MeasureValue;

public class NewValParser {

	public static ValSpec parsingValFile(Path valFilePath) {

		List<MeasureValue> measures = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(valFilePath.toFile()))) {
			for (String line; (line = br.readLine()) != null;) {
				if (line.startsWith("-")) {
					measures.add(new MeasureValue(line.substring(line.indexOf("\"") + 1, line.indexOf(":")-1),
							Float.parseFloat(br.readLine())));
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ValSpec(measures);
	}

}
