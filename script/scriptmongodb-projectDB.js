/*
 * Project Script
 * 
 */
print("STARTING SCRIPT");
//Host
conn = new Mongo("localhost");
//Database Name
db = conn.getDB("ProjectDB");


/*Clean Database if already exists*/
db.dropDatabase();

/*Collection creation*/

db.createCollection("student");
db.createCollection("parent");

print("***********creating students*********");


student1= {
"_id" : ObjectId("5d7c0696e7ac942af83bef07"),
"fullName" : "Alvaro Valdivia",
"gender" : "Male",
"birthdate" : ISODate("1992-09-16T05:00:00Z"),
"typeDocument" : "DNI",
"numberDocument" : "47856688"
}

student2= {
 "_id" : ObjectId("5d81247b19e5a726d811c93e"),
        "fullName" : "Juan Perez",
        "gender" : "male",
        "birthdate" : ISODate("1991-02-02T05:00:00Z"),
        "typeDocument" : "DNI",
        "numberDocument" : "84848444"
}


student3= {
		"_id": ObjectId("5d832c7aad119347a070e241"),
        "fullName": "Ashley Rotondo",
        "gender": "female",
        "birthdate": "1993-09-04",
        "typeDocument": "DNI",
        "numberDocument": "78546235"
}

student4 = {
		  "_id" : ObjectId("5d828ae33228833e74420270"),
	        "fullName" : "Micaela Rubin",
	        "gender" : "female",
	        "birthdate" : ISODate("2015-09-09T05:00:00Z"),
	        "typeDocument" : "DNI",
	        "numberDocument" : "78546235"
}
print("***********creating parents*********");

/* parent */

parent1= {
     
        "fullName" : "Elena Ruiz",
        "gender" : "female",
        "birthdate" : ISODate("1961-03-22T00:00:00Z"),
        "typeDocument" : "DNI",
        "numberDocument" : "15648572",
  
};

parent2= {
        "fullName" : "Andres Perez",
        "gender" : "Male",
        "birthdate" : ISODate("1971-03-22T00:00:00Z"),
        "typeDocument" : "DNI",
        "numberDocument" : "96854325",
};

parent3= {

        "fullName" : "Joseline Vasquez",
        "gender" : "female",
        "birthdate" : ISODate("1991-03-22T00:00:00Z"),
        "typeDocument" : "DNI",
        "numberDocument" : "14455153",
};

print("***********saving students*********");
db.student.save(student1);
db.student.save(student2);
db.student.save(student3);
db.student.save(student4);

print("***********saving parents*********");
db.parent.save(parent1);
db.parent.save(parent2);
db.parent.save(parent3);


print("SCRIPT FINISHED");

