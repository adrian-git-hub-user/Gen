package clean.javacode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class ToJsonJ {

	public static void main(String args[]) {
		new ToJsonJ().createJson();
	}

	public void createJson() {
	
		List<TreeElement> tl = new ArrayList<TreeElement>();

		java.util.Map<String, java.util.List<String>> map = clean.scalacode.ToJsonMain.getAsJava();

		for (Map.Entry<String, java.util.List<String>> entry : map.entrySet()) {
			TreeElement t = new TreeElement();
			t.header = entry.getKey();
			java.util.List<String> value = entry.getValue();

			List<TreeElementChild> tecl = new ArrayList<TreeElementChild>();
			for (String v : value) {
				TreeElementChild tec = new TreeElementChild();
				tec.count = v.split(",")[0];
				tec.date = v.split(",")[1];
				tecl.add(tec);
			}

			t.children = tecl;
			tl.add(t);
		}
		// String json
		System.out.println(getObjAsJson(tl));

	}

	private class TreeElement {
		public String header;
		public List<TreeElementChild> children;
	}

	private class TreeElementChild {
		public String count;
		public String date;
	}

	private static String getObjAsJson(Object ds) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(ds);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
