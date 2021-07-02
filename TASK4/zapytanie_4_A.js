printjson(db.people.aggregate([
	{$project: {
		nationality: "$nationality",
		bmi: { $divide: ["$weight",{$pow: [{$divide: ["$height", 100]}, 2]}
		]}
	}},
	{$group: {
		_id: "$nationality",
		minBmi: {$min: "$bmi"},
        maxBmi: {$max: "$bmi"},
        avgBmi: {$avg: "$bmi"}
	}}
]).toArray())