/**
 * 
 */
package valutazione.normalization.normalizeParts.normalizeAttacDeclInd;

import java.util.List;
import java.util.TreeMap;

import restrizioniIstanze.expressions.ExpressionNorm;
import restrizioniIstanze.structure.AttacDeclNorm;
import specificheAEmilia.AttacDecl;
import specificheAEmilia.AttacDeclInd;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.IntegerType;
import utilities.ErrorMessage;
import valutazione.ValueIdentExpr;
import valutazione.normalization.NormalizeException;
import valutazione.normalization.normalizeExpressionStrict.NormalizeExpressionStrict;
import valutazione.scope.ScopeArchiType;
import valutazione.scope.ScopeAttacDecl;
import valutazione.typeChecking.attacIndChecking.CheckRange4;
import valutazione.typeChecking.attacIndChecking.CheckRange5;
import valutazione.typeChecking.attacIndChecking.CheckRange6;

/**
 * @author Mirko
 *
 */
public class NormalizeAttacDeclInd {

	private ScopeArchiType scopeArchiType;
	private boolean errorOccurred;
	private int depth;
	private ErrorMessage errorMessage;
	
	public NormalizeAttacDeclInd(ScopeArchiType scopeArchiType,int depth) 
		{
		super();
		this.scopeArchiType = scopeArchiType;
		this.depth = depth;
		this.errorMessage = new ErrorMessage(depth);
		}	

	/**
	 * Restituisce un array di dichiarazioni di collegamenti architetturali semplici, a partire
	 * da una dichiarazione indicizzata.
	 *
	 * @param adi
	 * @return
	 * @throws NormalizeException
	 */
	public AttacDecl[] normalizeAttacDeclInd(AttacDeclInd adi) 
		throws NormalizeException 
		{
		try {
			AttacDeclInd clone = adi.copy();
			// si preleva lo scope della dichiarazione
			// di collegamento architetturale
			ScopeAttacDecl scopeAttacDecl = new ScopeAttacDecl(this.scopeArchiType,this.depth + 1);
			TreeMap<String, ValueIdentExpr> sco = scopeAttacDecl.getScopeAttacDecl(clone);
			if (scopeAttacDecl.isErrorOccurred()) 
				{
				String string = "Error to get scope for " + adi;
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = scopeAttacDecl.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			int indiceIniziale1 = 0, indiceFinale1 = 0, indiceIniziale2 = 0, indiceFinale2 = 0;
			// si valuta l'espressione iniziale per la prima indicizzazione
			NormalizeExpressionStrict normalizeExpressionStrict = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression = normalizeExpressionStrict.normalize(clone
					.getBeginningExpr1(), "", null, sco);
			if (normalizeExpressionStrict.isErrorOccurred()) 
				{
				String string = "Normalizing error for " + adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			Expression expression21 = expression.getNewExpression();
			clone.setBeginningExpr1(expression21);
			// si valuta l'espressione finale per la prima indicizzazione
			NormalizeExpressionStrict normalizeExpressionStrict2 = new NormalizeExpressionStrict(this.depth + 1);
			ExpressionNorm expression2 = normalizeExpressionStrict2.normalize(clone
					.getEndingExpr1(), "", null, sco);
			if (normalizeExpressionStrict2.isErrorOccurred()) 
				{
				String string = "Normalizing error for " + adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = normalizeExpressionStrict2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			Expression expression31 = expression2.getNewExpression();
			clone.setEndingExpr1(expression31);
			// si prelevano anche le espressioni iniziali e finali
			// per la seconda indicizzazione come interi
			if (clone.getIndex2() != null) 
				{
				// si valuta l'espressione iniziale per la seconda indicizzazione
				NormalizeExpressionStrict normalizeExpressionStrict3 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression3 = normalizeExpressionStrict3.normalize(
						clone.getBeginningExpr2(), "", null, sco);
				if (normalizeExpressionStrict3.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + adi.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict3.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression41 = expression3.getNewExpression();
				clone.setBeginningExpr2(expression41);
				// si valuta l'espressione finale per la seconda indicizzazione
				NormalizeExpressionStrict normalizeExpressionStrict4 = new NormalizeExpressionStrict(this.depth + 1);
				ExpressionNorm expression4 = normalizeExpressionStrict4.normalize(
						clone.getEndingExpr2(), "", null, sco);
				if (normalizeExpressionStrict4.isErrorOccurred()) 
					{
					String string = "Normalizing error for " + adi.toString();
					this.errorMessage.setErrorMessage(string);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = normalizeExpressionStrict4.getErrorMessage();
					list.add(errorMessage);
					this.errorOccurred = true;
					return null;
					}
				Expression expression5 = expression4.getNewExpression();
				clone.setEndingExpr2(expression5);
				}
			// effettuo il type checking
			valutazione.typeChecking.attacIndChecking.CheckRange checkRange = new valutazione.typeChecking.attacIndChecking.CheckRange(clone,sco,this.depth + 1);
			if (!checkRange.checkRange()) 
				{
				String string = "Type checking error for  " + adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			valutazione.typeChecking.attacIndChecking.CheckRange2 checkRange2 = new valutazione.typeChecking.attacIndChecking.CheckRange2(clone,sco,this.depth + 1);
			if (!checkRange2.checkRange2()) 
				{
				String string = "Type checking error for  "
						+ adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange2.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			valutazione.typeChecking.attacIndChecking.CheckRange3 checkRange3 = new valutazione.typeChecking.attacIndChecking.CheckRange3(clone,sco,this.depth + 1);
			if (!checkRange3.checkRange3()) 
				{
				String string = "Type checking error for  "
						+ adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange3.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			CheckRange4 checkRange4 = new CheckRange4(clone,sco,this.depth + 1);
			if (!checkRange4.checkRange4()) 
				{
				String string = "Type checking error for  "
						+ adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange4.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			CheckRange5 checkRange5 = new CheckRange5(clone,this.depth + 1);
			if (!checkRange5.checkRange5()) 
				{
				String string = "Type checking error for  "
						+ adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange5.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// verifico che il secondo range non sia vuoto
			CheckRange6 checkRange6 = new CheckRange6(clone,this.depth + 1);
			if (!checkRange6.checkRange6()) 
				{
				String string = "Type checking error for  "	+ adi.toString();
				this.errorMessage.setErrorMessage(string);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				ErrorMessage errorMessage = checkRange6.getErrorMessage();
				list.add(errorMessage);
				this.errorOccurred = true;
				return null;
				}
			// si preleva la prima espressione iniziale come un
			// intero
			indiceIniziale1 = ((Integer) clone.getBeginningExpr1()).getValore();
			// si preleva la prima espressione finale come un
			// intero
			indiceFinale1 = ((Integer) clone.getEndingExpr1()).getValore();
			// si alloca memoria per l'array risultato
			if (clone.getIndex2() != null) 
				{
				// si preleva la seconda espressione iniziale come un
				// intero
				indiceIniziale2 = ((Integer) clone.getBeginningExpr2()).getValore();
				// si preleva la seconda espressione finale come un
				// intero
				indiceFinale2 = ((Integer) clone.getEndingExpr2()).getValore();
				}
			AttacDecl[] ris = null;
			if (indiceFinale2 - indiceIniziale2 > 0)
				ris = new AttacDecl[(indiceFinale1 - indiceIniziale1 + 1)
						* (indiceFinale2 - indiceIniziale2 + 1)];
			else
				ris = new AttacDecl[indiceFinale1 - indiceIniziale1 + 1];
			int c = 0;
			for (int i = 0; i < indiceFinale1 - indiceIniziale1 + 1; i++) 
				{
				for (int j = 0; j < indiceFinale2 - indiceIniziale2 + 1; j++) 
					{
					// si aggiornano gli indici
					sco.put(clone.getIndex1(), new ValueIdentExpr(new Integer(i
							+ indiceIniziale1), true, new IntegerType()));
					if (indiceFinale2 - indiceIniziale2 > 0)
						sco.put(clone.getIndex2(), new ValueIdentExpr(new Integer(j
								+ indiceIniziale2), true, new IntegerType()));
					// si normalizza la dichiarazione secondo i nuovi valori
					// per gli indici
					NormalizeAttacDecl normalizeAttacDecl = new NormalizeAttacDecl(this.depth + 1);
					AttacDeclNorm attacDeclNorm = normalizeAttacDecl.normalizeAttacDecl(clone, sco);
					if (normalizeAttacDecl.isErrorOccurred()) 
						{
						String string = "Normalizing error for " + adi.toString();
						this.errorMessage.setErrorMessage(string);
						List<ErrorMessage> list = this.errorMessage.getCauses();
						ErrorMessage errorMessage = normalizeAttacDecl.getErrorMessage();
						list.add(errorMessage);
						return null;
						}
					ris[c] = attacDeclNorm.getNewAttacDecl();
					c++;
					}
				}
			// si restituisce l'array
			return ris;
			} 
		catch (Exception e) 
			{
			throw new NormalizeException(e);
			}
		}

	public boolean isErrorOccurred() {
		return errorOccurred;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}
