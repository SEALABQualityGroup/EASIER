/**
 * 
 */
package valutazione.normalization.normalizeExpressionStrict;

import restrizioniIstanze.expressions.AndNorm;
import restrizioniIstanze.expressions.ArrayConsNorm;
import restrizioniIstanze.expressions.BooleanNorm;
import restrizioniIstanze.expressions.ConcatNorm;
import restrizioniIstanze.expressions.DifferentNorm;
import restrizioniIstanze.expressions.DivisioneNorm;
import restrizioniIstanze.expressions.EqualNorm;
import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.expressions.FirstNorm;
import restrizioniIstanze.expressions.GetNorm;
import restrizioniIstanze.expressions.IdentExprNorm;
import restrizioniIstanze.expressions.InsertNorm;
import restrizioniIstanze.expressions.IntegerNorm;
import restrizioniIstanze.expressions.LengthNorm;
import restrizioniIstanze.expressions.ListConsNorm;
import restrizioniIstanze.expressions.MaggioreNorm;
import restrizioniIstanze.expressions.MaggioreUgualeNorm;
import restrizioniIstanze.expressions.MinoreNorm;
import restrizioniIstanze.expressions.MinoreUgualeNorm;
import restrizioniIstanze.expressions.MoltiplicazioneNorm;
import restrizioniIstanze.expressions.NegazioneNorm;
import restrizioniIstanze.expressions.OrNorm;
import restrizioniIstanze.expressions.PutNorm;
import restrizioniIstanze.expressions.ReadNorm;
import restrizioniIstanze.expressions.RealNorm;
import restrizioniIstanze.expressions.RecordConsNorm;
import restrizioniIstanze.expressions.RemoveNorm;
import restrizioniIstanze.expressions.SommaNorm;
import restrizioniIstanze.expressions.SottrazioneNorm;
import restrizioniIstanze.expressions.TailNorm;
import restrizioniIstanze.expressions.WriteNorm;
import specificheAEmilia.And;
import specificheAEmilia.ArrayCons;
import specificheAEmilia.Boolean;
import specificheAEmilia.Concat;
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

/**
 * @author Mirko
 *
 */
public class MetodiVari {

	ExpressionNorm getExpressionNorm(Expression oldExp, Expression newExp) 
	{
	if (newExp instanceof And)
		{
		AndNorm andNorm = new AndNorm();
		andNorm.setOldExpression(oldExp);
		andNorm.setNewExpression((And)newExp);
		return andNorm;
		}
	else if (newExp instanceof ArrayCons)
		{
		ArrayConsNorm arrayConsNorm = new ArrayConsNorm();
		arrayConsNorm.setOldExpression(oldExp);
		arrayConsNorm.setNewExpression((ArrayCons)newExp);
		return arrayConsNorm;
		}
	else if (newExp instanceof Boolean)
		{
		BooleanNorm booleanNorm = new BooleanNorm();
		booleanNorm.setOldExpression(oldExp);
		booleanNorm.setNewExpression((Boolean)newExp);
		return booleanNorm;
		}
	else if (newExp instanceof Concat)
		{
		ConcatNorm concatNorm = new ConcatNorm();
		concatNorm.setOldExpression(oldExp);
		concatNorm.setNewExpression((Concat)newExp);
		return concatNorm;
		}
	else if (newExp instanceof Different)
		{
		DifferentNorm differentNorm = new DifferentNorm();
		differentNorm.setOldExpression(oldExp);
		differentNorm.setNewExpression((Different)newExp);
		return differentNorm;
		}
	else if (newExp instanceof Divisione)
		{
		DivisioneNorm divisioneNorm = new DivisioneNorm();
		divisioneNorm.setOldExpression(oldExp);
		divisioneNorm.setNewExpression((Divisione)newExp);
		return divisioneNorm;
		}
	else if (newExp instanceof Equal)
		{
		EqualNorm equalNorm = new EqualNorm();
		equalNorm.setOldExpression(oldExp);
		equalNorm.setNewExpression((Equal)newExp);
		return equalNorm;
		}
	else if (newExp instanceof First)
		{
		FirstNorm firstNorm = new FirstNorm();
		firstNorm.setOldExpression(oldExp);
		firstNorm.setNewExpression((First)newExp);
		return firstNorm;
		}
	else if (newExp instanceof Get)
		{
		GetNorm getNorm = new GetNorm();
		getNorm.setOldExpression(oldExp);
		getNorm.setNewExpression((Get)newExp);
		return getNorm;
		}
	else if (newExp instanceof IdentExpr)
		{
		IdentExprNorm identExprNorm = new IdentExprNorm();
		identExprNorm.setOldExpression(oldExp);
		identExprNorm.setNewExpression((IdentExpr)newExp);
		return identExprNorm;
		}
	else if (newExp instanceof Insert)
		{
		InsertNorm insertNorm = new InsertNorm();
		insertNorm.setOldExpression(oldExp);
		insertNorm.setNewExpression((Insert)newExp);
		return insertNorm;
		}
	else if (newExp instanceof Integer)
		{
		IntegerNorm integerNorm = new IntegerNorm();
		integerNorm.setOldExpression(oldExp);
		integerNorm.setNewExpression((Integer)newExp);
		return integerNorm;
		}
	else if (newExp instanceof Length)
		{
		LengthNorm lengthNorm = new LengthNorm();
		lengthNorm.setOldExpression(oldExp);
		lengthNorm.setNewExpression((Length)newExp);
		return lengthNorm;
		}
	else if (newExp instanceof ListCons)
		{
		ListConsNorm listConsNorm = new ListConsNorm();
		listConsNorm.setOldExpression(oldExp);
		listConsNorm.setNewExpression((ListCons)newExp);
		return listConsNorm;
		}
	else if (newExp instanceof Maggiore)
		{
		MaggioreNorm maggioreNorm = new MaggioreNorm();
		maggioreNorm.setOldExpression(oldExp);
		maggioreNorm.setNewExpression((Maggiore)newExp);
		return maggioreNorm;
		}
	else if (newExp instanceof MaggioreUguale)
		{
		MaggioreUgualeNorm maggioreUgualeNorm = new MaggioreUgualeNorm();
		maggioreUgualeNorm.setOldExpression(oldExp);
		maggioreUgualeNorm.setNewExpression((MaggioreUguale)newExp);
		return maggioreUgualeNorm;
		}
	else if (newExp instanceof Minore)
		{
		MinoreNorm minoreNorm = new MinoreNorm();
		minoreNorm.setOldExpression(oldExp);
		minoreNorm.setNewExpression((Minore)newExp);
		return minoreNorm;
		}
	else if (newExp instanceof MinoreUguale)
		{
		MinoreUgualeNorm minoreUgualeNorm = new MinoreUgualeNorm();
		minoreUgualeNorm.setOldExpression(oldExp);
		minoreUgualeNorm.setNewExpression((MinoreUguale)newExp);
		return minoreUgualeNorm;
		}
	else if (newExp instanceof Moltiplicazione)
		{
		MoltiplicazioneNorm moltiplicazioneNorm = new MoltiplicazioneNorm();
		moltiplicazioneNorm.setOldExpression(oldExp);
		moltiplicazioneNorm.setNewExpression((Moltiplicazione)newExp);
		return moltiplicazioneNorm;
		}
	else if (newExp instanceof Negazione)
		{
		NegazioneNorm negazioneNorm = new NegazioneNorm();
		negazioneNorm.setOldExpression(oldExp);
		negazioneNorm.setNewExpression((Negazione)newExp);
		return negazioneNorm;
		}
	else if (newExp instanceof Or)
		{
		OrNorm orNorm = new OrNorm();
		orNorm.setOldExpression(oldExp);
		orNorm.setNewExpression((Or)newExp);
		return orNorm;
		}
	else if (newExp instanceof Put)
		{
		PutNorm putNorm = new PutNorm();
		putNorm.setOldExpression(oldExp);
		putNorm.setNewExpression((Put)newExp);
		return putNorm;
		}
	else if (newExp instanceof Read)
		{
		ReadNorm readNorm = new ReadNorm();
		readNorm.setOldExpression(oldExp);
		readNorm.setNewExpression((Read)newExp);
		return readNorm;
		}
	else if (newExp instanceof Real)
		{
		RealNorm realNorm = new RealNorm();
		realNorm.setOldExpression(oldExp);
		realNorm.setNewExpression((Real)newExp);
		return realNorm;
		}
	else if (newExp instanceof RecordCons)
		{
		RecordConsNorm recordConsNorm = new RecordConsNorm();
		recordConsNorm.setOldExpression(oldExp);
		recordConsNorm.setNewExpression((RecordCons)newExp);
		return recordConsNorm;
		}
	else if (newExp instanceof Remove)
		{
		RemoveNorm removeNorm = new RemoveNorm();
		removeNorm.setOldExpression(oldExp);
		removeNorm.setNewExpression((Remove)newExp);
		return removeNorm;
		}
	else if (newExp instanceof Somma)
		{
		SommaNorm sommaNorm = new SommaNorm();
		sommaNorm.setOldExpression(oldExp);
		sommaNorm.setNewExpression((Somma)newExp);
		return sommaNorm;
		}
	else if (newExp instanceof Sottrazione)
		{
		SottrazioneNorm sottrazioneNorm = new SottrazioneNorm();
		sottrazioneNorm.setOldExpression(oldExp);
		sottrazioneNorm.setNewExpression((Sottrazione)newExp);
		return sottrazioneNorm;
		}
	else if (newExp instanceof Tail)
		{
		TailNorm tailNorm = new TailNorm();
		tailNorm.setOldExpression(oldExp);
		tailNorm.setNewExpression((Tail)newExp);
		return tailNorm;
		}
	else if (newExp instanceof Write)
		{
		WriteNorm writeNorm = new WriteNorm();
		writeNorm.setOldExpression(oldExp);
		writeNorm.setNewExpression((Write)newExp);
		return writeNorm;
		}
	else
		return null;
	}
}
