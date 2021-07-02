// Wymagana była konwersja typu danych ze stringa na float 
//db.people.find().forEach( function(obj) {     obj.weight= parseFloat(obj.weight);     db.people.save(obj); });
printjson(db.people.find({weight:{$gte:68,$lt:71.5}}).toArray()) 