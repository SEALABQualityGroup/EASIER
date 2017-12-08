/**
 * 
 */
package it.univaq.from_aemilia_to_qn_plug_in.serialize;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import metamodel.mmaemilia.ArchiElemInstance;
import metamodel.mmaemilia.ArchiElemTypes;
import metamodel.mmaemilia.ArchiTopology;
import metamodel.mmaemilia.ArchitecturalInteraction;
import metamodel.mmaemilia.Attachment;
import metamodel.mmaemilia.Elem;
import metamodel.mmaemilia.ElemType;
import metamodel.mmaemilia.From;
import metamodel.mmaemilia.InputInteraction;
import metamodel.mmaemilia.InteractionType;
import metamodel.mmaemilia.OutputInteraction;
import metamodel.mmaemilia.To;
import metamodel.mmaemilia.Behavior.Action;
import metamodel.mmaemilia.Behavior.ActionInput;
import metamodel.mmaemilia.Behavior.ActionOutput;
import metamodel.mmaemilia.Behavior.ActionProcess;
import metamodel.mmaemilia.Behavior.ActionRate;
import metamodel.mmaemilia.Behavior.ActionType;
import metamodel.mmaemilia.Behavior.BehavEquation;
import metamodel.mmaemilia.Behavior.BehavProcess;
import metamodel.mmaemilia.Behavior.Behavior;
import metamodel.mmaemilia.Behavior.ChoiceProcess;
import metamodel.mmaemilia.Behavior.ProcessTerm;
import metamodel.mmaemilia.Behavior.RateExp;
import metamodel.mmaemilia.Behavior.RateInf;
import metamodel.mmaemilia.Behavior.RatePas;
import metamodel.mmaemilia.Behavior.Stop;
import metamodel.mmaemilia.DataType.Array;
import metamodel.mmaemilia.DataType.Boolean;
import metamodel.mmaemilia.DataType.DataType;
import metamodel.mmaemilia.DataType.Integer;
import metamodel.mmaemilia.DataType.Normal;
import metamodel.mmaemilia.DataType.RangeInt;
import metamodel.mmaemilia.DataType.Real;
import metamodel.mmaemilia.DataType.Record;
import metamodel.mmaemilia.DataType.Special;
import metamodel.mmaemilia.DataType.SpecialType;
import metamodel.mmaemilia.Expressions.ArithExpr;
import metamodel.mmaemilia.Expressions.ArithOpName;
import metamodel.mmaemilia.Expressions.ArrayExpr;
import metamodel.mmaemilia.Expressions.ArrayOpName;
import metamodel.mmaemilia.Expressions.BoolOpName;
import metamodel.mmaemilia.Expressions.BooleanExpr;
import metamodel.mmaemilia.Expressions.Expression;
import metamodel.mmaemilia.Expressions.IdentExpr;
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
import metamodel.mmaemilia.Headers.LeftSide;
import metamodel.mmaemilia.Headers.RightSide;
import metamodel.mmaemilia.Headers.Var;
import metamodel.mmaemilia.Headers.VarInit;
import restrizioniIstanze.qnElemTypes.ElemTypeNorm;
import scanSpecAEmilia.ScanException;
import scanSpecAEmilia.scanExp.ScanExp;
import specificheAEmilia.AEIdecl;
import specificheAEmilia.AEIident;
import specificheAEmilia.AETbehavior;
import specificheAEmilia.AETinteractions;
import specificheAEmilia.ANDinteractions;
import specificheAEmilia.And;
import specificheAEmilia.ArchiAttachments;
import specificheAEmilia.ArchiElemInstances;
import specificheAEmilia.ArchiInteractions;
import specificheAEmilia.ArchiType;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.ArrayType;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.BooleanType;
import specificheAEmilia.Concat;
import specificheAEmilia.Different;
import specificheAEmilia.Divisione;
import specificheAEmilia.Equal;
import specificheAEmilia.First;
import specificheAEmilia.Get;
import specificheAEmilia.Header;
import specificheAEmilia.InputInteractions;
import specificheAEmilia.Insert;
import specificheAEmilia.IntegerRangeType;
import specificheAEmilia.IntegerType;
import specificheAEmilia.InteractionDecl;
import specificheAEmilia.Length;
import specificheAEmilia.ListCons;
import specificheAEmilia.ListType;
import specificheAEmilia.Local;
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
import specificheAEmilia.Put;
import specificheAEmilia.RateType;
import specificheAEmilia.Rate_;
import specificheAEmilia.Read;
import specificheAEmilia.RealType;
import specificheAEmilia.RecordCons;
import specificheAEmilia.RecordType;
import specificheAEmilia.Remove;
import specificheAEmilia.Somma;
import specificheAEmilia.Sottrazione;
import specificheAEmilia.Tail;
import specificheAEmilia.UNIinteractions;
import specificheAEmilia.VariableDeclaration;
import specificheAEmilia.WeightType;
import specificheAEmilia.Write;

/**
 * @author Mirko
 *
 */
public class MMAemiliaToArchiType {

	public ArchiType transform(metamodel.mmaemilia.ArchiType archiType) throws Exception {
		ArchiType archiType2 = new ArchiType();
		// si serializza l'AT_Header
		String string = archiType.getAtName();
		AT_Header at_Header = archiType.getHeader();
		Header header = getHeader(at_Header, string);
		archiType2.setArchiTypeHeader(header);
		// si serializza l'ArchiElemTypes
		ArchiElemTypes archiElemTypes = archiType.getAetDeclaration();
		specificheAEmilia.ArchiElemTypes archiElemTypes2 = getAETs(archiElemTypes);
		archiType2.setArchiElemTypes(archiElemTypes2);
		// si serializza l'ArchiTopology
		ArchiTopology archiTopology = archiType.getAtDeclaration();
		specificheAEmilia.ArchiTopology archiTopology2 = getAT(archiTopology);
		archiType2.setTopologia(archiTopology2);
		return archiType2;
	}

	public List<ElemTypeNorm> getElems(metamodel.mmaemilia.ArchiType archiType) throws ScanException {
		List<ElemTypeNorm> elemTypeNorms = new ArrayList<ElemTypeNorm>();
		ArchiTopology archiTopology = archiType.getAtDeclaration();
		List<ArchiElemInstance> archiElemInstances = archiTopology.getAeiDecl();
		for (ArchiElemInstance archiElemInstance : archiElemInstances) {
			List<String> strings = archiElemInstance.getActualParam();
			String name = archiElemInstance.getInstanceName();
			ElemType elemType = archiElemInstance.getTypeOf();
			String elemTypeName = elemType.getEtName();
			Elem elem = archiElemInstance.getElem();
			Expression selector = archiElemInstance.getSelector();
			specificheAEmilia.Expression expression2 = getExpression(selector);
			AEIident aeIident = new AEIident(name, expression2);
			Behavior behavior = elem.getBehaviorDecl();
			ElemTypeNorm elemTypeNorm = new ElemTypeNorm();
			// si imposta l'aeidecl di elemTypeNorm
			AEIdecl aeIdecl = new AEIdecl();
			// si impostano i parametri attuali di aeIdecl
			specificheAEmilia.Expression[] expressions = new specificheAEmilia.Expression[strings.size()];
			for (int i = 0; i < strings.size(); i++) {
				String string = strings.get(i);
				ScanExp scanExp = new ScanExp(0);
				specificheAEmilia.Expression expression = scanExp.scanEspressione(string);
				expressions[i] = expression;
			}
			aeIdecl.setActualParams(expressions);
			// si imposta il nome per il tipo di elemento architetturale di aeIdecl
			aeIdecl.setAET(elemTypeName);
			// si imposta il nome di aeIdecl
			aeIdecl.setAeIident(aeIident);
			elemTypeNorm.setAEIdecl(aeIdecl);
			// si imposta il tipo di elemento architetturale di elemTypeNorm
			specificheAEmilia.ElemType elemType2 = getElemType(elemType);
			elemTypeNorm.setOldElemType(elemType2);
			// si imposta il tipo di elemento architetturale instanziato di elemTypeNorm
			Header header = elemType2.getHeader().copy();
			AETinteractions aeTinteractions = elemType2.getInteractions().copy();
			AETbehavior aeTbehavior = getAETBehavior(behavior);
			specificheAEmilia.ElemType elemType3 = new specificheAEmilia.ElemType(header, aeTbehavior, aeTinteractions);
			elemTypeNorm.setNewElemType(elemType3);
			elemTypeNorms.add(elemTypeNorm);
		}
		return elemTypeNorms;
	}

	private specificheAEmilia.ArchiTopology getAT(ArchiTopology archiTopology) throws Exception {
		specificheAEmilia.ArchiTopology archiTopology2 = new specificheAEmilia.ArchiTopology();
		// serializzo aiDecl
		ArchiInteractions archiInteractions = new ArchiInteractions();
		List<ArchitecturalInteraction> architecturalInteractions = archiTopology.getAiDecl();
		InteractionDecl[] interactionDecls = new InteractionDecl[architecturalInteractions.size()];
		for (int i = 0; i < architecturalInteractions.size(); i++) {
			ArchitecturalInteraction architecturalInteraction = architecturalInteractions.get(i);
			InteractionDecl interactionDecl = getInteractionDecl(architecturalInteraction);
			interactionDecls[i] = interactionDecl;
		}
		archiInteractions.setInteractions(interactionDecls);
		archiTopology2.setArchiInteractions(archiInteractions);
		// serializzo aeiDecl
		ArchiElemInstances archiElemInstances = new ArchiElemInstances();
		List<ArchiElemInstance> archiElemInstances2 = archiTopology.getAeiDecl();
		AEIdecl[] aeIdecls = new AEIdecl[archiElemInstances2.size()];
		for (int i = 0; i < archiElemInstances2.size(); i++) {
			ArchiElemInstance archiElemInstance = archiElemInstances2.get(i);
			AEIdecl aeIdecl = getAEIdecl(archiElemInstance);
			aeIdecls[i] = aeIdecl;
		}
		archiElemInstances.setAEIdeclSeq(aeIdecls);
		archiTopology2.setAEIs(archiElemInstances);
		// serializzo attDecl
		ArchiAttachments archiAttachments = new ArchiAttachments();
		List<Attachment> attachments = archiTopology.getAttDecl();
		AttacDecl[] attacDecls = new AttacDecl[attachments.size()];
		for (int i = 0; i < attachments.size(); i++) {
			Attachment attachment = attachments.get(i);
			AttacDecl attacDecl = getAttacDecl(attachment);
			attacDecls[i] = attacDecl;
		}
		archiAttachments.setAttachments(attacDecls);
		archiTopology2.setAttachments(archiAttachments);
		return archiTopology2;
	}

	private AttacDecl getAttacDecl(Attachment attachment) {
		AttacDecl attacDecl = new AttacDecl();
		// serializziamo end
		To to = attachment.getEnd();
		InputInteraction inputInteraction = to.getIsInput();
		ArchiElemInstance archiElemInstance = to.getToInstance();
		String string = inputInteraction.getIntName();
		String string2 = archiElemInstance.getInstanceName();
		Expression expression = archiElemInstance.getSelector();
		specificheAEmilia.Expression expression2 = getExpression(expression);
		AEIident aeIident = new AEIident(string2, expression2);
		attacDecl.setInputAei(aeIident);
		attacDecl.setInputInteraction(string);
		// serializziamo start
		From from = attachment.getStart();
		ArchiElemInstance archiElemInstance2 = from.getFromInstance();
		OutputInteraction outputInteraction = from.getIsOutput();
		String string3 = outputInteraction.getIntName();
		String string4 = archiElemInstance2.getInstanceName();
		Expression expression3 = archiElemInstance2.getSelector();
		specificheAEmilia.Expression expression4 = getExpression(expression3);
		AEIident aeIident2 = new AEIident(string4, expression4);
		attacDecl.setOutputAei(aeIident2);
		attacDecl.setOutputInteraction(string3);
		return attacDecl;
	}

	private AEIdecl getAEIdecl(ArchiElemInstance archiElemInstance) throws Exception {
		AEIdecl aeIdecl = new AEIdecl();
		// serializziamo actualParam
		List<String> list = archiElemInstance.getActualParam();
		specificheAEmilia.Expression[] expressions = new specificheAEmilia.Expression[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			ScanExp scanExp = new ScanExp(0);
			specificheAEmilia.Expression expression = scanExp.scanEspressione(string);
			expressions[i] = expression;
		}
		aeIdecl.setActualParams(expressions);
		// serializziamo instanceName e selector
		String string = archiElemInstance.getInstanceName();
		Expression expression = archiElemInstance.getSelector();
		specificheAEmilia.Expression selector = getExpression(expression);
		AEIident aeIident = new AEIident(string, selector);
		aeIdecl.setAeIident(aeIident);
		// serializziamo typeOf
		ElemType elemType = archiElemInstance.getTypeOf();
		String string2 = elemType.getEtName();
		aeIdecl.setAET(string2);
		return aeIdecl;
	}

	private InteractionDecl getInteractionDecl(ArchitecturalInteraction architecturalInteraction) {
		InteractionDecl interactionDecl = new InteractionDecl();
		// serializziamo fromInstance
		ArchiElemInstance archiElemInstance = architecturalInteraction.getFromInstance();
		String aei = archiElemInstance.getInstanceName();
		Expression expression = archiElemInstance.getSelector();
		specificheAEmilia.Expression selector = getExpression(expression);
		AEIident aeIident = new AEIident(aei, selector);
		interactionDecl.setAei(aeIident);
		// serializziamo name
		String string = architecturalInteraction.getName();
		interactionDecl.setInteraction(string);
		return interactionDecl;
	}

	private specificheAEmilia.ArchiElemTypes getAETs(ArchiElemTypes archiElemTypes) {
		specificheAEmilia.ArchiElemTypes archiElemTypes2 = new specificheAEmilia.ArchiElemTypes();
		// serializziamo etDeclaration
		List<ElemType> list = archiElemTypes.getEtDeclaration();
		specificheAEmilia.ElemType[] elemTypes = new specificheAEmilia.ElemType[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ElemType elemType = list.get(i);
			specificheAEmilia.ElemType elemType2 = getElemType(elemType);
			elemTypes[i] = elemType2;
		}
		archiElemTypes2.setElementTypes(elemTypes);
		return archiElemTypes2;
	}

	private specificheAEmilia.ElemType getElemType(ElemType elemType) {
		specificheAEmilia.ElemType elemType2 = new specificheAEmilia.ElemType();
		// serializziamo behaviorDecl
		Behavior behavior = elemType.getBehaviorDecl();
		AETbehavior aeTbehavior = getAETBehavior(behavior);
		elemType2.setBehavior(aeTbehavior);
		// serializziamo etName
		String string = elemType.getEtName();
		// serializziamo elemHeader
		ET_Header et_Header = elemType.getElemHeader();
		Header header = getHeader(et_Header, string);
		elemType2.setHeader(header);
		// serializziamo iiDecl
		List<InputInteraction> inputInteractions = elemType.getIiDecl();
		AETinteractions aeTinteractions = new AETinteractions();
		InputInteractions inputInteractions2 = new InputInteractions();
		ANDinteractions anDinteractions = new ANDinteractions();
		ORinteractions oRinteractions = new ORinteractions();
		UNIinteractions unIinteractions = new UNIinteractions();
		List<String> listAnds = new ArrayList<String>();
		List<String> listOrs = new ArrayList<String>();
		List<String> listUnis = new ArrayList<String>();
		for (InputInteraction inputInteraction : inputInteractions) {
			String string2 = inputInteraction.getIntName();
			InteractionType interactionType = inputInteraction.getType();
			if (InteractionType.AND.equals(interactionType)) {
				listAnds.add(string2);
			}
			if (InteractionType.OR.equals(interactionType)) {
				listOrs.add(string2);
			}
			if (InteractionType.UNI.equals(interactionType)) {
				listUnis.add(string2);
			}
		}
		if (!listAnds.isEmpty()) {
			String[] strings = new String[listAnds.size()];
			listAnds.toArray(strings);
			anDinteractions.setActions(strings);
			inputInteractions2.setAnd(anDinteractions);
		}
		if (!listOrs.isEmpty()) {
			String[] strings = new String[listOrs.size()];
			listOrs.toArray(strings);
			oRinteractions.setActions(strings);
			inputInteractions2.setOr(oRinteractions);
		}
		if (!listUnis.isEmpty()) {
			String[] strings = new String[listUnis.size()];
			listUnis.toArray(strings);
			unIinteractions.setActions(strings);
			inputInteractions2.setUni(unIinteractions);
		}
		aeTinteractions.setInIn(inputInteractions2);
		// serializziamo oiDecl
		List<OutputInteraction> outputInteractions = elemType.getOiDecl();
		OutputInteractions outputInteractions2 = new OutputInteractions();
		ANDinteractions anDinteractions2 = new ANDinteractions();
		ORinteractions oRinteractions2 = new ORinteractions();
		UNIinteractions unIinteractions2 = new UNIinteractions();
		List<String> listAnds2 = new ArrayList<String>();
		List<String> listOrs2 = new ArrayList<String>();
		List<String> listUnis2 = new ArrayList<String>();
		for (OutputInteraction outputInteraction : outputInteractions) {
			String string2 = outputInteraction.getIntName();
			InteractionType interactionType = outputInteraction.getType();
			if (InteractionType.AND.equals(interactionType)) {
				listAnds2.add(string2);
			}
			if (InteractionType.OR.equals(interactionType)) {
				listOrs2.add(string2);
			}
			if (InteractionType.UNI.equals(interactionType)) {
				listUnis2.add(string2);
			}
		}
		if (!listAnds2.isEmpty()) {
			String[] strings = new String[listAnds2.size()];
			listAnds2.toArray(strings);
			anDinteractions2.setActions(strings);
			outputInteractions2.setAnd(anDinteractions2);
		}
		if (!listOrs2.isEmpty()) {
			String[] strings = new String[listOrs2.size()];
			listOrs2.toArray(strings);
			oRinteractions2.setActions(strings);
			outputInteractions2.setOr(oRinteractions2);
		}
		if (!listUnis2.isEmpty()) {
			String[] strings = new String[listUnis2.size()];
			listUnis2.toArray(strings);
			unIinteractions2.setActions(strings);
			outputInteractions2.setUni(unIinteractions2);
		}
		aeTinteractions.setOuIn(outputInteractions2);
		elemType2.setInteractions(aeTinteractions);
		return elemType2;
	}

	private Header getHeader(ET_Header et_Header, String string) {
		Header header = new Header();
		// serializziamo costant
		List<Const> consts = et_Header.getCostant();
		ParamDeclaration[] paramDeclarations = null;
		if (consts.isEmpty())
			paramDeclarations = new ParamDeclaration[1];
		else
			paramDeclarations = new ParamDeclaration[consts.size()];
		for (int i = 0; i < consts.size(); i++) {
			Const const1 = consts.get(i);
			specificheAEmilia.Const const2 = getConst(const1);
			paramDeclarations[i] = const2;
		}
		header.setParameters(paramDeclarations);
		// serializziamo string
		header.setName(string);
		return header;
	}

	private specificheAEmilia.Const getConst(Const const1) {
		specificheAEmilia.Const const2 = new specificheAEmilia.Const();
		// serializziamo constantData
		DataType dataType = const1.getConstantData();
		specificheAEmilia.DataType dataType2 = getDataType(dataType);
		const2.setType(dataType2);
		// serializziamo name
		String string = const1.getName();
		const2.setName(string);
		return const2;
	}

	private specificheAEmilia.DataType getDataType(DataType dataType) {
		if (dataType instanceof Real) {
			RealType realType = new RealType();
			return realType;
		}
		if (dataType instanceof Boolean) {
			BooleanType booleanType = new BooleanType();
			return booleanType;
		}
		if (dataType instanceof RangeInt) {
			RangeInt rangeInt = (RangeInt) dataType;
			IntegerRangeType integerRangeType = new IntegerRangeType();
			// serializziamo maxVal
			Expression expression = rangeInt.getMaxVal();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			integerRangeType.setEndingInt(expression2);
			// serializziamo minVal
			Expression expression3 = rangeInt.getMinVal();
			specificheAEmilia.Expression expression4 = getExpression(expression3);
			integerRangeType.setBeginningInt(expression4);
			return integerRangeType;
		}
		if (dataType instanceof Integer) {
			IntegerType integerType = new IntegerType();
			return integerType;
		}
		if (dataType instanceof metamodel.mmaemilia.DataType.List) {
			metamodel.mmaemilia.DataType.List list = (metamodel.mmaemilia.DataType.List) dataType;
			ListType listType = new ListType();
			// serializzare listElemType
			Normal normal = list.getListElemType();
			NormalType normalType = getNormalType(normal);
			listType.setType(normalType);
			return listType;
		}
		if (dataType instanceof Array) {
			Array array = (Array) dataType;
			ArrayType arrayType = new ArrayType();
			// serializzo arrayElemType
			Normal normal = array.getArrayElemType();
			NormalType normalType = getNormalType(normal);
			arrayType.setType(normalType);
			// serializzo lenght
			Expression expression = array.getLength();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			arrayType.setLength(expression2);
			return arrayType;
		}
		if (dataType instanceof Record) {
			Record record = (Record) dataType;
			RecordType recordType = new RecordType();
			// serializzare field_decl_seq
			List<Var> vars = record.getField_decl_seq();
			VariableDeclaration[] variableDeclarations = new VariableDeclaration[vars.size()];
			for (int i = 0; i < vars.size(); i++) {
				Var var = vars.get(i);
				String string = var.getName();
				Normal normal = var.getVarType();
				NormalType normalType = getNormalType(normal);
				VariableDeclaration variableDeclaration = new VariableDeclaration();
				variableDeclaration.setName(string);
				variableDeclaration.setType(normalType);
				variableDeclarations[i] = variableDeclaration;
			}
			recordType.setVariableDeclarations(variableDeclarations);
			return recordType;
		}
		if (dataType instanceof Special) {
			Special special = (Special) dataType;
			SpecialType specialType = special.getType();
			if (SpecialType.PRIO.equals(specialType)) {
				PrioType prioType = new PrioType();
				return prioType;
			}
			if (SpecialType.RATE.equals(specialType)) {
				RateType rateType = new RateType();
				return rateType;
			}
			if (SpecialType.WEIGHT.equals(specialType)) {
				WeightType weightType = new WeightType();
				return weightType;
			}
		}
		return null;
	}

	private NormalType getNormalType(Normal normal) {
		if (normal instanceof Real) {
			RealType realType = new RealType();
			return realType;
		}
		if (normal instanceof Boolean) {
			BooleanType booleanType = new BooleanType();
			return booleanType;
		}
		if (normal instanceof RangeInt) {
			RangeInt rangeInt = (RangeInt) normal;
			IntegerRangeType integerRangeType = new IntegerRangeType();
			// serializziamo maxVal
			Expression expression = rangeInt.getMaxVal();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			integerRangeType.setEndingInt(expression2);
			// serializziamo minVal
			Expression expression3 = rangeInt.getMinVal();
			specificheAEmilia.Expression expression4 = getExpression(expression3);
			integerRangeType.setBeginningInt(expression4);
			return integerRangeType;
		}
		if (normal instanceof Integer) {
			IntegerType integerType = new IntegerType();
			return integerType;
		}
		if (normal instanceof metamodel.mmaemilia.DataType.List) {
			metamodel.mmaemilia.DataType.List list = (metamodel.mmaemilia.DataType.List) normal;
			ListType listType = new ListType();
			// serializzare listElemType
			Normal normal2 = list.getListElemType();
			NormalType normalType = getNormalType(normal2);
			listType.setType(normalType);
			return listType;
		}
		if (normal instanceof Array) {
			Array array = (Array) normal;
			ArrayType arrayType = new ArrayType();
			// serializzo arrayElemType
			Normal normal2 = array.getArrayElemType();
			NormalType normalType = getNormalType(normal2);
			arrayType.setType(normalType);
			// serializzo lenght
			Expression expression = array.getLength();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			arrayType.setLength(expression2);
			return arrayType;
		}
		if (normal instanceof Record) {
			Record record = (Record) normal;
			RecordType recordType = new RecordType();
			// serializzare field_decl_seq
			List<Var> vars = record.getField_decl_seq();
			VariableDeclaration[] variableDeclarations = new VariableDeclaration[vars.size()];
			for (int i = 0; i < vars.size(); i++) {
				Var var = vars.get(i);
				String string = var.getName();
				Normal normal2 = var.getVarType();
				NormalType normalType = getNormalType(normal2);
				VariableDeclaration variableDeclaration = new VariableDeclaration();
				variableDeclaration.setName(string);
				variableDeclaration.setType(normalType);
				variableDeclarations[i] = variableDeclaration;
			}
			recordType.setVariableDeclarations(variableDeclarations);
			return recordType;
		}
		return null;
	}

	private AETbehavior getAETBehavior(Behavior behavior) {
		// serializziamo equations
		AETbehavior aeTbehavior = new AETbehavior();
		List<BehavEquation> behavEquations = behavior.getEquations();
		specificheAEmilia.BehavEquation[] behavEquations2 = new specificheAEmilia.BehavEquation[behavEquations.size()];
		for (int i = 0; i < behavEquations.size(); i++) {
			BehavEquation behavEquation = behavEquations.get(i);
			specificheAEmilia.BehavEquation behavEquation2 = getBehavEquation(behavEquation);
			behavEquations2[i] = behavEquation2;
		}
		aeTbehavior.setBehaviors(behavEquations2);
		return aeTbehavior;
	}

	private specificheAEmilia.BehavEquation getBehavEquation(BehavEquation behavEquation) {
		specificheAEmilia.BehavEquation behavEquation2 = new specificheAEmilia.BehavEquation();
		// serializziamo bHeader
		BehavHeader behavHeader = behavEquation.getBHeader();
		Header header = new Header();
		ParamDeclaration[] paramDeclarations = null;
		List<ParamDeclaration> paramDeclarations2 = new ArrayList<ParamDeclaration>();
		List<ParamDeclaration> left = new ArrayList<ParamDeclaration>();
		List<Local> right = new ArrayList<Local>();
		// serializziamo left
		LeftSide leftSide = behavHeader.getLeft();
		if (leftSide != null) {
			// serializziamo initVar
			List<VarInit> varInits = leftSide.getInitVar();
			for (VarInit varInit : varInits) {
				specificheAEmilia.VarInit varInit2 = getVarInit(varInit);
				left.add(varInit2);
			}
			// serializziamo varDef
			List<Var> vars = leftSide.getVarDef();
			for (Var var : vars) {
				VariableDeclaration variableDeclaration = getVarDecl(var);
				left.add(variableDeclaration);
			}
		}
		if (left.isEmpty()) {
			paramDeclarations2.add(null);
		} else {
			paramDeclarations2.addAll(left);
		}
		// serializziamo right
		RightSide rightSide = behavHeader.getRight();
		if (rightSide != null) {
			// serializziamo localDef
			List<metamodel.mmaemilia.Headers.Local> locals = rightSide.getLocalDef();
			for (metamodel.mmaemilia.Headers.Local local : locals) {
				Local local2 = getLocal(local);
				right.add(local2);
			}
		}
		if (right.isEmpty()) {
			paramDeclarations2.add(null);
		} else {
			paramDeclarations2.addAll(right);
		}
		paramDeclarations = new ParamDeclaration[paramDeclarations2.size()];
		paramDeclarations2.toArray(paramDeclarations);
		header.setParameters(paramDeclarations);
		// serializziamo name
		String name = behavEquation.getName();
		header.setName(name);
		behavEquation2.setBehavHeader(header);
		// serializziamo pt
		ProcessTerm processTerm = behavEquation.getPt();
		specificheAEmilia.ProcessTerm processTerm2 = getPt(processTerm);
		behavEquation2.setTermineProcesso(processTerm2);
		return behavEquation2;
	}

	private specificheAEmilia.ProcessTerm getPt(ProcessTerm processTerm) {
		if (processTerm instanceof ChoiceProcess) {
			ChoiceProcess choiceProcess = (ChoiceProcess) processTerm;
			specificheAEmilia.ChoiceProcess choiceProcess2 = new specificheAEmilia.ChoiceProcess();
			// serializzo condition
			Expression expression = choiceProcess.getCondition();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			choiceProcess2.setCondition(expression2);
			// serializzo processes
			List<ProcessTerm> processTerms = choiceProcess.getProcesses();
			specificheAEmilia.ProcessTerm[] processTerms2 = new specificheAEmilia.ProcessTerm[processTerms.size()];
			for (int i = 0; i < processTerms.size(); i++) {
				ProcessTerm processTerm2 = processTerms.get(i);
				specificheAEmilia.ProcessTerm processTerm3 = getPt(processTerm2);
				processTerms2[i] = processTerm3;
			}
			choiceProcess2.setProcesses(processTerms2);
		}
		if (processTerm instanceof Stop) {
			specificheAEmilia.Stop stop = new specificheAEmilia.Stop();
			Expression expression = processTerm.getCondition();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			stop.setCondition(expression2);
			return stop;
		}
		if (processTerm instanceof BehavProcess) {
			BehavProcess behavProcess = (BehavProcess) processTerm;
			specificheAEmilia.BehavProcess behavProcess2 = new specificheAEmilia.BehavProcess();
			// serializzo condition
			Expression expression = behavProcess.getCondition();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			behavProcess2.setCondition(expression2);
			// serializzo eqCall
			BehavEquation behavEquation = behavProcess.getEqCall();
			String string = behavEquation.getName();
			behavProcess2.setName(string);
			// serializzo exprs
			List<Expression> expressions = behavProcess.getExprs();
			specificheAEmilia.Expression[] expressions2 = new specificheAEmilia.Expression[expressions.size()];
			for (int i = 0; i < expressions.size(); i++) {
				Expression expression3 = expressions.get(i);
				specificheAEmilia.Expression expression4 = getExpression(expression3);
				expressions2[i] = expression4;
			}
			behavProcess2.setExprs(expressions2);
			return behavProcess2;
		}
		if (processTerm instanceof ActionProcess) {
			ActionProcess actionProcess = (ActionProcess) processTerm;
			specificheAEmilia.ActionProcess actionProcess2 = new specificheAEmilia.ActionProcess();
			// serializzo act
			Action action = actionProcess.getAct();
			specificheAEmilia.Action action2 = getAction(action);
			actionProcess2.setAzione(action2);
			// serializzo condition
			Expression expression = actionProcess.getCondition();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			actionProcess2.setCondition(expression2);
			// serializzo process
			ProcessTerm processTerm2 = actionProcess.getProcess();
			specificheAEmilia.ProcessTerm processTerm3 = getPt(processTerm2);
			actionProcess2.setProcesso(processTerm3);
			return actionProcess2;
		}
		return null;
	}

	private specificheAEmilia.Action getAction(Action action) {
		specificheAEmilia.Action action2 = new specificheAEmilia.Action();
		// serializziamo name
		String string = action.getName();
		// serializziamo rate
		ActionRate actionRate = action.getRate();
		if (actionRate instanceof RateExp) {
			RateExp rateExp = (RateExp) actionRate;
			// serializziamo expr
			Expression expression = rateExp.getExpr();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			specificheAEmilia.RateExp rateExp2 = new specificheAEmilia.RateExp(expression2);
			action2.setRate(rateExp2);
		}
		if (actionRate instanceof RateInf) {
			RateInf rateInf = (RateInf) actionRate;
			specificheAEmilia.RateInf rateInf2 = new specificheAEmilia.RateInf();
			// serializzare inf_priority
			Expression expression = rateInf.getInf_priority();
			if (expression != null) {
				specificheAEmilia.Expression expression2 = getExpression(expression);
				rateInf2.setPrio(expression2);
			} else {
				specificheAEmilia.Integer integer = new specificheAEmilia.Integer(1);
				rateInf2.setPrio(integer);
			}
			// serializzare inf_weight
			Expression expression2 = rateInf.getInf_weight();
			if (expression2 != null) {
				specificheAEmilia.Expression expression3 = getExpression(expression2);
				rateInf2.setWeight(expression3);
			} else {
				specificheAEmilia.Integer integer = new specificheAEmilia.Integer(1);
				rateInf2.setWeight(integer);
			}
			action2.setRate(rateInf2);
		}
		if (actionRate instanceof RatePas) {
			RatePas ratePas = (RatePas) actionRate;
			Rate_ rate_ = new Rate_();
			// serializziamo priority
			Expression expression = ratePas.getPriority();
			if (expression != null) {
				specificheAEmilia.Expression expression2 = getExpression(expression);
				rate_.setPrio(expression2);
			} else {
				specificheAEmilia.Integer integer = new specificheAEmilia.Integer(1);
				rate_.setPrio(integer);
			}
			// serializziamo weight
			Expression expression2 = ratePas.getWeight();
			if (expression2 != null) {
				specificheAEmilia.Expression expression3 = getExpression(expression2);
				rate_.setWeight(expression3);
			} else {
				specificheAEmilia.Integer integer = new specificheAEmilia.Integer(1);
				rate_.setPrio(integer);
			}
			action2.setRate(rate_);
		}
		// serializziamo type
		ActionType actionType = action.getType();
		specificheAEmilia.ActionType actionType2 = null;
		if (actionType instanceof ActionInput) {
			ActionInput actionInput = (ActionInput) actionType;
			actionType2 = new specificheAEmilia.ActionInput();
			List<String> list = actionInput.getVar();
			String[] strings = new String[list.size()];
			list.toArray(strings);
			((specificheAEmilia.ActionInput) actionType2).setInputVariables(strings);
			action2.setType(actionType2);
		} else if (actionType instanceof ActionOutput) {
			ActionOutput actionOutput = (ActionOutput) actionType;
			actionType2 = new specificheAEmilia.ActionOutput();
			List<Expression> expressions = actionOutput.getExprOutput();
			specificheAEmilia.Expression[] expressions2 = new specificheAEmilia.Expression[expressions.size()];
			expressions.toArray(expressions2);
			((specificheAEmilia.ActionOutput) actionType2).setOutputExprs(expressions2);
			action2.setType(actionType2);
		} else {
			actionType2 = new specificheAEmilia.ActionType();
		}
		actionType2.setName(string);
		action2.setType(actionType2);
		return action2;
	}

	public specificheAEmilia.Expression getExpression(Expression expression) {
		if (expression instanceof ListExpr) {
			ListExpr listExpr = (ListExpr) expression;
			// serializzo list_operands
			List<Expression> expressions = listExpr.getList_operands();
			// serializzo operation
			ListOpName listOpName = listExpr.getOperation();
			if (ListOpName.CONCAT.equals(listOpName)) {
				Concat concat = new Concat();
				IdentExpr identExpr = (IdentExpr) expressions.get(0);
				IdentExpr identExpr2 = (IdentExpr) expressions.get(1);
				String string = identExpr.getName();
				String string2 = identExpr2.getName();
				specificheAEmilia.IdentExpr identExpr3 = new specificheAEmilia.IdentExpr(string);
				specificheAEmilia.IdentExpr identExpr4 = new specificheAEmilia.IdentExpr(string2);
				concat.setList1(identExpr3);
				concat.setList2(identExpr4);
				return concat;
			}
			if (ListOpName.FIRST.equals(listOpName)) {
				First first = new First();
				IdentExpr identExpr = (IdentExpr) expressions.get(0);
				String string = identExpr.getName();
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				first.setList(identExpr2);
				return first;
			}
			if (ListOpName.INSERT.equals(listOpName)) {
				Insert insert = new Insert();
				Expression expression2 = expressions.get(0);
				IdentExpr identExpr = (IdentExpr) expressions.get(1);
				specificheAEmilia.Expression expression3 = getExpression(expression2);
				String string = identExpr.getName();
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				insert.setItem(expression3);
				insert.setList(identExpr2);
				return insert;
			}
			if (ListOpName.LENGHT.equals(listOpName)) {
				Length length = new Length();
				IdentExpr identExpr = (IdentExpr) expressions.get(0);
				String string = identExpr.getName();
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				length.setList(identExpr2);
				return length;
			}
			if (ListOpName.LIST_CONS.equals(listOpName)) {
				ListCons listCons = new ListCons();
				specificheAEmilia.Expression[] expressions2 = new specificheAEmilia.Expression[expressions.size()];
				for (int i = 0; i < expressions.size(); i++) {
					Expression expression2 = expressions.get(i);
					specificheAEmilia.Expression expression3 = getExpression(expression2);
					expressions2[i] = expression3;
				}
				listCons.setListElements(expressions2);
				return listCons;
			}
			if (ListOpName.REMOVE.equals(listOpName)) {
				Remove remove = new Remove();
				Expression expression2 = expressions.get(0);
				IdentExpr identExpr = (IdentExpr) expressions.get(1);
				specificheAEmilia.Expression expression3 = getExpression(expression2);
				String string = identExpr.getName();
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				remove.setPosition(expression3);
				remove.setList(identExpr2);
				return remove;
			}
			if (ListOpName.TAIL.equals(listOpName)) {
				Tail tail = new Tail();
				IdentExpr identExpr = (IdentExpr) expressions.get(0);
				String string = identExpr.getName();
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				tail.setList(identExpr2);
				return tail;
			}
		}
		if (expression instanceof ArrayExpr) {
			ArrayExpr arrayExpr = (ArrayExpr) expression;
			// serializziamo array_operands
			List<Expression> expressions = arrayExpr.getArray_operands();
			// serializziamo operation
			ArrayOpName arrayOpName = arrayExpr.getOperation();
			if (ArrayOpName.ARRAY_CONS.equals(arrayOpName)) {
				ArrayCons arrayCons = new ArrayCons();
				specificheAEmilia.Expression[] expressions2 = new specificheAEmilia.Expression[expressions.size()];
				for (int i = 0; i < expressions.size(); i++) {
					Expression expression2 = expressions.get(i);
					specificheAEmilia.Expression expression3 = getExpression(expression2);
					expressions2[i] = expression3;
				}
				arrayCons.setArrayElements(expressions2);
				return arrayCons;
			}
			if (ArrayOpName.READ.equals(arrayOpName)) {
				Read read = new Read();
				Expression expression2 = expressions.get(0);
				IdentExpr identExpr = (IdentExpr) expressions.get(1);
				specificheAEmilia.Expression expression3 = getExpression(expression2);
				String string = identExpr.getName();
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				read.setArray(identExpr2);
				read.setIndex(expression3);
				return read;
			}
			if (ArrayOpName.WRITE.equals(arrayOpName)) {
				Write write = new Write();
				Expression position = expressions.get(0);
				Expression element = expressions.get(1);
				IdentExpr array = (IdentExpr) expressions.get(2);
				specificheAEmilia.Expression position2 = getExpression(position);
				specificheAEmilia.Expression element2 = getExpression(element);
				String string = array.getName();
				specificheAEmilia.IdentExpr array2 = new specificheAEmilia.IdentExpr(string);
				write.setArray(array2);
				write.setElement(element2);
				write.setIndex(position2);
				return write;
			}
		}
		if (expression instanceof RecordExpr) {
			RecordExpr recordExpr = (RecordExpr) expression;
			// serializziamo operation
			RecordOpName recordOpName = recordExpr.getOperation();
			// serializziamo record_operands
			List<Expression> expressions = recordExpr.getRecord_operands();
			if (RecordOpName.GET.equals(recordOpName)) {
				Get get = new Get();
				IdentExpr campo = (IdentExpr) expressions.get(0);
				IdentExpr record = (IdentExpr) expressions.get(1);
				String string = campo.getName();
				String string2 = record.getName();
				specificheAEmilia.IdentExpr record2 = new specificheAEmilia.IdentExpr(string2);
				get.setField(string);
				get.setRecord(record2);
				return get;
			}
			if (RecordOpName.PUT.equals(recordOpName)) {
				Put put = new Put();
				IdentExpr campo = (IdentExpr) expressions.get(0);
				Expression valore = expressions.get(1);
				IdentExpr record = (IdentExpr) expressions.get(2);
				String string = campo.getName();
				specificheAEmilia.Expression expression2 = getExpression(valore);
				String string2 = record.getName();
				specificheAEmilia.IdentExpr record2 = new specificheAEmilia.IdentExpr(string2);
				put.setField(string);
				put.setRecord(record2);
				put.setValue(expression2);
				return put;
			}
			if (RecordOpName.RECORD_CONS.equals(recordOpName)) {
				RecordCons recordCons = new RecordCons();
				specificheAEmilia.Expression[] expressions2 = new specificheAEmilia.Expression[expressions.size()];
				for (int i = 0; i < expressions.size(); i++) {
					Expression expression2 = expressions.get(i);
					specificheAEmilia.Expression expression3 = getExpression(expression2);
					expressions2[i] = expression3;
				}
				recordCons.setValues(expressions2);
				return recordCons;
			}
		}
		if (expression instanceof IdentExpr) {
			IdentExpr identExpr = (IdentExpr) expression;
			String string = identExpr.getName();
			IdentifierType identifierType = identExpr.getType();
			if (IdentifierType.NUMERIC_CONST.equals(identifierType)) {
				NumberFormat numberFormat = NumberFormat.getNumberInstance();
				Number number = null;
				try {
					number = numberFormat.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (number instanceof java.lang.Long) {
					java.lang.Long long1 = (java.lang.Long) number;
					specificheAEmilia.Integer integer1 = new specificheAEmilia.Integer(long1.intValue());
					return integer1;
				}
				if (number instanceof java.lang.Double) {
					java.lang.Double double1 = (java.lang.Double) number;
					specificheAEmilia.Real real = new specificheAEmilia.Real(double1);
					return real;
				}
			}
			if (IdentifierType.TRUTH_VAL.equals(identifierType)) {
				java.lang.Boolean boolean1 = new java.lang.Boolean(string);
				specificheAEmilia.Boolean boolean2 = new specificheAEmilia.Boolean(boolean1);
				return boolean2;
			}
			if (IdentifierType.TYPED_IDENT.equals(identifierType)) {
				specificheAEmilia.IdentExpr identExpr2 = new specificheAEmilia.IdentExpr(string);
				return identExpr2;
			}
		}
		if (expression instanceof BooleanExpr) {
			BooleanExpr booleanExpr = (BooleanExpr) expression;
			// serializziamo leftExprBool
			Expression expression2 = booleanExpr.getLeftExprBool();
			// serializziamo rightExprBool
			Expression expression3 = booleanExpr.getRightExprBool();
			// serializziamo operator
			BoolOpName boolOpName = booleanExpr.getOperator();
			if (BoolOpName.AND.equals(boolOpName)) {
				And and = new And();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				and.setExpr1(expression4);
				and.setExpr2(expression5);
				return and;
			}
			if (BoolOpName.NOT.equals(boolOpName)) {
				Negazione negazione = new Negazione();
				specificheAEmilia.Expression expression4 = getExpression(expression3);
				negazione.setExpr1(expression4);
				return negazione;
			}
			if (BoolOpName.OR.equals(boolOpName)) {
				Or or = new Or();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				or.setExpr1(expression4);
				or.setExpr2(expression5);
				return or;
			}
		}
		if (expression instanceof RelationalExpr) {
			RelationalExpr relationalExpr = (RelationalExpr) expression;
			// serializziamo leftExprRel
			Expression expression2 = relationalExpr.getLeftExprRel();
			// serializziamo rightExprRel
			Expression expression3 = relationalExpr.getRightExprRel();
			// serializziamo operator
			RelatOpName relatOpName = relationalExpr.getOperator();
			if (RelatOpName.EQUAL.equals(relatOpName)) {
				Equal equal = new Equal();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				equal.setExpr1(expression4);
				equal.setExpr2(expression5);
				return equal;
			}
			if (RelatOpName.GREATER.equals(relatOpName)) {
				Maggiore maggiore = new Maggiore();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				maggiore.setExpr1(expression4);
				maggiore.setExpr2(expression5);
				return maggiore;
			}
			if (RelatOpName.GREATER_EQUAL.equals(relatOpName)) {
				MaggioreUguale maggioreUguale = new MaggioreUguale();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				maggioreUguale.setExpr1(expression4);
				maggioreUguale.setExpr2(expression5);
				return maggioreUguale;
			}
			if (RelatOpName.LESS.equals(relatOpName)) {
				Minore minore = new Minore();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				minore.setExpr1(expression4);
				minore.setExpr2(expression5);
				return minore;
			}
			if (RelatOpName.LESS_EQUAL.equals(relatOpName)) {
				MinoreUguale minoreUguale = new MinoreUguale();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				minoreUguale.setExpr1(expression4);
				minoreUguale.setExpr2(expression5);
				return minoreUguale;
			}
			if (RelatOpName.NOT_EQUAL.equals(relatOpName)) {
				Different different = new Different();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				different.setExpr1(expression4);
				different.setExpr2(expression5);
				return different;
			}
		}
		if (expression instanceof ArithExpr) {
			ArithExpr arithExpr = (ArithExpr) expression;
			// serializziamo leftExprArith
			Expression expression2 = arithExpr.getLeftExprArith();
			// serializziamo rightExprArith
			Expression expression3 = arithExpr.getRightExprArith();
			// serializziamo operator
			ArithOpName arithOpName = arithExpr.getOperator();
			if (ArithOpName.DIV.equals(arithOpName)) {
				Divisione divisione = new Divisione();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				divisione.setExpr1(expression4);
				divisione.setExpr2(expression5);
				return divisione;
			}
			if (ArithOpName.MINUS.equals(arithOpName)) {
				Sottrazione sottrazione = new Sottrazione();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				sottrazione.setExpr1(expression4);
				sottrazione.setExpr2(expression5);
				return sottrazione;
			}
			if (ArithOpName.MULT.equals(arithOpName)) {
				Moltiplicazione moltiplicazione = new Moltiplicazione();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				moltiplicazione.setExpr1(expression4);
				moltiplicazione.setExpr2(expression5);
				return moltiplicazione;
			}
			if (ArithOpName.PLUS.equals(arithOpName)) {
				Somma somma = new Somma();
				specificheAEmilia.Expression expression4 = getExpression(expression2);
				specificheAEmilia.Expression expression5 = getExpression(expression3);
				somma.setExpr1(expression4);
				somma.setExpr2(expression5);
				return somma;
			}
		}
		return null;
	}

	private Local getLocal(metamodel.mmaemilia.Headers.Local local) {
		Local local2 = new Local();
		// serializziamo localType
		Normal normal = local.getLocalType();
		NormalType normalType = getNormalType(normal);
		local2.setType(normalType);
		// serializziamo name
		String string = local.getName();
		local2.setName(string);
		return local2;
	}

	private VariableDeclaration getVarDecl(Var var) {
		VariableDeclaration variableDeclaration = new VariableDeclaration();
		// serializziamo varType
		Normal normal = var.getVarType();
		NormalType normalType = getNormalType(normal);
		variableDeclaration.setType(normalType);
		// serializziamo name
		String string = var.getName();
		variableDeclaration.setName(string);
		return variableDeclaration;
	}

	private specificheAEmilia.VarInit getVarInit(VarInit varInit) {
		specificheAEmilia.VarInit varInit2 = new specificheAEmilia.VarInit();
		// si serializza varInit.getInitVarExpr()
		Expression expression = varInit.getInitVarExpr();
		specificheAEmilia.Expression expression2 = getExpression(expression);
		varInit2.setExpr(expression2);
		// si serializza varInit.getInitVarType()
		Normal normal = varInit.getInitVarType();
		NormalType normalType = getNormalType(normal);
		varInit2.setType(normalType);
		// si serializza varInit.getName()
		String string = varInit.getName();
		varInit2.setName(string);
		return varInit2;
	}

	private Header getHeader(AT_Header at_Header, String string) {
		Header header = new Header();
		header.setName(string);
		// si serializza initConst
		List<ConstInit> list = at_Header.getInitConst();
		ParamDeclaration[] paramDeclarations = null;
		if (list.isEmpty())
			paramDeclarations = new ParamDeclaration[1];
		else
			paramDeclarations = new ParamDeclaration[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ConstInit constInit = list.get(i);
			specificheAEmilia.ConstInit constInit2 = new specificheAEmilia.ConstInit();
			// serializziamo name
			String string2 = constInit.getName();
			constInit2.setName(string2);
			// serializziamo initConstData
			DataType dataType = constInit.getInitConstData();
			specificheAEmilia.DataType dataType2 = getDataType(dataType);
			constInit2.setType(dataType2);
			// serializziamo initConstExpr
			Expression expression = constInit.getInitConstExpr();
			specificheAEmilia.Expression expression2 = getExpression(expression);
			constInit2.setExpr(expression2);
			paramDeclarations[i] = constInit2;
		}
		header.setParameters(paramDeclarations);
		return header;
	}
}
