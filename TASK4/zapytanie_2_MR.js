db.people.mapReduce(
    function(){
        this.credit.forEach(item => {
			emit(item.currency,  parseFloat(item.balance));
		});
    },
    function(key,values)
        { return Array.sum(values)},
    {
            query:{},
            out:"totalSum"
    }
)
printjson(db.totalSum.find().toArray())