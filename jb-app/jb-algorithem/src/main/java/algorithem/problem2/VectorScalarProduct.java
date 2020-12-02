package algorithem.problem2;
//Consider the past, and you shall know the future

/**
 * Identify which is the most convenient data structure to implement a Vector of integers and code
 * it.
 * <p>
 * Once you have that Vector class ready implement the scalar product of two vectors. The scalar
 * product is an algebraic operation that takes two equal-length sequences of numbers (usually
 * coordinate vectors) and returns a single number. This operation can be defined either
 * algebraically or geometrically. Algebraically, it is the sum of the products of the
 * corresponding entries of the two sequences of numbers. For example
 * <p>
 * [1,2,3] * [1,3,2] = 1*1 + 2*3 + 3*2 = 7
 * [1,1] * [2,10] = 12
 */
public class VectorScalarProduct
{

	/**
	 * To resolve this problem we are going to take into account two main requirements: vectors can't
	 * be null and the number of elements for vector have to be the same. Once we have added this
	 * guards throwing execptions we are going to implement this algorithm.
	 * <p>
	 * To implement this scalar product we are going to iterate over v1 and v2 at the same time using
	 * a pointer defined with an integer named "i". For every iteration we are going to accumulate
	 * the product of elements at position "i" for v1 and v2 inside an auxiliary variable named
	 * result.
	 * <p>
	 * The complexity order of this algorithm in time terms is O(N) where N is the number of elements
	 * per vector. In space terms, the complexity order of this algorithm is O(1) because the
	 * auxiliary data structures used to implement this algorithm are not related to any problem
	 * parameter.
	 */
	public int calculateScalarProduct(Vector v1, Vector v2)
	{
		validateInput(v1, v2);

		int v1Size = v1.size();
		int v2Size = v2.size();
		if (v1Size != v2Size)
		{
			throw new IllegalArgumentException("Vectors should be contains the same number of elements.");
		}

		int result = 0;
		for (int i = 0; i < v1Size; i++)
		{
			result += v1.getAt(i) * v2.getAt(i);
		}
		return result;
	}

	private void validateInput(Vector v1, Vector v2)
	{
		if (v1 == null || v2 == null)
		{
			throw new IllegalArgumentException("This method doesn't accept null vectors");
		}
	}
}
