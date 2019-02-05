/**
 * 
 */
package it.univaq.from_aemilia_to_qn_plug_in.serialize;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import equivalenzaComportamentale.AETinteractionsParts;
import metamodel.mmaemilia.AEmiliaSpecification;
import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.From;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.InteractionType;
import metamodel.mmaemilia.LocalInteraction;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.To;
import metamodel.mmaemilia.mmaemiliaFactory;
import metamodel.mmaemilia.mmaemiliaPackage;
import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.Behavior;
import metamodel.mmaemilia.Behavior.BehaviorFactory;
import metamodel.mmaemilia.Behavior.RatePas;
import metamodel.mmaemilia.DataType.Array;
import metamodel.mmaemilia.DataType.DataTypeFactory;
import metamodel.mmaemilia.DataType.Normal;
import metamodel.mmaemilia.DataType.RangeInt;
import metamodel.mmaemilia.DataType.Record;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Expressions.ArithExpr;
import metamodel.mmaemilia.Expressions.ArithOpName;
import metamodel.mmaemilia.Expressions.ArrayExpr;
import metamodel.mmaemilia.Expressions.ArrayOpName;
import metamodel.mmaemilia.Expressions.BoolOpName;
import metamodel.mmaemilia.Expressions.BooleanExpr;
import metamodel.mmaemilia.Expressions.ExpressionsFactory;
import metamodel.mmaemilia.Expressions.IdentifierType;
import metamodel.mmaemilia.Expressions.ListExpr;
import metamodel.mmaemilia.Expressions.ListOpName;
import metamodel.mmaemilia.Expressions.RecordExpr;
import metamodel.mmaemilia.Expressions.RecordOpName;
import metamodel.mmaemilia.Expressions.RelatOpName;
import metamodel.mmaemilia.Expressions.RelationalExpr;
import metamodel.mmaemilia.Headers.AT_Header;
import metamodel.mmaemilia.Headers.BehavHeader;
import metamodel.mmaemilia.Headers.Const;
import metamodel.mmaemilia.Headers.ConstInit;
import metamodel.mmaemilia.Headers.ET_Header;
import metamodel.mmaemilia.Headers.HeadersFactory;
import metamodel.mmaemilia.Headers.LeftSide;
import metamodel.mmaemilia.Headers.Local;
import metamodel.mmaemilia.Headers.RightSide;
import metamodel.mmaemilia.Headers.Var;
import metamodel.mmaemilia.Headers.VarInit;
import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.ANDinteractions;
import specificheAEmilia.Action;
import specificheAEmilia.ActionInput;
import specificheAEmilia.ActionOutput;
import specificheAEmilia.ActionProcess;
import specificheAEmilia.ActionRate;
import specificheAEmilia.ActionType;
import specificheAEmilia.And;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiElemTypes;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiTopology;
import specificheAEmilia.ArchiType;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.ArrayType;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.BehavProcess;
import specificheAEmilia.Boolean;
import specificheAEmilia.BooleanType;
import specificheAEmilia.ChoiceProcess;
import specificheAEmilia.Concat;
import specificheAEmilia.DataType;
import specificheAEmilia.Different;
import specificheAEmilia.Divisione;
import specificheAEmilia.ElemType;
import specificheAEmilia.Equal;
import specificheAEmilia.Expression;
import specificheAEmilia.First;
import specificheAEmilia.Get;
import specificheAEmilia.Header;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.InputInteractions;
import specificheAEmilia.Insert;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.IntegerType;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.Length;
import specificheAEmilia.ListCons;
import specificheAEmilia.ListType;
import specificheAEmilia.Maggiore;
import specificheAEmilia.MaggioreUguale;
import specificheAEmilia.Minore;
import specificheAEmilia.MinoreUguale;
import specificheAEmilia.Moltiplicazione;
import specificheAEmilia.Negazione;
import specificheAEmilia.NormalType;
import specificheAEmilia.ORinteractions;
import specificheAEmilia.Or;
import specificheAEmilia.OutputInteractions;
import specificheAEmilia.ParamDeclaration;
import specificheAEmilia.PrioType;
import specificheAEmilia.ProcessTerm;
import specificheAEmilia.Put;
import specificheAEmilia.RateExp;
import specificheAEmilia.RateInf;
import specificheAEmilia.RateType;
import specificheAEmilia.Rate_;
import specificheAEmilia.Read;
import specificheAEmilia.Real;
import specificheAEmilia.RealType;
import specificheAEmilia.RecordCons;
import specificheAEmilia.RecordType;
import specificheAEmilia.Remove;
import specificheAEmilia.Somma;
import specificheAEmilia.Sottrazione;
import specificheAEmilia.Stop;
import specificheAEmilia.Tail;
import specificheAEmilia.UNIinteractions;
import specificheAEmilia.VariableDeclaration;
import specificheAEmilia.WeightType;
import specificheAEmilia.Write;

/**
 * @author Mirko
 *
 */
public class MMAemiliaSerialize {

	// ASE Serializza il modello e lo salve nel file
	public void serialize(ArchiType archiType, List<ElemTypeNorm> elemTypeNorms, String mmAemiliaPath)
			throws IOException {
		AEmiliaSpecification aEmiliaSpecification = mmaemiliaFactory.eINSTANCE.createAEmiliaSpecification();
		metamodel.mmaemilia.ArchiType archiType2 = mmaemiliaFactory.eINSTANCE.createArchiType();
		aEmiliaSpecification.setArchiTypeDecl(archiType2);
		Header header = archiType.getArchiTypeHeader();
		// impostiamo il nome dell'archiType2
		String string = header.getName();
		archiType2.setAtName(string);
		// impostiamo l'header per archiType2
		AT_Header at_Header = HeadersFactory.eINSTANCE.createAT_Header();
		// imposto i parametri costanti inizializzati per at_Header
		ParamDeclaration[] paramDeclarations = header.getParameters();
		List<ConstInit> constInits = at_Header.getInitConst();
		for (int i = 0; i < paramDeclarations.length; i++) {
			if (paramDeclarations[i] != null) {
				ConstInit constInit = HeadersFactory.eINSTANCE.createConstInit();
				specificheAEmilia.ConstInit constInit2 = (specificheAEmilia.ConstInit) paramDeclarations[i];
				String string2 = constInit2.getName();
				DataType dataType = constInit2.getType();
				Expression expression = constInit2.getExpr();
				// imposto il nome per constInit
				constInit.setName(string2);
				// imposto il tipo di dato per constInit
				metamodel.mmaemilia.DataType.DataType dataType2 = getDataType(dataType);
				constInit.setInitConstData(dataType2);
				// imposto l'espressione per constInit
				metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
				constInit.setInitConstExpr(expression2);
				constInits.add(constInit);
			}
		}
		archiType2.setHeader(at_Header);
		// impostiamo i tipi di elementi architetturali per archiType2
		ArchiElemTypes archiElemTypes = archiType.getArchiElemTypes();
		metamodel.mmaemilia.ArchiElemTypes archiElemTypes2 = mmaemiliaFactory.eINSTANCE.createArchiElemTypes();
		ElemType[] elemTypes = archiElemTypes.getElementTypes();
		List<metamodel.mmaemilia.ElemType> elemTypes2 = archiElemTypes2.getEtDeclaration();
		for (ElemType elemType : elemTypes) {
			// aggiungo elemType a elemTypes2
			metamodel.mmaemilia.ElemType elemType2 = mmaemiliaFactory.eINSTANCE.createElemType();
			Header headerEt = elemType.getHeader();
			// imposto il nome per elemType2
			String string2 = headerEt.getName();
			elemType2.setEtName(string2);
			// imposto l'header per elemType2
			ET_Header et_Header = HeadersFactory.eINSTANCE.createET_Header();
			// imposto i parametri costanti per et_Header
			ParamDeclaration[] paramDeclarations2 = headerEt.getParameters();
			List<Const> list = et_Header.getCostant();
			for (ParamDeclaration paramDeclaration : paramDeclarations2) {
				if (paramDeclaration != null) {
					specificheAEmilia.Const const1 = (specificheAEmilia.Const) paramDeclaration;
					Const const2 = HeadersFactory.eINSTANCE.createConst();
					String string3 = const1.getName();
					DataType dataType = const1.getType();
					metamodel.mmaemilia.DataType.DataType dataType2 = getDataType(dataType);
					const2.setName(string3);
					const2.setConstantData(dataType2);
					list.add(const2);
				}
			}
			elemType2.setElemHeader(et_Header);
			// imposto il comportamento per elemType2
			Behavior behavior = BehaviorFactory.eINSTANCE.createBehavior();
			// imposto le equazioni comportamentali per behavior
			List<BehavEquation> behavEquations = behavior.getEquations();
			AETbehavior aeTbehavior = elemType.getBehavior();
			specificheAEmilia.BehavEquation[] behavEquations2 = aeTbehavior.getBehaviors();
			for (specificheAEmilia.BehavEquation behavEquation : behavEquations2) {
				BehavEquation behavEquation2 = BehaviorFactory.eINSTANCE.createBehavEquation();
				// imposto il nome per behavEquation2
				Header header2 = behavEquation.getBehavHeader();
				String string3 = header2.getName();
				behavEquation2.setName(string3);
				// imposto l'intestazione per behavEquation2
				BehavHeader behavHeader = HeadersFactory.eINSTANCE.createBehavHeader();
				ParamDeclaration[] paramDeclarations3 = header2.getParameters();
				// imposto la parte destra di behavHeader
				RightSide rightSide = HeadersFactory.eINSTANCE.createRightSide();
				// imposto le variabili locali di rightSide
				List<Local> locals = rightSide.getLocalDef();
				List<Local> locals2 = getLocalVars(paramDeclarations3);
				locals.addAll(locals2);
				behavHeader.setRight(rightSide);
				// imposto la parte sinistra di behavHeader
				LeftSide leftSide = HeadersFactory.eINSTANCE.createLeftSide();
				// imposto le variabili inizializzate di leftSide
				List<VarInit> varInits = leftSide.getInitVar();
				List<VarInit> varInits2 = getInitVars(paramDeclarations3);
				varInits.addAll(varInits2);
				// imposto le variabili non inizializzate di leftSide
				List<Var> vars = leftSide.getVarDef();
				List<Var> vars2 = getVars(paramDeclarations3);
				vars.addAll(vars2);
				behavHeader.setLeft(leftSide);
				behavEquation2.setBHeader(behavHeader);
				behavEquations.add(behavEquation2);
			}
			elemType2.setBehaviorDecl(behavior);
			// imposto i termini di processo di behavEquations
			for (int i = 0; i < behavEquations2.length; i++) {
				specificheAEmilia.BehavEquation behavEquation = behavEquations2[i];
				BehavEquation behavEquation2 = behavEquations.get(i);
				// imposto il termine di processo per behavEquation2
				specificheAEmilia.ProcessTerm processTerm = behavEquation.getTermineProcesso();
				metamodel.mmaemilia.Behavior.ProcessTerm processTerm2 = getProcessTerm(processTerm, elemType2,
						elemType);
				behavEquation2.setPt(processTerm2);
			}
			AETinteractions aeTinteractions = elemType.getInteractions();
			InputInteractions inputInteractions = aeTinteractions.getInIn();
			OutputInteractions outputInteractions = aeTinteractions.getOuIn();
			ANDinteractions anDinteractionsInput = inputInteractions != null ? inputInteractions.getAnd() : null;
			ORinteractions oRinteractionsInput = inputInteractions != null ? inputInteractions.getOr() : null;
			UNIinteractions unIinteractionsInput = inputInteractions != null ? inputInteractions.getUni() : null;
			ANDinteractions anDinteractionsOutput = outputInteractions != null ? outputInteractions.getAnd() : null;
			ORinteractions oRinteractionsOutput = outputInteractions != null ? outputInteractions.getOr() : null;
			UNIinteractions unIinteractionsOutput = outputInteractions != null ? outputInteractions.getUni() : null;
			String[] strings = anDinteractionsInput != null ? anDinteractionsInput.getActions() : new String[0];
			String[] strings2 = oRinteractionsInput != null ? oRinteractionsInput.getActions() : new String[0];
			String[] strings3 = unIinteractionsInput != null ? unIinteractionsInput.getActions() : new String[0];
			String[] strings4 = anDinteractionsOutput != null ? anDinteractionsOutput.getActions() : new String[0];
			String[] strings5 = oRinteractionsOutput != null ? oRinteractionsOutput.getActions() : new String[0];
			String[] strings6 = unIinteractionsOutput != null ? unIinteractionsOutput.getActions() : new String[0];
			List<InputInteraction> inputInteractions2 = elemType2.getIiDecl();
			// imposto le input interactions di elemType2
			// imposto le and interaction
			for (String string3 : strings) {
				InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
				inputInteraction.setIntName(string3);
				inputInteraction.setType(InteractionType.AND);
				inputInteractions2.add(inputInteraction);
			}
			// imposto le or interaction
			for (String string3 : strings2) {
				InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
				inputInteraction.setIntName(string3);
				inputInteraction.setType(InteractionType.OR);
				inputInteractions2.add(inputInteraction);
			}
			// imposto le uni interaction
			for (String string3 : strings3) {
				InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
				inputInteraction.setIntName(string3);
				inputInteraction.setType(InteractionType.UNI);
				inputInteractions2.add(inputInteraction);
			}
			List<OutputInteraction> outputInteractions2 = elemType2.getOiDecl();
			// imposto le output interactions di elemType2
			// imposto le and interaction
			for (String string3 : strings4) {
				OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
				outputInteraction.setIntName(string3);
				outputInteraction.setType(InteractionType.AND);
				outputInteractions2.add(outputInteraction);
			}
			// imposto le or interaction
			for (String string3 : strings5) {
				OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
				outputInteraction.setIntName(string3);
				outputInteraction.setType(InteractionType.OR);
				outputInteractions2.add(outputInteraction);
			}
			// imposto le uni interaction
			for (String string3 : strings6) {
				OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
				outputInteraction.setIntName(string3);
				outputInteraction.setType(InteractionType.UNI);
				outputInteractions2.add(outputInteraction);
			}
			elemTypes2.add(elemType2);
		}
		archiType2.setAetDeclaration(archiElemTypes2);
		// impostaimo la topologia per archiType2
		ArchiTopology archiTopology = archiType.getTopologia();
		metamodel.mmaemilia.ArchiTopology archiTopology2 = mmaemiliaFactory.eINSTANCE.createArchiTopology();
		// impostiamo le istanze per archiTopology2
		ArchiElemInstances archiElemInstances = archiTopology.getAEIs();
		AEIdecl[] aeIdecls = archiElemInstances.getAEIdeclSeq();
		List<ArchiElemInstance> elemInstances = archiTopology2.getAeiDecl();
		for (AEIdecl idecl : aeIdecls) {
			ArchiElemInstance archiElemInstance = mmaemiliaFactory.eINSTANCE.createArchiElemInstance();
			// impostiamo il nome dell'istanza per archiElemInstance
			AEIident aeIident = idecl.getAeIident();
			String string2 = aeIident.getName();
			Expression selector = aeIident.getSelector();
			archiElemInstance.setInstanceName(string2);
			// impostaimo i parametri attuali per archiElemInstance
			Expression[] expressions = idecl.getActualParams();
			List<String> list = archiElemInstance.getActualParam();
			for (Expression expression : expressions) {
				String string3 = expression.toString();
				list.add(string3);
			}
			// impostiamo il tipo di elemento architetturale per archiElemInstance
			String string3 = idecl.getAET();
			metamodel.mmaemilia.ElemType elemType = getElemTypeFromAetName(elemTypes2, string3);
			archiElemInstance.setTypeOf(elemType);
			elemInstances.add(archiElemInstance);
			if (selector != null) {
				metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(selector);
				archiElemInstance.setSelector(expression2);
			}
			// si imposta elem di idecl
			for (ElemTypeNorm elemTypeNorm : elemTypeNorms) {
				AEIdecl aeIdecl = elemTypeNorm.getAEIdecl();
				ElemType elemType2 = elemTypeNorm.getNewElemType();
				AETbehavior aeTbehavior = elemType2.getBehavior();
				if (idecl.equals(aeIdecl)) {
					Elem elem = mmaemiliaFactory.eINSTANCE.createElem();
					archiElemInstance.setElem(elem);
					Behavior behavior = getBehavior(aeTbehavior, elemType, elemType2, archiElemInstance);
					elem.setBehaviorDecl(behavior);
				}
			}
		}
		// impostiamo le interazioni architetturali per archiTopology2
		ArchiInteractions archiInteractions = archiTopology.getArchiInteractions();
		InteractionDecl[] interactionDecls = archiInteractions.getInteractions();
		List<ArchitecturalInteraction> architecturalInteractions = archiTopology2.getAiDecl();
		if (interactionDecls != null) {
			for (InteractionDecl interactionDecl : interactionDecls) {
				String string2 = interactionDecl.getInteraction();
				AEIident aeIident = interactionDecl.getAei();
				ArchitecturalInteraction architecturalInteraction = mmaemiliaFactory.eINSTANCE
						.createArchitecturalInteraction();
				// si imposta il nome di architecturalInteraction
				architecturalInteraction.setName(string2);
				// imposto l'interazione relativa a architecturalInteraction appartenente al
				// tipo di elemento architetturale di aeIident
				ArchiElemInstance archiElemInstance = getInstanceFromName(elemInstances, aeIident);
				metamodel.mmaemilia.ElemType elemType = archiElemInstance.getTypeOf();
				LocalInteraction localInteraction = getInteractionFromName(elemType, string2);
				architecturalInteraction.setIs_A(localInteraction);
				// si imposta l'istanza per ogni interazione
				architecturalInteraction.setFromInstance(archiElemInstance);
				architecturalInteractions.add(architecturalInteraction);
			}
		}
		// impostiamo i collegamenti architetturali per archiTopology2
		ArchiAttachments archiAttachments = archiTopology.getAttachments();
		AttacDecl[] attacDecls = archiAttachments.getAttachments();
		List<Attachment> attachments = archiTopology2.getAttDecl();
		for (AttacDecl attacDecl : attacDecls) {
			Attachment attachment = mmaemiliaFactory.eINSTANCE.createAttachment();
			AEIident aeIident2 = attacDecl.getOutputAei();
			String string3 = attacDecl.getOutputInteraction();
			AEIident aeIident4 = attacDecl.getInputAei();
			String string5 = attacDecl.getInputInteraction();
			// si imposta il from di attachment
			From from = mmaemiliaFactory.eINSTANCE.createFrom();
			ArchiElemInstance archiElemInstance = getInstanceFromName(elemInstances, aeIident2);
			OutputInteraction outputInteraction = getOutputInteractionFromAei(archiElemInstance, string3);
			from.setFromInstance(archiElemInstance);
			from.setIsOutput(outputInteraction);
			attachment.setStart(from);
			// si imposta il to di attachment
			To to = mmaemiliaFactory.eINSTANCE.createTo();
			ArchiElemInstance archiElemInstance2 = getInstanceFromName(elemInstances, aeIident4);
			InputInteraction inputInteraction = getInputInteractionFromAei(archiElemInstance2, string5);
			to.setIsInput(inputInteraction);
			to.setToInstance(archiElemInstance2);
			attachment.setEnd(to);
			attachments.add(attachment);
		}
		archiType2.setAtDeclaration(archiTopology2);
		// salviamo l'mmaemilia
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileURI = URI.createFileURI(new File(mmAemiliaPath).getAbsolutePath());
		Resource asResource = resourceSet.createResource(fileURI);
		asResource.getContents().add(aEmiliaSpecification);
		asResource.save(null);
	}

	private Behavior getBehavior(AETbehavior aeTbehavior, metamodel.mmaemilia.ElemType elemType, ElemType elemType2,
			ArchiElemInstance archiElemInstance) {
		specificheAEmilia.BehavEquation[] behavEquations = aeTbehavior.getBehaviors();
		Behavior behavior = BehaviorFactory.eINSTANCE.createBehavior();
		List<BehavEquation> behavEquations2 = behavior.getEquations();
		// si impostano le equazioni comportamentali di behavEquations2
		for (specificheAEmilia.BehavEquation behavEquation : behavEquations) {
			BehavEquation behavEquation2 = BehaviorFactory.eINSTANCE.createBehavEquation();
			// imposto il nome per behavEquation2
			Header header2 = behavEquation.getBehavHeader();
			String string3 = header2.getName();
			behavEquation2.setName(string3);
			// imposto l'intestazione per behavEquation2
			BehavHeader behavHeader = HeadersFactory.eINSTANCE.createBehavHeader();
			ParamDeclaration[] paramDeclarations3 = header2.getParameters();
			// imposto la parte destra di behavHeader
			RightSide rightSide = HeadersFactory.eINSTANCE.createRightSide();
			// imposto le variabili locali di rightSide
			List<Local> locals = rightSide.getLocalDef();
			List<Local> locals2 = getLocalVars(paramDeclarations3);
			locals.addAll(locals2);
			behavHeader.setRight(rightSide);
			// imposto la parte sinistra di behavHeader
			LeftSide leftSide = HeadersFactory.eINSTANCE.createLeftSide();
			// imposto le variabili inizializzate di leftSide
			List<VarInit> varInits = leftSide.getInitVar();
			List<VarInit> varInits2 = getInitVars(paramDeclarations3);
			varInits.addAll(varInits2);
			// imposto le variabili non inizializzate di leftSide
			List<Var> vars = leftSide.getVarDef();
			List<Var> vars2 = getVars(paramDeclarations3);
			vars.addAll(vars2);
			behavHeader.setLeft(leftSide);
			behavEquation2.setBHeader(behavHeader);
			// si imposta il termine di processo
			behavEquations2.add(behavEquation2);
		}
		// imposto i termini di processo di behavEquations2
		for (int i = 0; i < behavEquations.length; i++) {
			specificheAEmilia.BehavEquation behavEquation = behavEquations[i];
			BehavEquation behavEquation2 = behavEquations2.get(i);
			// imposto il termine di processo per behavEquation2
			specificheAEmilia.ProcessTerm processTerm = behavEquation.getTermineProcesso();
			metamodel.mmaemilia.Behavior.ProcessTerm processTerm2 = getProcessTerm(processTerm, elemType, elemType2,
					archiElemInstance);
			behavEquation2.setPt(processTerm2);
		}
		return behavior;
	}

	private metamodel.mmaemilia.Behavior.ProcessTerm getProcessTerm(ProcessTerm processTerm,
			metamodel.mmaemilia.ElemType elemType, ElemType elemType2, ArchiElemInstance archiElemInstance) {
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			Action action = actionProcess.getAzione();
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			Expression expression = actionProcess.getCondition();
			metamodel.mmaemilia.Behavior.ActionProcess actionProcess2 = BehaviorFactory.eINSTANCE.createActionProcess();
			// imposto la condizione per actionProcess2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			actionProcess2.setCondition(expression2);
			// imposto la action per actionProcess2
			metamodel.mmaemilia.Behavior.Action action2 = getActionMM(action, elemType, elemType2, archiElemInstance);
			actionProcess2.setAct(action2);
			// imposto il termine di processo per actionProcess2
			metamodel.mmaemilia.Behavior.ProcessTerm processTerm3 = getProcessTerm(processTerm2, elemType, elemType2,
					archiElemInstance);
			actionProcess2.setProcess(processTerm3);
			return actionProcess2;
		} else if (processTerm instanceof BehavProcess) {
			BehavProcess behavProcess = (BehavProcess) processTerm;
			Expression expression = behavProcess.getCondition();
			Expression[] expressions = behavProcess.getExprs();
			String string = behavProcess.getName();
			metamodel.mmaemilia.Behavior.BehavProcess behavProcess2 = BehaviorFactory.eINSTANCE.createBehavProcess();
			// impostiamo la condizione per behavProcess2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			behavProcess2.setCondition(expression2);
			// impostiamo l'equazione comportamentale per behavProcess2
			Behavior behavior = elemType.getBehaviorDecl();
			List<BehavEquation> behavEquations2 = behavior.getEquations();
			BehavEquation behavEquation = null;
			for (BehavEquation behavEquation2 : behavEquations2) {
				String string2 = behavEquation2.getName();
				if (string.equals(string2)) {
					behavEquation = behavEquation2;
				}
			}
			behavProcess2.setEqCall(behavEquation);
			// impostiamo i parametri attuali per behavProcess2
			List<metamodel.mmaemilia.Expressions.Expression> list = behavProcess2.getExprs();
			for (Expression expression3 : expressions) {
				metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression3);
				list.add(expression4);
			}
			return behavProcess2;
		} else if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			Expression expression = choiceProcess.getCondition();
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			metamodel.mmaemilia.Behavior.ChoiceProcess choiceProcess2 = BehaviorFactory.eINSTANCE.createChoiceProcess();
			// imposto la condizione per choiceProcess2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			choiceProcess2.setCondition(expression2);
			// imposto i termini di processo per choiceProcess2
			List<metamodel.mmaemilia.Behavior.ProcessTerm> list = choiceProcess2.getProcesses();
			for (ProcessTerm processTerm2 : processTerms) {
				metamodel.mmaemilia.Behavior.ProcessTerm processTerm3 = getProcessTerm(processTerm2, elemType,
						elemType2, archiElemInstance);
				list.add(processTerm3);
			}
			return choiceProcess2;
		} else if (processTerm instanceof Stop) {
			Stop stop = (Stop) processTerm;
			Expression expression = stop.getCondition();
			metamodel.mmaemilia.Behavior.Stop stop2 = BehaviorFactory.eINSTANCE.createStop();
			// imposto la condizione per stop2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			stop2.setCondition(expression2);
			return stop2;
		}
		return null;
	}

	private metamodel.mmaemilia.Behavior.Action getActionMM(Action action, metamodel.mmaemilia.ElemType elemType,
			ElemType elemType2, ArchiElemInstance archiElemInstance) {
		ActionRate actionRate = action.getRate();
		ActionType actionType = action.getType();
		metamodel.mmaemilia.Behavior.Action action2 = BehaviorFactory.eINSTANCE.createAction();
		// imposto il nome di action2
		String string = actionType.getName();
		action2.setName(string);
		// imposto l'interazione locale relativa a action2
		LocalInteraction localInteraction = createInteractionFromName(elemType, elemType2, string);
		action2.setIs(localInteraction);
		// imposto il tasso di action2
		metamodel.mmaemilia.Behavior.ActionRate actionRate2 = getActionRateMM(actionRate);
		action2.setRate(actionRate2);
		// imposto il tipo di action2
		metamodel.mmaemilia.Behavior.ActionType actionType2 = getActionType(actionType);
		action2.setType(actionType2);
		// imposto l'istanza di action2
		Elem elem = archiElemInstance.getElem();
		action2.setElem(elem);
		return action2;
	}

	/**
	 * Questo metodo restituisce o un'interazione di input o una di output.
	 * 
	 * @param elemType
	 *            - tipo di elemento a cui l'interazione appartiene
	 * @param string2
	 *            - nome dell'interazione
	 * @return
	 */
	private LocalInteraction getInteractionFromName(metamodel.mmaemilia.ElemType elemType, String string2) {
		List<InputInteraction> inputInteractions = elemType.getIiDecl();
		List<OutputInteraction> outputInteractions = elemType.getOiDecl();
		for (InputInteraction inputInteraction : inputInteractions) {
			String string = inputInteraction.getIntName();
			if (string2.equals(string))
				return inputInteraction;
		}
		for (OutputInteraction outputInteraction : outputInteractions) {
			String string = outputInteraction.getIntName();
			if (string2.equals(string))
				return outputInteraction;
		}
		return null;
	}

	private ArchiElemInstance getInstanceFromName(List<ArchiElemInstance> elemInstances, AEIident aeIident) {
		for (ArchiElemInstance archiElemInstance : elemInstances) {
			String string = archiElemInstance.getInstanceName();
			metamodel.mmaemilia.Expressions.Expression expression = archiElemInstance.getSelector();
			String string2 = aeIident.getName();
			Expression expression2 = aeIident.getSelector();
			MMAemiliaToArchiType mmAemiliaToArchiType = new MMAemiliaToArchiType();
			Expression expression3 = mmAemiliaToArchiType.getExpression(expression);
			if (expression2 == null && expression3 == null) {
				if (string2.equals(string))
					return archiElemInstance;
			} else if (expression2 != null && expression3 != null) {
				if (string2.equals(string) && expression2.equals(expression3))
					return archiElemInstance;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param archiElemInstance2
	 *            - istanza di input
	 * @param string5
	 *            - nome dell'interazione di input
	 * @return
	 */
	private InputInteraction getInputInteractionFromAei(ArchiElemInstance archiElemInstance2, String string5) {
		metamodel.mmaemilia.ElemType elemType = archiElemInstance2.getTypeOf();
		List<InputInteraction> inputInteractions = elemType.getIiDecl();
		for (InputInteraction inputInteraction : inputInteractions) {
			String string = inputInteraction.getIntName();
			if (string5.equals(string))
				return inputInteraction;
		}
		return null;
	}

	/**
	 * 
	 * @param archiElemInstance
	 *            - istanza di output
	 * @param string3
	 *            - nome dell'interazione di output
	 * @return
	 */
	private OutputInteraction getOutputInteractionFromAei(ArchiElemInstance archiElemInstance, String string3) {
		metamodel.mmaemilia.ElemType elemType = archiElemInstance.getTypeOf();
		List<OutputInteraction> outputInteractions = elemType.getOiDecl();
		for (OutputInteraction outputInteraction : outputInteractions) {
			String string = outputInteraction.getIntName();
			if (string3.equals(string))
				return outputInteraction;
		}
		return null;
	}

	/**
	 * 
	 * @param elemTypes2
	 *            - tipi di elementi architetturali
	 * @param string3
	 *            - nome del tipo di elemento architetturale
	 * @return
	 */
	private metamodel.mmaemilia.ElemType getElemTypeFromAetName(List<metamodel.mmaemilia.ElemType> elemTypes2,
			String string3) {
		for (metamodel.mmaemilia.ElemType elemType : elemTypes2) {
			String string = elemType.getEtName();
			if (string3.equals(string))
				return elemType;
		}
		return null;
	}

	/**
	 * 
	 * @param processTerm
	 *            - termine di processo
	 * @param elemType
	 *            - tipo di elemento architetturale a cui processTerm appartiene
	 * @param elemType2
	 *            - tipo di elemento architetturale associato a elemType
	 * @return
	 */
	private metamodel.mmaemilia.Behavior.ProcessTerm getProcessTerm(specificheAEmilia.ProcessTerm processTerm,
			metamodel.mmaemilia.ElemType elemType, ElemType elemType2) {
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			Action action = actionProcess.getAzione();
			ProcessTerm processTerm2 = actionProcess.getProcesso();
			Expression expression = actionProcess.getCondition();
			metamodel.mmaemilia.Behavior.ActionProcess actionProcess2 = BehaviorFactory.eINSTANCE.createActionProcess();
			// imposto la condizione per actionProcess2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			actionProcess2.setCondition(expression2);
			// imposto la action per actionProcess2
			metamodel.mmaemilia.Behavior.Action action2 = getActionMM(action, elemType, elemType2);
			actionProcess2.setAct(action2);
			// imposto il termine di processo per actionProcess2
			metamodel.mmaemilia.Behavior.ProcessTerm processTerm3 = getProcessTerm(processTerm2, elemType, elemType2);
			actionProcess2.setProcess(processTerm3);
			return actionProcess2;
		} else if (processTerm instanceof BehavProcess) {
			BehavProcess behavProcess = (BehavProcess) processTerm;
			Expression expression = behavProcess.getCondition();
			Expression[] expressions = behavProcess.getExprs();
			String string = behavProcess.getName();
			metamodel.mmaemilia.Behavior.BehavProcess behavProcess2 = BehaviorFactory.eINSTANCE.createBehavProcess();
			// impostiamo la condizione per behavProcess2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			behavProcess2.setCondition(expression2);
			// impostiamo l'equazione comportamentale per behavProcess2
			Behavior behavior = elemType.getBehaviorDecl();
			List<BehavEquation> behavEquations2 = behavior.getEquations();
			BehavEquation behavEquation = null;
			for (BehavEquation behavEquation2 : behavEquations2) {
				String string2 = behavEquation2.getName();
				if (string.equals(string2)) {
					behavEquation = behavEquation2;
				}
			}
			behavProcess2.setEqCall(behavEquation);
			// impostiamo i parametri attuali per behavProcess2
			List<metamodel.mmaemilia.Expressions.Expression> list = behavProcess2.getExprs();
			for (Expression expression3 : expressions) {
				metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression3);
				list.add(expression4);
			}
			return behavProcess2;
		} else if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			Expression expression = choiceProcess.getCondition();
			ProcessTerm[] processTerms = choiceProcess.getProcesses();
			metamodel.mmaemilia.Behavior.ChoiceProcess choiceProcess2 = BehaviorFactory.eINSTANCE.createChoiceProcess();
			// imposto la condizione per choiceProcess2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			choiceProcess2.setCondition(expression2);
			// imposto i termini di processo per choiceProcess2
			List<metamodel.mmaemilia.Behavior.ProcessTerm> list = choiceProcess2.getProcesses();
			for (ProcessTerm processTerm2 : processTerms) {
				metamodel.mmaemilia.Behavior.ProcessTerm processTerm3 = getProcessTerm(processTerm2, elemType,
						elemType2);
				list.add(processTerm3);
			}
			return choiceProcess2;
		} else if (processTerm instanceof Stop) {
			Stop stop = (Stop) processTerm;
			Expression expression = stop.getCondition();
			metamodel.mmaemilia.Behavior.Stop stop2 = BehaviorFactory.eINSTANCE.createStop();
			// imposto la condizione per stop2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			stop2.setCondition(expression2);
			return stop2;
		}
		return null;
	}

	/**
	 * 
	 * @param action
	 * @param elemType
	 * @return
	 */
	private metamodel.mmaemilia.Behavior.Action getActionMM(Action action, metamodel.mmaemilia.ElemType elemType,
			ElemType elemType2) {
		ActionRate actionRate = action.getRate();
		ActionType actionType = action.getType();
		metamodel.mmaemilia.Behavior.Action action2 = BehaviorFactory.eINSTANCE.createAction();
		// imposto il nome di action2
		String string = actionType.getName();
		action2.setName(string);
		// imposto il tipo di elemento architetturale a cui action2 appartiene
		action2.setBelongs(elemType);
		// imposto l'interazione locale relativa a action2
		LocalInteraction localInteraction = createInteractionFromName(elemType, elemType2, string);
		action2.setIs(localInteraction);
		// imposto il tasso di action2
		metamodel.mmaemilia.Behavior.ActionRate actionRate2 = getActionRateMM(actionRate);
		action2.setRate(actionRate2);
		// imposto il tipo di action2
		metamodel.mmaemilia.Behavior.ActionType actionType2 = getActionType(actionType);
		action2.setType(actionType2);
		return action2;
	}

	private metamodel.mmaemilia.Behavior.ActionType getActionType(ActionType actionType) {
		if (actionType instanceof ActionInput) {
			ActionInput actionInput = (ActionInput) actionType;
			String[] strings = actionInput.getInputVariables();
			metamodel.mmaemilia.Behavior.ActionInput actionInput2 = BehaviorFactory.eINSTANCE.createActionInput();
			// imposto i nomi delle variabili di input
			List<String> list = actionInput2.getVar();
			for (String string : strings) {
				list.add(string);
			}
			return actionInput2;
		} else if (actionType instanceof ActionOutput) {
			ActionOutput actionOutput = (ActionOutput) actionType;
			Expression[] expressions = actionOutput.getOutputExprs();
			metamodel.mmaemilia.Behavior.ActionOutput actionOutput2 = BehaviorFactory.eINSTANCE.createActionOutput();
			// imposto le espressioni di output
			List<metamodel.mmaemilia.Expressions.Expression> list = actionOutput2.getExprOutput();
			for (Expression expression : expressions) {
				metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
				list.add(expression2);
			}
			return actionOutput2;
		} else {
			metamodel.mmaemilia.Behavior.ActionType actionType2 = BehaviorFactory.eINSTANCE.createActionType();
			return actionType2;
		}
	}

	private metamodel.mmaemilia.Behavior.ActionRate getActionRateMM(ActionRate actionRate) {
		if (actionRate instanceof RateExp) {
			RateExp rateExp = (RateExp) actionRate;
			Expression expression = rateExp.getExpr();
			metamodel.mmaemilia.Behavior.RateExp rateExp2 = BehaviorFactory.eINSTANCE.createRateExp();
			// impostosto l'espressione tasso per rateExp2
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			rateExp2.setExpr(expression2);
			return rateExp2;
		} else if (actionRate instanceof Rate_) {
			Rate_ rate_ = (Rate_) actionRate;
			Expression expression = rate_.getPrio();
			Expression expression2 = rate_.getWeight();
			RatePas ratePas = BehaviorFactory.eINSTANCE.createRatePas();
			// imposto la priorita' per ratePas
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression);
			ratePas.setPriority(expression3);
			// imposto il peso per ratePas
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			ratePas.setWeight(expression4);
			return ratePas;
		} else if (actionRate instanceof RateInf) {
			RateInf rateInf = (RateInf) actionRate;
			Expression expression = rateInf.getPrio();
			Expression expression2 = rateInf.getWeight();
			metamodel.mmaemilia.Behavior.RateInf rateInf2 = BehaviorFactory.eINSTANCE.createRateInf();
			// imposto la priorita' per rateInf2
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression);
			rateInf2.setInf_priority(expression3);
			// imposto il peso per rateInf2
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			rateInf2.setInf_weight(expression4);
			return rateInf2;
		} else
			return null;
	}

	/**
	 * 
	 * @param elemType
	 * @param elemType2
	 * @param string
	 *            - stringe'il nome dell'interazione
	 * @return
	 */
	private LocalInteraction createInteractionFromName(metamodel.mmaemilia.ElemType elemType, ElemType elemType2,
			String string) {
		AETinteractions aeTinteractions = elemType2.getInteractions();
		AETinteractionsParts tinteractionsParts = new AETinteractionsParts(aeTinteractions);
		List<String> list = tinteractionsParts.getAndInput();
		List<String> list2 = tinteractionsParts.getAndOutput();
		List<String> list3 = tinteractionsParts.getOrInput();
		List<String> list4 = tinteractionsParts.getOrOutput();
		List<String> list5 = tinteractionsParts.getUniInput();
		List<String> list6 = tinteractionsParts.getUniOutput();
		if (list.contains(string)) {
			// stringe'una and interazione di input
			InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
			// imposto il nome per inputInteraction
			inputInteraction.setIntName(string);
			// imposto il tipo di inputInteraction
			inputInteraction.setType(InteractionType.AND);
			// aggiungo inputInteraction a elemType
			List<InputInteraction> list7 = elemType.getIiDecl();
			list7.add(inputInteraction);
			return inputInteraction;
		} else if (list2.contains(string)) {
			// stringe'una and interazione di output
			OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
			// imposto il nome per outputInteraction
			outputInteraction.setIntName(string);
			// imposto il tipo di outputInteraction
			outputInteraction.setType(InteractionType.AND);
			// aggiungo outputInteraction a elemType
			List<OutputInteraction> list7 = elemType.getOiDecl();
			list7.add(outputInteraction);
			return outputInteraction;
		} else if (list3.contains(string)) {
			// stringe'una or interazione di input
			InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
			// imposto il nome per inputInteraction
			inputInteraction.setIntName(string);
			// imposto il tipo di inputInteraction
			inputInteraction.setType(InteractionType.OR);
			// aggiungo inputInteraction a elemType
			List<InputInteraction> list7 = elemType.getIiDecl();
			list7.add(inputInteraction);
			return inputInteraction;
		} else if (list4.contains(string)) {
			// stringe'una or interazione di output
			OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
			// imposto il nome per outputInteraction
			outputInteraction.setIntName(string);
			// imposto il tipo di outputInteraction
			outputInteraction.setType(InteractionType.OR);
			// aggiungo outputInteraction a elemType
			List<OutputInteraction> list7 = elemType.getOiDecl();
			list7.add(outputInteraction);
			return outputInteraction;
		} else if (list5.contains(string)) {
			// stringe'una uni interazione di input
			InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
			// imposto il nome per inputInteraction
			inputInteraction.setIntName(string);
			// imposto il tipo per outputInteraction
			inputInteraction.setType(InteractionType.UNI);
			// aggiungo outputInteraction a elemType
			List<InputInteraction> list7 = elemType.getIiDecl();
			list7.add(inputInteraction);
			return inputInteraction;
		} else if (list6.contains(string)) {
			// stringe'una uni interazione di output
			OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
			// imposto il nome per outputInteraction
			outputInteraction.setIntName(string);
			// imposto il tipo di outputInteraction
			outputInteraction.setType(InteractionType.UNI);
			// aggiungo outputInteraction a elemType
			List<OutputInteraction> list7 = elemType.getOiDecl();
			list7.add(outputInteraction);
			return outputInteraction;
		} else {
			// stringe'una interazione locale
			LocalInteraction localInteraction = mmaemiliaFactory.eINSTANCE.createLocalInteraction();
			// imposto il nome per localInteraction
			localInteraction.setIntName(string);
			return localInteraction;
		}
	}

	public metamodel.mmaemilia.Expressions.Expression getExpressionMM(Expression expression) {
		if (expression instanceof And) {
			And and = (And) expression;
			Expression expression2 = and.getExpr1();
			Expression expression3 = and.getExpr2();
			BooleanExpr booleanExpr = ExpressionsFactory.eINSTANCE.createBooleanExpr();
			// imposto l'operatore per booleanExpr
			booleanExpr.setOperator(BoolOpName.AND);
			// imposto l'operando di sinistra per booleanExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			booleanExpr.setLeftExprBool(expression4);
			// imposto l'operando di destra per booleanExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			booleanExpr.setRightExprBool(expression5);
			return booleanExpr;
		} else if (expression instanceof ArrayCons) {
			ArrayCons arrayCons = (ArrayCons) expression;
			Expression[] expressions = arrayCons.getArrayElements();
			ArrayExpr arrayExpr = ExpressionsFactory.eINSTANCE.createArrayExpr();
			// imposto l'operatore per arrayExpr
			arrayExpr.setOperation(ArrayOpName.ARRAY_CONS);
			// imposto gli operandi per arrayExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = arrayExpr.getArray_operands();
			for (Expression expression2 : expressions) {
				metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
				list.add(expression3);
			}
			return arrayExpr;
		} else if (expression instanceof specificheAEmilia.Boolean) {
			Boolean boolean1 = (Boolean) expression;
			boolean b = boolean1.getValore();
			java.lang.Boolean boolean2 = new java.lang.Boolean(b);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr = ExpressionsFactory.eINSTANCE.createIdentExpr();
			// imposto il tipo per identExpr
			identExpr.setType(IdentifierType.TRUTH_VAL);
			// imposto il valore per identExpr
			identExpr.setName(boolean2.toString());
			return identExpr;
		} else if (expression instanceof Concat) {
			Concat concat = (Concat) expression;
			IdentExpr identExpr = concat.getList1();
			IdentExpr identExpr2 = concat.getList2();
			String string = identExpr.getNome();
			String string2 = identExpr2.getNome();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			// imposto l'operatore per list Expr
			listExpr.setOperation(ListOpName.CONCAT);
			// imposto gli operandi per listExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr3 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			// imposto il tipo per identExpr3
			identExpr3.setType(IdentifierType.TYPED_IDENT);
			// imposto il valore per identExpr3
			identExpr3.setName(string);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr4 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			// imposto il tipo per identExpr4
			identExpr4.setType(IdentifierType.TYPED_IDENT);
			// imposto il valore per identExpr4
			identExpr4.setName(string2);
			list.add(identExpr3);
			list.add(identExpr4);
			return listExpr;
		} else if (expression instanceof Different) {
			Different different = (Different) expression;
			Expression expression2 = different.getExpr1();
			Expression expression3 = different.getExpr2();
			RelationalExpr relationalExpr = ExpressionsFactory.eINSTANCE.createRelationalExpr();
			// imposto il tipo per relationalExpr
			relationalExpr.setOperator(RelatOpName.NOT_EQUAL);
			// imposto l'operando di sinistra di relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			relationalExpr.setLeftExprRel(expression4);
			// imposto l'operando di destra di relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			relationalExpr.setRightExprRel(expression5);
			return relationalExpr;
		} else if (expression instanceof Divisione) {
			Divisione divisione = (Divisione) expression;
			Expression expression2 = divisione.getExpr1();
			Expression expression3 = divisione.getExpr2();
			ArithExpr arithExpr = ExpressionsFactory.eINSTANCE.createArithExpr();
			// imposto il tipo per arithExpr
			arithExpr.setOperator(ArithOpName.DIV);
			// imposto l'operando di sinistra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			arithExpr.setLeftExprArith(expression4);
			// imposto l'operando di destra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			arithExpr.setRightExprArith(expression5);
			return arithExpr;
		} else if (expression instanceof Equal) {
			Equal equal = (Equal) expression;
			Expression expression2 = equal.getExpr1();
			Expression expression3 = equal.getExpr2();
			RelationalExpr relationalExpr = ExpressionsFactory.eINSTANCE.createRelationalExpr();
			// imposto il tipo per relationalExpr
			relationalExpr.setOperator(RelatOpName.EQUAL);
			// imposto l'operando di sinistra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			relationalExpr.setLeftExprRel(expression4);
			// imposto l'operando di destra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			relationalExpr.setRightExprRel(expression5);
			return relationalExpr;
		} else if (expression instanceof First) {
			First first = (First) expression;
			IdentExpr identExpr = first.getList();
			String string = identExpr.getNome();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			// imposto il tipo per listExpr
			listExpr.setOperation(ListOpName.FIRST);
			// imposto la lista degli operandi per listExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(identExpr2);
			return listExpr;
		} else if (expression instanceof Get) {
			Get get = (Get) expression;
			String string = get.getField();
			IdentExpr identExpr = get.getRecord();
			String string2 = identExpr.getNome();
			RecordExpr recordExpr = ExpressionsFactory.eINSTANCE.createRecordExpr();
			// imposto il tipo per recordExpr
			recordExpr.setOperation(RecordOpName.GET);
			// imposto gli operandi per recordExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = recordExpr.getRecord_operands();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr3 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr3.setType(IdentifierType.TYPED_IDENT);
			identExpr3.setName(string2);
			list.add(identExpr2);
			list.add(identExpr3);
			return recordExpr;
		} else if (expression instanceof IdentExpr) {
			IdentExpr identExpr = (IdentExpr) expression;
			String string = identExpr.getNome();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			return identExpr2;
		} else if (expression instanceof Insert) {
			Insert insert = (Insert) expression;
			Expression expression2 = insert.getItem();
			IdentExpr identExpr = insert.getList();
			String string = identExpr.getNome();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			// imposto il tipo per listExpr
			listExpr.setOperation(ListOpName.INSERT);
			// imposto gli operandi per listExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(expression3);
			list.add(identExpr2);
			return listExpr;
		} else if (expression instanceof Length) {
			Length length = (Length) expression;
			IdentExpr identExpr = length.getList();
			String string = identExpr.getNome();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			// imposto il tipo per listExpr
			listExpr.setOperation(ListOpName.LENGHT);
			// imposto gli operandi per listExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(identExpr2);
			return listExpr;
		} else if (expression instanceof ListCons) {
			ListCons listCons = (ListCons) expression;
			Expression[] expressions = listCons.getListElements();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			// imposto il tipo per listExpr
			listExpr.setOperation(ListOpName.LIST_CONS);
			// imposto gli operandi per listExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			for (Expression expression2 : expressions) {
				metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
				list.add(expression3);
			}
			return listExpr;
		} else if (expression instanceof Maggiore) {
			Maggiore maggiore = (Maggiore) expression;
			Expression expression2 = maggiore.getExpr1();
			Expression expression3 = maggiore.getExpr2();
			RelationalExpr relationalExpr = ExpressionsFactory.eINSTANCE.createRelationalExpr();
			// imposto il tipo per relationalExpr
			relationalExpr.setOperator(RelatOpName.GREATER);
			// imposto l'operando di sinistra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			relationalExpr.setLeftExprRel(expression4);
			// imposto l'operando di destra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			relationalExpr.setRightExprRel(expression5);
			return relationalExpr;
		} else if (expression instanceof MaggioreUguale) {
			MaggioreUguale maggioreUguale = (MaggioreUguale) expression;
			Expression expression2 = maggioreUguale.getExpr1();
			Expression expression3 = maggioreUguale.getExpr2();
			RelationalExpr relationalExpr = ExpressionsFactory.eINSTANCE.createRelationalExpr();
			// imposto il tipo per relationalExpr
			relationalExpr.setOperator(RelatOpName.GREATER_EQUAL);
			// imposto l'operando di sinistra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			relationalExpr.setLeftExprRel(expression4);
			// imposto l'operando di destra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			relationalExpr.setRightExprRel(expression5);
			return relationalExpr;
		} else if (expression instanceof Minore) {
			Minore minore = (Minore) expression;
			Expression expression2 = minore.getExpr1();
			Expression expression3 = minore.getExpr2();
			RelationalExpr relationalExpr = ExpressionsFactory.eINSTANCE.createRelationalExpr();
			// imposto il tipo per relationalExpr
			relationalExpr.setOperator(RelatOpName.LESS);
			// imposto l'operando di sinistra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			relationalExpr.setLeftExprRel(expression4);
			// imposto l'operando di destra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			relationalExpr.setRightExprRel(expression5);
			return relationalExpr;
		} else if (expression instanceof MinoreUguale) {
			MinoreUguale minore_uguale = (MinoreUguale) expression;
			Expression expression2 = minore_uguale.getExpr1();
			Expression expression3 = minore_uguale.getExpr2();
			RelationalExpr relationalExpr = ExpressionsFactory.eINSTANCE.createRelationalExpr();
			// imposto il tipo per relationalExpr
			relationalExpr.setOperator(RelatOpName.LESS_EQUAL);
			// imposto l'operando di sinistra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			relationalExpr.setLeftExprRel(expression4);
			// imposto l'operando di destra per relationalExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			relationalExpr.setRightExprRel(expression5);
			return relationalExpr;
		} else if (expression instanceof Moltiplicazione) {
			Moltiplicazione moltiplicazione = (Moltiplicazione) expression;
			Expression expression2 = moltiplicazione.getExpr1();
			Expression expression3 = moltiplicazione.getExpr2();
			ArithExpr arithExpr = ExpressionsFactory.eINSTANCE.createArithExpr();
			// imposto il tipo per arithExpr
			arithExpr.setOperator(ArithOpName.MULT);
			// imposto l'operando di sinistra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			arithExpr.setLeftExprArith(expression4);
			// imposto l'operando di destra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			arithExpr.setRightExprArith(expression5);
			return arithExpr;
		} else if (expression instanceof Negazione) {
			Negazione negazione = (Negazione) expression;
			Expression expression2 = negazione.getExpr1();
			BooleanExpr booleanExpr = ExpressionsFactory.eINSTANCE.createBooleanExpr();
			// imposto il tipo per booleanExpr
			booleanExpr.setOperator(BoolOpName.NOT);
			// imposto gli operandi per booleanExpr
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
			booleanExpr.setLeftExprBool(expression3);
			return booleanExpr;
		} else if (expression instanceof specificheAEmilia.Integer) {
			Integer integer = (Integer) expression;
			java.lang.Integer integer2 = integer.getValore();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr = ExpressionsFactory.eINSTANCE.createIdentExpr();
			// imposto il tipo di identExpr
			identExpr.setType(IdentifierType.NUMERIC_CONST);
			// imposto il valore per identExpr
			identExpr.setName(integer2.toString());
			return identExpr;
		} else if (expression instanceof Real) {
			Real real = (Real) expression;
			Double double1 = real.getValore();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr = ExpressionsFactory.eINSTANCE.createIdentExpr();
			// imposto il tipo per identExpr
			identExpr.setType(IdentifierType.NUMERIC_CONST);
			// imposto il valore per identExpr
			identExpr.setName(double1.toString());
			return identExpr;
		} else if (expression instanceof Or) {
			Or or = (Or) expression;
			Expression expression2 = or.getExpr1();
			Expression expression3 = or.getExpr2();
			BooleanExpr booleanExpr = ExpressionsFactory.eINSTANCE.createBooleanExpr();
			// imposto l'operatore per booleanExpr
			booleanExpr.setOperator(BoolOpName.OR);
			// imposto l'operando di sinistra per booleanExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			booleanExpr.setLeftExprBool(expression4);
			// imposto l'operando di destra per booleanExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			booleanExpr.setRightExprBool(expression5);
			return booleanExpr;
		} else if (expression instanceof Put) {
			Put put = (Put) expression;
			String string = put.getField();
			Expression expression2 = put.getValue();
			IdentExpr identExpr = put.getRecord();
			String string2 = identExpr.getNome();
			RecordExpr recordExpr = ExpressionsFactory.eINSTANCE.createRecordExpr();
			// imposto il tipo per recordExpr
			recordExpr.setOperation(RecordOpName.PUT);
			// imposto gli operandi per recordExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = recordExpr.getRecord_operands();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr3 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr3.setType(IdentifierType.TYPED_IDENT);
			identExpr3.setName(string2);
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
			list.add(identExpr2);
			list.add(expression3);
			list.add(identExpr3);
			return recordExpr;
		} else if (expression instanceof Read) {
			Read read = (Read) expression;
			Expression expression2 = read.getIndex();
			IdentExpr identExpr = read.getArray();
			String string = identExpr.getNome();
			ArrayExpr arrayExpr = ExpressionsFactory.eINSTANCE.createArrayExpr();
			// imposto il tipo per arrayExpr
			arrayExpr.setOperation(ArrayOpName.READ);
			// imposto gli operandi per arrayExpr
			List<metamodel.mmaemilia.Expressions.Expression> list = arrayExpr.getArray_operands();
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(expression3);
			list.add(identExpr2);
			return arrayExpr;
		} else if (expression instanceof RecordCons) {
			RecordCons recordCons = (RecordCons) expression;
			Expression[] expressions = recordCons.getValues();
			RecordExpr recordExpr = ExpressionsFactory.eINSTANCE.createRecordExpr();
			recordExpr.setOperation(RecordOpName.RECORD_CONS);
			List<metamodel.mmaemilia.Expressions.Expression> list = recordExpr.getRecord_operands();
			for (Expression expression2 : expressions) {
				metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
				list.add(expression3);
			}
			return recordExpr;
		} else if (expression instanceof Remove) {
			Remove remove = (Remove) expression;
			Expression expression2 = remove.getPosition();
			IdentExpr identExpr = remove.getList();
			String string = identExpr.getNome();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			listExpr.setOperation(ListOpName.REMOVE);
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression2);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(expression3);
			list.add(identExpr2);
			return listExpr;
		} else if (expression instanceof Somma) {
			Somma somma = (Somma) expression;
			Expression expression2 = somma.getExpr1();
			Expression expression3 = somma.getExpr2();
			ArithExpr arithExpr = ExpressionsFactory.eINSTANCE.createArithExpr();
			// imposto il tipo per arithExpr
			arithExpr.setOperator(ArithOpName.PLUS);
			// imposto l'operando di sinistra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			arithExpr.setLeftExprArith(expression4);
			// imposto l'operando di destra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			arithExpr.setRightExprArith(expression5);
			return arithExpr;
		} else if (expression instanceof Sottrazione) {
			Sottrazione sottrazione = (Sottrazione) expression;
			Expression expression2 = sottrazione.getExpr1();
			Expression expression3 = sottrazione.getExpr2();
			ArithExpr arithExpr = ExpressionsFactory.eINSTANCE.createArithExpr();
			// imposto il tipo per arithExpr
			arithExpr.setOperator(ArithOpName.MINUS);
			// imposto l'operando di sinistra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			arithExpr.setLeftExprArith(expression4);
			// imposto l'operando di destra per arithExpr
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			arithExpr.setRightExprArith(expression5);
			return arithExpr;
		} else if (expression instanceof Tail) {
			Tail tail = (Tail) expression;
			IdentExpr identExpr = tail.getList();
			String string = identExpr.getNome();
			ListExpr listExpr = ExpressionsFactory.eINSTANCE.createListExpr();
			listExpr.setOperation(ListOpName.TAIL);
			List<metamodel.mmaemilia.Expressions.Expression> list = listExpr.getList_operands();
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(identExpr2);
			return listExpr;
		} else if (expression instanceof Write) {
			Write write = (Write) expression;
			Expression expression2 = write.getIndex();
			Expression expression3 = write.getElement();
			IdentExpr identExpr = write.getArray();
			String string = identExpr.getNome();
			ArrayExpr arrayExpr = ExpressionsFactory.eINSTANCE.createArrayExpr();
			arrayExpr.setOperation(ArrayOpName.WRITE);
			List<metamodel.mmaemilia.Expressions.Expression> list = arrayExpr.getArray_operands();
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			metamodel.mmaemilia.Expressions.Expression expression5 = getExpressionMM(expression3);
			metamodel.mmaemilia.Expressions.IdentExpr identExpr2 = ExpressionsFactory.eINSTANCE.createIdentExpr();
			identExpr2.setType(IdentifierType.TYPED_IDENT);
			identExpr2.setName(string);
			list.add(expression4);
			list.add(expression5);
			list.add(identExpr2);
			return arrayExpr;
		} else
			return null;
	}

	/**
	 * paramDeclaration3 contiene dichiarazioni di parametri per un'equazione
	 * comportamentale, quindie'formata da null, Var, VarInit o Local
	 * 
	 * @param paramDeclarations3
	 * @return
	 */
	private List<Var> getVars(ParamDeclaration[] paramDeclarations3) {
		List<Var> list = new ArrayList<Var>();
		for (ParamDeclaration paramDeclaration : paramDeclarations3) {
			if (paramDeclaration instanceof VariableDeclaration) {
				VariableDeclaration variableDeclaration = (VariableDeclaration) paramDeclaration;
				String string = variableDeclaration.getName();
				NormalType normalType = variableDeclaration.getType();
				Normal normal = getNormal(normalType);
				Var var = HeadersFactory.eINSTANCE.createVar();
				// imposto il nome di var
				var.setName(string);
				// imposto il tipo della variabile
				var.setVarType(normal);
				list.add(var);
			}
		}
		return list;
	}

	private Normal getNormal(NormalType normalType) {
		if (normalType instanceof ArrayType) {
			ArrayType arrayType = (ArrayType) normalType;
			Expression expression = arrayType.getLength();
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			NormalType normalType2 = arrayType.getType();
			Normal normal = getNormal(normalType2);
			Array array = DataTypeFactory.eINSTANCE.createArray();
			// imposto la lunghezza dell'array
			array.setLength(expression2);
			// imposto il tipo dell'array
			array.setArrayElemType(normal);
			return array;
		} else if (normalType instanceof BooleanType) {
			metamodel.mmaemilia.DataType.Boolean boolean1 = DataTypeFactory.eINSTANCE.createBoolean();
			return boolean1;
		} else if (normalType instanceof IntegerRangeType) {
			IntegerRangeType integerRangeType = (IntegerRangeType) normalType;
			Expression expression = integerRangeType.getBeginningInt();
			Expression expression2 = integerRangeType.getEndingInt();
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression);
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			RangeInt rangeInt = DataTypeFactory.eINSTANCE.createRangeInt();
			// imposti i limiti di rangeInt
			rangeInt.setMinVal(expression3);
			rangeInt.setMaxVal(expression4);
			return rangeInt;
		} else if (normalType instanceof IntegerType) {
			metamodel.mmaemilia.DataType.Integer integer = DataTypeFactory.eINSTANCE.createInteger();
			return integer;
		} else if (normalType instanceof ListType) {
			ListType listType = (ListType) normalType;
			NormalType normalType2 = listType.getType();
			Normal normal = getNormal(normalType2);
			metamodel.mmaemilia.DataType.List list = DataTypeFactory.eINSTANCE.createList();
			list.setListElemType(normal);
			return list;
		} else if (normalType instanceof RealType) {
			metamodel.mmaemilia.DataType.Real real = DataTypeFactory.eINSTANCE.createReal();
			return real;
		} else if (normalType instanceof RecordType) {
			RecordType recordType = (RecordType) normalType;
			VariableDeclaration[] variableDeclarations = recordType.getVariableDeclarations();
			Record record = DataTypeFactory.eINSTANCE.createRecord();
			List<Var> list = record.getField_decl_seq();
			for (VariableDeclaration variableDeclaration : variableDeclarations) {
				String string = variableDeclaration.getName();
				NormalType normalType2 = variableDeclaration.getType();
				metamodel.mmaemilia.DataType.Normal normal = getNormal(normalType2);
				Var var = HeadersFactory.eINSTANCE.createVar();
				var.setName(string);
				var.setVarType(normal);
				list.add(var);
			}
			return record;
		} else
			return null;
	}

	private List<VarInit> getInitVars(ParamDeclaration[] paramDeclarations3) {
		List<VarInit> list = new ArrayList<VarInit>();
		for (ParamDeclaration paramDeclaration : paramDeclarations3) {
			if (paramDeclaration instanceof specificheAEmilia.VarInit) {
				specificheAEmilia.VarInit varInit = (specificheAEmilia.VarInit) paramDeclaration;
				Expression expression = varInit.getExpr();
				String string = varInit.getName();
				NormalType normalType = varInit.getType();
				VarInit varInit2 = HeadersFactory.eINSTANCE.createVarInit();
				// imposto l'espressione di inizializzazione per varInit2
				metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
				varInit2.setInitVarExpr(expression2);
				// imposto il nome di varInit2
				varInit2.setName(string);
				// imposto il tipo di varInit2
				Normal normal = getNormal(normalType);
				varInit2.setInitVarType(normal);
				list.add(varInit2);
			}
		}
		return list;
	}

	private List<Local> getLocalVars(ParamDeclaration[] paramDeclarations3) {
		List<Local> list = new ArrayList<Local>();
		for (ParamDeclaration paramDeclaration : paramDeclarations3) {
			if (paramDeclaration instanceof specificheAEmilia.Local) {
				specificheAEmilia.Local local = (specificheAEmilia.Local) paramDeclaration;
				String string = local.getName();
				NormalType normalType = local.getType();
				Normal normal = getNormal(normalType);
				Local local2 = HeadersFactory.eINSTANCE.createLocal();
				// imposto il nome di local2
				local2.setName(string);
				// imposto il tipo di local2
				local2.setLocalType(normal);
				list.add(local2);
			}
		}
		return list;
	}

	private metamodel.mmaemilia.DataType.DataType getDataType(DataType dataType) {
		if (dataType instanceof ArrayType) {
			ArrayType arrayType = (ArrayType) dataType;
			Expression expression = arrayType.getLength();
			metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
			NormalType normalType2 = arrayType.getType();
			Normal normal = getNormal(normalType2);
			Array array = DataTypeFactory.eINSTANCE.createArray();
			// imposto la lunghezza dell'array
			array.setLength(expression2);
			// imposto il tipo dell'array
			array.setArrayElemType(normal);
			return array;
		} else if (dataType instanceof BooleanType) {
			metamodel.mmaemilia.DataType.Boolean boolean1 = DataTypeFactory.eINSTANCE.createBoolean();
			return boolean1;
		} else if (dataType instanceof IntegerRangeType) {
			IntegerRangeType integerRangeType = (IntegerRangeType) dataType;
			Expression expression = integerRangeType.getBeginningInt();
			Expression expression2 = integerRangeType.getEndingInt();
			metamodel.mmaemilia.Expressions.Expression expression3 = getExpressionMM(expression);
			metamodel.mmaemilia.Expressions.Expression expression4 = getExpressionMM(expression2);
			// per precondizione expression e expression2 sono interi
			RangeInt rangeInt = DataTypeFactory.eINSTANCE.createRangeInt();
			// imposti i limiti di rangeInt
			rangeInt.setMinVal(expression3);
			rangeInt.setMaxVal(expression4);
			return rangeInt;
		} else if (dataType instanceof IntegerType) {
			metamodel.mmaemilia.DataType.Integer integer = DataTypeFactory.eINSTANCE.createInteger();
			return integer;
		} else if (dataType instanceof ListType) {
			ListType listType = (ListType) dataType;
			NormalType normalType2 = listType.getType();
			Normal normal = getNormal(normalType2);
			metamodel.mmaemilia.DataType.List list = DataTypeFactory.eINSTANCE.createList();
			list.setListElemType(normal);
			return list;
		} else if (dataType instanceof RealType) {
			metamodel.mmaemilia.DataType.Real real = DataTypeFactory.eINSTANCE.createReal();
			return real;
		} else if (dataType instanceof RecordType) {
			RecordType recordType = (RecordType) dataType;
			VariableDeclaration[] variableDeclarations = recordType.getVariableDeclarations();
			Record record = DataTypeFactory.eINSTANCE.createRecord();
			List<Var> list = record.getField_decl_seq();
			for (VariableDeclaration variableDeclaration : variableDeclarations) {
				String string = variableDeclaration.getName();
				NormalType normalType = variableDeclaration.getType();
				Normal normal = getNormal(normalType);
				Var var = HeadersFactory.eINSTANCE.createVar();
				var.setName(string);
				var.setVarType(normal);
				list.add(var);
			}
			return record;
		} else if (dataType instanceof PrioType) {
			Special special = DataTypeFactory.eINSTANCE.createSpecial();
			special.setType(SpecialType.PRIO);
			return special;
		} else if (dataType instanceof RateType) {
			Special special = DataTypeFactory.eINSTANCE.createSpecial();
			special.setType(SpecialType.RATE);
			return special;
		} else if (dataType instanceof WeightType) {
			Special special = DataTypeFactory.eINSTANCE.createSpecial();
			special.setType(SpecialType.WEIGHT);
			return special;
		} else
			return null;
	}

	public void serialize(ArchiType archiType, List<ElemTypeNorm> elemTypeNorms, Path mmAemiliaPath)
			throws IOException {
		AEmiliaSpecification aEmiliaSpecification = mmaemiliaFactory.eINSTANCE.createAEmiliaSpecification();
		metamodel.mmaemilia.ArchiType archiType2 = mmaemiliaFactory.eINSTANCE.createArchiType();
		aEmiliaSpecification.setArchiTypeDecl(archiType2);
		Header header = archiType.getArchiTypeHeader();
		// impostiamo il nome dell'archiType2
		String string = header.getName();
		archiType2.setAtName(string);
		// impostiamo l'header per archiType2
		AT_Header at_Header = HeadersFactory.eINSTANCE.createAT_Header();
		// imposto i parametri costanti inizializzati per at_Header
		ParamDeclaration[] paramDeclarations = header.getParameters();
		List<ConstInit> constInits = at_Header.getInitConst();
		for (int i = 0; i < paramDeclarations.length; i++) {
			if (paramDeclarations[i] != null) {
				ConstInit constInit = HeadersFactory.eINSTANCE.createConstInit();
				specificheAEmilia.ConstInit constInit2 = (specificheAEmilia.ConstInit) paramDeclarations[i];
				String string2 = constInit2.getName();
				DataType dataType = constInit2.getType();
				Expression expression = constInit2.getExpr();
				// imposto il nome per constInit
				constInit.setName(string2);
				// imposto il tipo di dato per constInit
				metamodel.mmaemilia.DataType.DataType dataType2 = getDataType(dataType);
				constInit.setInitConstData(dataType2);
				// imposto l'espressione per constInit
				metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(expression);
				constInit.setInitConstExpr(expression2);
				constInits.add(constInit);
			}
		}
		archiType2.setHeader(at_Header);
		// impostiamo i tipi di elementi architetturali per archiType2
		ArchiElemTypes archiElemTypes = archiType.getArchiElemTypes();
		metamodel.mmaemilia.ArchiElemTypes archiElemTypes2 = mmaemiliaFactory.eINSTANCE.createArchiElemTypes();
		ElemType[] elemTypes = archiElemTypes.getElementTypes();
		List<metamodel.mmaemilia.ElemType> elemTypes2 = archiElemTypes2.getEtDeclaration();
		for (ElemType elemType : elemTypes) {
			// aggiungo elemType a elemTypes2
			metamodel.mmaemilia.ElemType elemType2 = mmaemiliaFactory.eINSTANCE.createElemType();
			Header headerEt = elemType.getHeader();
			// imposto il nome per elemType2
			String string2 = headerEt.getName();
			elemType2.setEtName(string2);
			// imposto l'header per elemType2
			ET_Header et_Header = HeadersFactory.eINSTANCE.createET_Header();
			// imposto i parametri costanti per et_Header
			ParamDeclaration[] paramDeclarations2 = headerEt.getParameters();
			List<Const> list = et_Header.getCostant();
			for (ParamDeclaration paramDeclaration : paramDeclarations2) {
				if (paramDeclaration != null) {
					specificheAEmilia.Const const1 = (specificheAEmilia.Const) paramDeclaration;
					Const const2 = HeadersFactory.eINSTANCE.createConst();
					String string3 = const1.getName();
					DataType dataType = const1.getType();
					metamodel.mmaemilia.DataType.DataType dataType2 = getDataType(dataType);
					const2.setName(string3);
					const2.setConstantData(dataType2);
					list.add(const2);
				}
			}
			elemType2.setElemHeader(et_Header);
			// imposto il comportamento per elemType2
			Behavior behavior = BehaviorFactory.eINSTANCE.createBehavior();
			// imposto le equazioni comportamentali per behavior
			List<BehavEquation> behavEquations = behavior.getEquations();
			AETbehavior aeTbehavior = elemType.getBehavior();
			specificheAEmilia.BehavEquation[] behavEquations2 = aeTbehavior.getBehaviors();
			for (specificheAEmilia.BehavEquation behavEquation : behavEquations2) {
				BehavEquation behavEquation2 = BehaviorFactory.eINSTANCE.createBehavEquation();
				// imposto il nome per behavEquation2
				Header header2 = behavEquation.getBehavHeader();
				String string3 = header2.getName();
				behavEquation2.setName(string3);
				// imposto l'intestazione per behavEquation2
				BehavHeader behavHeader = HeadersFactory.eINSTANCE.createBehavHeader();
				ParamDeclaration[] paramDeclarations3 = header2.getParameters();
				// imposto la parte destra di behavHeader
				RightSide rightSide = HeadersFactory.eINSTANCE.createRightSide();
				// imposto le variabili locali di rightSide
				List<Local> locals = rightSide.getLocalDef();
				List<Local> locals2 = getLocalVars(paramDeclarations3);
				locals.addAll(locals2);
				behavHeader.setRight(rightSide);
				// imposto la parte sinistra di behavHeader
				LeftSide leftSide = HeadersFactory.eINSTANCE.createLeftSide();
				// imposto le variabili inizializzate di leftSide
				List<VarInit> varInits = leftSide.getInitVar();
				List<VarInit> varInits2 = getInitVars(paramDeclarations3);
				varInits.addAll(varInits2);
				// imposto le variabili non inizializzate di leftSide
				List<Var> vars = leftSide.getVarDef();
				List<Var> vars2 = getVars(paramDeclarations3);
				vars.addAll(vars2);
				behavHeader.setLeft(leftSide);
				behavEquation2.setBHeader(behavHeader);
				behavEquations.add(behavEquation2);
			}
			elemType2.setBehaviorDecl(behavior);
			// imposto i termini di processo di behavEquations
			for (int i = 0; i < behavEquations2.length; i++) {
				specificheAEmilia.BehavEquation behavEquation = behavEquations2[i];
				BehavEquation behavEquation2 = behavEquations.get(i);
				// imposto il termine di processo per behavEquation2
				specificheAEmilia.ProcessTerm processTerm = behavEquation.getTermineProcesso();
				metamodel.mmaemilia.Behavior.ProcessTerm processTerm2 = getProcessTerm(processTerm, elemType2,
						elemType);
				behavEquation2.setPt(processTerm2);
			}
			AETinteractions aeTinteractions = elemType.getInteractions();
			InputInteractions inputInteractions = aeTinteractions.getInIn();
			OutputInteractions outputInteractions = aeTinteractions.getOuIn();
			ANDinteractions anDinteractionsInput = inputInteractions != null ? inputInteractions.getAnd() : null;
			ORinteractions oRinteractionsInput = inputInteractions != null ? inputInteractions.getOr() : null;
			UNIinteractions unIinteractionsInput = inputInteractions != null ? inputInteractions.getUni() : null;
			ANDinteractions anDinteractionsOutput = outputInteractions != null ? outputInteractions.getAnd() : null;
			ORinteractions oRinteractionsOutput = outputInteractions != null ? outputInteractions.getOr() : null;
			UNIinteractions unIinteractionsOutput = outputInteractions != null ? outputInteractions.getUni() : null;
			String[] strings = anDinteractionsInput != null ? anDinteractionsInput.getActions() : new String[0];
			String[] strings2 = oRinteractionsInput != null ? oRinteractionsInput.getActions() : new String[0];
			String[] strings3 = unIinteractionsInput != null ? unIinteractionsInput.getActions() : new String[0];
			String[] strings4 = anDinteractionsOutput != null ? anDinteractionsOutput.getActions() : new String[0];
			String[] strings5 = oRinteractionsOutput != null ? oRinteractionsOutput.getActions() : new String[0];
			String[] strings6 = unIinteractionsOutput != null ? unIinteractionsOutput.getActions() : new String[0];
			List<InputInteraction> inputInteractions2 = elemType2.getIiDecl();
			// imposto le input interactions di elemType2
			// imposto le and interaction
			for (String string3 : strings) {
				InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
				inputInteraction.setIntName(string3);
				inputInteraction.setType(InteractionType.AND);
				inputInteractions2.add(inputInteraction);
			}
			// imposto le or interaction
			for (String string3 : strings2) {
				InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
				inputInteraction.setIntName(string3);
				inputInteraction.setType(InteractionType.OR);
				inputInteractions2.add(inputInteraction);
			}
			// imposto le uni interaction
			for (String string3 : strings3) {
				InputInteraction inputInteraction = mmaemiliaFactory.eINSTANCE.createInputInteraction();
				inputInteraction.setIntName(string3);
				inputInteraction.setType(InteractionType.UNI);
				inputInteractions2.add(inputInteraction);
			}
			List<OutputInteraction> outputInteractions2 = elemType2.getOiDecl();
			// imposto le output interactions di elemType2
			// imposto le and interaction
			for (String string3 : strings4) {
				OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
				outputInteraction.setIntName(string3);
				outputInteraction.setType(InteractionType.AND);
				outputInteractions2.add(outputInteraction);
			}
			// imposto le or interaction
			for (String string3 : strings5) {
				OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
				outputInteraction.setIntName(string3);
				outputInteraction.setType(InteractionType.OR);
				outputInteractions2.add(outputInteraction);
			}
			// imposto le uni interaction
			for (String string3 : strings6) {
				OutputInteraction outputInteraction = mmaemiliaFactory.eINSTANCE.createOutputInteraction();
				outputInteraction.setIntName(string3);
				outputInteraction.setType(InteractionType.UNI);
				outputInteractions2.add(outputInteraction);
			}
			elemTypes2.add(elemType2);
		}
		archiType2.setAetDeclaration(archiElemTypes2);
		// impostaimo la topologia per archiType2
		ArchiTopology archiTopology = archiType.getTopologia();
		metamodel.mmaemilia.ArchiTopology archiTopology2 = mmaemiliaFactory.eINSTANCE.createArchiTopology();
		// impostiamo le istanze per archiTopology2
		ArchiElemInstances archiElemInstances = archiTopology.getAEIs();
		AEIdecl[] aeIdecls = archiElemInstances.getAEIdeclSeq();
		List<ArchiElemInstance> elemInstances = archiTopology2.getAeiDecl();
		for (AEIdecl idecl : aeIdecls) {
			ArchiElemInstance archiElemInstance = mmaemiliaFactory.eINSTANCE.createArchiElemInstance();
			// impostiamo il nome dell'istanza per archiElemInstance
			AEIident aeIident = idecl.getAeIident();
			String string2 = aeIident.getName();
			Expression selector = aeIident.getSelector();
			archiElemInstance.setInstanceName(string2);
			// impostaimo i parametri attuali per archiElemInstance
			Expression[] expressions = idecl.getActualParams();
			List<String> list = archiElemInstance.getActualParam();
			for (Expression expression : expressions) {
				String string3 = expression.toString();
				list.add(string3);
			}
			// impostiamo il tipo di elemento architetturale per archiElemInstance
			String string3 = idecl.getAET();
			metamodel.mmaemilia.ElemType elemType = getElemTypeFromAetName(elemTypes2, string3);
			archiElemInstance.setTypeOf(elemType);
			elemInstances.add(archiElemInstance);
			if (selector != null) {
				metamodel.mmaemilia.Expressions.Expression expression2 = getExpressionMM(selector);
				archiElemInstance.setSelector(expression2);
			}
			// si imposta elem di idecl
			for (ElemTypeNorm elemTypeNorm : elemTypeNorms) {
				AEIdecl aeIdecl = elemTypeNorm.getAEIdecl();
				ElemType elemType2 = elemTypeNorm.getNewElemType();
				AETbehavior aeTbehavior = elemType2.getBehavior();
				if (idecl.equals(aeIdecl)) {
					Elem elem = mmaemiliaFactory.eINSTANCE.createElem();
					archiElemInstance.setElem(elem);
					Behavior behavior = getBehavior(aeTbehavior, elemType, elemType2, archiElemInstance);
					elem.setBehaviorDecl(behavior);
				}
			}
		}
		// impostiamo le interazioni architetturali per archiTopology2
		ArchiInteractions archiInteractions = archiTopology.getArchiInteractions();
		InteractionDecl[] interactionDecls = archiInteractions.getInteractions();
		List<ArchitecturalInteraction> architecturalInteractions = archiTopology2.getAiDecl();
		if (interactionDecls != null) {
			for (InteractionDecl interactionDecl : interactionDecls) {
				String string2 = interactionDecl.getInteraction();
				AEIident aeIident = interactionDecl.getAei();
				ArchitecturalInteraction architecturalInteraction = mmaemiliaFactory.eINSTANCE
						.createArchitecturalInteraction();
				// si imposta il nome di architecturalInteraction
				architecturalInteraction.setName(string2);
				// imposto l'interazione relativa a architecturalInteraction appartenente al
				// tipo di elemento architetturale di aeIident
				ArchiElemInstance archiElemInstance = getInstanceFromName(elemInstances, aeIident);
				metamodel.mmaemilia.ElemType elemType = archiElemInstance.getTypeOf();
				LocalInteraction localInteraction = getInteractionFromName(elemType, string2);
				architecturalInteraction.setIs_A(localInteraction);
				// si imposta l'istanza per ogni interazione
				architecturalInteraction.setFromInstance(archiElemInstance);
				architecturalInteractions.add(architecturalInteraction);
			}
		}
		// impostiamo i collegamenti architetturali per archiTopology2
		ArchiAttachments archiAttachments = archiTopology.getAttachments();
		AttacDecl[] attacDecls = archiAttachments.getAttachments();
		List<Attachment> attachments = archiTopology2.getAttDecl();
		for (AttacDecl attacDecl : attacDecls) {
			Attachment attachment = mmaemiliaFactory.eINSTANCE.createAttachment();
			AEIident aeIident2 = attacDecl.getOutputAei();
			String string3 = attacDecl.getOutputInteraction();
			AEIident aeIident4 = attacDecl.getInputAei();
			String string5 = attacDecl.getInputInteraction();
			// si imposta il from di attachment
			From from = mmaemiliaFactory.eINSTANCE.createFrom();
			ArchiElemInstance archiElemInstance = getInstanceFromName(elemInstances, aeIident2);
			OutputInteraction outputInteraction = getOutputInteractionFromAei(archiElemInstance, string3);
			from.setFromInstance(archiElemInstance);
			from.setIsOutput(outputInteraction);
			attachment.setStart(from);
			// si imposta il to di attachment
			To to = mmaemiliaFactory.eINSTANCE.createTo();
			ArchiElemInstance archiElemInstance2 = getInstanceFromName(elemInstances, aeIident4);
			InputInteraction inputInteraction = getInputInteractionFromAei(archiElemInstance2, string5);
			to.setIsInput(inputInteraction);
			to.setToInstance(archiElemInstance2);
			attachment.setEnd(to);
			attachments.add(attachment);
		}
		archiType2.setAtDeclaration(archiTopology2);
		
		// ASE Method for adding dsl extention to the EMF Extention Register
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mmaemilia", new XMIResourceFactoryImpl());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(mmaemiliaPackage.eINSTANCE.getNsURI(), mmaemiliaPackage.eINSTANCE);

		URI fileURI = URI.createFileURI(mmAemiliaPath.toString());
		Resource asResource = resourceSet.createResource(fileURI);
		asResource.getContents().add(aEmiliaSpecification);
		asResource.save(null);
	}

}
