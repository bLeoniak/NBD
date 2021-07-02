db.people.mapReduce(
	function() {
		var key = this.nationality;
		var values = this.weight / ((this.height / 100) ** 2);
		emit(key, values);
	},
	function(key, values) {
		let sumBmi = 0;
		let maxBmi = 0;
		let minBmi = 9999;
	
		values.forEach(item => {
			sumBmi += item;
			
			if (item > maxBmi) 
				maxBmi = item;
			
			if (item < minBmi) 
				minBmi = item;
		})
	
		return{
			avgBmi: sumBmi / values.length,
			maxBmi: maxBmi,
			minBmi: minBmi
		}
	},
	{
		out: "bmi",
	}
)

printjson(db.bmi.find().toArray())