# RepositoryBatchLoad
test
test project


//For reference


List < Document > results =
	collection.aggregate(
		Arrays.asList(
			Aggregates.match(
				
			)
		)
	).into(new ArrayList < > ());

//read excel file
	read header line
	create hashmap with keys and value as arraylist
	and arraylist of keys for iterating and get arraylist based on key
	read line by line
	iterate key list also
		add value to arraylist based on key : note check not empty for key value
		
Document condition  = new Document();
ArrayList<Document> conList = new ArrayList<Document>();

// iterate hashmap keyset and value
Document keyCon = new Document();
keyCon.append(key,new Document().append("$in",List));
conList.add(keyCon);
//iterate end

condition.append("$or",conList);
collection.find(condition);

//if output came write result ot excel and make download


----------------------------------
app.directive('selectFile', ['$parse','uploadFile','daismvw', function ($parse,uploadFile,daismvw,$scope,$mdDialog) {
            return {
               restrict: 'A',
               link: function($scope, element, attrs) {
                  var model = $parse(attrs.selectFile);
                  var modelSetter = model.assign;
                  element.bind('change', function(){
                	 
                     $scope.$apply(function(){
                    	 
                    	 modelSetter($scope, element[0].files[0]);
                    	 var file = element[0].files[0];
                    	 if(file != null){
                    		                   		
                    		 uploadFile.uploadFileToUrl(file, $scope, daismvw);
                    	 }else{
                    		 $scope.returnMessage = "Please Select File";
                 		     $scope.showAlert(event);
                 		     console.log("File Object is null:Please select File");
                    	 }
                        
                     });
                     
                  });
               }
            };
    }]); 

	app.service('uploadFile', ['$http','daismvw', function ($http,$scope,$rootScope,$mdDialog,daismvw) {
		
	 
		 this.uploadFileToUrl = function(file, $scope,daismvw){
			var uploadData = {"fileSystemId":"temporaryFileSystem1"};
			var frameworkcontext = daismvw.getNewFrameworkContext();
			daismvw.upload(frameworkcontext, uploadData, file).then(function (result) {
				var context = FrameworkContext;
				context = result.header;
					if(context.returnStatus.returnCode == "99999"){
						$scope.returnMessage = context.returnStatus.messageList[0].message;
						$scope.showAlert(event);
				  
					}else{
	        	 
						if(result["fileRepository"] != null){
							fileId  = (result["fileRepository"])["fileId"];
							$scope.fileID = (result["fileRepository"])["fileId"];
							$scope.loadData((result["fileRepository"])["fileId"]);
							angular.element("input[type='file']").val(null);
						}else{
							$scope.returnMessage = "Error while Import data [File upload failed]";
							$scope.showAlert(event);
							angular.element("input[type='file']").val(null);
						}
 					
					}
	    });
		 }; 
	}]);




//Mongo Export java code

mongoexport -d test -c user -q '{ coins: { $elemMatch: { "id":"30","amount":0}}}' --type=csv --out exportdir/myRecords.json

public static void main(String[] args) {

        String db = "pack";
        String col = "col";
        String Host = "localhost";
        String Port = "27017";
        String fileName = "D:/files/sample.csv";

    String command = "C:\\Program Files\\MongoDB\\Server\\3.4\\bin\\mongoexport.exe --host " + Host + " --port " + Port + " --db " + db + " --collection " + col + " --type=csv --fields _id,email,createdAt, --out " + fileName + "";
    String command = "C:\\Program Files\\MongoDB\\Server\\3.4\\bin\\mongoexport.exe --host " + Host + " --port " + Port + " --db " + db + " --collection " + col + " --query"+ query +" --type=csv  --out " + fileName + "";

    try {
        System.out.println(command);

        StringTokenizer st = new StringTokenizer(command);
        String[] cmdarray = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++)
            cmdarray[i] = st.nextToken();

        ProcessBuilder processBuilder = new ProcessBuilder(cmdarray);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String s = "";
        while ((s = processOutput.readLine()) != null) {
            System.out.println(s);
        }


    } catch (Exception e) {
        e.printStackTrace();
    }
