printjson(db.people.insert({
  sex: 'Male',
  first_name: 'Bartlomiej',
  last_name: 'Leoniak',
  job: '.NET developer',
  email: 's23904@pjwstk.edu.pl',
  location: {
    city: 'Warsaw',
    address: { streetname: 'Przykladowa', streetnumber: '1' }
  },
  description: "Lubie placki",
  height: 186.01,
  weight: 90.50,
  birth_date: '1998-01-03T00:00:00Z',
  nationality: 'Poland',
  credit: [
    {
      type: 'maestro',
      number: '201508948540842',
      currency: 'THB',
      balance: '5512.85'
    }
  ]
}));