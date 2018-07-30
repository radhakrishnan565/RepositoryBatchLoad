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
