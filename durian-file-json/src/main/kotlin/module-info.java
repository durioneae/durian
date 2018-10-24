module durian.file.json {
	requires kotlin.stdlib;
	requires durian.core;
	requires json.path;
	requires com.fasterxml.jackson.databind;
	requires jackson.json;

	exports com.psmelser.durian.file.json;
}