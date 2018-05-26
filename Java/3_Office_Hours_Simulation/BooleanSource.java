/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 4
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_4;

public class BooleanSource {
	
	private double probability;
	
	/**
	 * [Constructor that initialize the probability 
	 * to the given parameter.]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [0 < initProbability <= 1.]
	 * @param initProbability
	 *     [double: Given probability to initialize constructor.]
	 * @throws IllegalArgumentException
	 *     [If initProbability is <= 0 or > 1]
	 */
	
	public BooleanSource(double initProbability) throws IllegalArgumentException {
		
		if(initProbability <= 0 || initProbability > 1)
			throw new IllegalArgumentException();
		
		this.probability = initProbability;
		
	}
	
	/**
	 * [Returns true with the probability indicated by the
	 * member variable probability; utilizing Math.random().]
	 *
	 * <dt><b>Preconditions:</b><dd>
	 *     [Probability is a valid probability 0 < initProbability <= 1.]
	 * @return
	 *     [boolean: true if event occurred, false if not.]
	 */
	
	public boolean occurs() {
		
		return Math.random() < probability;
		
	}

}
