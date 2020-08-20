/*
This is a groovy script to convert csv file to json object and removing comma in between the double quoted.
*/
import groovy.json.JsonOutput
//import java.util.regex.Matcher

def dataList = []
def lineCounter = 0
def headers = []

new File("C://Users//nlshr//Downloads//Balance Sheet_669_1_worksheet.csv").eachLine(){ line ->
if (lineCounter == 0) {

	headers = line.toString().split(",").collect{it.trim()}

	}
else
{
	def dataItem = [:]
	def tokens = line.toString().split(/,(?=([^"]*"[^"]*")*[^"]*$)/)

     def row = tokens.collect{it.trim()}


     headers.eachWithIndex() { header, index ->
     		 def row_after_comma_removed = row[index].replaceAll(",","").replaceAll("\"","")

                dataItem.put("${header}", "${row_after_comma_removed}")
            }
     dataList.add(dataItem)
}
lineCounter = lineCounter + 1

}
String output = JsonOutput.toJson(dataList)
vars.put("csv_json",output)
