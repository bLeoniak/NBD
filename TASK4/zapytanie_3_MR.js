db.people.mapReduce(
    function(){emit(this.job, null);},
    function(key,values){return;},
	{out:"jobs"}
)
printjson(db.jobs.find().toArray())