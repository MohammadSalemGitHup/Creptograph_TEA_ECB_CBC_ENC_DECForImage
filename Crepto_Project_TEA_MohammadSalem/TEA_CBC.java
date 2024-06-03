public class TEA_CBC {
	
	protected static int delta = 0x9e3779b9;
	protected static int ROUNDS = 32;
	protected int sum;
	protected int[] key;
	
	public TEA_CBC(int[] newKey){
		this.key = new int[4];
		key[0] = newKey[0];
		key[1] = newKey[1];
		key[2] = newKey[2];
		key[3] = newKey[3];

	}
	
	public int[] encrypt(int[] p, int[] prev){
		
		int c[] = new int[2];
		int L = p[0] ^ prev[0];
		int R = p[1] ^ prev[1];
		
		sum = 0;

		for(int i=0; i<32;i++){
			sum += delta;
			L += ((R << 4) + key[0]) ^ (R+sum) ^ ((R >> 5) + key[1]);
			R += ((L << 4) + key[2]) ^ (L+sum) ^ ((L >> 5) + key[3]);

		}
		
		c[0] = L;
		c[1] = R;

		return c;

	}
	
	public int[] decrypt(int[] c, int[] prev){
		
		int P[] = new int[2];
		int L = c[0];
		int R = c[1];

		sum = delta << 5;

		for(int i=0; i<32;i++){
			R -= ((L << 4) + key[2]) ^ (L+sum) ^ ((L >> 5) + key[3]);
			L -= ((R << 4) + key[0]) ^ (R+sum) ^ ((R >> 5) + key[1]);
			sum -= delta;
		}
		
		P[0] = L ^ prev[0];
		P[1] = R ^ prev[1];

		return P;

	}

}



