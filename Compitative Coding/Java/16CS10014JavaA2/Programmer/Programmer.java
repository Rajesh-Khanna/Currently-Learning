public class Programmer extends Employee{
	private String lang;
	public Programmer(String n,int s,String l){
		super(n,s);
		this.setLang(l);
	}
	public String getLang(){
		return this.lang;
	}
	public void setLang( String s){
		this.lang = s;
	}
	public static void main(String args[]){
		Employee e = new Employee("ABC",100);
		Programmer p = new Programmer("DEF",200,"Java");
		System.out.println("Name of the Employee: "+e.getName());
		System.out.println("Salary of the Employee: "+e.getSalary());
		System.out.println("Name of the Programmer: "+p.getName());
		System.out.println("Salary of the Programmer: "+p.getSalary());
		System.out.println("Language of the Programmer: "+p.getLang());
		p.setSalary(100);
		System.out.println("Updated Salary of the Programmer: "+p.getSalary());
	}
}
class Employee{
	private String name;
	private int salary;

	public Employee(String n,int s){
		this.name = n;
		this.salary = s;
	}
	public String getName(){
		return this.name;
	}
	public int getSalary(){
		return this.salary;
	}
	public void setSalary(int s){
		this.salary = s;
	}
}
