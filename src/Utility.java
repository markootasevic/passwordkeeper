import java.io.File;

//ne razumem cemu ova klasa, samo sam je prekopirao sa predavanja, i radi ^^
public class Utility {

	public static String getProjectDir() {
		File dir = new File(".");
		StringBuffer s = new StringBuffer(dir.getAbsolutePath());
		s.deleteCharAt(s.length() - 1);
		return s.toString();
	}

	public static String getDir(String child) {
		// File dir = new File(getProjectDir() + "resources");
		File dir = new File(getProjectDir() + child);
		dir.mkdir();
		// if (dir.mkdir()) {
		// System.out.println("Created.");
		// } else {
		// System.out.println("Not created.");
		// }
		return dir.getAbsolutePath();
	}

}
