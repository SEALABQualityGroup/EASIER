package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import restrizioniSpecifiche.Interaction;
import specificheAEmilia.AEIident;
import specificheAEmilia.Action;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionType;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Real;
import specificheAEmilia.Stop;

public class MetodiVari {

	public static boolean distinctNames(List<Action> list) {
		for (int i = 0; i < list.size(); i++) {
			Action action = list.get(i);
			ActionType actionType = action.getType();
			String string = actionType.getName();
			for (int j = i + 1; j < list.size(); j++) {
				Action action2 = list.get(j);
				ActionType actionType2 = action2.getType();
				String string2 = actionType2.getName();
				if (string.equals(string2))
					return false;
			}
		}
		return true;
	}

	// restituisce il termine di processo processTerm concatenato in modo non
	// deterministico con
	// processTerm2. processTerm puo' avere foglie uguali a null. Se a processTerm
	// non puo'
	// essere concatenato processTerm2 si restituisce null.
	//
	public static ProcessTerm somma(ProcessTerm processTerm, ProcessTerm processTerm2) {
		ProcessTerm term = null;
		// 1) processTerme'null
		if (processTerm == null)
			return processTerm2;
		// 2) processTerme'stop
		if (processTerm instanceof Stop)
			return null;
		// 3) processTerme'ActionProcess
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			ProcessTerm processTerm3 = actionProcess.getProcesso();
			term = actionProcess.copy();
			if (processTerm3 == null) {
				((ActionProcess) term).setProcesso(processTerm2 == null ? null : processTerm2.copy());
			} else {
				ProcessTerm processTerm4 = somma(processTerm3 == null ? null : processTerm3.copy(),
						processTerm2 == null ? null : processTerm2.copy());
				((ActionProcess) term).setProcesso(processTerm4);
			}
		}
		// 4) processTerme'ChoiceProcess
		if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			term = new ChoiceProcess();
			ProcessTerm[] processTerms2 = new ProcessTerm[processTerms.length];
			((ChoiceProcess) term).setProcesses(processTerms2);
			for (int i = 0; i < processTerms.length; i++) {
				ProcessTerm processTerm3 = processTerms[i];
				ProcessTerm processTerm4 = somma(processTerm3 == null ? null : processTerm3.copy(),
						processTerm2 == null ? null : processTerm2.copy());
				processTerms2[i] = processTerm4;
			}
		}
		// 5) processTerme'BehavProcess
		if (processTerm instanceof BehavProcess)
			return null;
		return term;
	}

	public static ActionProcess somma(Action action, ProcessTerm processTerm2) {
		ActionProcess actionProcess = new ActionProcess();
		actionProcess.setAzione(action);
		actionProcess.setProcesso(processTerm2);
		return actionProcess;
	}

	// restituisce le code di processTerm senza il comportamento definito
	// da processTerm2. processTerm2 puo' avere foglie uguali a null e deve essere
	// un sottocomportamento di processTerm.
	// Se none'un sottocomportamento di
	// processTerm si restituisce una lista contenente processTerm.
	// Se sono uguali si restituisce una lista vuota.
	public static List<ProcessTerm> differenza(ProcessTerm processTerm, ProcessTerm processTerm2) {
		List<ProcessTerm> list = new ArrayList<ProcessTerm>();
		// 1) processTerm2e'null
		if (processTerm2 == null) {
			if (processTerm == null) {
				list.add(null);
				return list;
			} else
				list.add(processTerm == null ? null : processTerm.copy());
		}
		// 2) processTerm2e'stop
		if (processTerm2 instanceof Stop) {
			if (processTerm instanceof Stop)
				return list;
			else
				list.add(processTerm == null ? null : processTerm.copy());
		}
		// 3) processTerm2e'ActionProcess
		if (processTerm2 instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm2;
			Action action = actionProcess.getAzione();
			if (!(processTerm instanceof ActionProcess)) {
				list.add(processTerm == null ? null : processTerm.copy());
				return list;
			}
			ActionProcess actionProcess2 = (ActionProcess) processTerm;
			Action action2 = actionProcess2.getAzione();
			if (!action.equals(action2)) {
				list.add(processTerm == null ? null : processTerm.copy());
				return list;
			}
			ProcessTerm processTerm3 = actionProcess.getProcesso();
			ProcessTerm processTerm4 = actionProcess2.getProcesso();
			List<ProcessTerm> list2 = MetodiVari.differenza(processTerm4 == null ? null : processTerm4.copy(),
					processTerm3 == null ? null : processTerm3.copy());
			// a list va aggiunto ogni elemento di list
			list.addAll(list2);
		}
		// 4) processTerm2e'ChoiceProcess
		if (processTerm2 instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm2;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			if (!(processTerm instanceof ChoiceProcess)) {
				list.add(processTerm == null ? null : processTerm.copy());
				return list;
			}
			ChoiceProcess choiceProcess2 = (ChoiceProcess) processTerm;
			ProcessTerm[] processTerms2 = choiceProcess2.getProcesses();
			if (processTerms.length != processTerms2.length) {
				list.add(processTerm == null ? null : processTerm.copy());
				return list;
			}
			for (int i = 0; i < processTerms.length; i++) {
				ProcessTerm processTerm3 = processTerms[i];
				ProcessTerm processTerm4 = processTerms2[i];
				List<ProcessTerm> list2 = MetodiVari.differenza(processTerm4 == null ? null : processTerm4.copy(),
						processTerm3 == null ? null : processTerm3.copy());
				list.addAll(list2);
			}
		}
		// 5) processTerm2e'BehavProcess
		if (processTerm2 instanceof BehavProcess) {
			if (processTerm instanceof BehavProcess) {
				if (processTerm.equals(processTerm2))
					return list;
				else
					list.add(processTerm == null ? null : processTerm.copy());
			}
		}
		return list;
	}

	public static boolean distinct(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			for (int j = i + 1; j < list.size(); j++) {
				String string2 = list.get(j);
				if (string.equals(string2))
					return false;
			}
		}
		return true;
	}

	public static boolean isOnlyReal(Expression expression) {
		// se none'un real si restiuisce false
		if (!(expression instanceof Real))
			return false;
		return true;
	}

	public static boolean isOnlyInteger(Expression expression) {
		// se none'un Integer si restiuisce false
		if (!(expression instanceof Integer))
			return false;
		return true;
	}

	public static List<String> onlyNotNull(List<String> list) {
		List<String> list2 = new ArrayList<String>();
		for (String string : list) {
			if (string != null)
				list2.add(string);
		}
		return list2;
	}

	/*
	 * Restituisce la chiamata di comportamento di processTerm. Restituisce null se
	 * processTerm non ha un BehavProcess come comportamento finale, Se
	 * processTerme'uno ChoiceProcess si restituisce la coda del primo processo.
	 */
	public static BehavProcess returnTail(ProcessTerm processTerm) {
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			BehavProcess behavProcess = returnTail(processTerm2);
			return behavProcess.copy();
		} else if (processTerm instanceof BehavProcess) {
			BehavProcess behavProcess = (BehavProcess) processTerm;
			return behavProcess.copy();
		} else if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			ProcessTerm processTerm2 = processTerms[0];
			return returnTail(processTerm2);
		} else
			return null;
	}
	
	static public String namespaceCompute(File file) {
		File file2 = file.getAbsoluteFile();
		String string = file2.getName();
		String string2 = file2.getParent();
		String string3 = string2 + File.separatorChar + "GaussJordan" + File.separatorChar + string;
		// si crea la directory relativa a
		// string2+File.separatorChar+"GaussJordan"
		File file3 = new File(string2 + File.separatorChar + "GaussJordan");
		file3.mkdir();
		return string3;
	}

	/**
	 * Restituisce una lista di oggetti interaction con istanze uguale ai nomi degli
	 * AEI di input contenuti in list, e come azioni le interazioni di input
	 * corrispondenti. list deve essere non null.
	 *
	 * @param list
	 * @return una lista non null.
	 */
	static public List<Interaction> getActionsInputFromAttacs(List<AttacDecl> list) {
		List<Interaction> list2 = new ArrayList<Interaction>();
		for (AttacDecl attacDecl : list) {
			String string = attacDecl.getInputInteraction();
			AEIident aeIident = attacDecl.getInputAei();
			Interaction interaction = new Interaction(aeIident, string);
			list2.add(interaction);
		}
		return list2;
	}

	/**
	 * Restituisce una lista di oggetti interaction con istanze uguale ai nomi degli
	 * AEI di output contenuti in list, e come azioni le interazioni di output
	 * corrispondenti.
	 *
	 * @param list
	 * @return
	 */
	static public List<Interaction> getActionsOutputFromAttacs(List<AttacDecl> list) {
		List<Interaction> list2 = new ArrayList<Interaction>();
		for (AttacDecl attacDecl : list) {
			String string = attacDecl.getOutputInteraction();
			AEIident aeIident = attacDecl.getOutputAei();
			Interaction interaction = new Interaction(aeIident, string);
			list2.add(interaction);
		}
		return list2;
	}



	public static List<AttacDecl> getAttacsFromInputInteraction(Interaction interaction, AttacDecl[] attacDecls) {
		List<AttacDecl> list = new ArrayList<AttacDecl>();
		for (AttacDecl attacDecl : attacDecls) {
			AEIident aeIident = attacDecl.getInputAei();
			String string = attacDecl.getInputInteraction();
			Interaction interaction2 = new Interaction(aeIident, string);
			if (interaction2.equals(interaction))
				list.add(attacDecl);
		}
		return list;
	}

	public static String fromStream(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder out = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		String line;
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			out.append(line);
			out.append(newLine);
		}
		return out.toString();
	}
}
	
