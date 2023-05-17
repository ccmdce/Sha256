public class SecureFunction {

	public static void main (String[] args) {
	
		Hash Encrypt = new Hash ();

		String input = "abc";

		System.out.println (Encrypt.run (input));
	}
}

class Hash {

	Develop Station = new Develop ();

	Constant Hold = new Constant ();

	Push Image = new Push ();

	String run (String input) {
	
		initialize ();

		return compress (input);
	}

	private String compress (String input) {

		String hex = "";

		long[][] chain = fill (Station.pattern (input));

		long[] wV = new long[8];
		
		for (int in = 0; in < wV.length; in++) wV[in] = Hold.h[in];
		
		for (int loop = chain.length - 1; loop >= 0; loop--) {
		
			long[] wG = choice (chain, wV, loop);

			wV = sum (wG, wV);
		}

		for (long v : wV) hex += Long.toHexString (v); 

		return hex;
	}

	private long[] choice (long[][] chain, long[] prev, int loop) {

		long[] wV = new long[8];

		for (int in = 0; in < wV.length; in++) wV[in] = prev[in];
				
		for (int v = 0; v < 64; v++) {

			long a = add (Image.sD (wV[4]), Image.cH (wV[4], wV[5], wV[6]));

			long b = add (add (add (a, wV[7]), Hold.k[v]), chain[loop][v]);

			long c = add (Image.sC (wV[0]), Image.mJ (wV[0], wV[1], wV[2]));

			long d = add (b, c);

			wV = adjust (b, d, wV);
		}

		return wV;
	}

	private long[] sum (long[] in, long[] held) {
	
		for (int v = 0; v < in.length; v++) in[v] = add (in[v], held[v]);

		return in;
	}

	private long[] adjust (long p, long chg, long[] cur) {
	
		for (int in = cur.length - 1; in > 0; in--) cur[in] = cur[in - 1];

		cur[0] = chg;

		cur[4] = add (cur[4], p);

		return cur;
	}

	private long[][] fill (long[][] sV) {

		for (int rep = 0; rep < sV.length; rep++) {
		
			int bG = 16;

			while (bG < 64) {

				long a = add (Image.sB (sV[rep][bG - 2]), sV[rep][bG - 7]);

				long b = add (add (a, Image.sA (sV[rep][bG - 15])), sV[rep][bG - 16]);
			
				sV[rep][bG] = b;

				bG++;
			}
		}

		return sV;
	}

	private void initialize () {
	
		Hold.set ();
	}

	private long add (long first, long second) {
	
		return (first + second) % Hold.power;
	}
}

class Develop {

	long[][] pattern (String input) {
	
		return send (input);
	}

	private long[][] send (String input) {
	
		short[] control = padding (input);

		short[][] block = sector (control);

		return map (block);
	}

	private long[][] map (short[][] area) {
	
		long[][] original = new long[area.length][64];

		for (int div = 0; div < area.length; div++) {
		
			for (int line = 0; line < 16; line++) {

				int val = line * 4;
			
				for (int sec = 0; sec < 4; sec++) {
				
					original[div][line] <<= 8;

					original[div][line] |= (long) (area[div][val + sec]);
				}
			}
		}

		return original;
	}

	private short[][] sector (short[] region) {
	
		int length = (int) (region.length * 0.015625);
		
		short[][] section = new short[length][64];

		for (int divide = 0; divide < length; divide++) {
		
			for (int value = 0; value < 64; value++) {
			
				section[divide][value] = region[divide * 64 + value];
			}
		}

		return section;
	}

	private short[] padding (String input) {

		short append = 128;
		
		byte[] hold = input.getBytes ();

		int size = hold.length;

		int padlength = 64 - (((size + 8) + 1) % 64);

		int hashVal = size * 8;

		short[] total = new short[size + padlength + 1 + 8];

		for (int section = 0; section < size; section++) {
			
			total[section] = (short) hold[section];
		}

		total[size] = append;

		short[] ending = intSep (hashVal);

		int end = total.length - ending.length;

		while (end < total.length) {

			total[end] = ending[total.length - end - 1];

			end++;
		}

		return total;
	}

	private short[] intSep (int hashVal) {
		
		int length = 0;

		short[] section = new short[4];

		while (hashVal != 0) {

			section[length] = (short) (hashVal & 255);
			
			hashVal >>= 8;

			length++;
		}

		short[] reduce = new short[length];

		for (int value = 0; value < reduce.length; value++) {
			
			reduce[value] = section[value];
		}

		return reduce;
	}
}

class Push {

	Direction Apply = new Direction ();

	long sA (long value) {
	
		return sigmaA (value);
	}
	
	long sB (long value) {
		
		return sigmaB (value);
	}
	
	long sC (long value) {
		
		return sigmaC (value);
	}
	
	long sD (long value) {
		
		return sigmaD (value);
	}
	
	long mJ (long a, long b, long c) {
		
		return majority (a, b, c);
	}
	
	long cH (long a, long b, long c) {
		
		return choose (a, b, c);
	}

	private long sigmaA (long value) {
	
		long a = Apply.rotateR (value, 7);
		
		long b = Apply.rotateR (value, 18);
		
		long c = Apply.shiftR (value, 3);

		return Apply.exR (Apply.exR (a, b), c);
	}

	private long sigmaB (long value) {
	
		long a = Apply.rotateR (value, 17);
		
		long b = Apply.rotateR (value, 19);
		
		long c = Apply.shiftR (value, 10);

		return Apply.exR (Apply.exR (a, b), c);
	}

	private long sigmaC (long value) {
	
		long a = Apply.rotateR (value, 2);
		
		long b = Apply.rotateR (value, 13);

		long c = Apply.rotateR (value, 22);

		return Apply.exR (Apply.exR (a, b), c);
	}

	private long sigmaD (long value) {
	
		long a = Apply.rotateR (value, 6);
		
		long b = Apply.rotateR (value, 11);
		
		long c = Apply.rotateR (value, 25);

		return Apply.exR (Apply.exR (a, b), c);
	}

	private long majority (long a, long b, long c) {

		//can also be choose ((a ^ b), c, a);
		return (a & b) ^ (a & c) ^ (b & c);
	}
	
	private long choose (long a, long b, long c) {
		
		//can also be (a & b) ^ (~a & c);
		return (a & b) | (~a & c);
	}
}

class Direction {

	long hashLength = 2147483648L;

	long shiftR (long value, int times) {
		
		return shiftRight (value, times);
	}
	
	long rotateR (long value, int times) {
		
		return rotateRight (value, times);
	}
	
	long exR (long first, long second) {
		
		return xor (first, second);
	}

	private long shiftRight (long value, int times) {

		return value >> times;
	}

	private long rotateRight (long value, int times) {
		
		for (int rot = 0; rot < times; rot++) {

			if ((value & 1) == 1) {
			
				value >>= 1;

				value |= hashLength;

			} else {
			
				value >>= 1;
			}
		}

		return value;
	}

	private long xor (long first, long second) {
	
		return first ^ second;
	}
}

class Constant {

	long power = 4294967296L;
	
	long[] prime = new long [64];

	//fractional part square root first 8 primes
	long[] h = new long [8];

	//fractional part cube root first 64 pimes
	long[] k = new long [64];

       	void set () {
		
		find (2, 64, 2, 0);
		
		sqR ();
		
		crT ();
	}

	private void find (int start, int end, int check, int count) {
	
		if (count >= end) return;

		if (start == check) {

			prime[count] = start;
			find (++start, end, 2, ++count);

			return;
		}

		if (start % check != 0) find (start, end, ++check, count);
		else find (++start, end, 2, count);

		return;
	}

	private void sqR () {
	
		for (int level = 0; level < h.length; level++) {
		
			double radical = Math.sqrt (prime [level]);

			h[level] = (long) (power * (radical - (int) radical));
		}
	}

	private void crT () {
	
		for (int level = 0; level < k.length; level++) {
	
			double radical = Math.cbrt (prime [level]);

			k[level] = (long) (power * (radical - (int) radical));
		}
	}
}
