import java.lang.reflect.Constructor;

/**
 * Example of a framework for testing multiple implementations of the
 * ListADT for TEAM_TestListImplementations assignment.
 * 
 * @version 0.0
 * @author deppeler
 *
 */
public class Test_ListImplementations {

	/**
	 * Run all tests for each list submission available.
	 * 
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		String [] ta_list = { "Deb", "Mingi", "Yash", "Sonu", 
						"Sapan", "Tianrun", "Roshan" };
		for ( String ta_name : ta_list ) { 
			String listClassName = "List_"+ta_name;
			System.out.println("\n==================== TESTING "+listClassName );
			runTestsFor(listClassName);
			System.out.println("==================== done");
		}
		
		
	}

	/**
	 * Constructs a list of the correct type based on the name provided.
	 * This method assumes there is a class with the name List_taName
	 * that implements the ListADT<T> type given.
	 * 
	 * For example: if taName is Deb, then it constructs an instance of
	 * a generic class with the following structure.
	 * 
	 *  <pre>public class List_Deb<T> implements ListADT<T> { ... }</pre>
	 * 
	 * @param listClassName The name "List_Deb" or other list type name.
	 */
	private static void runTestsFor(String listClassName) {
		try {
			ListADT<String> list = constructListOfString(listClassName);
			runTestsOn(listClassName,list);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to construct "+listClassName+" NO TESTS RUN");
		}
	}

	/**
	 * Constructs an instance of the list type that will contains String data.
	 * The class name is given and it is assumed to be a generic type with
	 * a no-arg constructor. If the List of Strings can not be constructed
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param listname List_Deb or other that matches existing list type
	 * @return a ListADT<String> constructed with specified class name.
	 * 
	 * @throws IllegalArgumentException if any of several types of exceptions 
	 * occur when attempting to construct a ListADT<String> object from 
	 * the given class name. 
	 */
	private static ListADT<String> constructListOfString(String listname) {
		try {
			Class<?> listClassName = Class.forName(listname);
			Constructor<?> ctr = listClassName.getDeclaredConstructor();
			Object [] initargs = new Object[] { };

			// CAUTION: Type cast warnings are suppressed here.
			// to constructing Lists that will contain String data items.
			@SuppressWarnings("unchecked")  
			ListADT<String> list = ((ListADT<String>) ctr.newInstance(initargs));
			return list;
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to construct instance of "+listname+"<String>()");
		}
	}

	/**
	 * Runs all tests on this list.  Note, may have to create new lists 
	 * 
	 * to be sure that we start with a new empty list instance.  
	 * Otherwise, errors from one test may affect the next test.
	 * 
	 * @param className List_Deb or other list class name
	 * @param list 
	 */
	private static void runTestsOn(String className,ListADT<String> list) {

		if ( ! list.isEmpty() ) { 
			System.out.println("test00 failed: "+className+" should be empty at start. ");
		}

		if ( list.size() != 0 ) { 
			System.out.println("test00 failed: "+className+" size() should be 0 at start, but size()="+ list.size());
		}

		// TODO: name and write additional tests to run on each list.
//		test01_List_Constructor(className, list);
		test01_add_One_Item(className,list);
		test02_add_At_Negative_Pos(className, list);
		test03_add_At_Positive_Index_Out_Of_Bound(className, list);
		test04_add_A_Null_Item_At_A_Pos(className, list);
		test05_add_Single_Item_To_Pos_Zero_Of_Empty_List(className, list);
		test06_add_Items_At_Front_Rear_And_In_Between(className, list);
		test07_add_Multiple_Items(className, list);
	}
	

	 private static void failMsg(String msg, String expected, String actual) {
	        System.out.println("FAILED " + msg);
	        System.out.println("    expected: " + expected);
	        System.out.println("      actual: " + actual);
	    }

	/** 
	 * A sample test method.
	 * @param list
	 */
	private static void test01_add_One_Item(String className, ListADT<String> list) {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName(); // unnecessary line I believe
		list = constructListOfString(className);
		
		try{
			list.add("1");
			
			int expectedSize = 1;
			int actualSize = list.size();
			
			if (actualSize != expectedSize){
				failMsg(name+"_size() for "+ta_name,""+expectedSize,""+actualSize);
			}
			
			System.out.println(name + " for " + ta_name + " passed");
	
		}catch(Exception e){
			failMsg(name+ " add(\"1\") for "+ ta_name, "No exception", ""+e);
			
		}
		
		try{
			String getItem = list.get(0);
			String expItem = "1";
			
			if(getItem != expItem){
				failMsg(name + " for " + ta_name, "1", "" + getItem);
			}
		}catch(Exception e){
			failMsg(name+ "_get(0)", "1", ""+e);
			return;
		}
		
	
	}
	 
	 private static void test02_add_At_Negative_Pos(String className, ListADT<String> list) {
			list = constructListOfString(className);
			String name = new Object(){}.getClass().getEnclosingMethod().getName();
			String ta_name = list.getClass().getName();
			
			
			try {
				list.add(-1,"a");
				failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
			}
			
			catch (IndexOutOfBoundsException e) {
				System.out.println(name + " for " + ta_name + " passed");
			}
			
			catch (Exception e) {
				failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
			}
		}
	
	// Note from Hongyi: for test 03-06, I did not check the size of the list because I think the size may better to be tested
	// separately. 
	// Also test06 will cause an infinite loop for Saipan.
	private static void test03_add_At_Positive_Index_Out_Of_Bound(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			list.add(1,"a");
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
		}
		
		catch (IndexOutOfBoundsException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
		}
	}
	
	private static void test04_add_A_Null_Item_At_A_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		try {
			String item = null;
			list.add(0,item);
			failMsg(name+" for "+ta_name,"IllegalArgumentException","Not throwing an exception");
		}
		
		catch (IllegalArgumentException e) {
			System.out.println(name + " for " + ta_name + " passed");
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,"IllegalArgumentException", ""+e);
		}
	}
	
	private static void test05_add_Single_Item_To_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		String expItem = "1";
		try {
			list.add(0,"1");
			String getItem = list.get(0);
			
			if (!getItem.equals(expItem)) {
				failMsg(name+" for "+ta_name,expItem, ""+getItem);
			}
			
			else {
				System.out.println(name + " for " + ta_name + " passed");
			}
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,expItem, ""+e);
		}
	}
	
	private static void test06_add_Items_At_Front_Rear_And_In_Between(String className, ListADT<String> list) {
		// It seems that this method will cause an infinite loop for Sapan.
		// So we will manually break out of that.
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		String expItem = "2314";
		try {
			list.add(0,"1");
			list.add(0,"2");
			list.add(1,"3");
			list.add(3,"4");
			String getItem = "" + list.get(0) + list.get(1) + list.get(2) + list.get(3);
			
			if (!getItem.equals(expItem)) {
				failMsg(name+" for "+ta_name,expItem, ""+getItem);
			}
			
			else {
				System.out.println(name + " for " + ta_name + " passed");
			}
		}
		
		catch (Exception e) {
			failMsg(name+" for "+ta_name,expItem, ""+e);
		}
	}
	
	 private static void test07_add_Multiple_Items(String className, ListADT<String> list){
		 String name = new Object(){}.getClass().getEnclosingMethod().getName();
		 String ta_name = list.getClass().getName();
		 
		 int numItems = 50; //
		 for (int i=0; i<numItems;i++){
			 list.add(""+i);
		 }
		 
		 
	 }
	
}
