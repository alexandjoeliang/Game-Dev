package Software;
import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
	private String name; //name of the category
	public ArrayList<Subcategory> subcategories = new ArrayList<Subcategory>();
	
	public Category(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public double spending(String category_name) { // sums all transactions in the category 
		for (Subcategory sc: this.getSubcategories()) {
			if (sc.getName().equals(category_name)) {
				return sc.get_money_out()	;		
			}
		}
		System.out.println("no matching category found. make sure you spelled it correctly!");
		return 0;
	}
	
	public void add_subcategory(Subcategory sc) {
		this.subcategories.add(sc);
	}

	
	public void print() { 
		System.out.println(this.name);
		for (Subcategory sc: this.getSubcategories()) {
			System.out.print("\t");
			sc.print();
		}
	}

	public ArrayList<Subcategory> getSubcategories() {
		return subcategories;
	}
	
	public Integer _find_subcategory_idx(String name) {
		if (this.subcategories.size() != 0) {
			Integer i = 0;
			for (Subcategory c: this.subcategories) {
				if (c.getName().equals(name)) {
					return i;
				}
				i = i +1;
			}
			
			System.out.println("no matching catories found. check spelling or create a new subcategory " + name);
			return -1;
		}
		return 0;
		
	}
	protected Subcategory _find_subcategory(String name) {
		Subcategory sub = null;
		if (this.subcategories.size() != 0) {
			for (Subcategory sc: this.subcategories) {
				if (sc.getName().equals(name)) {
					sub = sc;
				}
				
			}
			
			//System.out.println("no matching catories found. check spelling or create a new subcategory " + name);
			//return sub;
		}//end empty categories list case
		return sub;
		
	}
	
	//resets all subcategories
	public void reset() {
		for (Subcategory sc: this.subcategories) {
			sc.reset();
		}
	}
	public void delete_subcategory(String sc) {
		int idx = _find_subcategory_idx( sc);
		this.subcategories.remove(idx);
	}
	
}
