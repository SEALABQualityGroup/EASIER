#################################################################
#								#
#								#
#								#
#			  Makefile				#
#								#
#								#
#								#
#################################################################

manual_ps: manual.tex macro.tex Makefile \
	   chapter.01.tex chapter.02.tex chapter.03.tex chapter.04.tex \
	   chapter.05.tex chapter.06.tex chapter.07.tex biblio.tex \
	   twotowers.eps
	latex manual.tex
	latex manual.tex
	latex manual.tex
	dvips -o ../docs/manual.ps manual.dvi
	gv ../docs/manual.ps &

manual_pdf: manual.tex macro.tex Makefile \
	    chapter.01.tex chapter.02.tex chapter.03.tex chapter.04.tex \
	    chapter.05.tex chapter.06.tex chapter.07.tex biblio.tex \
	    twotowers.eps
	latex manual.tex
	latex manual.tex
	latex manual.tex
	dvipdfm -o ../docs/manual.pdf manual.dvi
	acroread ../docs/manual.pdf &

twotowers.eps: twotowers.fig Makefile
	fig2dev -Leps -m0.42 -p1 twotowers.fig twotowers.eps

clean:
	rm -f *.eps *.dvi *.log *.aux *.toc *.lof *.lot *.bak

cleanall:
	rm -f ../docs/manual.ps ../docs/manual.pdf *.eps *.dvi *.log *.aux *.toc *.lof *.lot *.bak
