
db.people.mapReduce(
  function () {
	  var key = this.sex;
	  var values = {weight: this.weight, height: this.height };
      emit(key, values);
  },

  function (key, values) {
	var result  = {heightSum:0, weightSum:0, count: values.length};

    values.forEach(item => {
      result.weightSum += item.weight,
      result.heightSum += item.height
    });

	return result;
  },
  { out: 'avgWeightHeight',
			finalize: function(key, result){
            return {
                avgHeight: result.heightSum / result.count,
                avgWeight: result.weightSum / result.count
            }
        }  
	}
)
printjson(db.avgWeightHeight.find().toArray())
