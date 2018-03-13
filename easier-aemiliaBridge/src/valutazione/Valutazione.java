package valutazione;

import interfacceSpecifiche.NumberExp;

import java.util.List;

import specificheAEmilia.ArrayCons;
import specificheAEmilia.Expression;
import specificheAEmilia.Integer;
import specificheAEmilia.ListCons;
import specificheAEmilia.Real;
import specificheAEmilia.RecordCons;

/**
 * Classe utilizzata per la valutazione di un'espressione.
 * Serve per ottenere un oggetto di tipo primitivo da uno Expression.
 * Come precondizione le espressioni argomento dei metodi devono essere gia' normalizzate,
 * altrimenti si solleva una ValutationeExcption.
 * 
 * @author Mirko
 *
 */
public class Valutazione 
	{
    	
    public Valutazione() 
    	{
		super();
    	}

    /**
     * Converte un oggetto di tipo Integer, Double, Boolean, List, Object[] o RecordEvaluation 
     * in un corrispondente
     * oggetto AEmilia. Restituisce null, se o none'di tipo Integer, Double, Boolean, List, 
     * Object[] o RecordEvaluation.
     *
     * @param o
     * @return
     */
    public static Expression obtainExp(Object o)
        {
        Expression ris = null;
        if (o instanceof java.lang.Integer)
            ris = new Integer(((java.lang.Integer)o).intValue());
        else if (o instanceof Double)
            ris = new Real(((Double)o).doubleValue());
        else if (o instanceof Boolean)
            ris = new specificheAEmilia.Boolean(((Boolean)o).booleanValue());
        else if (o instanceof List<?>)
        	{
        	List<?> list = (List<?>)o;
        	Expression[] espressiones = new Expression[list.size()];
        	for (int i = 0; i < list.size(); i++)
        		{
        		espressiones[i] = obtainExp(list.get(i));
        		}
        	ris = new ListCons(espressiones);
        	}
        else if (o instanceof Object[])
        	{
        	Object[] objects = (Object[])o;
        	Expression[] expressions = new Expression[objects.length];
        	for (int i = 0; i < objects.length; i++)
        		{
        		expressions[i] = obtainExp(objects[i]);
        		}
        	ris = new ArrayCons(expressions);
        	}
        else if (o instanceof RecordEvaluation)
        	{
        	RecordEvaluation recordEvaluation = (RecordEvaluation)o;
        	Expression[] expressions = new Expression[recordEvaluation.size()];
        	for (int i = 0; i < recordEvaluation.size(); i++)
        		{
        		RecordMapping recordMapping = recordEvaluation.get(i);
        		expressions[i] = obtainExp(recordMapping.getValue());
        		}
        	ris = new RecordCons(expressions);
        	}
        return ris;
        }
	
	public boolean valutaBoolean(Expression expression) 
		throws ValutazioneException
		{
		try {
			specificheAEmilia.Boolean boolean1 = (specificheAEmilia.Boolean) expression;
			boolean b = boolean1.getValore();
			return b;
			} 
		catch (Exception e) 
			{
			throw new ValutazioneException(e);
			}
		}
	
	public int valutaInteger(Expression expression)
		throws ValutazioneException
		{
		try {
			Integer integer = (Integer) expression;
			int i = integer.getValore();
			return i;
			} 
		catch (Exception e) 
			{
			throw new ValutazioneException(e);
			}
		}
	
	public int valutaPrio(Expression expression)
		throws ValutazioneException
		{
		try {
			Integer integer = (Integer) expression;
			int i = integer.getValore();
			return i;
			} 
		catch (Exception e) 
			{
			throw new ValutazioneException(e);
			}
		}
	
	public double valutaRate(Expression expression)
		throws ValutazioneException
		{
		try {
			NumberExp numberExp = (NumberExp) expression;
			double d = numberExp.getNumber();
			return d;
			} 
		catch (Exception e) 
			{
			throw new ValutazioneException(e);
			}
		}
	
	public double valutaReal(Expression expression)
		throws ValutazioneException
		{
		try {
			NumberExp numberExp = (NumberExp) expression;
			double d = numberExp.getNumber();
			return d;
			} 
		catch (Exception e) 
			{
			throw new ValutazioneException(e);
			}
		}
	
	public double valutaWeight(Expression expression)
		throws ValutazioneException
		{
		try {
			NumberExp numberExp = (NumberExp) expression;
			double d = numberExp.getNumber();
			return d;
			} 
		catch (Exception e) 
			{
			throw new ValutazioneException(e);
			}
		}
	
	public boolean isBoolean(Expression expression) 
		{
		if (expression instanceof specificheAEmilia.Boolean)
			return true;
		else
			return false;
		}
	
	public boolean isInteger(Expression expression)
		{
		if (expression instanceof Integer)
			return true;
		else
			return false;
		}
	
	public boolean isPrio(Expression expression)
		{
		if (expression instanceof Integer)
			return true;
		else
			return false;
		}
	
	public boolean isRate(Expression expression)
		{
		if (expression instanceof NumberExp)
			return true;
		else
			return false;
		}
	
	public boolean isReal(Expression expression)
		{
		if (expression instanceof NumberExp)
			return true;
		else
			return false;
		}
	
	public boolean isWeight(Expression expression)
		{
		if (expression instanceof NumberExp)
			return true;
		else
			return false;
		}
	}