printjson(db.people.aggregate(
	{$unwind:'$credit'},
		{$group:
			{_id:"$credit.currency",totalSum:
			{$sum:{$toDouble: "$credit.balance"}}
			}
	}
	)
.toArray())