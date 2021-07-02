printjson(db.people.aggregate([
  {
    $group: {
		_id: '$sex',
		heightSum: { $sum: '$height' },
		weightSum: { $sum: '$weight' },
		count: { $sum: 1 }
    }
  },
  {
    $project: {
      avgWeight: {$divide: ['$weightSum', '$count']},
      avgHeight: {$divide: ['$heightSum', '$count']}
    }
  }
]).toArray());
