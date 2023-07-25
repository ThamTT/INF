//package commom;
//
//import org.apache.commons.codec.binary.StringUtils;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.collections4.map.HashedMap;
//
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Utils {
//    public static List<Integer> testCaseIdsList = null;
//    public static List<String> scenariosList = new ArrayList<String>();
//
//    public static String getRandomNumber() {
//        /*
//         * Generate random number
//         */
//        String randomNum;
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy_HHmmss");
//        randomNum = sdf.format(date);
//        return randomNum;
//
//    }
//
//    public static List<String> addPararmaterURL(List<String> paramList, String name, String value) {
//        if(name!= null && value.toUpperCase() != "NO PARAM") {
//            paramList.add("${name}=${value}");
//        }
//        return paramList;
//    }
//    public static List<String> addParameters(String testDataListener, int rowIndex, String... params) {
//        List<String> paramsString = new ArrayList<String>();
//        for (String param : params) {
//            addPararmaterURL(paramsString, param, "add String");
//        }
//        return paramsString;
//    }
//
//    public static getRandomOneRecordInDB(TestData testData, int rowIndex, String tableName, String[] paramsName) throws SQLException {
//        String queryGetRandomDataInDB = buildQueryGetRandomRecordDB(testData, rowIndex, tableName, paramsName)
//        String randomData;
//        if (StringUtils.isNotBlank(queryGetRandomDataInDB)) {
//            List<HashMap<String, String>> randomDataListFromDB = SQLServer.executeQuery(queryGetRandomDataInDB);
//            randomData = CollectionUtils.isNotEmpty(randomDataListFromDB) ? (String) randomDataListFromDB.get(0) : null;
//        }
//        return randomData;
//    }
//
//
//    public static String buildQueryGetRandomRecordDB(TestData testDataListener, int rowIndex, String tableName, String... paramsName) {
//        List<String> columnList = new ArrayList<>();
//        boolean isColumnName;
//        String valueInCell;
//        List<String> valueWithFormat;
//        for (String param : paramsName) {
//            valueInCell = testDataListener.getValue(param, rowIndex);
//            isColumnName = valueInCell.matches(PATTERN_SQUARE_BRACKETS_BOUND);
//            if (isColumnName) {
//                valueWithFormat = valueInCell.findAll(PATTERN_GET_VALUE_INSIDE_BRACKETS);
//                columnList.add(valueWithFormat.get(0));
//            }
//        }
//        String columnsJoined = columnList.join(', ');
//        String columnsInWhere = columnList.join(' IS NOT NULL AND ');
//        if (CollectionUtils.isNotEmpty(columnList)) {
//            StringBuilder result = new StringBuilder('SELECT TOP(1) ').append(columnsJoined)
//                    .append(" FROM ${tableName} WHERE ")
//                    .append(columnsInWhere).append(' IS NOT NULL ORDER BY NEWID(); ');
//            return result.toString();
//        };
//        return EMPTY_STRING;
//    }
//
//   a def static Map<String, String> getMapParamURLAndValue(TestData testDataListener, int rowIndex, String[] paramsAPI, def dataInDB, boolean checkDate = false) {
//        Map<String, String> result = new HashedMap<String, String>();
//        boolean isBlankDataFromDB = Objects.isNull(dataInDB);
//        String valueInCell;
//        List<String> valueByColumnDB;
//
//        for (String param : paramsAPI) {
//            valueInCell = testDataListener.getValue(param, rowIndex);
//            if (valueInCell.matches(PATTERN_GET_AUTHORIZATION_CODE)) {
//                result.put(param, getAuthorizationCode(valueInCell));
//            } else if (!isBlankDataFromDB && valueInCell.matches(PATTERN_SQUARE_BRACKETS_BOUND)) {
//                valueByColumnDB = valueInCell.findAll(PATTERN_GET_VALUE_INSIDE_BRACKETS);
//                if (valueByColumnDB.size() == 2 && checkDate == false) {
//                    result.put(param,
//                            convertDateTimeFormat(dataInDB.get(valueByColumnDB.get(0)), FULL_DATETIME_FORMAT, valueByColumnDB.get(1)))
//                } else if (valueByColumnDB.size() == 2 && checkDate == true) {
//                    result.put(param,
//                            convertDateTimeFormat(dataInDB.get(valueByColumnDB.get(0)), FULL_DATETIME_FORMAT_WITHOUT_S, valueByColumnDB.get(1)))
//                }else if(valueByColumnDB.size() == 3){
//                    result.put(param,
//                            convertDateTimeFormat(dataInDB.get(valueByColumnDB.get(0)), valueByColumnDB.get(1), valueByColumnDB.get(2)))
//                }else {
//                    result.put(param, dataInDB.get(valueByColumnDB.get(0)));
//                }
//            } else {
//                valueInCell.equalsIgnoreCase('NO PARAM') ? '' : result.put(param, valueInCell)
//            }
//        }
//        return result;
//    }
//    public static List<String> addParametersURL(Map<String, String> mapParamWithData) {
//        List<String> result = new ArrayList<>();
//        String value;
//        mapParamWithData.each { entry ->
//                value = URLEncoder.encode(entry.value.trim(), StandardCharsets.UTF_8.toString())
//            result.add("${entry.key}=${value}");
//        }
//        return result;
//    }
//
//    def static String rebuildSQLQueryGetTestData(String rawSQL, Map<String, String> mapParamWithData) {
//        String[] searchList = new String[mapParamWithData.size()];
//        String[] replacementList = new String[mapParamWithData.size()];
//        mapParamWithData.eachWithIndex { entry, i ->
//                searchList[i] = "[${entry.key}]";
//            replacementList[i] = entry.value;
//        }
//        return StringUtils.replaceEach(rawSQL, searchList, replacementList);
//    }
//    public static String plusDate(String dateValue, String formatDate) throws ParseException {
//        SimpleDateFormat oldDate = new SimpleDateFormat(formatDate);
//        Calendar c = Calendar.getInstance();
//        c.setTime(oldDate.parse(dateValue));
//        c.add(Calendar.DATE, 60);  // number of days to add
//        dateValue = oldDate.format(c.getTime());  // dt is now the new date
//        return dateValue;
//    }
//}
