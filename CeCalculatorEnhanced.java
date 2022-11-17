/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be  with  code.  If you do not have a copy,
 * :
 *
 *      http://www.gnu.org/copyleft/lesser.
 *
 *   this code is  jointly by the individual
 *
 * For  information on the BioJava project and its aims,
 * or  joi the biojava-l mailing list, visit the home page
 * at:
 *
 *      
 ..org/
 *
 *    25, 2009
 * Author: Andreas Prlic
 *
 */

package org.biojava.nbio.structure.align.ce;

import org.biojava.nbio.core.alignment.matrices.ScaledSubstitutionMatrix;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.structure.*;
import org.biojava.nbio.structure.align.model.AFP;
import org.biojava.nbio.structure.align.model.AFPChain;
import org.biojava.nbio.structure.align.util.AFPAlignmentDisplay;
import org.biojava.nbio.structure.geometry.Matrices;
import org.biojava.nbio.structure.geometry.SuperPositions;
import org.biojava.nbio.structure.jama.Matrix;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompoundSet;
import org.biojava.nbio.core.alignment.matrices.ScaledSubstitutionMatrix;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.structure.*;
import org.biojava.nbio.structure.align.model.AFP;
import org.biojava.nbio.structure.align.model.AFPChain;
import org.biojava.nbio.structure.align.util.AFPAlignmentDisplay;
import org.biojava.nbio.structure.geometry.Matrices;
import org.biojava.nbio.structure.geometry.SuperPositions;
import org.biojava.nbio.structure.jama.Matrix;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompoundSet;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Matrix4d;
if(distAll ) {
			for(int i=0; i<traceMaxSize; i++)
				traceIndexContainer[i]=(i+1)*i*winSize*winSize/2+(i+1)*winSizeComb1;
		} else {
			for(int i=0; i<traceMaxSize; i++) {
				traceIndexContainer[i]=(i+1)*i*winSize/2+(i+1)*winSizeComb1;


			}
		}


/**
 * This is based on the original Combinatorial Extension (CE) source code from 2003 or 2004 (CE version 2.3),
 * as has been originally developed by I. Shindyalov and P.Bourne (1998).
 * The original CE paper is available from here: <a href="http://peds.oxfordjournals.org/cgi/content/short/11/9/739">http://peds.oxfordjournals.org/cgi/content/short/11/9/739</a>.
 *
 * This class is a pretty much exact 1:1 port from C, where I cared about exact reproduce of the CE results
 * and not about Java style.
 *
 * @author Spencer Bliven
 * @author Andreas Prlic
 *
 */
public class CeCalculatorEnhanced {

	protected static final boolean isPrint = true;
	private static final boolean showAlignmentSteps = true;
	private static final boolean debug = true;

	int[] f1;
	int[] f2;
	double[][]dist1;
	double[][]dist2;
	protected double[][]mat;
	protected int[] bestTrace1;
	protected int[] bestTrace2;
	protected int[][] bestTraces1;
	protected int[][] bestTraces2;
	protected int nBestTrace;
	protected int nBestTraces;
	double[] d_ = new double[20];
	protected int[] bestTracesN;
	protected double bestTraceScore;
	protected int nTrace;
	protected double[] bestTracesScores;
	protected int[] trace1;
	protected int[] trace2;

	protected static final 	double  zThr=-0.1;

	long timeStart ;
	long timeEnd;
	private int nAtom;

	// the equivalent positions in the alignment...
	private int[] align_se1;
	private int[] align_se2;


	private int alignmentPositionOrLength;
	private int[] bestTraceLen;
	private Matrix r;
	private Atom t;
	protected int nTraces;

	private double z;
	private int[] traceIndexContainer;

	protected CeParameters params;
	// SHOULD these fields be PARAMETERS?

	protected static final int nIter = 1;
	private static final boolean distAll = false;

	List<MatrixListener> matrixListeners;

	public static final boolean GLOBAL_ALIGN1 = false;
	public static final boolean GLOBAL_ALIGN2 = false;

	public CeCalculatorEnhanced(CeParameters params){
		timeStart = System.currentTimeMillis();
		dist1= new double[0][0];
		dist2= new double[0][0];
		this.params = params;
		matrixListeners = new ArrayList<MatrixListener>();

	}
	public class CeCalculatorEnhanced {

	protected static final boolean isPrint = true;
	private static final boolean showAlignmentSteps = true;
	private static final boolean debug = true;

	int[] f1;
	int[] f2;
	double[][]dist1;
	double[][]dist2;
	protected double[][]mat;
	protected int[] bestTrace1;
	protected int[] bestTrace2;
	protected int[][] bestTraces1;
	protected int[][] bestTraces2;
	protected int nBestTrace;
	protected int nBestTraces;
	double[] d_ = new double[20];
	protected int[] bestTracesN;
	protected double bestTraceScore;
	protected int nTrace;
	protected double[] bestTracesScores;
	protected int[] trace1;
	protected int[] trace2;

	protected static final 	double  zThr=-0.1;

	long timeStart ;
	long timeEnd;
	private int nAtom;

	// the equivalent positions in the alignment...
	private int[] align_se1;
	private int[] align_se2;


	private int alignmentPositionOrLength;
	private int[] bestTraceLen;
	private Matrix r;
	private Atom t;
	protected int nTraces;

	private double z;
	private int[] traceIndexContainer;

	protected CeParameters params;
	// SHOULD these fields be PARAMETERS?

	protected static final int nIter = 1;
	private static final boolean distAll = false;

	List<MatrixListener> matrixListeners;

	public static final boolean GLOBAL_ALIGN1 = false;
	public static final boolean GLOBAL_ALIGN2 = false;

	public CeCalculatorEnhanced(CeParameters params){
		timeStart = System.currentTimeMillis();
		dist1= new double[0][0];
		dist2= new double[0][0];
		this.params = params;
		matrixListeners = new ArrayList<MatrixListener>();

	}

	/**
	 *
	 * @param afpChain A new AFPChain, which will be filled in by this function
	 * @param ca1
	 * @param ca2
	 * @return afpChain
	 * @throws StructureException
	 */
	public AFPChain extractFragments(AFPChain afpChain,
			Atom[] ca1, Atom[] ca2) throws StructureException{

		int nse1 = ca1.length;
		int nse2 = ca2.length;

		afpChain.setCa1Length(nse1);
		afpChain.setCa2Length(nse2);

		int traceMaxSize=nse1<nse2?nse1:nse2;

		f1 = new int[nse1];
		f2 = new int[nse2];

		dist1 = initIntraDistmatrix(ca1, nse1);
		dist2 = initIntraDistmatrix(ca2, nse2);


		if ( debug )
			System.out.println("parameters: " + params);

		if ( params.getScoringStrategy() == CeParameters.ScoringStrategy.SEQUENCE_CONSERVATION){
			if ( params.getSeqWeight() < 1)
				params.setSeqWeight(2);
		}

		int winSize = params.getWinSize();

		int winSizeComb1 = (winSize-1)*(winSize-2)/2;

		traceIndexContainer = new int[traceMaxSize];

		// CE: unused code. distAll is always false and both loops do the same???
		// CE v2.3 calls this Weight factors for trace extension
		if(distAll ) {
			for(int i=0; i<traceMaxSize; i++)
				traceIndexContainer[i]=(i+1)*i*winSize*winSize/2+(i+1)*winSizeComb1;
		} else {
			for(int i=0; i<traceMaxSize; i++) {
				traceIndexContainer[i]=(i+1)*i*winSize/2+(i+1)*winSizeComb1;


			}
		}

}
