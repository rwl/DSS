package com.epri.dss.shared.test;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.ComplexMatrix;
import com.epri.dss.shared.impl.ComplexMatrixImpl;

import junit.framework.TestCase;

public class ComplexMatrixTest extends TestCase {

	int n = 6;
	ComplexMatrix cm;

	protected void setUp() throws Exception {
		int i, j;
		cm = new ComplexMatrixImpl(n);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				cm.set(i, j, new Complex(i, j));

		super.setUp();
	}

	/**
	 * Test matrix instantiation.
	 */
	public void testCMatrixImpl() {
		ComplexMatrix m = new ComplexMatrixImpl(n);
		assertNotNull(m);
	}

	/**
	 * Test matrix order.
	 */
	public void testOrder() {
		ComplexMatrix m = new ComplexMatrixImpl(n);
		assertEquals(n, m.order());
	}

	/**
	 * Test getting matrix elements.
	 */
	public void testGetElement() {
		ComplexMatrix m = new ComplexMatrixImpl(n);
		assertEquals(0.0, m.get(0, 0).getReal());
		assertEquals(0.0, m.get(0, 0).getImaginary());
	}

	/**
	 * Test setting matrix elements.
	 */
	public void testSetElement() {
		int i, j;
		double real, imag;
		ComplexMatrix m = new ComplexMatrixImpl(n);

		i = 0; j = 0;
		real = 2; imag = 3;
		m.set(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).getReal());
		assertEquals(imag, m.get(i, j).getImaginary());

		i = 1; j = 2;
		real = 3; imag = 4;
		m.set(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).getReal());
		assertEquals(imag, m.get(i, j).getImaginary());
	}

	/**
	 * Test setting symmetrical matrix elements.
	 */
	public void testSetElemSym() {
		int i, j;
		double real, imag;
		ComplexMatrix m = new ComplexMatrixImpl(n);

		i = 1; j = 3;
		real = 2; imag = 5;
		m.setSym(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).getReal());
		assertEquals(imag, m.get(i, j).getImaginary());
		assertEquals(real, m.get(j, i).getReal());
		assertEquals(imag, m.get(j, i).getImaginary());
	}

	/**
	 * Test adding to a matrix element.
	 */
	public void testAddElement() {
		int i, j;
		double real, imag;
		ComplexMatrix m = new ComplexMatrixImpl(n);

		i = 3; j = 2;
		real = 2; imag = 3;
		m.add(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).getReal());
		assertEquals(imag, m.get(i, j).getImaginary());

		m.add(i, j, new Complex(real, imag));
		assertEquals(real + real, m.get(i, j).getReal());
		assertEquals(imag + imag, m.get(i, j).getImaginary());
	}

	/**
	 * Test adding to symmetrical matrix elements.
	 */
	public void testAddElemSym() {
		int i, j;
		double real, imag;
		ComplexMatrix m = new ComplexMatrixImpl(n);

		i = 4; j = 1;
		real = 1; imag = 3;
		m.addSym(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).getReal());
		assertEquals(imag, m.get(i, j).getImaginary());
		assertEquals(real, m.get(j, i).getReal());
		assertEquals(imag, m.get(j, i).getImaginary());

		m.addSym(i, j, new Complex(real, imag));
		assertEquals(real + real, m.get(i, j).getReal());
		assertEquals(imag + imag, m.get(i, j).getImaginary());
		assertEquals(real + real, m.get(j, i).getReal());
		assertEquals(imag + imag, m.get(j, i).getImaginary());
	}

	/**
	 * Test zeroing all matrix elements.
	 */
	public void testClear() {
		int i, j, i1, j1, i2, j2;
		double real, imag;
		ComplexMatrix m = new ComplexMatrixImpl(n);

		i1 = 0; j1 = 0; i2 = 3; j2 = 5;
		real = 2; imag = 3;
		m.set(i1, j1, new Complex(real, imag));
		m.setSym(i2, j2, new Complex(real, imag));

		m.clear();
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(0.0, m.get(i, j).getReal());
				assertEquals(0.0, m.get(i, j).getImaginary());
			}
	}

	/**
	 * Test copying element values from another matrix.
	 */
	public void testCopyFrom() {
		int i, j;
		ComplexMatrix cm2;

		cm2 = new ComplexMatrixImpl(n);
		cm2.copyFrom(cm);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(Double.valueOf(i), cm2.get(i, j).getReal());
				assertEquals(Double.valueOf(j), cm2.get(i, j).getImaginary());
			}
	}

	/**
	 * Test adding element values from another matrix.
	 */
	public void testAddFrom() {
		int i, j;

		cm.addFrom(cm);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(Double.valueOf(i + i), cm.get(i, j).getReal());
				assertEquals(Double.valueOf(j + j), cm.get(i, j).getImaginary());
			}
	}

	/**
	 * Test the array representation of a matrix (in column-major order).
	 */
	public void testAsArray() {
		int i, j, k;
		Complex[] array;

		array = cm.asArray();

		for (Complex c : array)
			System.out.printf("%.2f +j%.2f\n", c.getReal(), c.getImaginary());

		k = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(Double.valueOf(j), array[k].getReal());
				assertEquals(Double.valueOf(i), array[k].getImaginary());
				k += 1;
			}
	}

	/**
	 * Test zeroing out a single matrix row.
	 */
	public void testZeroRow() {
		int iRow, j;

		iRow = 2;

		cm.zeroRow(iRow);
		for (j = 0; j < cm.order(); j++) {
			assertEquals(0.0, cm.get(iRow, j).getReal());
			assertEquals(0.0, cm.get(iRow, j).getImaginary());
		}
	}

	/**
	 * Test zeroing out a single matrix column.
	 */
	public void testZeroCol() {
		int i, jCol;

		jCol = 3;
		cm.zeroCol(jCol);
		for (i = 0; i < cm.order(); i++) {
			assertEquals(0.0, cm.get(i, jCol).getReal());
			assertEquals(0.0, cm.get(i, jCol).getImaginary());
		}
	}

	/**
	 * Test averaging the diagonal elements of a matrix.
	 */
	public void testAvgDiagonal() {
		double expect;
		Complex avg;

		expect = 0;
		for (int i = 0; i < n; i++)
			expect += n;
		expect = expect / n;

		avg = cm.avgDiag();

		assertEquals(expect, avg.getReal());
		assertEquals(expect, avg.getImaginary());
	}

	/**
	 * Test averaging the upper triangle off diagonal elements of a matrix.
	 */
	public void testAvgOffDiagonal() {
		Complex avg;
		double delta = 1e-09;

		avg = cm.avgOffDiag();

		assertEquals(1.1428571428571428, avg.getReal(), delta);
		assertEquals(3.5, avg.getImaginary(), delta);
	}

	/**
	 * Test multiplication of all elements by a constant.
	 */
	public void testMultByConst() {
		int i, j;
		double constant = 2;
		double delta = 1e-09;

		cm.mult(constant);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(i * constant, cm.get(i, j).getReal(), delta);
				assertEquals(i * constant, cm.get(i, j).getImaginary(), delta);
			}
	}

	/**
	 * Test summation of all elements in a matrix block.
	 */
	public void testSumBlock() {
		int row1 = 1, row2 = 3, col1 = 1, col2 = 3;
		Complex sum;
		double delta = 1e-09;

		sum = cm.sumBlock(row1, row2, col1, col2);

		assertEquals(18.0, sum.getReal(), delta);
		assertEquals(18.0, sum.getImaginary(), delta);
	}

	/**
	 * Test matrix-vector multiplication.
	 */
	public void testVMult() {
		int i;
		Complex[] x = new Complex[n];
		Complex[] b = new Complex[n];
		double delta = 1e-09;

		for (i = 0; i < n; i++)
			x[i] = new Complex(i, i);

		cm.vMult(b, x);

		assertEquals(-55., b[0].getReal(), delta);
		assertEquals( 55., b[0].getImaginary(), delta);
		assertEquals(-40., b[1].getReal(), delta);
		assertEquals( 70., b[1].getImaginary(), delta);
		assertEquals(-25., b[2].getReal(), delta);
		assertEquals( 85., b[2].getImaginary(), delta);
		assertEquals(-10., b[3].getReal(), delta);
		assertEquals(100., b[3].getImaginary(), delta);
		assertEquals(  5., b[4].getReal(), delta);
		assertEquals(115., b[4].getImaginary(), delta);
		assertEquals( 20., b[5].getReal(), delta);
		assertEquals(130., b[5].getImaginary(), delta);
	}

	/**
	 * Test matrix-vector multiplication with result accumulation.
	 */
	public void testVMultAccum() {
		int i;
		Complex[] x = new Complex[n];
		Complex[] b = new Complex[n];
		double delta = 1e-09, real = 2, imag = 3;

		for (i = 0; i < n; i++) {
			x[i] = new Complex(i, i);
			b[i] = new Complex(real, imag);
		}

		cm.vMult(b, x);

		assertEquals(-55. + real, b[0].getReal(), delta);
		assertEquals( 55. + imag, b[0].getImaginary(), delta);
		assertEquals(-40. + real, b[1].getReal(), delta);
		assertEquals( 70. + imag, b[1].getImaginary(), delta);
		assertEquals(-25. + real, b[2].getReal(), delta);
		assertEquals( 85. + imag, b[2].getImaginary(), delta);
		assertEquals(-10. + real, b[3].getReal(), delta);
		assertEquals(100. + imag, b[3].getImaginary(), delta);
		assertEquals(  5. + real, b[4].getReal(), delta);
		assertEquals(115. + imag, b[4].getImaginary(), delta);
		assertEquals( 20. + real, b[5].getReal(), delta);
		assertEquals(130. + imag, b[5].getImaginary(), delta);
	}

	/**
	 * Test matrix inversion.
	 */
	public void testInvert() {
		double delta = 1e-09;

		// avoid singularity
		cm.setSym(2, 2, new Complex(6, 9));
		cm.setSym(3, 3, new Complex(2, 4));
		cm.setSym(1, 2, new Complex(2, 4));

		cm.invert();

		assertEquals(-8e-01, cm.get(0, 4).getImaginary(), delta);
		assertEquals(-6.66666667e-01, cm.get(1, 1).getReal(), delta);
		assertEquals(-4.10256410e-02, cm.get(1, 2).getReal(), delta);
		assertEquals(7.17948718e-02, cm.get(1, 2).getImaginary(), delta);
	}

	/**
	 * Test inversion error code.
	 */
	public void testGetErrorCode() {
		cm.invert();

		assertEquals(2, cm.getErrorCode());
	}

	/**
	 * Test Kron reduction.
	 */
//	public void testKron() {
//		fail("Not yet implemented");
//	}

}
