/**
 * 
 */
package valutazione.typeChecking.typeRising;

import java.util.List;
import java.util.TreeMap;

import specificheAEmilia.And;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.Boolean;
import specificheAEmilia.Concat;
import specificheAEmilia.DataType;
import specificheAEmilia.Different;
import specificheAEmilia.Divisione;
import specificheAEmilia.Equal;
import specificheAEmilia.Expression;
import specificheAEmilia.First;
import specificheAEmilia.Get;
import specificheAEmilia.IdentExpr;
import specificheAEmilia.Insert;
import specificheAEmilia.Integer;
import specificheAEmilia.Length;
import specificheAEmilia.ListCons;
import specificheAEmilia.Maggiore;
import specificheAEmilia.MaggioreUguale;
import specificheAEmilia.Minore;
import specificheAEmilia.MinoreUguale;
import specificheAEmilia.Moltiplicazione;
import specificheAEmilia.Negazione;
import specificheAEmilia.Or;
import specificheAEmilia.Put;
import specificheAEmilia.Read;
import specificheAEmilia.Real;
import specificheAEmilia.RecordCons;
import specificheAEmilia.Remove;
import specificheAEmilia.Somma;
import specificheAEmilia.Sottrazione;
import specificheAEmilia.Tail;
import specificheAEmilia.Write;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.typeChecking.DataTypeEnum;
import valutazione.typeChecking.TypeRisingException;

/**
 * Classe utilizzata per ricavare il tipo di un'espressione a 
 * seconda del tipo delle sue sotto espressioni.
 * Tale classe puo' essere utilizzata solo per la normalizzazione stretta. 
 * Il tipo viene ricavato staticamente, senza alcuna normalizzazione.
 * E' stato adottato questo approccio per evitare il side effect di eventuli valutazioni di espressioni per ricavarne il tipo dinamicamente.
 * Di solito nei linguaggi di programmazione il tipo di un'espressione viene definito durante l'istanziazzione e non durante la definizione di variabile.
 * 
 * @author Mio
 *
 */
public class TypeRising 
	{

    private TreeMap<String, ValueIdentExpr> tm;
    private String message;
    private boolean errorOccurred = false;
    private int depth;
    private ErrorMessage errorMessage;

    public TypeRising(int depth)
    	{
    	super();
    	this.tm = new TreeMap<String, ValueIdentExpr>();
    	this.depth = depth;
    	this.errorMessage = new ErrorMessage(depth);
    	}
    
	public TreeMap<String, ValueIdentExpr> getTm() 
		{
		return tm;
		}

	public void setTm(TreeMap<String, ValueIdentExpr> tm) 
		{
		this.tm = tm;
		}

	public DataTypeEnum rising(Expression expression, String parent, 
    		DataType dataType)
		throws TypeRisingException
		{
		if (expression instanceof And)
			{
			And and = (And)expression;
			AndRising andRising = new AndRising(this.depth + 1);
			andRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = andRising.risingAnd(and, parent, dataType);
			if (andRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = andRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof ArrayCons)
			{
			ArrayCons arrayCons = (ArrayCons)expression;
			ArrayConsRising arrayConsRising = new ArrayConsRising(this.depth + 1);
			arrayConsRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = arrayConsRising.risingArrayCons(arrayCons, parent, dataType);
			if (arrayConsRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = arrayConsRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Boolean)
			{
			Boolean boolean1 = (Boolean)expression;
			BooleanRising booleanRising = new BooleanRising(this.depth + 1);
			booleanRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = booleanRising.risingBoolean(boolean1, parent, dataType);
			if (booleanRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = booleanRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Concat)
			{
			Concat concat = (Concat)expression;
			ConcatRising concatRising = new ConcatRising(this.depth + 1);
			concatRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = concatRising.risingConcat(concat, parent, dataType);
			if (concatRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = concatRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Different)
			{
			Different different = (Different)expression;
			DifferentRising differentRising = new DifferentRising(this.depth + 1);
			differentRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = differentRising.risingDifferent(different, parent, dataType);
			if (differentRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = differentRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Divisione)
			{
			Divisione divisione = (Divisione)expression;
			DivisioneRising divisioneRising = new DivisioneRising(this.depth + 1);
			divisioneRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = divisioneRising.risingDivisione(divisione, parent, dataType);
			if (divisioneRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = divisioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Equal)
			{
			Equal equal = (Equal)expression;
			EqualRising equalRising = new EqualRising(this.depth + 1);
			equalRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = equalRising.risingEqual(equal, parent, dataType);
			if (equalRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = equalRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof First)
			{
			First first = (First)expression;
			FirstRising firstRising = new FirstRising(this.depth + 1);
			firstRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = firstRising.risingFirst(first, parent, dataType);
			if (firstRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = firstRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Get)
			{
			Get get = (Get)expression;
			GetRising getRising = new GetRising(this.depth + 1);
			getRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = getRising.risingGet(get, parent, dataType);
			if (getRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = getRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof IdentExpr)
			{
			IdentExpr identExpr = (IdentExpr)expression;
			IdentExprRising identExprRising = new IdentExprRising(this.depth + 1);
			identExprRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = identExprRising.risingIdentExpr(identExpr, parent, dataType);
			if (identExprRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = identExprRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Insert)
			{
			Insert insert = (Insert)expression;
			InsertRising insertRising = new InsertRising(this.depth + 1);
			insertRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = insertRising.risingInsert(insert, parent, dataType);
			if (insertRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = insertRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Integer)
			{
			Integer integer = (Integer)expression;
			IntegerRising integerRising = new IntegerRising(this.depth + 1);
			integerRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = integerRising.risingInteger(integer, parent, dataType);
			if (integerRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = integerRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Length)
			{
			Length length = (Length)expression;
			LengthRising lengthRising = new LengthRising(this.depth + 1);
			lengthRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = lengthRising.risingLength(length, parent, dataType);
			if (lengthRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = lengthRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof ListCons)
			{
			ListCons listCons = (ListCons)expression;
			ListConsRising listConsRising = new ListConsRising(this.depth + 1);
			listConsRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = listConsRising.risingListCons(listCons, parent, dataType);
			if (listConsRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = listConsRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Maggiore)
			{
			Maggiore maggiore = (Maggiore)expression;
			MaggioreRising maggioreRising = new MaggioreRising(this.depth + 1);
			maggioreRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = maggioreRising.risingMaggiore(maggiore, parent, dataType);
			if (maggioreRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = maggioreRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof MaggioreUguale)
			{
			MaggioreUguale maggioreUguale = (MaggioreUguale)expression;
			MaggioreUgualeRising maggioreUgualeRising = new MaggioreUgualeRising(this.depth + 1);
			maggioreUgualeRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = maggioreUgualeRising.risingMaggioreUguale(maggioreUguale, parent, dataType);
			if (maggioreUgualeRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = maggioreUgualeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Minore)
			{
			Minore minore = (Minore)expression;
			MinoreRising minoreRising = new MinoreRising(this.depth + 1);
			minoreRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = minoreRising.risingMinore(minore, parent, dataType);
			if (minoreRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = minoreRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof MinoreUguale)
			{
			MinoreUguale minoreUguale = (MinoreUguale)expression;
			MinoreUgualeRising minoreUgualeRising = new MinoreUgualeRising(this.depth + 1);
			minoreUgualeRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = minoreUgualeRising.risingMinoreUguale(minoreUguale, parent, dataType);
			if (minoreUgualeRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = minoreUgualeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Moltiplicazione)
			{
			Moltiplicazione moltiplicazione = (Moltiplicazione)expression;
			MoltiplicazioneRising moltiplicazioneRising = new MoltiplicazioneRising(this.depth + 1);
			moltiplicazioneRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = moltiplicazioneRising.risingMoltiplicazione(moltiplicazione, parent, dataType);
			if (moltiplicazioneRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = moltiplicazioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Negazione)
			{
			Negazione negazione = (Negazione)expression;
			NegazioneRising negazioneRising = new NegazioneRising(this.depth + 1);
			negazioneRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = negazioneRising.risingNegazione(negazione, parent, dataType);
			if (negazioneRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = negazioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Or)
			{
			Or or = (Or)expression;
			OrRising orRising = new OrRising(this.depth + 1);
			orRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = orRising.risingOr(or, parent, dataType);
			if (orRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = orRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Put)
			{
			Put put = (Put)expression;
			PutRising putRising = new PutRising(this.depth + 1);
			putRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = putRising.risingPut(put, parent, dataType);
			if (putRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = putRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Read)
			{
			Read read = (Read)expression;
			ReadRising readRising = new ReadRising(this.depth + 1);
			readRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = readRising.risingRead(read, parent, dataType);
			if (readRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = readRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Real)
			{
			Real real = (Real)expression;
			RealRising realRising = new RealRising(this.depth + 1);
			realRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = realRising.risingReal(real, parent, dataType);
			if (realRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = realRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof RecordCons)
			{
			RecordCons recordCons = (RecordCons)expression;
			RecordConsRising recordConsRising = new RecordConsRising(this.depth + 1);
			recordConsRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = recordConsRising.risingRecordCons(recordCons, parent, dataType);
			if (recordConsRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = recordConsRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Remove)
			{
			Remove remove = (Remove)expression;
			RemoveRising removeRising = new RemoveRising(this.depth + 1);
			removeRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = removeRising.risingRemove(remove, parent, dataType);
			if (removeRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = removeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Somma)
			{
			Somma somma = (Somma)expression;
			SommaRising sommaRising = new SommaRising(this.depth + 1);
			sommaRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = sommaRising.risingSomma(somma, parent, dataType);
			if (sommaRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = sommaRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Sottrazione)
			{
			Sottrazione sottrazione = (Sottrazione)expression;
			SottrazioneRising sottrazioneRising = new SottrazioneRising(this.depth + 1);
			sottrazioneRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = sottrazioneRising.risingSottrazione(sottrazione, parent, dataType);
			if (sottrazioneRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = sottrazioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Tail)
			{
			Tail tail = (Tail)expression;
			TailRising tailRising = new TailRising(this.depth + 1);
			tailRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = tailRising.risingTail(tail, parent, dataType);
			if (tailRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = tailRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Write)
			{
			Write write = (Write)expression;
			WriteRising writeRising = new WriteRising(this.depth + 1);
			writeRising.setTm(this.tm);
			DataTypeEnum dataTypeEnum = writeRising.risingWrite(write, parent, dataType);
			if (writeRising.isErrorOccurred())
				{
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = writeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else 
			throw new TypeRisingException("unexpected expression");
		}
	
	public DataTypeEnum rising(Expression expression, String parent, 
    		DataType dataType, TreeMap<String, ValueIdentExpr> tma)
		throws TypeRisingException
		{
		/* MODELED */
		if (expression instanceof And) 
			{
			And and = (And) expression;
			AndRising andRising = new AndRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = andRising.risingAnd(and, parent, dataType,
					tma);
			if (andRising.isErrorOccurred())
				{
				// 1
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = andRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof ArrayCons) 
			{
			ArrayCons arrayCons = (ArrayCons) expression;
			ArrayConsRising arrayConsRising = new ArrayConsRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = arrayConsRising.risingArrayCons(arrayCons, parent,
					dataType, tma);
			if (arrayConsRising.isErrorOccurred())
				{
				// 2
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = arrayConsRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Boolean) 
			{
			Boolean boolean1 = (Boolean) expression;
			BooleanRising booleanRising = new BooleanRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = booleanRising.risingBoolean(boolean1, parent,
					dataType, tma);
			return dataTypeEnum;
			} 
		else if (expression instanceof Concat) 
			{
			Concat concat = (Concat) expression;
			ConcatRising concatRising = new ConcatRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = concatRising.risingConcat(concat, parent,
					dataType, tma);
			if (concatRising.isErrorOccurred())
				{
				// 4
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = concatRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Different) 
			{
			Different different = (Different) expression;
			DifferentRising differentRising = new DifferentRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = differentRising.risingDifferent(different, parent,
					dataType, tma);
			if (differentRising.isErrorOccurred())
				{
				// 5
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = differentRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Divisione) 
			{
			Divisione divisione = (Divisione) expression;
			DivisioneRising divisioneRising = new DivisioneRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = divisioneRising.risingDivisione(divisione, parent,
					dataType, tma);
			if (divisioneRising.isErrorOccurred())
				{
				// 6
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = divisioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			}
		else if (expression instanceof Equal) 
			{
			Equal equal = (Equal) expression;
			EqualRising equalRising = new EqualRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = equalRising.risingEqual(equal, parent,
					dataType, tma);
			if (equalRising.isErrorOccurred())
				{
				// 7
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = equalRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof First) 
			{
			First first = (First) expression;
			FirstRising firstRising = new FirstRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = firstRising.risingFirst(first, parent,
					dataType, tma);
			if (firstRising.isErrorOccurred())
				{
				// 8
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = firstRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Get) 
			{
			Get get = (Get) expression;
			GetRising getRising = new GetRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = getRising.risingGet(get, parent, dataType,
					tma);
			if (getRising.isErrorOccurred())
				{
				// 9
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = getRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof IdentExpr) 
			{
			IdentExpr identExpr = (IdentExpr) expression;
			IdentExprRising identExprRising = new IdentExprRising(this.depth + 1); 
			DataTypeEnum dataTypeEnum = identExprRising.risingIdentExpr(identExpr, parent,
					dataType, tma);
			if (identExprRising.isErrorOccurred())
				{
				// 10
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = identExprRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Insert) 
			{
			Insert insert = (Insert) expression;
			InsertRising insertRising = new InsertRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = insertRising.risingInsert(insert, parent,
					dataType, tma);
			if (insertRising.isErrorOccurred())
				{
				// 11
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = insertRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Integer) 
			{
			Integer integer = (Integer) expression;
			IntegerRising integerRising = new IntegerRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = integerRising.risingInteger(integer, parent,
					dataType, tma);
			return dataTypeEnum;
			} 
		else if (expression instanceof Length) 
			{
			Length length = (Length) expression;
			LengthRising lengthRising = new LengthRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = lengthRising.risingLength(length, parent,
					dataType, tma);
			return dataTypeEnum;
			} 
		else if (expression instanceof ListCons) 
			{
			ListCons listCons = (ListCons) expression;
			ListConsRising listConsRising = new ListConsRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = listConsRising.risingListCons(listCons, parent,
					dataType, tma);
			if (listConsRising.isErrorOccurred())
				{
				// 14
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = listConsRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Maggiore) 
			{
			Maggiore maggiore = (Maggiore) expression;
			MaggioreRising maggioreRising = new MaggioreRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = maggioreRising.risingMaggiore(maggiore, parent,
					dataType, tma);
			if (maggioreRising.isErrorOccurred())
				{
				// 15
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = maggioreRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof MaggioreUguale) 
			{
			MaggioreUguale maggioreUguale = (MaggioreUguale) expression;
			MaggioreUgualeRising maggioreUgualeRising = new MaggioreUgualeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = maggioreUgualeRising.risingMaggioreUguale(
					maggioreUguale, parent, dataType, tma);
			if (maggioreUgualeRising.isErrorOccurred())
				{
				// 16
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = maggioreUgualeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Minore) 
			{
			Minore minore = (Minore) expression;
			MinoreRising minoreRising = new MinoreRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = minoreRising.risingMinore(minore, parent,
					dataType, tma);
			if (minoreRising.isErrorOccurred())
				{
				// 17
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = minoreRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof MinoreUguale) 
			{
			MinoreUguale minoreUguale = (MinoreUguale) expression;
			MinoreUgualeRising minoreUgualeRising = new MinoreUgualeRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = minoreUgualeRising.risingMinoreUguale(minoreUguale,
					parent, dataType, tma);
			if (minoreUgualeRising.isErrorOccurred())
				{
				// 18
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = minoreUgualeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Moltiplicazione) 
			{
			Moltiplicazione moltiplicazione = (Moltiplicazione) expression;
			MoltiplicazioneRising moltiplicazioneRising = new MoltiplicazioneRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = moltiplicazioneRising.risingMoltiplicazione(
					moltiplicazione, parent, dataType, tma);
			if (moltiplicazioneRising.isErrorOccurred())
				{
				// 19
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = moltiplicazioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Negazione) 
			{
			Negazione negazione = (Negazione) expression;
			NegazioneRising negazioneRising = new NegazioneRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = negazioneRising.risingNegazione(negazione, parent,
					dataType, tma);
			if (negazioneRising.isErrorOccurred())
				{
				// 20
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = negazioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Or) 
			{
			Or or = (Or) expression;
			OrRising orRising = new OrRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = orRising.risingOr(or, parent, dataType, tma);
			if (orRising.isErrorOccurred())
				{
				// 21
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = orRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Put) 
			{
			Put put = (Put) expression;
			PutRising putRising = new PutRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = putRising.risingPut(put, parent, dataType,
					tma);
			if (putRising.isErrorOccurred())
				{
				// 22
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = putRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Read) 
			{
			Read read = (Read) expression;
			ReadRising readRising = new ReadRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = readRising.risingRead(read, parent, dataType,
					tma);
			if (readRising.isErrorOccurred())
				{
				// 23
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = readRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Real) 
			{
			Real real = (Real) expression;
			RealRising realRising = new RealRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = realRising.risingReal(real, parent, dataType,
					tma);
			return dataTypeEnum;
			} 
		else if (expression instanceof RecordCons) 
			{
			RecordCons recordCons = (RecordCons) expression;
			RecordConsRising recordConsRising = new RecordConsRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = recordConsRising.risingRecordCons(recordCons,
					parent, dataType, tma);
			return dataTypeEnum;
			} 
		else if (expression instanceof Remove) 
			{
			Remove remove = (Remove) expression;
			RemoveRising removeRising = new RemoveRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = removeRising.risingRemove(remove, parent,
					dataType, tma);
			if (removeRising.isErrorOccurred())
				{
				// 26
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = removeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Somma) 
			{
			Somma somma = (Somma) expression;
			SommaRising sommaRising = new SommaRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = sommaRising.risingSomma(somma, parent,
					dataType, tma);
			if (sommaRising.isErrorOccurred())
				{
				// 27
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = sommaRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Sottrazione) 
			{
			Sottrazione sottrazione = (Sottrazione) expression;
			SottrazioneRising sottrazioneRising = new SottrazioneRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = sottrazioneRising.risingSottrazione(sottrazione,
					parent, dataType, tma);
			if (sottrazioneRising.isErrorOccurred())
				{
				// 28
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = sottrazioneRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else if (expression instanceof Tail) 
			{
			Tail tail = (Tail) expression;
			TailRising tailRising = new TailRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = tailRising.risingTail(tail, parent, dataType,
					tma);
			return dataTypeEnum;
			} 
		else if (expression instanceof Write) 
			{
			Write write = (Write) expression;
			WriteRising writeRising = new WriteRising(this.depth + 1);
			DataTypeEnum dataTypeEnum = writeRising.risingWrite(write, parent,
					dataType, tma);
			if (writeRising.isErrorOccurred())
				{
				// 30
				String string = "type checking error for " + expression.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = writeRising.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			return dataTypeEnum;
			} 
		else 
			throw new TypeRisingException("unexpected expression");
		}
					    				
	public String getMessage() 
		{
		return message;
		}

	public boolean isErrorOccurred() 
		{
		return errorOccurred;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
