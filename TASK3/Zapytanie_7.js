// Wymagana była konwersja typu danych ze stringa na float 
//db.people.find().forEach( function(obj) {     obj.height= parseFloat(obj.height);     db.people.save(obj); });
printjson(db.people.remove({height:{$gt: 190}}));