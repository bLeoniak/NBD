db.people.mapReduce(
  function () {
    this.credit.forEach(item => {
		if (this.nationality != "Poland" || this.sex != "Female")
			return;
		var key = item.currency;
		var values =  parseFloat(item.balance);
      emit(key,values);
    });
  },
  function (key, values) {
	  var sum = Array.sum(values)
    return { 
		sumBalance: sum,
		averageBalance: sum / values.length
	};
  },
  {out: 'balances'}
);

printjson(db.balances.find().toArray());