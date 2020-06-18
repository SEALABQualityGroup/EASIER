package it.univaq.disim.sealab.metaheuristic.managers.ocl.aemilia;

import it.univaq.disim.sealab.metaheuristic.managers.ocl.OclStringManager;

public class OclAemiliaStringManager implements OclStringManager {

	private static class ManagerHolder {
		private static final OclAemiliaStringManager INSTANCE = new OclAemiliaStringManager();
	}

	public static OclAemiliaStringManager getInstance() {
		OclAemiliaStringManager instance = ManagerHolder.INSTANCE;

		return instance;
	}

	public String getRandomAEIQuery(int start, int end) {
		String query;
		query = "ArchiElemInstance.allInstances()->asSequence()->subSequence(" + start + ", " + end + ")";
		return query;
	}

	public String getAEIListQuery() {
		String query;
		query = "ArchiElemInstance.allInstances()->asSequence()";
		return query;
	}

	public String getAllLocalInteractsQuery(String name) {
		String query;
		query = "Attachment.allInstances()->select(a:Attachment|a.end.toInstance.oclAsType(ArchiElemInstance).instanceName='"
				+ name + "' or a.start.fromInstance.oclAsType(ArchiElemInstance).instanceName='" + name
				+ "')->iterate(x : Attachment; acc : Sequence(LocalInteraction) = Sequence{} | acc->including(x.end.isInput))->asSet()->union(Attachment.allInstances()->asSequence()->select(a:Attachment|a.end.toInstance.oclAsType(ArchiElemInstance).instanceName='"
				+ name + "' or a.start.fromInstance.oclAsType(ArchiElemInstance).instanceName='" + name
				+ "')->iterate(x : Attachment; acc : Sequence(LocalInteraction) = Sequence{} | acc->including(x.start.isOutput))->asSet())";
		return query;
	}

	public String getAllInInteractsQuery() {
		String query;
		query = "InputInteraction.allInstances()->asSequence()";
		return query;
	}

	public String getAllOutInteractsQuery() {
		String query;
		query = "OutputInteraction.allInstances()->asSequence()";
		return query;
	}

	public String getAllInteractsQuery() {
		String query;
		query = "Interaction.allInstances()->select(i | i.oclIsTypeOf(InputInteraction) or i.oclIsTypeOf(OutputInteraction))->asSequence()";
		return query;
	}

	public String getAllUniInteractsQuery() {
		String query;
		query = "LocalInteraction.allInstances()->asSequence()->select(i | (i.oclIsTypeOf(InputInteraction) or i.oclIsTypeOf(OutputInteraction)) and i.type=InteractionType::UNI)->asSequence()";
		return query;
	}

	public String getAllOrInteractsQuery() {
		String query;
		query = "LocalInteraction.allInstances()->select(i | (i.oclIsTypeOf(InputInteraction) or i.oclIsTypeOf(OutputInteraction)) and i.type=InteractionType::OR)->asSequence()";
		return query;
	}

	public String getAllInLocalInteractsQuery(String name) {
		String query;
		query = "Attachment.allInstances()->select(a:Attachment|a.end.toInstance.oclAsType(ArchiElemInstance).instanceName.startsWith('"
				+ name + "')->collect(a:Attachment|a.end.isInput))";
		return query;
	}

	public String getAllOutLocalInteractsQuery(String name) {
		String query;
		query = "Attachment.allInstances()->select(a:Attachment|a.start.fromInstance.oclAsType(ArchiElemInstance).instanceName.startsWith('"
				+ name + "')->collect(a:Attachment|a.start.isOutput))";
		return query;
	}

	public String getAllOutRemoteInteractsQuery(String name) {
		String query;
		query = "Attachment.allInstances()->select(a:Attachment|a.end.toInstance.oclAsType(ArchiElemInstance).instanceName.startsWith('"
				+ name + "')->collect(a:Attachment|a.start.isOutput))";
		return query;
	}

	public String getAllInRemoteInteractsQuery(String name) {
		String query;
		query = "Attachment.allInstances()->select(a:Attachment|a.start.fromInstance.oclAsType(ArchiElemInstance).instanceName.startsWith('"
				+ name + "')->collect(a:Attachment|a.end.isInput))";
		return query;
	}

	public String getAllAttachesQuery() {
		String query;
		query = "Attachment.allInstances()->asSequence()";
		return query;
	}

	public String getAEIQuery(String name) {
		String query;
		query = "ArchiElemInstance.allInstances()->select(aei | aei.instanceName = '" + name
				+ "')->asSequence()->first()";
		return query;
	}

	public String getConstInitQuery(String name) {
		String query;
		query = "Headers::ConstInit.allInstances()->select(ci | ci.name = '" + name + "')->asSequence()->first()";
		return query;
	}

	public String getAllConstInitListQuery() {
		String query;
		query = "Headers::ConstInit.allInstances()->asSequence()";
		return query;
	}

	public String getClonedAEIQuery(String name) {
		String query;
		query = "ArchiElemInstance.allInstances()->select(aei | aei.instanceName.startsWith('" + name
				+ "'))->asSequence()->first()";
		return query;
	}
}
