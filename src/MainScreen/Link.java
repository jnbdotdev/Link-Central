package MainScreen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class Link {
	
	private String linkName;
	private String linkCorp;

//private static String directory;

	Path currentRelativePath = Paths.get("");
	public String url = currentRelativePath.toAbsolutePath().toString();

	public Link(String linkName, String linkCorp) {
		linkName = this.linkName;
		linkCorp = this.linkCorp;
	}

	public Link() {
		
	}

//=====> Getters and Setters

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkCorp() {
		return linkCorp;
	}

	public void setLinkCorp(String linkCorp) {
		this.linkCorp = linkCorp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


//=====> Methods


	public void saveLink(String linkName, String linkCorp){
			
		ArrayList<String> completeList = this.listItens();
		String content = "";
		
		System.out.println(completeList);
		
		try {
			File archive = new File(this.getUrl()+"\\LinkCentralMainArchive.txt");
			FileOutputStream fOut = new FileOutputStream(archive);
			completeList.add(linkName+" : "+linkCorp);
			for(int i = 0; i < completeList.size(); i++) {
				content = content + completeList.get(i) + "\n";
			}
			
			fOut.write(content.getBytes());
			
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public boolean detectFile() {
		
		boolean statsFile = false;
		
		String allFiles = "";
		
		File file = new File(url);
		File[] archives = file.listFiles();
		
		for(File fileTxt : archives) {
			if(fileTxt.getName().endsWith(".txt")) {
				allFiles = fileTxt.getName().replaceAll(".txt", "");
				if(allFiles.equals("LinkCentralMainArchive")) {
					statsFile = true;
				}
			}
		}
		
		return statsFile;
	}
	
	public void firstFile() {
		
		File archive = new File(this.getUrl()+"\\LinkCentralMainArchive.txt");
		try {
			archive.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFile() throws IOException {
		
		String content = "";
		BufferedReader bufR = null;
		
		try {
			bufR = new BufferedReader(new FileReader(this.getUrl()+"\\LinkCentralMainArchive.txt"));
			System.out.println(this.getUrl()+"\\LinkCentralMainArchive.txt");
			StringBuilder sb = new StringBuilder();
			String line = bufR.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = bufR.readLine();
			}
			content = sb.toString();
			
		} finally {
			bufR.close();
		}
		
		return content;
	}
	
	public ArrayList<String> listItens() {
		
		ArrayList<String> list = new ArrayList<>();
		
		BufferedReader bR = null;
		
		try {
			bR = new BufferedReader(new FileReader(this.getUrl()+"\\LinkCentralMainArchive.txt"));
			String line = bR.readLine();
			
			while(line != null) {
				
				list.add(line);

				line = bR.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bR.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public File fileLinks(){
		
		String baseDirectory = System.getProperty("user.home")+"/Documents";
		File dir = new File(baseDirectory);
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(dir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int openFile = chooser.showOpenDialog(null);

		if(openFile == JFileChooser.APPROVE_OPTION) {
			dir = chooser.getSelectedFile();
		}
		return dir;
	}
	
	public ArrayList<String> readImported(File imported){
		
		ArrayList<String> list = new ArrayList<>();
		
		BufferedReader bR = null;
		
		try {
			bR = new BufferedReader(new FileReader(imported));
			String line = bR.readLine();
			
			while(line != null) {
				
				list.add(line);

				line = bR.readLine();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bR != null) {
					bR.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(list);
		for(int i = 0; i < list.size(); i++) {
			String link = list.get(i);
			if(!link.contains(":")) {
				list.set(i, "Imported "+i+" : "+link);
			}
		}
		System.out.println(list);
		return list;
	}

	public ArrayList<String> importLinks(ArrayList<String> importedList) {
		
		ArrayList<String> iList = importedList;
		;
		System.out.println(iList);
		
		return iList;
		
	}
	
	public void updateList(String changeContent,  int index) throws IOException {
		
		Path path = Paths.get(getUrl()+"\\LinkCentralMainArchive.txt");
		List<String> lines = Files.readAllLines(path);
		
		lines.remove(index);
		lines.add(index, changeContent);
		
		Files.write(path, lines);
		
	}

	public void deleteItem(int index) throws IOException {
		Path path = Paths.get(getUrl()+"\\LinkCentralMainArchive.txt");
		List<String> lines = Files.readAllLines(path);
		
		System.out.println(lines.get(index));
		
		lines.remove(index);
		
		
		Files.write(path, lines);
	}


}
