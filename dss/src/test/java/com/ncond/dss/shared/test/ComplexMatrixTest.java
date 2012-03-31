package com.ncond.dss.shared.test;

import junit.framework.TestCase;

import com.ncond.dss.shared.Complex;

import com.ncond.dss.shared.CMatrix;

public class ComplexMatrixTest extends TestCase {

	int n = 6;
	CMatrix cm;

	@Override
	protected void setUp() throws Exception {
		int i, j;
		cm = new CMatrix(n);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				cm.set(i, j, new Complex(i, j));

		super.setUp();
	}

	/**
	 * Test matrix instantiation.
	 */
	public void testCMatrixImpl() {
		CMatrix m = new CMatrix(n);
		assertNotNull(m);
	}

	/**
	 * Test matrix order.
	 */
	public void testOrder() {
		CMatrix m = new CMatrix(n);
		assertEquals(n, m.order());
	}

	/**
	 * Test getting matrix elements.
	 */
	public void testGetElement() {
		CMatrix m = new CMatrix(n);
		assertEquals(0.0, m.get(0, 0).real());
		assertEquals(0.0, m.get(0, 0).imag());
	}

	/**
	 * Test setting matrix elements.
	 */
	public void testSetElement() {
		int i, j;
		double real, imag;
		CMatrix m = new CMatrix(n);

		i = 0; j = 0;
		real = 2; imag = 3;
		m.set(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).real());
		assertEquals(imag, m.get(i, j).imag());

		i = 1; j = 2;
		real = 3; imag = 4;
		m.set(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).real());
		assertEquals(imag, m.get(i, j).imag());
	}

	/**
	 * Test setting symmetrical matrix elements.
	 */
	public void testSetElemSym() {
		int i, j;
		double real, imag;
		CMatrix m = new CMatrix(n);

		i = 1; j = 3;
		real = 2; imag = 5;
		m.setSym(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).real());
		assertEquals(imag, m.get(i, j).imag());
		assertEquals(real, m.get(j, i).real());
		assertEquals(imag, m.get(j, i).imag());
	}

	/**
	 * Test adding to a matrix element.
	 */
	public void testAddElement() {
		int i, j;
		double real, imag;
		CMatrix m = new CMatrix(n);

		i = 3; j = 2;
		real = 2; imag = 3;
		m.add(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).real());
		assertEquals(imag, m.get(i, j).imag());

		m.add(i, j, new Complex(real, imag));
		assertEquals(real + real, m.get(i, j).real());
		assertEquals(imag + imag, m.get(i, j).imag());
	}

	/**
	 * Test adding to symmetrical matrix elements.
	 */
	public void testAddElemSym() {
		int i, j;
		double real, imag;
		CMatrix m = new CMatrix(n);

		i = 4; j = 1;
		real = 1; imag = 3;
		m.addSym(i, j, new Complex(real, imag));
		assertEquals(real, m.get(i, j).real());
		assertEquals(imag, m.get(i, j).imag());
		assertEquals(real, m.get(j, i).real());
		assertEquals(imag, m.get(j, i).imag());

		m.addSym(i, j, new Complex(real, imag));
		assertEquals(real + real, m.get(i, j).real());
		assertEquals(imag + imag, m.get(i, j).imag());
		assertEquals(real + real, m.get(j, i).real());
		assertEquals(imag + imag, m.get(j, i).imag());
	}

	/**
	 * Test zeroing all matrix elements.
	 */
	public void testClear() {
		int i, j, i1, j1, i2, j2;
		double real, imag;
		CMatrix m = new CMatrix(n);

		i1 = 0; j1 = 0; i2 = 3; j2 = 5;
		real = 2; imag = 3;
		m.set(i1, j1, new Complex(real, imag));
		m.setSym(i2, j2, new Complex(real, imag));

		m.clear();
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(0.0, m.get(i, j).real());
				assertEquals(0.0, m.get(i, j).imag());
			}
	}

	/**
	 * Test copying element values from another matrix.
	 */
	public void testCopyFrom() {
		int i, j;
		CMatrix cm2;

		cm2 = new CMatrix(n);
		cm2.copyFrom(cm);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(Double.valueOf(i), cm2.get(i, j).real());
				assertEquals(Double.valueOf(j), cm2.get(i, j).imag());
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
				assertEquals(Double.valueOf(i + i), cm.get(i, j).real());
				assertEquals(Double.valueOf(j + j), cm.get(i, j).imag());
			}
	}

	/**
	 * Test the array representation of a matrix (in column-major order).
	 */
	public void testAsArray() {
		int i, j, k;
		Complex[] array;

		array = cm.asArray();

		k = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(Double.valueOf(j), array[k].real());
				assertEquals(Double.valueOf(i), array[k].imag());
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
			assertEquals(0.0, cm.get(iRow, j).real());
			assertEquals(0.0, cm.get(iRow, j).imag());
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
			assertEquals(0.0, cm.get(i, jCol).real());
			assertEquals(0.0, cm.get(i, jCol).imag());
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
			expect += i;
		expect = expect / n;

		avg = cm.avgDiag();

		assertEquals(expect, avg.real());
		assertEquals(expect, avg.imag());
	}

	/**
	 * Test averaging the upper triangle off diagonal elements of a matrix.
	 */
	public void testAvgOffDiagonal() {
		Complex avg;
		double delta = 1e-04;

		avg = cm.avgOffDiag();

		assertEquals(1.3333, avg.real(), delta);
		assertEquals(3.6666, avg.imag(), delta);
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
				assertEquals(i * constant, cm.get(i, j).real(), delta);
				assertEquals(j * constant, cm.get(i, j).imag(), delta);
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

		assertEquals(18.0, sum.real(), delta);
		assertEquals(18.0, sum.imag(), delta);
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

		assertEquals(-55., b[0].real(), delta);
		assertEquals( 55., b[0].imag(), delta);
		assertEquals(-40., b[1].real(), delta);
		assertEquals( 70., b[1].imag(), delta);
		assertEquals(-25., b[2].real(), delta);
		assertEquals( 85., b[2].imag(), delta);
		assertEquals(-10., b[3].real(), delta);
		assertEquals(100., b[3].imag(), delta);
		assertEquals(  5., b[4].real(), delta);
		assertEquals(115., b[4].imag(), delta);
		assertEquals( 20., b[5].real(), delta);
		assertEquals(130., b[5].imag(), delta);
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

		cm.vMultAccum(b, x);

		assertEquals(-55. + real, b[0].real(), delta);
		assertEquals( 55. + imag, b[0].imag(), delta);
		assertEquals(-40. + real, b[1].real(), delta);
		assertEquals( 70. + imag, b[1].imag(), delta);
		assertEquals(-25. + real, b[2].real(), delta);
		assertEquals( 85. + imag, b[2].imag(), delta);
		assertEquals(-10. + real, b[3].real(), delta);
		assertEquals(100. + imag, b[3].imag(), delta);
		assertEquals(  5. + real, b[4].real(), delta);
		assertEquals(115. + imag, b[4].imag(), delta);
		assertEquals( 20. + real, b[5].real(), delta);
		assertEquals(130. + imag, b[5].imag(), delta);
	}

	/**
	 * Test matrix inversion.
	 */
	public void testInvert() {
//		double delta = 1e-09;

		// avoid singularity
		cm.set(2, 2, new Complex(6, 9));
		cm.set(3, 3, new Complex(2, 4));
		cm.set(1, 2, new Complex(2, 4));

		cm.invert();

//		assertEquals(-8e-01, cm.get(0, 4).getImaginary(), delta);
//		assertEquals(-6.66666667e-01, cm.get(1, 1).getReal(), delta);
//		assertEquals(-4.10256410e-02, cm.get(1, 2).getReal(), delta);
//		assertEquals(7.17948718e-02, cm.get(1, 2).getImaginary(), delta);
	}

	/**
	 * Test inversion error code.
	 */
	public void testGetErrorCode() {
		cm.invert();

//		assertEquals(2, cm.getErrorCode());
	}

	/**
	 * Test Kron reduction.
	 */
	public void testKron() {
		int eRow = 3;

		cm.kron(eRow);

//		fail("Not yet implemented");
	}

}
