/**
 * 
 */
package restrizioniIstanze.expressions;

import specificheAEmilia.Expression;
import specificheAEmilia.Moltiplicazione;

/**
 * @author Mio
 *
 */
public class MoltiplicazioneNorm 
	extends ExpressionNorm 
	{

	private Expression oldExpression;
	private Moltiplicazione newExpression;
	
	public void setOldExpression(Expression expression) 
		{
		this.oldExpression = expression;
		}

	public void setNewExpression(Moltiplicazione moltiplicazione) 
		{
		this.newExpression = moltiplicazione;
		}

	@Override
	public Object copy() 
		{
		MoltiplicazioneNorm moltiplicazioneNorm = new MoltiplicazioneNorm();
		moltiplicazioneNorm.setNewExpression(this.newExpression.copy());
		moltiplicazioneNorm.setOldExpression(this.oldExpression.copy());
		return moltiplicazioneNorm;
		}

	@Override
	public boolean equals(Object obj) 
		{
		if (!(obj instanceof MoltiplicazioneNorm))
			return false;
		MoltiplicazioneNorm moltiplicazioneNorm = (MoltiplicazioneNorm)obj;
		if (!this.newExpression.equals(moltiplicazioneNorm.getNewExpression()))
			return false;
		if (!this.oldExpression.equals(moltiplicazioneNorm.getOldExpression()))
			return false;
		return true;
		}

	@Override
	public Moltiplicazione getNewExpression() 
		{
		return this.newExpression;
		}

	@Override
	public void print() 
		{
		System.out.println("MoltiplicazioneNorm object");
		System.out.print("Old Expression: ");
		this.oldExpression.print();
		System.out.println("New Expression: ");
		this.newExpression.print();
		}

	@Override
	public String toString() 
		{
		String string = new String();
		string += "New Expression: ";
		string += this.newExpression;
		string += " Old Expression: ";
		string += this.oldExpression + " ";
		return string;
		}

	public Expression getOldExpression() 
		{
		return oldExpression;
		}
	}
