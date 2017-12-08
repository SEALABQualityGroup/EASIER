/**
 * 
 */
package restrizioniGenerali.secondRelease;

import java.util.ArrayList;
import java.util.List;

import restrizioniGenerali.RestrizioniGenException;
import specificheAEmilia.ArchiType;
import specificheAEmilia.ElemType;
import specificheAEmilia.Header;
import utilities.ErrorMessage;
import equivalenzaComportamentale.secondRelease.Equivalenza2;
import equivalenzaComportamentale.secondRelease.EquivalenzaArriviFiniti2;
import equivalenzaComportamentale.secondRelease.EquivalenzaArriviInfiniti2;
import equivalenzaComportamentale.secondRelease.EquivalenzaBufferIllimitato2;
import equivalenzaComportamentale.secondRelease.EquivalenzaBufferLimitato2;
import equivalenzaComportamentale.secondRelease.EquivalenzaForkConBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaForkSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaJoin2;
import equivalenzaComportamentale.secondRelease.EquivalenzaRoutingConBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaRoutingSenzaBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaServizioConBuffer2;
import equivalenzaComportamentale.secondRelease.EquivalenzaServizioSenzaBuffer2;

/**
 * @author Mirko
 *
 */
public class GeneraliRules2 
	{

	private ArchiType archiType;
	private List<Equivalenza2> tipiEquivalenza = new ArrayList<Equivalenza2>();
	private ErrorMessage errorMessage = new ErrorMessage(1);
	
	public GeneraliRules2() 
		{
		super();
		}

	public GeneraliRules2(ArchiType archiType) 
		{
		super();
		this.archiType = archiType;
		}

	/**
	 * La prima restrizione generalee'che ogni AEI di una specifica AEmilia 
	 * deve essere un processo di arrivi,
	 * un buffer, un processo fork, un processo join, un processo di servizio o un processo di routing 
	 *
	 * @return
	 * @throws RestrizioniGenException
	 */
	public boolean regola1() throws RestrizioniGenException
		{
		// si verifica la tipologia degli elementi architetturali
		ArchiType at = this.archiType;
		ElemType[] elemTypes = at.getArchiElemTypes().getElementTypes();
		for (int i = 0; i < elemTypes.length; i++)
			{
			if (!verificaTipoElemento(elemTypes[i]))
				return false;
			}
		// la connessione non va verificata
		return true;
		}

	/**
	 * I tipi degli elementi architetturali debbono avere nomi distinti tra loro.
	 * 
	 * @return false se esistono almeno due tipi di elementi architetturali che hanno lo stesso nome.
	 * 
	 * @throws RestrizioniGenException
	 */
	public boolean regola2() throws RestrizioniGenException
		{
		// si verifica la tipologia degli elementi architetturali
		ArchiType at = this.archiType;
		ElemType[] elemTypes = at.getArchiElemTypes().getElementTypes();
		for (int i = 0; i < elemTypes.length; i++)
			{
			ElemType elemType = elemTypes[i];
			Header header = elemType.getHeader();
			String string = header.getName();
			for (int j = i + 1; j < elemTypes.length; j++)
				{
				ElemType elemType2 = elemTypes[j];
				Header header2 = elemType2.getHeader();
				String string2 = header2.getName();
				if (string.equals(string2))
					{
					// 1
					String string3 = "general rules aren't satisfied";
					this.errorMessage.setErrorMessage(string3);
					List<ErrorMessage> list = this.errorMessage.getCauses();
					ErrorMessage errorMessage = new ErrorMessage(2);
					String string4 = "Architectural element types " + elemType.toString() + " and " +
						elemType2.toString() + " have same name";
					errorMessage.setErrorMessage(string4);
					list.add(errorMessage);
					return false;
					}
				}
			}
		return true;
		}
	
	public boolean isCompliantGeneralRules(boolean qnCheck) 
		throws RestrizioniGenException 
		{
		/* MODELLED */
		if (qnCheck)
			return regola1() && regola2();
		else 
			return regola2();
		}
	
	/**
	 * Verifica se un tipo di elemento architetturale abbia un comportamento equivalente ad
	 * un elemento base di una rete di code.
	 *
	 * @return
	 * @throws RestrizioniGenException
	 */
public boolean verificaTipoElemento(ElemType elemType) 
	throws RestrizioniGenException
		{
		/* MODELLED */
		try {
			EquivalenzaBufferLimitato2 equivalenzaBufferLimitato2 = new EquivalenzaBufferLimitato2(3);
			equivalenzaBufferLimitato2.setEt(elemType);
			EquivalenzaForkSenzaBuffer2 equivalenzaForkSenzaBuffer2 = new EquivalenzaForkSenzaBuffer2(3);
			equivalenzaForkSenzaBuffer2.setEt(elemType);
			EquivalenzaForkConBuffer2 equivalenzaForkConBuffer2 = new EquivalenzaForkConBuffer2(3);
			equivalenzaForkConBuffer2.setEt(elemType);
			EquivalenzaJoin2 equivalenzaJoin = new EquivalenzaJoin2(3);
			equivalenzaJoin.setEt(elemType);
			EquivalenzaArriviFiniti2 equivalenzaArriviFiniti2 = new EquivalenzaArriviFiniti2(3);
			equivalenzaArriviFiniti2.setEt(elemType);
			EquivalenzaServizioSenzaBuffer2 equivalenzaServizioSenzaBuffer2 = new EquivalenzaServizioSenzaBuffer2(3);
			equivalenzaServizioSenzaBuffer2.setEt(elemType);
			EquivalenzaServizioConBuffer2 equivalenzaServizioConBuffer2 = new EquivalenzaServizioConBuffer2(3);
			equivalenzaServizioConBuffer2.setEt(elemType);
			EquivalenzaBufferIllimitato2 equivalenzaBufferIllimitato2 = new EquivalenzaBufferIllimitato2(3);
			equivalenzaBufferIllimitato2.setEt(elemType);
			EquivalenzaArriviInfiniti2 equivalenzaArriviInfiniti2 = new EquivalenzaArriviInfiniti2(3);
			equivalenzaArriviInfiniti2.setEt(elemType);
			EquivalenzaRoutingSenzaBuffer2 equivalenzaRoutingSenzaBuffer = new EquivalenzaRoutingSenzaBuffer2(3);
			equivalenzaRoutingSenzaBuffer.setEt(elemType);
			EquivalenzaRoutingConBuffer2 equivalenzaRoutingConBuffer = new EquivalenzaRoutingConBuffer2(3);
			equivalenzaRoutingConBuffer.setEt(elemType);
			if (equivalenzaBufferLimitato2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaBufferLimitato2);
				return true;
			} else if (equivalenzaForkSenzaBuffer2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaForkSenzaBuffer2);
				return true;
			} else if (equivalenzaForkConBuffer2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaForkConBuffer2);
				return true;
			} else if (equivalenzaJoin.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaJoin);
				return true;
			} else if (equivalenzaArriviFiniti2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaArriviFiniti2);
				return true;
			} else if (equivalenzaServizioSenzaBuffer2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaServizioSenzaBuffer2);
				return true;
			} else if (equivalenzaServizioConBuffer2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaServizioConBuffer2);
				return true;
			} else if (equivalenzaBufferIllimitato2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaBufferIllimitato2);
				return true;
			} else if (equivalenzaArriviInfiniti2.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaArriviInfiniti2);
				return true;
			} else if (equivalenzaRoutingConBuffer.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaRoutingConBuffer);
				return true;
			} else if (equivalenzaRoutingSenzaBuffer.isEquivalente()) {
				this.tipiEquivalenza.add(equivalenzaRoutingSenzaBuffer);
				return true;
			} else
				{
				// 1
				String string = "general rules aren't satisfied";
				this.errorMessage.setErrorMessage(string);
				String string2 = elemType.toString() + " is not equivalent to queueing network element";
				ErrorMessage errorMessage = new ErrorMessage(2);
				errorMessage.setErrorMessage(string2);
				List<ErrorMessage> list = this.errorMessage.getCauses();
				list.add(errorMessage);
				List<ErrorMessage> list2 = errorMessage.getCauses();
				ErrorMessage errorMessage2 = equivalenzaArriviFiniti2.getErrorMessage();
				ErrorMessage errorMessage3 = equivalenzaArriviInfiniti2.getErrorMessage();
				ErrorMessage errorMessage4 = equivalenzaBufferIllimitato2.getErrorMessage();
				ErrorMessage errorMessage5 = equivalenzaBufferLimitato2.getErrorMessage();
				ErrorMessage errorMessage6 = equivalenzaForkConBuffer2.getErrorMessage();
				ErrorMessage errorMessage7 = equivalenzaForkSenzaBuffer2.getErrorMessage();
				ErrorMessage errorMessage8 = equivalenzaJoin.getErrorMessage();
				ErrorMessage errorMessage9 = equivalenzaRoutingConBuffer.getErrorMessage();
				ErrorMessage errorMessage10 = equivalenzaRoutingSenzaBuffer.getErrorMessage();
				ErrorMessage errorMessage11 = equivalenzaServizioConBuffer2.getErrorMessage();
				ErrorMessage errorMessage12 = equivalenzaServizioSenzaBuffer2.getErrorMessage();
				list2.add(errorMessage2);
				list2.add(errorMessage3);
				list2.add(errorMessage4);
				list2.add(errorMessage5);
				list2.add(errorMessage6);
				list2.add(errorMessage7);
				list2.add(errorMessage8);
				list2.add(errorMessage9);
				list2.add(errorMessage10);
				list2.add(errorMessage11);
				list2.add(errorMessage12);
				return false;
				}
			} 
		catch (Exception e) 
			{
			throw new RestrizioniGenException(e);
			}
		}

	public List<Equivalenza2> getEquivalenze() 
		{
		return this.tipiEquivalenza;
		}

	public ErrorMessage getErrorMessage() 
		{
		return errorMessage;
		}
	}
