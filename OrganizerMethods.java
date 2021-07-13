import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrganizerMethods {
	
	private static String projectName = "Projects.txt";
	private static String judgeName = "Judges.txt";
	private static List<String> removedNames = new ArrayList();

	public static void main(String[] args) {
		
		List projects = new ArrayList<String>();
		List groupedProjects = new ArrayList<ArrayList>();
		
		List judges = new ArrayList<String>();
		List groupedJudges = new ArrayList<ArrayList>();

		 try {
	            projects = readFile(projectName);
	            projects = removeSpaces(projects);
	            groupedProjects = groupProjects(projects);
	            
	            judges = readFile(judgeName);
	            groupedJudges = groupJudges(judges);
	            
	            finalOutput(groupedProjects, groupedJudges);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }		 

	}
	
	 protected static List readFile(String fileName) throws IOException {
	        List<String> result;
	        
	        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
	            result = lines.collect(Collectors.toList());
	        }
	        return result;
	 }
	 
	 public static boolean isNumeric(final String str) {
	        if (str == null || str.length() == 0) {
	            return false;
	        }

	        for (char c : str.toCharArray()) {
	            if (!Character.isDigit(c)) {
	                return false;
	            }
	        }
	        return true;
	 }
	 
	 protected static List removeSpaces(List input) {
		 String temp = null;
		 List<String> splitList = new ArrayList<String>();
		 String[] splitArray = new String[2 * input.size()];
		 
		 for(int i = 0; i < input.size(); i++) {
			 temp = (String) input.get(i);
			 splitArray = temp.split("\\s+");
			 for(int j = 0; j < splitArray.length; j++) {
				 splitList.add(splitArray[j]);
			 }
		 }
		 
		 for(int k = 0; k < splitList.size(); k++) {
			 splitList.remove("");
		 }
		 
		 for(int p = 0; p < splitList.size() - 2; p++) {
			 String check = splitList.get(p);
			 if(isNumeric(check) == true) {
				 int count = p + 2;
				 check = splitList.get(count);
				 if(isNumeric(check) == false) {
					 String combine = splitList.get((p + 1)) + " " + splitList.get(count);
					 splitList.set((p + 1), combine);
					 splitList.remove(count);
				 }
			 }
			 
		 }
		 return splitList;
	 }
	 
	 public static List<ArrayList> groupProjects(List<String> input) {
		 List<ArrayList> grouped = new ArrayList<ArrayList>();
		 
		 List<String> Behave = new ArrayList<String>();
		 List<String> Chem = new ArrayList<String>();
		 List<String> Earth = new ArrayList<String>();
		 List<String> Enviro = new ArrayList<String>();
		 List<String> Life = new ArrayList<String>();
		 List<String> Math = new ArrayList<String>();
		 
		 String num = "";
		 String id = "";
		 String temp = "";
		 for(int i = 0; i < input.size() - 1; i++) {
			 num = input.get(i);
			 id = input.get(i + 1);
			 if(isNumeric(num) == true) {
				 temp = id.substring(0, 2).toLowerCase();
				 switch(temp) {
				 case "be":
					 if(Behave.size() < 6) {
						 Behave.add(num);
						 Collections.sort(Behave, Collections.reverseOrder());
					 }
					 else {
						 Behave.add(0, "be");
						 grouped.add((ArrayList) Behave);
						 Behave = new ArrayList<String>();
						 Behave.add(num);
					 }
					 break;
					 
				 case "ch":
					 if(Chem.size() < 6) {
						 Chem.add(num);
						 Collections.sort(Chem, Collections.reverseOrder());
					 }
					 else {
						 Chem.add(0, "ch");
						 grouped.add((ArrayList) Chem);
						 Chem = new ArrayList<String>();
						 Chem.add(num);
					 }
					 break;
					 
				 case "ea":
					 if(Earth.size() < 6) {
						 Earth.add(num);
						 Collections.sort(Earth, Collections.reverseOrder());
					 }
					 else {
						 Earth.add(0, "ea");
						 grouped.add((ArrayList) Earth);
						 Earth = new ArrayList<String>();
						 Earth.add(num);
					 }
					 break;
				
				 case "en":
					 if(Enviro.size() < 6) {
						 Enviro.add(num);
						 Collections.sort(Enviro, Collections.reverseOrder());
					 }
					 else {
						 Enviro.add(0, "en");
						 grouped.add((ArrayList) Enviro);
						 Enviro = new ArrayList<String>();
						 Enviro.add(num);
					 }
					 break;
					 
				 case "li":
					 if(Life.size() < 6) {
						 Life.add(num);
						 Collections.sort(Life, Collections.reverseOrder());
					 }
					 else {
						 Life.add(0, "li");
						 grouped.add((ArrayList) Life);
						 Life = new ArrayList<String>();
						 Life.add(num);
					 }
					 break;
					 
				 case "ma":
					 if(Math.size() < 6) {
						 Math.add(num);
						 Collections.sort(Math, Collections.reverseOrder());
					 }
					 else {
						 Math.add(0, "ma");
						 grouped.add((ArrayList) Math);
						 Math = new ArrayList<String>();
						 Math.add(num);
					 }
					 break;
					 
				default:
					break;
					 
				 }
			 }
		 }
		 
		 Behave.add(0, "be");
		 Chem.add(0, "ch");
		 Earth.add(0, "ea");
		 Enviro.add(0, "en");
		 Life.add(0, "li");
		 Math.add(0, "ma");
		 
		 grouped.add((ArrayList) Behave);
		 grouped.add((ArrayList) Chem);
		 grouped.add((ArrayList) Earth);
		 grouped.add((ArrayList) Enviro);
		 grouped.add((ArrayList) Life);
		 grouped.add((ArrayList) Math);
		 
		 return grouped;
	 }
	 
	 public static List<ArrayList> groupJudges(List<String> input) {
		 List<ArrayList> grouped = new ArrayList<ArrayList>();
		 
		 List<String> Behave = new ArrayList<String>();
		 List<String> Chem = new ArrayList<String>();
		 List<String> Earth = new ArrayList<String>();
		 List<String> Enviro = new ArrayList<String>();
		 List<String> Life = new ArrayList<String>();
		 List<String> Math = new ArrayList<String>();
		 
		 String judgesName = "";
		 String topic = "";
		 
		 for(int i = 0; i < input.size(); i++) {
			 try {
				 judgesName = input.get(i).substring(0, input.get(i).indexOf(":"));
				 topic = input.get(i).substring((input.get(i).indexOf(":") + 1)).toLowerCase();
			 }
			 catch (IndexOutOfBoundsException e){
				 continue;
			 }
			 if(topic.contains("behavi")) {
					Behave.add(judgesName);
				 }
			 if(topic.contains("chem")) {
				 	Chem.add(judgesName);
			 }
			 if(topic.contains("earth")) {
					Earth.add(judgesName);
			 }
			 if(topic.contains("enviro")) {
					Enviro.add(judgesName);
			 }
			 if(topic.contains("life")) {
					Life.add(judgesName);
			 }
			 if(topic.contains("math")) {
					Math.add(judgesName);
			 }	 
		 }
		 
		 grouped.add((ArrayList) Behave);
		 grouped.add((ArrayList) Chem);
		 grouped.add((ArrayList) Earth);
		 grouped.add((ArrayList) Enviro);
		 grouped.add((ArrayList) Life);
		 grouped.add((ArrayList) Math);
		 
		 return grouped;
	 }
	 
	 public static void finalOutput(List<ArrayList> Projects, List<ArrayList> Judges) throws IOException {
		 List<String> result = new ArrayList<String>();
		 
		 List<String> judgesUsed = new ArrayList<String>();
		 List<String> holder = new ArrayList<String>();
		 List<String> holderJudge = new ArrayList<String>();
		 
		 String temp = "";
		 
		 int behave = 0, chem = 0, earth = 0, enviro = 0, life = 0, math = 0;
		 
		 for(int i = 0; i < Projects.size(); i++) {
			 holder = Projects.get(i);
			 temp = holder.get(0);
			 switch(temp) {
			 	case "be":
			 		holderJudge = removeNames(Judges.get(0));
			 		behave++;
			 		String proBe = "Behavioral/Social Science_" + behave + " ";
			 		for(int j = 0; j < 3 && j < holderJudge.size() ; j++) {
			 			proBe += holderJudge.get(j) + ", ";
			 			removedNames.add(holderJudge.get(j));
			 		}
			 		result.add(proBe);
			 		String proBeNum = "Projects: ";
			 		for(int k = 1; k < holder.size(); k++) {
			 			proBeNum += holder.get(k) + ", ";
			 		}
			 		result.add(proBeNum);
			 		break;
				 
			 	case "ch":
			 		holderJudge = removeNames(Judges.get(1));
			 		System.out.println(holderJudge);
			 		chem++;
			 		String proCh = "Chemistry_" + chem + " ";
			 		for(int j = 0; j < 3 && j < holderJudge.size() ; j++) {
			 			proCh += holderJudge.get(j) + ", ";
			 			removedNames.add(holderJudge.get(j));
			 		}
			 		result.add(proCh);
			 		String proChNum = "Projects: ";
			 		for(int k = 1; k < holder.size(); k++) {
			 			proChNum += holder.get(k) + ", ";
			 		}
			 		result.add(proChNum);
			 		break;
				 
			 	case "ea":
			 		holderJudge = removeNames(Judges.get(2));
			 		System.out.println(holderJudge);
			 		earth++;
			 		String proEa = "Earth/Space Science_" + earth + " ";
			 		for(int j = 0; j < 3 && j < holderJudge.size() ; j++) {
			 			proEa += holderJudge.get(j) + ", ";
			 			removedNames.add(holderJudge.get(j));
			 		}
			 		result.add(proEa);
			 		String proEaNum = "Projects: ";
			 		for(int k = 1; k < holder.size(); k++) {
			 			proEaNum += holder.get(k) + ", ";
			 		}
			 		result.add(proEaNum);
			 		break;
			
			 	case "en": 
			 		holderJudge = removeNames(Judges.get(3));
			 		System.out.println(holderJudge);
			 		enviro++;
			 		String proEn = "Environmental Science_" + enviro + " ";
			 		for(int j = 0; j < 3 && j < holderJudge.size() ; j++) {
			 			proEn += holderJudge.get(j) + ", ";
			 			removedNames.add(holderJudge.get(j));
			 		}
			 		result.add(proEn);
			 		String proEnNum = "Projects: ";
			 		for(int k = 1; k < holder.size(); k++) {
			 			proEnNum += holder.get(k) + ", ";
			 		}
			 		result.add(proEnNum);
			 		break;
				 
			 	case "li":
			 		holderJudge = removeNames(Judges.get(4));
			 		System.out.println(holderJudge);
			 		life++;
			 		String proLi = "Life Science_" + life + " ";
			 		for(int j = 0; j < 3 && j < holderJudge.size() ; j++) {
			 			proLi += holderJudge.get(j) + ", ";
			 			removedNames.add(holderJudge.get(j));
			 		}
			 		result.add(proLi);
			 		String proLiNum = "Projects: ";
			 		for(int k = 1; k < holder.size(); k++) {
			 			proLiNum += holder.get(k) + ", ";
			 		}
			 		result.add(proLiNum);
			 		break;
				 
			 	case "ma":
			 		holderJudge = removeNames(Judges.get(5));
			 		System.out.println(holderJudge);
			 		math++;
			 		String proMa = "Mathematics/Physics_" + math + " ";
			 		for(int j = 0; j < 3 && j < holderJudge.size() ; j++) {
			 			proMa += holderJudge.get(j) + ", ";
			 			removedNames.add(holderJudge.get(j));
			 		}
			 		result.add(proMa);
			 		String proMaNum = "Projects: ";
			 		for(int k = 1; k < holder.size(); k++) {
			 			proMaNum += holder.get(k) + ", ";
			 		}
			 		result.add(proMaNum);
			 		break;
				 
			 	default:
			 		break;
				 
			 }
			 FileWriter writer = new FileWriter("output.txt");
			 for(String str: result) {
				 writer.write(str + System.lineSeparator());
			 }
			 writer.close();
		 }
		 
	 }
	 
	 public static List<String> removeNames(List<String> input) {
		 String temp = "";
		 List<String> result = new ArrayList<String>();
		 
		 for(int i = 0; i < removedNames.size(); i++) {
			 temp = removedNames.get(i);
			 for(int j = 0; j < input.size(); j++) {
				 input.remove(temp);
			 }
		 }
		 result = input;
		 return result;
	 }
	 
}
