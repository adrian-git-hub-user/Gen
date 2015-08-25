import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouping {

	public static void main(String args[]) {

		java.util.List<String> stringList = new java.util.ArrayList<String>();
		stringList.add("key1.value1");
		stringList.add("key1.value2");
		stringList.add("key2.value3");

		Map<String, List<String>> map = stringList.stream().map(s -> s.split("\\.")).map(s -> {
			if (s.length > 1) {
				return s;
			} else {
				return new String[] { s[0], "" };
			}
		})
		.collect(Collectors.groupingBy(s -> s[0], Collectors.mapping(s -> s[1], Collectors.toList())));
	}

}
