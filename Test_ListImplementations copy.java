import java.lang.reflect.Constructor;

/**
 * Tests multiple implementations of the
 * ListADT for TEAM_TestListImplementations assignment.
 * 
 * 
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
		test01_add_Get_One_Item(className,list);
		test02_add_At_Negative_Pos(className, list);
		test03_add_A_Null_Item_At_End(className, list);
		test04_add_A_Null_Item_At_A_Pos(className, list);
		test05_add_Single_Item_To_Pos_Zero_Of_Empty_List(className, list);
//		test06_add_Items_At_Front_Rear_And_In_Between(className, list);
		test07_add_Multiple_Items(className, list);
		test08_add_At_Pos_size(className, list);
		test09_add_At_Positive_Index_Out_Of_Bound(className, list);
		test10_List_remove(className, list);
		test11_List_Contains(className,list);
		test12_List_isEmpty(className, list);
		test12_List_isEmpty(className, list);
		test13_get_At_Negative_Pos(className, list);
		test14_get_At_Pos_Zero_Of_Empty_List(className, list);
		test15_remove_At_Negative_Pos(className, list);
		test16_remove_At_Pos_Zero_Of_Empty_List(className, list);
		test17_remove_At_Size(className, list);
		test18_List_Iterator(className, list);
		
	}
	
	/** Prints failure message from test methods.
	 * 
	 * @param msg 
	 * @param expected
	 * @param actual
	 */

	 private static void failMsg(String msg, String expected, String actual) {
	        System.out.println("FAILED " + msg);
	        System.out.println("    expected: " + expected);
	        System.out.println("      actual: " + actual);
	    }

	/** This test adds one item "1" and then uses get(0) to access the item added.
	 * 
	 * @param list
	 * @param className
	 */
	private static void test01_add_Get_One_Item(String className, ListADT<String> list) {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName(); 
		list = constructListOfString(className);
		boolean failure = false;
		
		String expItem = "1";
		try{
			list.add("1");
			
			String getItem = list.get(0);
			
			
			if(getItem != expItem){
				failMsg(name + " for " + ta_name, "1", "" + getItem);
				failure = true;
			}
			
			if (!failure){
				System.out.println(name + " for " + ta_name + " passed");	
			}
			
			
		}catch(Exception e){
			failMsg(name+ " for "+ ta_name, expItem, ""+e);
			
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
	
	 	/**
		 * 
		 * @param className
		 * @param list
		 */
	 private static void test03_add_A_Null_Item_At_End(String className, ListADT<String> list) {
 		list = constructListOfString(className);
 		String name = new Object(){}.getClass().getEnclosingMethod().getName();
 		String ta_name = list.getClass().getName();
 		
 		
 		// should we include list.add(null) general check?
 		try {
 			String item = null;
 			list.add(item);
 			failMsg(name+" for "+ta_name,"IllegalArgumentException","Not throwing an exception");
 		}
 		
 		catch (IllegalArgumentException e) {
 			System.out.println(name + " for " + ta_name + " passed");
 		}
 		
 		catch (Exception e) {
 			failMsg(name+" for "+ta_name,"IllegalArgumentException", ""+e);
 		}
 	
 	}
	
	private static void test04_add_A_Null_Item_At_A_Pos(String className, ListADT<String> list) {
		list = constructListOfString(className);
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		String ta_name = list.getClass().getName();
		
		
		// should we include list.add(null) general check?
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
			list.add(0,"2"); // add to front
			list.add(1,"3"); // add in between
			list.add(3,"4"); // add to rear
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
	
	/** This test adds many items to a list and checks that they are all added correctly. 
	 * This should force the list to expand if the implementation uses an array. 
	 * 
	 * @param className
	 * @param list
	 */
	 private static void test07_add_Multiple_Items(String className, ListADT<String> list){
		 String name = new Object(){}.getClass().getEnclosingMethod().getName();
		 String ta_name = list.getClass().getName();
		 
		 boolean failure = false;
		 
		 list = constructListOfString(className);
		 int numItems = 101; //
		 int i=0;
		 try{
			 
			 for (i=0; i<=numItems;i++){
				 list.add(""+i); 
				 
				 if (!list.get(i).equals(""+i)){
					 failMsg(name + "for list.get(" + i + ").equals(\"\"+" + i  + ") for " + ta_name, "true", "false");
					 failure = true;
				 }
		 
			 } 
			 
			 if (!failure){
					System.out.println(name + " for " + ta_name + " passed");	
			 }
			 
		 } catch (Exception e){
			failMsg(name + "_add("+ i + ") for " + ta_name, "No exception", ""+e);
		 }
		 
		 
	 }
	 
	 /** This test adds an item using add(list.size(), item) and checks that the item was 
	  * correctly added to the list.
	  *  
	  * @param className
	  * @param list
	  */
	 	public static void test08_add_At_Pos_size(String className, ListADT<String> list){
	 		 String name = new Object(){}.getClass().getEnclosingMethod().getName();
			 String ta_name = list.getClass().getName();
			 list = constructListOfString(className);
			 boolean failure = false;
			 
			 try{
				 list.add("1");
				 list.add(list.size(), "2"); 
				 
				 if (!list.get(1).equals("2")){
					 failMsg(name + "_add(list.size(), \"2\") for " + ta_name, "true" , "false");
					 failure = true;
				 }
				 
				 if (!failure){
						System.out.println(name + " for " + ta_name + " passed");	
				 }
				 
			 } catch(Exception e){
				 failMsg(name + "_add(list.size(), \"2\") for " + ta_name, "No exception", ""+e);
			 }
			
	 	}
	 
	 
    	private static void test09_add_At_Positive_Index_Out_Of_Bound(String className, ListADT<String> list) {
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
        
    
	    /**
	       * test add("item") and then remove() on a non-empty list
	         */
	        public static void test10_List_remove(String className, ListADT<String> list) {
	        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	            list = constructListOfString(className);
	            
	            boolean failure = false;
	            int initialSize = list.size();
	            try{
		            // add "item", then "remove"
		            String expected = "item";
		            list.add(expected);
		            String actual = list.remove(0);
		            if (!expected.equals(actual)) {
		            	System.out.println("Testing remove method failed1");
		                failMsg(name,""+expected,""+actual);
		                failure = true;
		            }
		            // check that size is back to 0
		            expected = ""+initialSize;
		            actual = list.size()+"";
		            if (!expected.equals(actual)) {
		            	System.out.println("Testing remove method failed2");
		                failMsg(name+" size()",""+expected,""+actual);
		                failure = true;
		    
		            }
		            
		            if (!failure){
						System.out.println(name + " for " + ta_name + " passed");	
		            }
		            
	            }catch(Exception e)
	            {
	            	failMsg(name + " for " + ta_name, "No exception", ""+e);
	           }
	        }
	
	       
	        
	     
	    	
	    	 /**
	         * creates empty List and adds multiple items
	         * and checks that size is one
	         */
	        public static void test11_List_Contains(String className, ListADT<String> list) {
	        	String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		list = constructListOfString(className);
	            int numItems = 101; // number of items to add and check size
	            
	            boolean failure = false;
	            
	            try{
	           
		            for ( int i=0; i < numItems; i++ ) {
		                String s = "item_"+i;
		                list.add(s);
		                boolean expected = true;
		                boolean actual = list.contains(s);
		                if ( expected != actual ) {
		                    failMsg(name+ "for ContainsItem_"+s,""+expected,""+actual);
		                    failure = true;
		                }
	               
	            } 
	            
	            if (!failure){
	    			System.out.println(name + " for " + ta_name + " passed");	
	            }
	            
	            } catch(Exception e)
	            {
	            	failMsg(name+ "for " + ta_name, "No exception", ""+e);
	            }
	            
	        }
	        
	        public static void test12_List_isEmpty(String className, ListADT<String> list) {
	        			String name = className;
	        	        ListADT<String> ta_list = constructListOfString(className);
	        	         
	        	        String ta_name = ta_list.getClass().getName();
	        	        if (! ta_list.isEmpty()) {
	        	        	System.out.println("Testing isEmpty method failed");
	        	            failMsg(name+"_isEmpty for "+ta_name,""+true,""+false);
	        	        }
	        	        int expected = 0;
	        	        int actual = ta_list.size();
	        	        if (actual != expected) {
	        	            failMsg(name+"_size for "+ta_name,""+expected,""+actual);
	        	        }
	        	    }
	        
	        private static void test13_get_At_Negative_Pos(String className, ListADT<String> list) {
	    		list = constructListOfString(className);
	    		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		
	    		try {
	    			list.get(-1);
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
	    		}
	    		
	    		catch (IndexOutOfBoundsException e) {
	    			System.out.println(name + " for " + ta_name + " passed");
	    		}
	    		
	    		catch (Exception e) {
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
	    		}
	    	}
	    	
	    	private static void test14_get_At_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
	    		list = constructListOfString(className);
	    		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		
	    		try {
	    			list.get(0);
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
	    		}
	    		
	    		catch (IndexOutOfBoundsException e) {
	    			System.out.println(name + " for " + ta_name + " passed");
	    		}
	    		
	    		catch (Exception e) {
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
	    		}
	    	}
	    	
	    	private static void test15_remove_At_Negative_Pos(String className, ListADT<String> list) {
	    		list = constructListOfString(className);
	    		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		
	    		try {
	    			list.remove(-1);
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
	    		}
	    		
	    		catch (IndexOutOfBoundsException e) {

				 private static void test12__List_Iterator(String className, ListADT<String> list) {
	    		 String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		 String ta_name = list.getClass().getName();
	    		 int numItems = 20;
	    		 Object obj1 = null;
	    		 Object obj2 = null;
	    		 int pos = 0;
	    		 list = constructListOfString(className);
	    		 Iterator<String> itr = list.iterator();
	    		 try {
	    			 for(int i = 0; i < numItems; i++) {
	    				 list.add(String.valueOf(i));
	    			 }
	    			 if(null == itr) {
	    				 failMsg(name+"_no_iterator", "valid iterator", ""+itr);
	    			 }
	    			 while(itr.hasNext()) {
	    				 obj1 = itr.next();
	    				 obj2 = list.get(pos);
	    				 pos++;
	    				// for(int j = 0; j < list.size(); j++) {
	    					// System.out.println(list.get(j));
	    				 //}

	    				 if(!obj1.equals(obj2)) {
	    					 failMsg(name+"_Iterator error : item in iterator is not correct","" + obj2,""+ obj1);
	    				 }

	    			 }
	    			 System.out.println(name + " for " + ta_name + " passed");
	    		 }

	    		 catch(Exception e) {
	    			 failMsg(className+" test12_List_Iterator ","NullPointerException","Throwing other exceptions");
	    	 }
	    	 }System.out.println(name + " for " + ta_name + " passed");
	    		}
	    		
	    		catch (Exception e) {
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
	    		}
	    	}
	    	
	    	private static void test16_remove_At_Pos_Zero_Of_Empty_List(String className, ListADT<String> list) {
	    		list = constructListOfString(className);
	    		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		
	    		try {
	    			list.remove(0);
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
	    		}
	    		
	    		catch (IndexOutOfBoundsException e) {
	    			System.out.println(name + " for " + ta_name + " passed");
	    		}
	    		
	    		catch (Exception e) {
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
	    		}
	    	}
	    	
	    	private static void test17_remove_At_Size(String className, ListADT<String> list) {
	    		list = constructListOfString(className);
	    		String name = new Object(){}.getClass().getEnclosingMethod().getName();
	    		String ta_name = list.getClass().getName();
	    		
	    		try {
	    			list.add("a");
	    			list.remove(list.size());
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException","Not throwing an exception");
	    		}
	    		
	    		catch (IndexOutOfBoundsException e) {
	    			System.out.println(name + " for " + ta_name + " passed");
	    		}
	    		
	    		catch (Exception e) {
	    			failMsg(name+" for "+ta_name,"IndexOutOfBoundsException", ""+e);
	    		}
	    	}
	/**
	     * confirms that Iterator method works and returns an
	     * iterator that works
	     */
	    public static void test18_List_Iterator(String className, ListADT<String> list) {
		String name = className;
		list = constructListOfString(className);
		java.util.List<String> alist = new java.util.ArrayList<String>();
		int numItems = 10; // number of items to add and check size
		// should be big enough to force expand

		try{
			for ( int i=0; i < numItems; i++ ) {
			    String s = "item_"+i;
			    list.add(s);
			    alist.add(s);
			}

			Iterator<String> itr1 = list.iterator();
			Iterator<String> itr2 = alist.iterator();

			if (null==itr1)
			    failMsg(name+"_no_iterator","valid iterator",""+itr1);

			if (null==itr2)
			    failMsg(name+"_no_iterator from java.util.ArrayList","valid iterator",""+itr2);

			while (itr1.hasNext() && itr2.hasNext()){
			    Object obj1 = itr1.next();

			    Object obj2 = itr2.next();

			    boolean expected = true;
			    boolean actual = obj1.equals(obj2);
			    if ( expected != actual ) {
				failMsg(name+"_iterator_error: item in iterator is not correct",""+expected,""+actual);
				return;
			    }

			}

			if ( itr1.hasNext() ) {
			    failMsg(name+"_iterator_error: item in iterator but should not be", " ", itr1.next() );
			    return;
			}

			if ( itr2.hasNext() ) {
			    failMsg(name+"_iterator_error: item not in iterator but should be",itr2.next()," ");
			    return;
			}

			System.out.println("Iterator method passed for " + className);
		}catch(Exception e)
		{
			System.out.println("Exception found in testing iterator as: " + e);
		}

	    }
	
}
