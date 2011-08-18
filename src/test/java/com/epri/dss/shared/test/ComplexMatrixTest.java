package com.epri.dss.shared.test;

import org.apache.commons.math.complex.Complex;

import com.epri.dss.shared.ComplexMatrix;
import com.epri.dss.shared.impl.ComplexMatrixImpl;

import junit.framework.TestCase;

public class ComplexMatrixTest extends TestCase {

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
		ComplexMatrix cm = new ComplexMatrixImpl(n);
		assertNotNull(cm);
	}

	/**
	 * Test matrix order.
	 */
	public void testOrder() {
		ComplexMatrix cm = new ComplexMatrixImpl(n);
		assertEquals(n, cm.order());
	}

	/**
	 * Test getting matrix elements.
	 */
	public void testGetElement() {
		ComplexMatrix cm = new ComplexMatrixImpl(n);
		assertEquals(0, cm.get(0, 0).getReal());
		assertEquals(0, cm.get(0, 0).getImaginary());
	}

	/**
	 * Test setting matrix elements.
	 */
	public void testSetElement() {
		int i, j;
		double real, imag;
		ComplexMatrix cm = new ComplexMatrixImpl(n);

		i = 0; j = 0;
		real = 2; imag = 3;
		cm.set(i, j, new Complex(real, imag));
		assertEquals(real, cm.get(i, j).getReal());
		assertEquals(imag, cm.get(i, j).getImaginary());

		i = 1; j = 2;
		real = 3; imag = 4;
		cm.set(i, j, new Complex(real, imag));
		assertEquals(real, cm.get(i, j).getReal());
		assertEquals(imag, cm.get(i, j).getImaginary());
	}

	/**
	 * Test setting symmetrical matrix elements.
	 */
	public void testSetElemSym() {
		int i, j;
		double real, imag;
		ComplexMatrix cm = new ComplexMatrixImpl(n);

		i = 1; j = 3;
		real = 2; imag = 5;
		cm.setSym(i, j, new Complex(real, imag));
		assertEquals(real, cm.get(i, j).getReal());
		assertEquals(imag, cm.get(i, j).getImaginary());
		assertEquals(real, cm.get(j, i).getReal());
		assertEquals(imag, cm.get(j, i).getImaginary());
	}

	/**
	 * Test adding to a matrix element.
	 */
	public void testAddElement() {
		int i, j;
		double real, imag;
		ComplexMatrix cm = new ComplexMatrixImpl(n);

		i = 3; j = 2;
		real = 2; imag = 3;
		cm.add(i, j, new Complex(real, imag));
		assertEquals(real, cm.get(i, j).getReal());
		assertEquals(imag, cm.get(i, j).getImaginary());

		cm.add(i, j, new Complex(real, imag));
		assertEquals(real + real, cm.get(i, j).getReal());
		assertEquals(imag + imag, cm.get(i, j).getImaginary());
	}

	/**
	 * Test adding to symmetrical matrix elements.
	 */
	public void testAddElemSym() {
		int i, j;
		double real, imag;
		ComplexMatrix cm = new ComplexMatrixImpl(n);

		i = 4; j = 1;
		real = 1; imag = 3;
		cm.addSym(i, j, new Complex(real, imag));
		assertEquals(real, cm.get(i, j).getReal());
		assertEquals(imag, cm.get(i, j).getImaginary());
		assertEquals(real, cm.get(j, i).getReal());
		assertEquals(imag, cm.get(j, i).getImaginary());

		cm.addSym(i, j, new Complex(real, imag));
		assertEquals(real + real, cm.get(i, j).getReal());
		assertEquals(imag + imag, cm.get(i, j).getImaginary());
		assertEquals(real + real, cm.get(j, i).getReal());
		assertEquals(imag + imag, cm.get(j, i).getImaginary());
	}

	/**
	 * Test zeroing all matrix elements.
	 */
	public void testClear() {
		int i, j, i1, j1, i2, j2;
		double real, imag;
		ComplexMatrix cm = new ComplexMatrixImpl(n);

		i1 = 0; j1 = 0; i2 = 3; j2 = 5;
		real = 2; imag = 3;
		cm.set(i1, j1, new Complex(real, imag));
		cm.setSym(i2, j2, new Complex(real, imag));

		cm.clear();
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(0, cm.get(i, j).getReal());
				assertEquals(0, cm.get(i, j).getImaginary());
			}
	}

	/**
	 * Test copying element values from another matrix.
	 */
	public void testCopyFrom() {
		int i, j;
		ComplexMatrix cm1, cm2;

		cm1 = createTestMatrix();
		cm2 = new ComplexMatrixImpl(n);
		cm2.copyFrom(cm1);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(i, cm2.get(i, j).getReal());
				assertEquals(j, cm2.get(i, j).getImaginary());
			}
	}

	/**
	 * Test adding element values from another matrix.
	 */
	public void testAddFrom() {
		int i, j;
		ComplexMatrix cm;

		cm = createTestMatrix();
		cm.addFrom(cm);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				assertEquals(i + i, cm.get(i, j).getReal());
				assertEquals(j + j, cm.get(i, j).getImaginary());
			}
	}

	/**
	 * Test the array representation of a matrix.
	 */
	public void testAsArray() {
		int i, j, k;
		Complex[] array;
		ComplexMatrix cm;

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
		ComplexMatrix cm;

		iRow = 2;
		cm = createTestMatrix();
		cm.zeroRow(iRow);
		for (j = 0; j < cm.order(); j++) {
			assertEquals(0, cm.get(iRow, j).getReal());
			assertEquals(0, cm.get(iRow, j).getImaginary());
		}
	}

	/**
	 * Test zeroing out a single matrix column.
	 */
	public void testZeroCol() {
		int i, jCol;
		ComplexMatrix cm;

		jCol = 3;
		cm = createTestMatrix();
		cm.zeroCol(jCol);
		for (i = 0; i < cm.order(); i++) {
			assertEquals(0, cm.get(i, jCol).getReal());
			assertEquals(0, cm.get(i, jCol).getImaginary());
		}
	}

	/**
	 * Test averaging the diagonal elements of a matrix.
	 */
	public void testAvgDiagonal() {
		double expect;
		Complex avg;
		ComplexMatrix cm;

		expect = 0;
		for (int i = 0; i < n; i++)
			expect += n;
		expect = expect / n;

		cm = createTestMatrix();
		avg = cm.avgDiag();

		assertEquals(expect, avg.getReal());
		assertEquals(expect, avg.getImaginary());
	}

	/**
	 * Test averaging the upper triangle off diagonal elements of a matrix.
	 */
	public void testAvgOffDiagonal() {
		Complex avg;
		ComplexMatrix cm;

		cm = createTestMatrix();
		avg = cm.avgOffDiag();
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
	private ComplexMatrix createTestMatrix() {
		int i, j;
		ComplexMatrix cm = new ComplexMatrixImpl(n);

		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) {
				cm.set(i, i, new Complex(i, j));
			}

		return cm;
	}

}
