package com.epri.dss.shared.test;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.CMatrix;
import com.epri.dss.shared.impl.CMatrixImpl;

import junit.framework.TestCase;

public class CMatrixTest extends TestCase {

	int n = 6;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test matrix instantiation.
	 */
	public void testCMatrixImpl() {
		CMatrix cm = new CMatrixImpl(n);
		assertNotNull(cm);
	}

	/**
	 * Test matrix order.
	 */
	public void testOrder() {
		CMatrix cm = new CMatrixImpl(n);
		assertEquals(n, cm.order());
	}

	/**
	 * Test getting matrix elements.
	 */
	public void testGetElement() {
		CMatrix cm = new CMatrixImpl(n);
		assertEquals(0, cm.getElement(0, 0).getReal());
		assertEquals(0, cm.getElement(0, 0).getImaginary());
	}

	/**
	 * Test setting matrix elements.
	 */
	public void testSetElement() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 0; j = 0;
		real = 2; imag = 3;
		cm.setElement(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());

		i = 1; j = 2;
		real = 3; imag = 4;
		cm.setElement(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());
	}

	/**
	 * Test setting symmetrical matrix elements.
	 */
	public void testSetElemSym() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 1; j = 3;
		real = 2; imag = 5;
		cm.setElemSym(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());
		assertEquals(real, cm.getElement(j, i).getReal());
		assertEquals(imag, cm.getElement(j, i).getImaginary());
	}

	/**
	 * Test adding to a matrix element.
	 */
	public void testAddElement() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 3; j = 2;
		real = 2; imag = 3;
		cm.addElement(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());

		cm.addElement(i, j, new Complex(real, imag));
		assertEquals(real + real, cm.getElement(i, j).getReal());
		assertEquals(imag + imag, cm.getElement(i, j).getImaginary());
	}

	/**
	 * Test adding to symmetrical matrix elements.
	 */
	public void testAddElemSym() {
		int i, j;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i = 4; j = 1;
		real = 1; imag = 3;
		cm.addElemSym(i, j, new Complex(real, imag));
		assertEquals(real, cm.getElement(i, j).getReal());
		assertEquals(imag, cm.getElement(i, j).getImaginary());
		assertEquals(real, cm.getElement(j, i).getReal());
		assertEquals(imag, cm.getElement(j, i).getImaginary());

		cm.addElemSym(i, j, new Complex(real, imag));
		assertEquals(real + real, cm.getElement(i, j).getReal());
		assertEquals(imag + imag, cm.getElement(i, j).getImaginary());
		assertEquals(real + real, cm.getElement(j, i).getReal());
		assertEquals(imag + imag, cm.getElement(j, i).getImaginary());
	}

	/**
	 * Test zeroing all matrix elements.
	 */
	public void testClear() {
		int i, j, i1, j1, i2, j2;
		double real, imag;
		CMatrix cm = new CMatrixImpl(n);

		i1 = 0; j1 = 0; i2 = 3; j2 = 5;
		real = 2; imag = 3;
		cm.setElement(i1, j1, new Complex(real, imag));
		cm.setElemSym(i2, j2, new Complex(real, imag));

		cm.clear();
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(0, cm.getElement(i, j).getReal());
				assertEquals(0, cm.getElement(i, j).getImaginary());
			}
	}

	/**
	 * Test copying element values from another matrix.
	 */
	public void testCopyFrom() {
		int i, j;
		CMatrix cm1, cm2;

		cm1 = createTestMatrix();
		cm2 = new CMatrixImpl(n);
		cm2.copyFrom(cm1);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(i, cm2.getElement(i, j).getReal());
				assertEquals(j, cm2.getElement(i, j).getImaginary());
			}
	}

	/**
	 * Test adding element values from another matrix.
	 */
	public void testAddFrom() {
		int i, j;
		CMatrix cm;

		cm = createTestMatrix();
		cm.addFrom(cm);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(i + i, cm.getElement(i, j).getReal());
				assertEquals(j + j, cm.getElement(i, j).getImaginary());
			}
	}

	/**
	 * Test the array representation of a matrix.
	 */
	public void testAsArray() {
		int i, j, k;
		Complex[] array;
		CMatrix cm;

		cm = createTestMatrix();
		array = cm.asArray();

		k = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(i, array[k].getReal());
				assertEquals(j, array[k].getImaginary());
				k += 1;
			}
	}

	/**
	 * Test zeroing out a single matrix row.
	 */
	public void testZeroRow() {
		int iRow, j;
		CMatrix cm;

		iRow = 2;
		cm = createTestMatrix();
		cm.zeroRow(iRow);
		for (j = 0; j < cm.order(); j++) {
			assertEquals(0, cm.getElement(iRow, j).getReal());
			assertEquals(0, cm.getElement(iRow, j).getImaginary());
		}
	}

	/**
	 * Test zeroing out a single matrix column.
	 */
	public void testZeroCol() {
		int i, jCol;
		CMatrix cm;

		jCol = 3;
		cm = createTestMatrix();
		cm.zeroCol(jCol);
		for (i = 0; i < cm.order(); i++) {
			assertEquals(0, cm.getElement(i, jCol).getReal());
			assertEquals(0, cm.getElement(i, jCol).getImaginary());
		}
	}

	/**
	 * Test averaging the diagonal elements of a matrix.
	 */
	public void testAvgDiagonal() {
		double expect;
		Complex avg;
		CMatrix cm;

		expect = 0;
		for (int i = 0; i < n; i++)
			expect += n;
		expect = expect / n;

		cm = createTestMatrix();
		avg = cm.avgDiagonal();

		assertEquals(expect, avg.getReal());
		assertEquals(expect, avg.getImaginary());
	}

	/**
	 * Test averaging the upper triangle off diagonal elements of a matrix.
	 */
	public void testAvgOffDiagonal() {
		Complex avg;
		CMatrix cm;

		cm = createTestMatrix();
		avg = cm.avgOffDiagonal();
	}

	/**
	 * Test multiplication of all elements by a constant.
	 */
	public void testMultByConst() {
		fail("Not yet implemented");
	}

	/**
	 * Test summation of all elements in a matrix block.
	 */
	public void testSumBlock() {
		fail("Not yet implemented");
	}

	/**
	 * Test matrix-vector multiplication.
	 */
	public void testVMult() {
		fail("Not yet implemented");
	}

	/**
	 * Test matrix-vector multiplication with result accumulation.
	 */
	public void testVMultAccum() {
		fail("Not yet implemented");
	}

	/**
	 * Test matrix inversion.
	 */
	public void testInvert() {
		fail("Not yet implemented");
	}

	/**
	 * Test inversion error code.
	 */
	public void testGetErrorCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test Kron reduction.
	 */
	public void testKron() {
		fail("Not yet implemented");
	}

	/**
	 * @return an n x n test matrix
	 */
	private CMatrix createTestMatrix() {
		int i, j;
		CMatrix cm = new CMatrixImpl(n);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				cm.setElement(i, i, new Complex(i, j));
			}

		return cm;
	}

}
