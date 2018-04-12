	interface I1 {
		void methodI1(); //public static by default
	}

	interface I2 extends I1 {
		void methodI2(); // public static by default
	}

	class A1 {
		public String methodA1() {
			String strA1 = "I am in methodC1 of class A1";
			return strA1;
		}
		public String toString() {
			return "toString() method of class A1";
		}
	}

	class B1 extends A1 implements I2 {
	
	public void methodI1(){
		System.out.println("I am in methodI1 of class B1");
	}
	public void methodI2(){
		System.out.println("I am in methodI2 of class B1");
	}

	public static void main(String args[]){
		B1 obj = new B1();
		obj.methodI2();
		obj.methodI2();
		System.out.println(obj.methodA1());
		System.out.println(obj.toString());
		}
	}