/**
 * 
 */
package restrizioniIstanze.qnElemTypes;

import restrizioniIstanze.RestrizioniIstanzeException;

/**
 * @author Mirko
 *
 */
public abstract class ElemTypeNormSP 
	extends ElemTypeNorm 
	{

	// i pesi delle azioni pre-phase-action devono essere reali maggiori di zero
	public abstract boolean restrizioneIstanze25() throws RestrizioniIstanzeException;

	// le priorita' delle azioni pre-phase-action devono essere interi non minori di uno
	public abstract boolean restrizioneIstanze24() throws RestrizioniIstanzeException;

	// ogni processo choice deve avere azioni pre-phase-action con la stessa priorita'
	public abstract boolean restrizioneIstanze23() throws RestrizioniIstanzeException;

	/*
	 * i pesi dei tassi delle azioni di selezione devono essere reali maggiori di zero
	 */
	public abstract boolean restrizioneIstanze22() throws RestrizioniIstanzeException;

	/*
	 * le priorita' delle azioni di selezione devono essere interi non minori di uno
	 */
	public abstract boolean restrizioneIstanze21() throws RestrizioniIstanzeException;

	/*
	 * le priorita' delle azioni di prosecuzione del percorso devono essere tutte uguali tra loro
	 */
	public abstract boolean restrizioneIstanze20() throws RestrizioniIstanzeException;

	/*
	 * le priorita' delle azioni di prosecuzione del percorso devono essere interi non minori di uno
	 */
	public abstract boolean restrizioneIstanze19() throws RestrizioniIstanzeException;

	/*
	 * 1) i pesi delle azioni di scelta di fase devono essere Real maggiori di zero
	 */
	public abstract boolean restrizioneIstanze5() throws RestrizioniIstanzeException;

	/*
	 * 2) i tassi delle azioni di fase devono essere Real maggiori di zero
	 */	
	public abstract boolean restrizioneIstanze6() throws RestrizioniIstanzeException;

	/*
	 * 3) i pesi delle azioni di prosecuzione del percorso devono essere Real maggiori di zero
	 */
	public abstract boolean restrizioneIstanze7() throws RestrizioniIstanzeException;
	
		@Override
		public boolean isCompliantInstanceRules()
				throws RestrizioniIstanzeException 
			{
			if (!restrizioneIstanze6()) return false;
			if (!restrizioneIstanze7()) return false;
			if (!restrizioneIstanze16()) return false;
			if (!restrizioneIstanze19()) return false;
			if (!restrizioneIstanze20()) return false;
			if (!restrizioneIstanze21()) return false;
			if (!restrizioneIstanze22()) return false;
			if (!restrizioneIstanze23()) return false;
			if (!restrizioneIstanze24()) return false;
			if (!restrizioneIstanze25()) return false;
			return true;
			}
	}
