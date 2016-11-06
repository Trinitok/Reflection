/**
 * This is to help show just how vulnerable the world of JAVA really is.  You can change just about everything.  You can make the values for anything equal anything.  This is because everything in Java is stored as an integer (typically) or some other type of field.  
 * This is meant to help raise awareness of the vulnerabilities and the easiness of Java.  I am not responsible for any malicious use of this code.
 * 
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;


import defender.Defender;

public class Attacker {

	public static void main(String[] args) {
		try{
			//  in order to access we must create an instance
			Class cls = Class.forName("defender.Defender");
			Constructor[] constructors = cls.getDeclaredConstructors();
			System.out.println("Constructor Name--->>>"+constructors[0].getName());  // this will only list Defender because it is top level
			constructors[0].setAccessible(true);
			System.out.println("Object creation--->>>"+constructors[0].newInstance(/*input any parameters for the constructor here */ ));
			
			Defender taken = (Defender) constructors[0].newInstance();
			
			//  now we will get the fields for the private class
			Field[] defenderFields = cls.getDeclaredFields();
			
			for(int i = 0; i < defenderFields.length; i ++){
				defenderFields[i].setAccessible(true);
				System.out.println("Field Name-->"+defenderFields[i].getName()+"\t" 
						+"Field Type-->"+ defenderFields[i].getType().getName()+"\t"
						+"Field Value-->"+ defenderFields[i].get(taken));
				
			}
//			new king();
			
			System.out.println(Defender.class.getDeclaredClasses()[0]);
			Defender.class.getDeclaredClasses()[0].getDeclaredConstructors()[0].setAccessible(true);
			Defender.class.getDeclaredField("numSoldiers").setAccessible(true);
			setFinalStatic(Defender.class.getField("numSoldiers"), 0);
			System.out.println("remaining soldiers: " + defenderFields[0].get(taken));  // we have changed the number of soldiers to 0
			
			
			System.out.println();System.out.println();  // add spaces.  It was getting messy
			
			//  get the private inner class and all inner variables
			Class[] innerClasses = cls.getDeclaredClasses();
			Constructor CapturedGilgamesh = innerClasses[0].getDeclaredConstructors()[0];
			CapturedGilgamesh.setAccessible(true);
			System.out.println("Inner classes--->>>"+innerClasses[0].getName());
//			innerClasses[0]
			Method[] innerMethod = innerClasses[0].getDeclaredMethods();
			System.out.println("Inner classes--->>>"+innerMethod[0].getName());
			Field[] innerFields = innerClasses[0].getDeclaredFields();
			Object King = CapturedGilgamesh.newInstance();
			for(int i = 0; i < innerFields.length; i ++){
				innerFields[i].setAccessible(true);
				System.out.println("Field Name-->"+innerFields[i].getName()+"\t" 
						+"Field Type-->"+ innerFields[i].getType().getName()+"\t"
						+"Field Value-->"+ innerFields[i].get(King));
				
			}
			//  set new values for the reflected variables
			King.getClass().getDeclaredField("name").setAccessible(true);
//			System.out.println("king class? " + Class.forName("defender.Defender$king").);
			
			innerFields[0].setAccessible(true);
			innerFields[0].set(King, "Dethroned, Bitch");
//			innerFields[1].set(King, false);
			System.out.println("Is the king dethroned?  " + innerFields[0].get(King));  // The String for the King's name has been changed.  Huzzah!
			
			//  get the private methods
			Method[] pmethods = cls.getDeclaredMethods();
//			King usurped = new King();
			
			//  what does this print?
			
			setFinalStatic(Defender.class.getDeclaredClasses()[0].getDeclaredField("alive"), Boolean.FALSE);
			System.out.println("is the king dead?  " + innerFields[0].get(King));
//			seeAllField();
//			seeAllModifiers();
//			seeAllTypes();
		}catch(Exception e){
			System.err.println("Sir, we were unsuccessful in our attack because of " + e.getMessage());
			e.printStackTrace();
		}
	}
	
			//  this is used ONLY to make the final modifer...useless
			//  A good example of this is with Boolean.False and Boolean.True (or the reserved words false and true respectively).  If it is final, it normally can't be changed.  But after this method runs, it will be changed to the new value that you pass into it
	/**
	 * @param field - The field that you wish to change.  Must have the final reserved word modifier on it
	 * @param newValue - The new value you with the field associated with	   
	 */
	static void setFinalStatic(Field field, Object newValue) throws Exception {
		      field.setAccessible(true);
		      System.out.println(field);
		      Field modifiersField = Field.class.getDeclaredField("modifiers");
		      modifiersField.setAccessible(true);
		      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL); // this is what will set Final to...not Final.   Really it doesn't matter.  The point is that the JVM will not have the original settings for final and you can change final variables as you could any other value

		      field.set(null, newValue);  // this is for static fields
		   }
		   
		   static void seeAllField(){
			//  We will list all the different types of modifiers for us to see
			   
			      Field[] allFieldMods = Field.class.getDeclaredFields();
			      for(int i = 0; i < allFieldMods.length; i ++){
			    	  allFieldMods[i].setAccessible(true);
			    	  System.out.println("Field name " + allFieldMods[i].getName());
			    	  
			      }
			      
			      
		   }
		   
		   static void seeAllTypes(){
			   Field[] allTypes = Type.class.getDeclaredFields();
			   for(int i = 0; i < allTypes.length; i ++){
				   allTypes[i].setAccessible(true);
			    	  System.out.println("Type name " + allTypes[i].getName());
			    	  
			      }
		   }
		   
		   static void seeAllModifiers(){
			   Field[] allTypes = Modifier.class.getDeclaredFields();
			   for(int i = 0; i < allTypes.length; i ++){
				   allTypes[i].setAccessible(true);
			    	  System.out.println("Modifier name " + allTypes[i].getName());
			    	  
			      }
		   }

}
