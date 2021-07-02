printjson(db.people.aggregate(
	{$unwind:'$credit'},
	{$match: {nationality: "Poland", sex: "Female"}},
	{$group:
		{_id: "$credit.currency",
		balanceSum:{$sum:{$toDouble: "$credit.balance"}}, 
		avgBalance: {$avg:{$toDouble: "$credit.balance"}}}
	}
).toArray())